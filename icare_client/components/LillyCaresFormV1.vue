<template>
  <IContainer>
    <div class="top">
      <h1>Lilly Cares Application</h1>
      <div v-if="showLoadTestData" class="top-buttons">
        <button type="button" color="secondary" @click="loadTestData">
          Load Test Data
        </button>
        <IButton size="sm" to="/">Home</IButton>
      </div>
    </div>
    <hr />
    <IForm name="lilly_cares_v1" :readonly="disabled" :disabled="disabled">
      <IContainer>
        <IRow>
          <h3>Patient Information</h3>
        </IRow>
        <IRow>
          <IColumn xs="12" sm="4">
            <TextFieldInput
              v-model="metadata.patient_first_name"
              name="patient_first_name"
              label="First name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="12" sm="3">
            <TextFieldInput
              v-model="metadata.patient_middle_initial"
              name="patient_middle_initial"
              label="Middle initial"
              maxLength="1"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="12" sm="5">
            <TextFieldInput
              v-model="metadata.patient_last_name"
              name="patient_last_name"
              label="Last name"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.patient_address"
              name="patient_address"
              label="Address"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.patient_city"
              name="patient_city"
              label="City"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.patient_state"
              name="patient_state"
              label="State"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.patient_zip_code"
              name="patient_zip_code"
              label="Zip Code"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.patient_dob"
              name="patient_dob"
              label="DOB"
              type="date"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.patient_phone_number"
              name="patient_phone_number"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="5">
            <IFormGroup>
              <IFormLabel
                >Where would you like your medication delivered?</IFormLabel
              >
              <IRadioGroup
                v-model="metadata.deliver_medication_to_home"
                name="deliver_medication_to_home"
                :options="[
                  {
                    id: 1,
                    label: 'To home',
                  },
                  {
                    id: 2,
                    label: 'To healthcare provider\'s office',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.patient_drug_allergies"
              name="patient_drug_allergies"
              label="Drug allergies"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.other_medications"
              name="other_medications"
              label="Other medications"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Patient Income Information</h3>
        </IRow>
        <IRow>
          <IColumn xs="6">
            <NumberFieldInput
              v-model="metadata.number_of_people_in_household"
              name="number_of_people_in_household"
              label="Number of people in your household?"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="6">
            <NumberFieldInput
              v-model="metadata.annual_household_income_pretax"
              name="annual_household_income_pretax"
              label="Annual Household Income before taxes?"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Patient Insurance Information</h3>
        </IRow>
        <IRow>
          <IColumn xs="6">
            <IFormGroup>
              <IFormLabel
                >Has your employer, insurance company, or their appointed
                representative directed you to seek enrollment in this program
                as a requirement of your drug coverage plan? This does not
                include your healthcare provider or their office, specialty
                pharmacy, or a family member?</IFormLabel
              >
              <IRadioGroup
                v-model="metadata.directed_to_seek_enrollment"
                name="directed_to_seek_enrollment"
                :options="[
                  {
                    id: 1,
                    label: 'No',
                  },
                  {
                    id: 2,
                    label: 'Yes',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
          <IColumn xs="6">
            <IFormGroup>
              <IFormLabel
                >Do you have insurance? (Check all that apply)</IFormLabel
              >
              <ICheckboxGroup
                v-model="metadata.patient_insurance"
                name="patient_insurance"
                :options="[
                  {
                    id: 'insurance_none',
                    label: 'None',
                  },
                  {
                    id: 'insurance_medicaid',
                    label: 'Medicaid',
                  },
                  {
                    id: 'insurance_medicaid_part_d',
                    label: 'Medicare Part D',
                  },
                  {
                    id: 'insurance_va_or_military',
                    label: 'VA or Military',
                  },
                  {
                    id: 'insurance_medicare_part_b_without_supplemental',
                    label:
                      'Medicare Part B WITHOUT supplemental/secondary insurance',
                  },
                  {
                    id: 'insurance_medicare_part_b_with_supplemental',
                    label:
                      'Medicare Part B WITH supplemental/secondary insurance',
                  },
                  {
                    id: 'insurance_private_excluding_medicare_part_d',
                    label: 'Private Insurance (excluding Medicare Part D)',
                  },
                  {
                    id: 'insurance_other',
                    label: 'Other',
                  },
                ]"
              />
              <TextFieldInput
                v-model="metadata.other_insurance"
                name="other_insurance"
                label="Other Insurance"
                :disabled="isOtherInsuranceDisabled || disabled"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>
            Patient Authorization for Automatic Rx Refills (“Auto-refill”)
          </h3>
        </IRow>
        <IRow>
          <IColumn>
            <IFormGroup>
              <IFormLabel
                >Has your employer, insurance company, or their appointed
                representative directed you to seek enrollment in this program
                as a requirement of your drug coverage plan? This does not
                include your healthcare provider or their office, specialty
                pharmacy, or a family member?</IFormLabel
              >
              <IRadioGroup
                v-model="metadata.prescription_auto_refill"
                name="prescription_auto_refill"
                :options="[
                  {
                    id: 1,
                    label:
                      'Yes, automatically fill my medication when I am due for a refill.',
                  },
                  {
                    id: 2,
                    label:
                      'No, do NOT automatically refill my medication. I will call Lilly Cares when I am due for a refill.',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Patient Authorization to Speak with Authorized Representative</h3>
        </IRow>
        <IRow>
          <IColumn>
            <IFormGroup>
              <IFormLabel
                >You may provide the names of one or more people with whom you
                authorize Lilly Cares to speak on your behalf about this
                application or your participation in the Lilly Cares Program.
                These people can provide or receive your personal information as
                necessary until the end of your enrollment period unless you
                request their authority be terminated prior to then</IFormLabel
              >
              <IRadioGroup
                v-model="metadata.authorize_representative"
                name="authorize_representative"
                :options="[
                  {
                    id: 1,
                    label:
                      'Yes, I\'d like to authorize a person to speak on my behalf.',
                  },
                  {
                    id: 2,
                    label:
                      'No, I do not want anyone speaking to Lilly Cares on my behalf.',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.authorized_representative_1_name"
              name="authorized_representative_1_name"
              label="Authorized Representative 1"
              :disabled="allowsAuthorizedRepresentative || disabled"
            />
          </IColumn>
          <IColumn>
            <IFormGroup>
              <IFormLabel>Relationship to patient</IFormLabel>
              <IRadioGroup
                v-model="metadata.authorized_representative_1_relationship"
                name="authorized_representative_1_relationship"
                :options="[
                  {
                    id: 1,
                    label: 'Family Member/Caregiver',
                  },
                  {
                    id: 2,
                    label: 'Other, please specify.',
                  },
                ]"
                :disabled="allowsAuthorizedRepresentative || disabled"
              />
            </IFormGroup>
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.authorized_representative_1_relationship_other"
              name="authorized_representative_1_relationship_other"
              label="Other"
              :disabled="isOtherRepresentative1Disabled || disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.authorized_representative_2_name"
              name="authorized_representative_2_name"
              label="Authorized Representative 2"
              :disabled="allowsAuthorizedRepresentative || disabled"
            />
          </IColumn>
          <IColumn>
            <IFormGroup>
              <IFormLabel>Relationship to patient</IFormLabel>
              <IRadioGroup
                v-model="metadata.authorized_representative_2_relationship"
                name="authorized_representative_2_relationship"
                :options="[
                  {
                    id: 1,
                    label: 'Family Member/Caregiver',
                  },
                  {
                    id: 2,
                    label: 'Other, please specify.',
                  },
                ]"
                :disabled="allowsAuthorizedRepresentative || disabled"
              />
            </IFormGroup>
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.authorized_representative_2_relationship_other"
              name="authorized_representative_2_relationship_other"
              label="Other"
              :disabled="isOtherRepresentative2Disabled || disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Healthcare Provider/Prescriber Section</h3>
        </IRow>
        <IRow>
          <IColumn xs="8">
            <TextFieldInput
              v-model="metadata.prescriber_name"
              name="prescriber_name"
              label="Prescriber Name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.prescriber_medications_requested"
              name="prescriber_medications_requested"
              label="Medication(s) Requested"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.healthcare_provider_dea_number"
              name="healthcare_provider_dea_number"
              label="DEA #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.healthcare_provider_npi_number"
              name="healthcare_provider_npi_number"
              label="NPI #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.healthcare_provider_license_and_state"
              name="healthcare_provider_license_and_state"
              label="State License # and State"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.healthcare_provider_address"
              name="healthcare_provider_address"
              label="Address"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.healthcare_provider_city"
              name="healthcare_provider_city"
              label="City"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.healthcare_provider_state"
              name="healthcare_provider_state"
              label="State"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.healthcare_provider_zip_code"
              name="healthcare_provider_zip_code"
              label="Zip Code"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.healthcare_provider_phone_number"
              name="healthcare_provider_phone_number"
              label="Phone Number"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.healthcare_provider_fax_number"
              name="healthcare_provider_fax_number"
              label="Fax Number"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.healthcare_provider_office_contact_name"
              name="healthcare_provider_office_contact_name"
              label="Office Contact Name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.healthcare_provider_office_contact_phone_number"
              name="healthcare_provider_office_contact_phone_number"
              label="Office Contact Phone Number"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <IFormGroup>
              <IFormLabel>
                Please indicate the method for submitting a prescription to
                Lilly Cares.
              </IFormLabel>
              <ICheckboxGroup
                v-model="metadata.electronic_prescription_or_fax"
                name="electronic_prescription_or_fax"
                :options="[
                  {
                    id: 'electronic_prescription',
                    label:
                      'Electronic prescription: select Fortrea Specialty Pharmacy (NPI 1780811125) in your eRx software.',
                  },
                  {
                    id: 'fax_prescription_utilizing_page_9',
                    label:
                      'Fax prescription to 1-844-431-6650 utilizing the optional prescription page 9 of the application, resources on lillycares.com, or HCP prescription.',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <hr />
        <IRow class="medication-buttons">
          <IButton
            circle
            @click="metadata.medications.pop()"
            :disabled="metadata.medications.length === 1"
          >
            <template #icon>
              <IIcon name="ink-minus" />
            </template>
          </IButton>
          <IButton
            circle
            @click="metadata.medications.push(defaultMedication)"
            :disabled="metadata.medications.length === 3"
          >
            <template #icon>
              <IIcon name="ink-plus" />
            </template>
          </IButton>
        </IRow>
        <div v-for="(med, i) in metadata.medications" class="medication">
          <h3>Medication {{ i + 1 }}</h3>
          <IRow>
            <IColumn>
              <TextFieldInput
                v-model="metadata.medications[i].medication"
                :name="`medication_${i}`"
                label="Medication"
                :disabled="disabled"
              />
            </IColumn>
            <IColumn>
              <TextFieldInput
                v-model="metadata.medications[i].strength"
                :name="`strength_${i}`"
                label="Strength"
                :disabled="disabled"
              />
            </IColumn>
            <IColumn>
              <TextFieldInput
                v-model="metadata.medications[i].max_dose_per_day"
                :name="`max_dose_per_day_${i}`"
                label="Max Dose per Day"
                :disabled="disabled"
              />
            </IColumn>
          </IRow>
          <IRow>
            <IColumn>
              <TextFieldInput
                v-model="metadata.medications[i].directions"
                :name="`directions_${i}`"
                label="Directions"
                :disabled="disabled"
              />
            </IColumn>
          </IRow>
          <IRow>
            <IColumn xs="4">
              <IFormGroup>
                <IFormLabel>Are you prescribing insulin?</IFormLabel>
                <IRadioGroup
                  v-model="metadata.medications[i].prescribing_insulin"
                  :name="`prescribing_insulin_${i}`"
                  @change="handlePrescibingInsulinChange(i)"
                  :options="[
                    {
                      id: 1,
                      label: 'Yes',
                    },
                    {
                      id: 2,
                      label: 'No',
                    },
                  ]"
                />
              </IFormGroup>
            </IColumn>
            <IColumn xs="8">
              <IFormGroup>
                <IFormLabel>If yes, select the prescribed insulin</IFormLabel>
                <IRadioGroup
                  v-model="metadata.medications[i].prescribed_insulin"
                  :name="`prescribed_insulin_${i}`"
                  :options="[
                    {
                      id: 1,
                      label:
                        'Vial (not available for Basaglar®, Humalog® U-200, Humalog®	50/50, or Lyumjev™ U-200)',
                    },
                    {
                      id: 2,
                      label:
                        'KwikPen (not available for Humulin® R 100 units/mL)',
                    },
                    {
                      id: 3,
                      label:
                        'Cartridge (only available for Humalog® 100 units/mL)',
                    },
                  ]"
                  :disabled="isPrescribingInsulin(i)"
                />
              </IFormGroup>
            </IColumn>
          </IRow>
          <IRow>
            <IColumn xs="4">
              <TextFieldInput
                v-model="metadata.medications[i].refill_number"
                :name="`refill_number_${i}`"
                label="Refill #"
                :disabled="disabled"
              />
            </IColumn>
            <IColumn xs="8">
              <IFormGroup>
                <IFormLabel
                  >Quantity to be Dispensed (Oncology Medications are limited to
                  a ONE-month supply)</IFormLabel
                >
                <IRadioGroup
                  v-model="metadata.medications[i].quantity_dispensed"
                  :name="`quantity_dispensed_${i}`"
                  :options="[
                    {
                      id: 1,
                      label: '4 Month (max)',
                    },
                    {
                      id: 3,
                      label: '3 Month',
                    },
                    {
                      id: 4,
                      label: '2 Month',
                    },
                    {
                      id: 2,
                      label: '1 Month',
                    },
                  ]"
                />
              </IFormGroup>
            </IColumn>
          </IRow>
          <IRow>
            <IColumn>
              <IFormGroup>
                <IFormLabel
                  >Your state may require that prescriptions follow certain
                  content requirements or use a particular form. Non-compliance
                  with state-specific requirements will result in outreach to
                  the prescriber and may delay shipping of medication. By
                  signing below, you certify that you are abiding by laws
                  applicable to prescriptions and authorized prescribers in the
                  states in which you are prescribing. I authorize Lilly Cares
                  to act on my behalf for the limited purposes of transmitting
                  this order for prescription medication</IFormLabel
                >
                <IRadioGroup
                  v-model="metadata.medications[i].dispense_as_written"
                  :name="`dispense_as_written_${i}`"
                  :options="[
                    {
                      id: 1,
                      label: 'Dispense as written',
                    },
                    {
                      id: 2,
                      label: 'Substitution Brand Exchange Permitted',
                    },
                  ]"
                />
              </IFormGroup>
            </IColumn>
          </IRow>
        </div>
      </IContainer>
      <IFormGroup class="submit">
        <IButton
          v-if="showDelete == true"
          outline
          color="danger"
          @click="deleteModalVisible = true"
          >Delete</IButton
        >
        <IButton color="primary" @click="submit">Save</IButton>
      </IFormGroup>
    </IForm>
    <IModal v-model="deleteModalVisible">
      <template #header>
        <h3>Delete Application</h3>
      </template>
      Are you sure you want to delete this application?
      <template #footer>
        <div class="delete-footer">
          <IButton color="primary" @click="deleteApplication">Yes</IButton>
        </div>
      </template>
    </IModal>
  </IContainer>
</template>
<script>
import { useToast } from "@inkline/inkline/composables";

const toast = useToast();

export default {
  props: {
    id: String,
    metadata: Object,
    disabled: Boolean,
    showDelete: Boolean,
    showLoadTestData: Boolean,
  },
  data() {
    return {
      deleteModalVisible: false,
      defaultMedication: {
        refill_number: "1 Year",
        quantity_dispensed: 1,
        dispense_as_written: 1,
      },
    };
  },
  computed: {
    isOtherInsuranceDisabled() {
      return !(
        this.metadata.patient_insurance !== undefined &&
        this.metadata.patient_insurance.includes("insurance_other")
      );
    },
    allowsAuthorizedRepresentative() {
      return !(
        this.metadata.authorize_representative !== undefined &&
        this.metadata.authorize_representative === 1
      );
    },
    isOtherRepresentative1Disabled() {
      return !(
        this.metadata.authorized_representative_1_relationship !== undefined &&
        this.metadata.authorized_representative_1_relationship === 2
      );
    },
    isOtherRepresentative2Disabled() {
      return !(
        this.metadata.authorized_representative_2_relationship !== undefined &&
        this.metadata.authorized_representative_2_relationship === 2
      );
    },
  },
  methods: {
    isPrescribingInsulin(i) {
      return !(
        this.metadata.medications[i].prescribing_insulin !== undefined &&
        this.metadata.medications[i].prescribing_insulin === 1
      );
    },
    handlePrescibingInsulinChange(i) {
      if (this.metadata.medications[i].prescribing_insulin == 2) {
        this.metadata.medications[i].prescribed_insulin = undefined;
      }
    },
    async submit() {
      this.$emit("submit", {
        id: this.id,
        type: "LILLY_CARES_V1",
        metadata: this.metadata,
      });

      toast.show({
        title: "Success",
        message: "Patient application saved!",
        color: "success",
      });
    },
    deleteApplication() {
      this.deleteModalVisible = false;
      this.$emit("delete");
      toast.show({
        title: "Success",
        message: "Patient application deleted!",
        color: "success",
      });
    },
    loadTestData() {
      this.$emit("loadTestData", {
        medications: [
          {
            medication: "A",
            strength: "A",
            max_dose_per_day: "A",
            directions: "A",
            prescribing_insulin: 1,
            prescribed_insulin: 1,
            refill_number: "A",
            quantity_dispensed: 1,
            dispense_as_written: 1,
          },
          {
            medication: "B",
            strength: "B",
            max_dose_per_day: "B",
            directions: "B",
            refill_number: "B",
            prescribing_insulin: 1,
            prescribed_insulin: 2,
            dispense_as_written: 2,
            quantity_dispensed: 3,
          },
          {
            prescribing_insulin: 1,
            prescribed_insulin: 3,
            quantity_dispensed: 4,
            refill_number: "C",
            directions: "C",
            medication: "C",
            strength: "C",
            max_dose_per_day: "C",
            dispense_as_written: 2,
          },
        ],
        patient_first_name: "John",
        patient_middle_initial: "F",
        patient_last_name: "Wick",
        patient_address: "6958 N. Oleander Ave",
        patient_city: "Chicago",
        patient_state: "IL",
        patient_zip_code: "60631",
        patient_phone_number: "4433019719",
        deliver_medication_to_home: 2,
        patient_dob: "1988-04-28",
        patient_drug_allergies: "Some drug allergies, not many",
        other_medications: "Lots of other meds...",
        number_of_people_in_household: "2",
        annual_household_income_pretax: "75000",
        directed_to_seek_enrollment: 2,
        patient_insurance: [
          "insurance_none",
          "insurance_medicaid",
          "insurance_medicaid_part_d",
          "insurance_va_or_military",
          "insurance_medicare_part_b_without_supplemental",
          "insurance_medicare_part_b_with_supplemental",
          "insurance_private_excluding_medicare_part_d",
          "insurance_other",
        ],
        other_insurance: "Also, other insurance",
        authorize_representative: 1,
        authorized_representative_1_name: "Mother",
        authorized_representative_1_relationship: 2,
        authorized_representative_1_relationship_other: "Step mom",
        authorized_representative_2_name: "Father",
        authorized_representative_2_relationship: 2,
        authorized_representative_2_relationship_other: "Step father",
        prescriber_name: "Tom Burkens",
        healthcare_provider_dea_number: "1234",
        healthcare_provider_npi_number: "7890",
        prescriber_medications_requested: "All the medication please",
        healthcare_provider_license_and_state: "MD 0000999",
        healthcare_provider_address: "123 Elf Road",
        healthcare_provider_city: "North Pole",
        healthcare_provider_state: "GL",
        healthcare_provider_zip_code: "88888",
        healthcare_provider_phone_number: "4435559999",
        healthcare_provider_fax_number: "4435558888",
        healthcare_provider_office_contact_name: "Steve Smith",
        healthcare_provider_office_contact_phone_number: "4435557777",
        electronic_prescription_or_fax: [
          "electronic_prescription",
          "fax_prescription_utilizing_page_9",
        ],
      });
    },
  },
};
</script>
<style>
.top-buttons button {
  margin: 0 12px;
}
.row {
  margin-bottom: 8px;
}
.submit {
  display: flex;
  justify-content: center;
  padding: 32px;
}

.submit button {
  margin: 0 12px;
}

.medication {
  margin-bottom: 22px;
}
.medication-buttons {
  justify-content: flex-end;
}

.medication-buttons button {
  margin: 12px;
}

.delete-footer {
  display: flex;
  justify-content: center;
}
</style>
