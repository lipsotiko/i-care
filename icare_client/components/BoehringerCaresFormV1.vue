<template>
  <IContainer>
    <div class="top">
      <h1>Boehringer Ingelheim Cares Application</h1>
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
          <IColumn xs="12" sm="6">
            <TextFieldInput
              v-model="metadata.patient_first_name"
              name="patient_first_name"
              label="First name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="12" sm="6">
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
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.last_4_ssn"
              name="last_4_ssn"
              label="Last 4 SSN"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="3">
            <IFormGroup>
              <IFormLabel>Preferred Language</IFormLabel>
              <RadioGroupSpecial
                name="patient_preferred_language"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_preferred_language_english',
                    label: 'English',
                  },
                  {
                    id: 'patient_preferred_language_spanish',
                    label: 'Spanish',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
          <IColumn xs="3">
            <IFormGroup>
              <IFormLabel>Gender</IFormLabel>
              <RadioGroupSpecial
                name="patient_gender"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_gender_male',
                    label: 'Male',
                  },
                  {
                    id: 'patient_gender_female',
                    label: 'Female',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="2">
            <TextFieldInput
              v-model="metadata.patient_phone_1"
              name="patient_phone_1"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="2">
            <TextFieldInput
              v-model="metadata.patient_phone_2"
              name="patient_phone_2"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.patient_phone_3"
              name="patient_phone_3"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Patient Financial Information</h3>
        </IRow>
        <IRow>
          <IColumn xs="4">
            <NumberFieldInput
              v-model="metadata.number_of_people_in_household"
              name="number_of_people_in_household"
              label="Number of people in your household?"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <NumberFieldInput
              v-model="metadata.annual_household_income_pretax"
              name="annual_household_income_pretax"
              label="Annual Household Income before taxes?"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <NumberFieldInput
              v-model="metadata.total_household_assets"
              name="total_household_assets"
              label="Total patient household assets?"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Patient Insurance Information</h3>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel>
                Have you received disability payments from Social Security for
                more than 24 months?
              </IFormLabel>
              <RadioGroupSpecial
                name="patient_insurance_q1"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_insurance_q1_yes',
                    label: 'Yes',
                  },
                  {
                    id: 'patient_insurance_q1_no',
                    label: 'No',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel>
                Have you received a denial letter from Medicare Low Income
                Subsidy? If yes, please attach a recent copy of this letter
                along with your application.
              </IFormLabel>
              <RadioGroupSpecial
                name="patient_insurance_q2"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_insurance_q2_yes',
                    label: 'Yes',
                  },
                  {
                    id: 'patient_insurance_q2_no',
                    label: 'No',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel>
                Do you have Medicare Part D or Medicare Advantage?
              </IFormLabel>
              <RadioGroupSpecial
                name="patient_insurance_q3"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_insurance_q3_yes',
                    label: 'Yes',
                  },
                  {
                    id: 'patient_insurance_q3_no',
                    label: 'No',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel> Do you have Medicaid? </IFormLabel>
              <RadioGroupSpecial
                name="patient_insurance_q4"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_insurance_q4_yes',
                    label: 'Yes',
                  },
                  {
                    id: 'patient_insurance_q4_no',
                    label: 'No',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel>
                Do you have prescription drug coverage from a commercial or
                private health insurer? (Not including Medicare Part D
                prescription benefits)
              </IFormLabel>
              <RadioGroupSpecial
                name="patient_insurance_q5"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_insurance_q5_yes',
                    label: 'Yes',
                  },
                  {
                    id: 'patient_insurance_q5_no',
                    label: 'No',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel>
                Do you receive Veterans Affairs prescription drug coverage
                benefits?
              </IFormLabel>
              <RadioGroupSpecial
                name="patient_insurance_q6"
                :metadata="metadata"
                :options="[
                  {
                    id: 'patient_insurance_q6_yes',
                    label: 'Yes',
                  },
                  {
                    id: 'patient_insurance_q6_no',
                    label: 'No',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Prescriber Information</h3>
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
              v-model="metadata.prescriber_npi"
              name="prescriber_npi"
              label="NPI #"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.prescriber_specialty"
              name="prescriber_specialty"
              label="Prescriber Specialty"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.prescriber_sln"
              name="prescriber_sln"
              label="SLN #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.prescriber_sln_exp"
              name="prescriber_sln_exp"
              label="SLN Expiration Date"
              type="date"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.prescriber_facility_name"
              name="prescriber_facility_name"
              label="Facility Name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.prescriber_office_contact_name"
              name="prescriber_office_contact_name"
              label="Office Contact Name"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_address"
              name="prescriber_address"
              label="Address"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.prescriber_city"
              name="prescriber_city"
              label="City"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.prescriber_state"
              name="prescriber_state"
              label="State"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.prescriber_zip_code"
              name="prescriber_zip_code"
              label="Zip Code"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_phone"
              name="prescriber_phone"
              label="Phone Number"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_fax"
              name="prescriber_fax"
              label="Fax Number"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Prescription & Medication Information</h3>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.product_name_strength"
              name="product_name_strength"
              label="Product Name / Strength"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="8">
            <TextFieldInput
              v-model="metadata.directions"
              name="directions"
              label="Directions"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <IFormGroup>
              <IFormLabel> Refills </IFormLabel>
              <RadioGroupSpecial
                name="rx_refill"
                :metadata="metadata"
                :options="[
                  {
                    id: 'rx_refill_1',
                    label: '1',
                  },
                  {
                    id: 'rx_refill_2',
                    label: '2',
                  },
                  {
                    id: 'rx_refill_3',
                    label: '3',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="3">
            <IFormGroup>
              <IFormLabel> Medication Allergies? </IFormLabel>
              <RadioGroupSpecial
                name="medication_allergies"
                :metadata="metadata"
                :options="[
                  {
                    id: 'medication_allergies_yes',
                    label: 'Yes',
                  },
                  {
                    id: 'medication_allergies_no',
                    label: 'No',
                  },
                ]"
              />
            </IFormGroup>
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.drug_allergies"
              name="drug_allergies"
              label="Drug Allergies"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.current_medications"
              name="current_medications"
              label="Current Medications"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
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
import TextFieldInput from "./TextFieldInput.vue";

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
    };
  },
  methods: {
    async submit() {
      this.$emit("submit", {
        id: this.id,
        type: "BOEHRINGER_CARES_V1",
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
        patient_first_name: "John",
        patient_last_name: "Palen",
        patient_address: "123 North Pole",
        patient_city: "Baltimore",
        patient_state: "MD",
        patient_zip_code: "12332",
        patient_phone_1: "410",
        patient_phone_2: "333",
        patient_phone_3: "1234",
        patient_phone_sms_1: "444",
        patient_phone_sms_2: "555",
        patient_phone_sms_3: "6666",
        patient_send_sms_notifications_yes: true,
        patient_send_sms_notifications_no: false,
        patient_dob: "2023-01-02",
        patient_gender_male: true,
        patient_gender_female: false,
        last_4_ssn: "1234",
        preferred_language_other: "Greek",
        number_of_people_in_household: "1000",
        annual_household_income_pretax: "2000",
        total_household_assets: "3000",
        patient_insurance_q1_yes: true,
        patient_insurance_q1_no: false,
        patient_insurance_q2_yes: true,
        patient_insurance_q2_no: false,
        patient_insurance_q3_yes: true,
        patient_insurance_q3_no: false,
        patient_insurance_q4_yes: true,
        patient_insurance_q4_no: false,
        patient_insurance_q5_yes: true,
        patient_insurance_q5_no: false,
        patient_insurance_q6_yes: true,
        patient_insurance_q6_no: false,
        patient_preferred_language_english: true,
        patient_preferred_language_spanish: false,
        rx_refill_1: false,
        rx_refill_2: true,
        rx_refill_3: false,
        medication_allergies_yes: true,
        medication_allergies_no: false,
        drug_allergies: "some",
        prescriber_name: "Saul Goodman",
        prescriber_npi: "AB123",
        prescriber_specialty: "Bones",
        prescriber_sln: "HB2",
        prescriber_sln_exp: "2023-02-01",
        prescriber_facility_name: "OPA",
        prescriber_office_contact_name: "Joan",
        prescriber_address: "1 Merry Way",
        prescriber_city: "Bel Air",
        prescriber_state: "MD",
        prescriber_zip_code: "454545",
        prescriber_phone: "432-202-2222",
        prescriber_fax: "432-202-3333",
        product_name_strength: "Boo",
        directions: "up",
        current_medications: "none",
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
