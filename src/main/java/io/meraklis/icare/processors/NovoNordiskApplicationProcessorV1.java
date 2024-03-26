package io.meraklis.icare.processors;

import io.meraklis.icare.applications.PatientApplicationType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.meraklis.icare.applications.PatientApplicationType.NOVO_NORDISK_V1;

@Service
public class NovoNordiskApplicationProcessorV1 extends AbstractApplicationProcessor {

    @Override
    PatientApplicationType applicationType() {
        return NOVO_NORDISK_V1;
    }

    @Override
    public List<Integer> pagesToRemove() {
        return List.of(0);
    }

    @Override
    public List<String> multiCheckBoxFields() {
        return List.of("patient_insurance");
    }

    @Override
    public List<String> radioFields() {
        return List.of(
                "patient_gender", "patient_coverage", "patient_authorized_representative_family_other",
                "patient_authorized_representative", "new_or_renewal", "rybelsus_60_day", "rybelsus_120_day");
    }

    @Override
    public List<String> dateFields() {
        return List.of("patient_dob");
    }

    @Override
    public List<String> singleCheckBoxFields() {
        return List.of("allow_electronic_income_verification", "hipaa_legal_representative_authorization", "program_authorization_and_certification", "auto_refill");
    }

    @Override
    Map<String, String> pdfFieldsMap() {
        return new HashMap<>() {
            {
                put("NewOrRenewal", "new_or_renewal");
                put("FirstName", "patient_first_name");
                put("LastName", "patient_last_name");
                put("PatientDOB", "patient_dob");
                put("Text Field 344", "patient_address");
                put("Text Field 345", "patient_city");
                put("PatientDOB 2", "patient_state");
                put("PatientDOB 3", "patient_zip_code");
                put("Text Field 346", "patient_home_number");
                put("Text Field 347", "patient_mobile_number");
                put("Text Field 348", "patient_email");
                put("Gender", "patient_gender");
                put("Coverage", "patient_coverage");
                put("Text Field 428", "insurance_plan_name");
                put("Text Field 429", "insurance_plan_member_id");
                put("Text Field 430", "insurance_plan_phone_number");
                put("Check Box 190", "insurance_employer_supplied_coverage");
                put("Check Box 299", "insurance_medicare");
                put("Check Box 188", "insurance_medicare_part_b");
                put("Check Box 167", "insurance_va_military_benefits");
                put("Check Box 300", "insurance_medicaid");
                put("Check Box 187", "insurance_medicaid_low_income_subsidy");
                put("Text Field 480", "patient_authorized_representative_name");
                put("Text Field 479", "patient_authorized_representative_phone_number");
                put("FamilyOther", "patient_authorized_representative_family_other");
                put("Text Field 478", "patient_authorized_representative_caregiver");
                put("Text Field 477", "signature_date");
                put("Check Box 366", "allow_electronic_income_verification");
                put("Text Field 412", "total_household_income");
                put("Text Field 389", "number_of_people_in_household");
                put("Text Field 390", "number_of_dependents");
                put("Text Field 452", "medicare_drug_coverage_consent_member_number");
                put("Text Field 444", "medicare_drug_coverage_consent_enrollment_year");
                put("Text Field 443", "signature_date");
                put("RepPermission", "patient_authorized_representative");
                put("FirstName 4", "patient_authorized_representative_name");
                put("Text Field 481", "signature_date");
                put("Relationship 5", "patient_authorized_representative_family_other");
                put("Text Field 457", "patient_authorized_representative_phone_number");
                put("Text Field 458", "tcpa_phone_number");
                put("Check Box 367", "program_authorization_and_certification");
                put("Check Box 368", "hipaa_legal_representative_authorization");
                put("FirstName 5", "hipaa_legal_representative");
                put("Relationship 6", "hipaa_legal_representative_relationship");
                put("Text Field 482", "hipaa_legal_representative_phone_number");
                put("Text Field 483", "signature_date");
                put("Text Field 405", "known_drug_allergies");
                put("FirstName 2", "prescriber_first_name");
                put("LastName 2", "prescriber_last_name");
                put("Text Field 394", "prescriber_designation");
                put("Text Field 463", "prescriber_address");
                put("Text Field 464", "prescriber_address_2");
                put("Text Field 453", "prescriber_city");
                put("PatientDOB 5", "prescriber_state");
                put("PatientDOB 4", "prescriber_zip_code");
                put("Text Field 396", "prescriber_phone_number");
                put("Text Field 398", "prescriber_state_license_number");
                put("Text Field 432", "prescriber_state_license");
                put("Text Field 401", "prescriber_fax");
                put("Text Field 404", "prescriber_office_contact");
                put("Text Field 4019", "prescriber_office_email");
                put("Text Field 484", "prescriber_npi");
                put("Text Field 4025", "days_closed_for_deliveries");
                put("Text Field 392", "signature_date");

                put("Text Field 4022", "fiasp_max");
                put("Text Field 4033", "fiasp_sig_directions");
                put("Check Box 365", "fiasp_vial");
                put("Check Box 364", "fiasp_flex_touch");
                put("Check Box 363", "fiasp_cartridge");
                put("Text Field 408", "fiasp_qty");

                put("Text Field 4023", "tresiba_u100_max");
                put("Text Field 4034", "tresiba_u100_sig_directions");
                put("Check Box 362", "tresiba_u100_vial");
                put("Check Box 361", "tresiba_u100_flex_touch");
                put("Text Field 409", "tresiba_u100_qty");

                put("Text Field 4053", "degludec_u100_max");
                put("Text Field 4052", "degludec_u100_sig_directions");
                put("Check Box 3022", "degludec_u100_vial");
                put("Check Box 3021", "degludec_u100_flex_touch");
                put("Text Field 4051", "degludec_u100_qty");

                put("Text Field 4024", "tresiba_u200_max");
                put("Text Field 4035", "tresiba_u200_sig_directions");
                put("Check Box 360", "tresiba_u200_flex_touch");
                put("Text Field 4010", "tresiba_u200_qty");

                put("Text Field 4049", "degludec_u200_max");
                put("Text Field 4048", "degludec_u200_sig_directions");
                put("Check Box 3020", "degludec_u200_flex_touch");
                put("Text Field 4047", "degludec_u200_qty");

                put("Text Field 4026", "novolog_u100_max");
                put("Text Field 4037", "novolog_u100_sig_directions");
                put("Check Box 357", "novolog_u100_vial");
                put("Check Box 356", "novolog_u100_flex_pen");
                put("Check Box 355", "novolog_u100_cartridge");
                put("Text Field 4012", "novolog_u100_qty");

                put("Text Field 4027", "aspart_injection_u100_max");
                put("Text Field 4038", "aspart_injection_u100_sig_directions");
                put("Check Box 354", "aspart_injection_u100_vial");
                put("Check Box 3019", "aspart_injection_u100_flex_pen");
                put("Check Box 3018", "aspart_injection_u100_cartridge");
                put("Text Field 4013", "aspart_injection_u100_qty");

                put("Text Field 4028", "novolog_mix_70_30_u100_vial_max");
                put("Text Field 4039", "novolog_mix_70_30_u100_vial_sig_directions");
                put("Check Box 3017", "novolog_mix_70_30_u100_vial");
                put("Check Box 3016", "novolog_mix_70_30_u100_flex_pen");
                put("Text Field 4014", "novolog_mix_70_30_u100_vial_qty");

                put("Text Field 4032", "aspart_mix_70_30_u100_max");
                put("Text Field 4046", "aspart_mix_70_30_u100_sig_directions");
                put("Check Box 3015", "aspart_mix_70_30_u100_vial");
                put("Check Box 3014", "aspart_mix_70_30_u100_flex_pen");
                put("Text Field 4021", "aspart_mix_70_30_u100_qty");

                put("Text Field 4029", "novolin_r_u100_max");
                put("Text Field 4040", "novolin_r_u100_sig_directions");
                put("Check Box 353", "novolin_r_u100_vial");
                put("Text Field 4015", "novolin_r_u100_qty");

                put("Text Field 4030", "novolin_n_u100_max");
                put("Text Field 4041", "novolin_n_u100_sig_directions");
                put("Check Box 352", "novolin_n_u100_vial");
                put("Text Field 4016", "novolin_n_u100_qty");

                put("Text Field 4031", "novolin_70_30_u100_max");
                put("Text Field 4043", "novolin_70_30_u100_sig_directions");
                put("Check Box 351", "novolin_70_30_u100_vial");
                put("Text Field 4017", "novolin_70_30_u100_qty");

                put("Text Field 4018", "novo_fine_qty");

                put("Check Box 305", "zegalogue_auto_1pack");
                put("Check Box 306", "zegalogue_auto_2pack");
                put("Check Box 308", "zegalogue_syringe_1pack");
                put("Check Box 307", "zegalogue_syringe_2pack");
                put("Text Field 4042", "zegalogue_qty");

                put("Check Box 303", "novopen_echo_pen");

                put("Text Field 4076", "ozempic_0_25mg_0_5mg_1pack_sig_directions");
                put("Check Box 278", "ozempic_0_25mg_0_5mg_1pack");
                put("Text Field 4075", "ozempic_0_25mg_0_5mg_1pack_qty");

                put("Text Field 4078", "ozempic_1mg_1pack_sig_directions");
                put("Check Box 279", "ozempic_1mg_1pack");
                put("Text Field 4080", "ozempic_1mg_1pack_qty");

                put("Text Field 4079", "ozempic_2mg_1pack_sig_directions");
                put("Check Box 301", "ozempic_2mg_1pack");
                put("Text Field 4081", "ozempic_2mg_1pack_qty");

                put("Text Field 4071", "vicotoza_1_2mg_2pack_max");
                put("Text Field 4070", "vicotoza_1_2mg_2pack_sig_directions");
                put("Check Box 280", "vicotoza_1_2mg_2pack");
                put("Text Field 4069", "vicotoza_1_2mg_2pack_qty");

                put("Text Field 4068", "vicotoza_1_8mg_3pack_max");
                put("Text Field 4067", "vicotoza_1_8mg_3pack_sig_directions");
                put("Check Box 281", "vicotoza_1_8mg_3pack");
                put("Text Field 4066", "vicotoza_1_8mg_3pack_qty");

                put("Text Field 4065", "xultophy_5pack_max");
                put("Text Field 4064", "xultophy_5pack_sig_directions");
                put("Check Box 282", "xultophy_5pack");
                put("Text Field 4063", "xultophy_5pack_qty");

                put("Text Field 4062", "rybelsus_60_day_max");
                put("Text Field 4061", "rybelsus_60_day_sig_directions");
                put("RYB60", "rybelsus_60_day");

                put("Text Field 4059", "rybelsus_120_day_max");
                put("Text Field 4058", "rybelsus_120_day_sig_directions");
                put("RYB120", "rybelsus_120_day");

                put("Check Box 295", "auto_refill");
            }
        };
    }

}
