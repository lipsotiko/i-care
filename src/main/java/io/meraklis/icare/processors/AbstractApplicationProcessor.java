package io.meraklis.icare.processors;

import io.meraklis.icare.applications.PatientApplicationType;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static io.meraklis.icare.helpers.Helpers.tmpFile;
import static io.meraklis.icare.processors.DocumentHelper.*;

@Slf4j
abstract class AbstractApplicationProcessor implements ApplicationProcessor {

    @Autowired
    protected SignatureApplicator signatureApplicator;
    protected String CHECK = "X";
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    protected Map<Integer, String> radioChoices = new HashMap<>() {
        {
            put(1, "Choice1");
            put(2, "Choice2");
            put(3, "Choice3");
            put(4, "Choice4");
        }
    };

    abstract PatientApplicationType applicationType();

    abstract Map<String, String> pdfFieldsMap();

    public List<String> dateFields() {
        return Collections.emptyList();
    }

    public List<String> multiCheckBoxFields() {
        return Collections.emptyList();
    }

    public List<String> radioFields() {
        return Collections.emptyList();
    }

    public List<String> singleCheckBoxFields() {
        return Collections.emptyList();
    }

    public List<String> derivedFields() {
        return Collections.emptyList();
    }

    public List<Integer> pagesToRemove() {
        return Collections.emptyList();
    }

    public List<SignatureConfig> signatureConfigs(String patientSignature, String prescriberSignature) {
        return Collections.emptyList();
    }

    protected PDDocument loadPdfDoc() throws IOException {
        // https://stackoverflow.com/questions/25869428/classpath-resource-not-found-when-running-as-jar
        File tmpFile = tmpFile();
        try (OutputStream outStream = new FileOutputStream(tmpFile)) {
            String formPath = applicationType().getFormPath();
            outStream.write(new ClassPathResource(formPath).getInputStream().readAllBytes());
            return Loader.loadPDF(tmpFile);
        }
    }

    protected List<String> findPdfFieldName(Map.Entry<String, Object> entry) {
        String field = entry.getKey();
        if (multiCheckBoxFields().contains(field)) {
            List<String> checkBoxFields = convertObjectToList(entry.getValue()).stream().map(v -> (String) v).toList();
            return reverseMap().entrySet().stream()
                    .filter(es -> checkBoxFields.contains(es.getKey()))
                    .flatMap(es -> es.getValue().stream()).collect(Collectors.toList());
        }

        return reverseMap().get(field);
    }

    protected Map<String, List<String>> reverseMap() {
        return pdfFieldsMap().entrySet().stream().collect(Collectors.groupingBy(
                Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
    }

    protected void removePages(PDDocument doc, List<Integer> pages) {
        for (Integer page : pages) {
            doc.removePage(page);
        }
    }

    protected static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

    protected boolean isDateField(String field) {
        return dateFields().contains(pdfFieldsMap().get(field));
    }

    protected void applySignatures(PDDocument doc, List<SignatureConfig> configs) {
        if (!configs.isEmpty()) {
            signatureApplicator.apply(doc, configs);
        }
    }

    protected String getPatientFullName(Map<String, Object> metadata) {
        String patientFirstName = (String) metadata.get("patient_first_name");
        String patientMiddleInitial = (String) metadata.get("patient_middle_initial");
        String patientLastName = (String) metadata.get("patient_last_name");

        return (patientMiddleInitial != null)
                ? String.format("%s %s. %s", patientFirstName, patientMiddleInitial, patientLastName)
                : String.format("%s %s", patientFirstName, patientLastName);
    }

    protected void assignSignatureDate(PDDocument document) throws IOException {
        for (String pdfFieldName : reverseMap().getOrDefault("signature_date", Collections.emptyList())) {
            setField(document, pdfFieldName, LocalDate.now().format(formatter));
        }
    }

    public byte[] process(Map<String, Object> metadata, String patientSignature, String prescriberSignature) {
        try (PDDocument doc = loadPdfDoc()) {
            removePages(doc, pagesToRemove());
            assignValues(doc, metadata);
            assignSignatureDate(doc);
            return additionalProcessing(doc, metadata, patientSignature, prescriberSignature);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] additionalProcessing(PDDocument doc, Map<String, Object> metadata, String patientSignature, String prescriberSignature) {
        applySignatures(doc, signatureConfigs(patientSignature, prescriberSignature));
        return docToBytes(doc);
    }

    protected void assignValues(PDDocument doc, Map<String, Object> metadata) throws IOException {
        String metadataKey = null;
        try {
            for (Map.Entry<String, Object> entry : metadata.entrySet()) {
                List<String> pdfFieldNames = findPdfFieldName(entry);

                metadataKey = entry.getKey();

                if (pdfFieldNames == null && entry.getValue() instanceof ArrayList<?>) {
                    continue;
                }

                if (isDerivedField(metadataKey)) {
                    setDerivedField(doc, metadata, metadataKey);
                    continue;
                }

                if (pdfFieldNames == null) {
                    setField(doc, metadataKey, (String) entry.getValue());
                    continue;
                }

                if (multiCheckBoxFields().contains(metadataKey)) {
                    for (String checkBoxField : pdfFieldNames) {
                        setField(doc, checkBoxField, CHECK);
                    }
                    continue;
                }

                String value;
                for (String pdfFieldName : pdfFieldNames) {
                    if (pdfFieldName != null) {
                        if (isRadioField(entry)) {
                            Integer v = (Integer) entry.getValue();
                            value = radioChoices.get(v);
                        } else if (isDateField(pdfFieldName)) {
                            value = (String) entry.getValue();
                            value = LocalDate.parse(value).format(formatter);
                        } else if (isSingleCheckBoxField(metadataKey)) {
                            if ((Boolean) entry.getValue()) {
                                value = CHECK;
                            } else {
                                continue;
                            }
                        } else {
                            value = (String) entry.getValue();
                        }

                        setField(doc, pdfFieldName, value);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Error: Metadata key {} could not be assigned", metadataKey);
        }
    }

    public void setDerivedField(PDDocument doc, Map<String, Object> metadata, String key) {

    }

    private boolean isDerivedField(String key) {
        return derivedFields().contains(key);
    }

    private boolean isSingleCheckBoxField(String key) {
        return singleCheckBoxFields().contains(key);
    }

    private boolean isRadioField(Map.Entry<String, Object> entry) {
        return radioFields().contains(entry.getKey());
    }

    public byte[] previewWithFieldsPopulated(Boolean skipAddedFields) {
        try (PDDocument doc = Loader.loadPDF(new ClassPathResource(applicationType().getFormPath()).getFile())) {
            for (PDField field : doc.getDocumentCatalog().getAcroForm().getFields()) {
                if (skipAddedFields && pdfFieldsMap().containsKey(field.getPartialName())) {
                    continue;
                }
                System.out.println(field.getPartialName());
                if (field instanceof PDTextField) {
                    setField(doc, field.getPartialName(), field.getPartialName());
                } else if (field instanceof PDCheckBox) {
                    setField(doc, field.getPartialName(), "X");
                } else {
                    processField(field, "|--", field.getPartialName());
                }
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            doc.save(byteArrayOutputStream);
            doc.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
