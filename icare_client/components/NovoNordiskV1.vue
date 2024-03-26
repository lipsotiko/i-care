<template>
  <IContainer>
    <div class="top">
      <h1>Novo Nordisk Application</h1>
      <div v-if="showLoadTestData" class="top-buttons">
        <button type="button" color="secondary" @click="loadTestData">
          Load Test Data
        </button>
        <IButton size="sm" to="/">Home</IButton>
      </div>
    </div>
    <IForm name="novo_nordisk_v1" :readonly="disabled" :disabled="disabled">
      <IContainer>
        <hr />
        <IRow>
          <h3>Patient Information</h3>
        </IRow>
        <IRow>
          <IColumn xs="12" sm="3">
            <TextFieldInput
              v-model="metadata.patient_first_name"
              name="patient_first_name"
              label="First name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="12" sm="4">
            <TextFieldInput
              v-model="metadata.patient_last_name"
              name="patient_last_name"
              label="Last name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="12" sm="2">
            <TextFieldInput
              v-model="metadata.patient_dob"
              name="patient_dob"
              label="DOB"
              type="date"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="12" sm="3">
            <IFormGroup>
              <IFormLabel>
                New Application or Renewal?
              </IFormLabel>
              <IRadioGroup
                v-model="metadata.new_or_renewal"
                name="new_or_renewal"
                :options="[
                  {
                    id: 1,
                    label: 'New',
                  },
                  {
                    id: 2,
                    label: 'Renewal',
                  },
                ]"
              />
            </IFormGroup>
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
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.patient_home_number"
              name="patient_home_number"
              label="Home Phone #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.patient_mobile_number"
              name="patient_mobile_number"
              label="Mobile Phone #"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="6">
            <IFormGroup>
              <IFormLabel>
                Gender
              </IFormLabel>
              <IRadioGroup
                v-model="metadata.patient_gender"
                name="patient_gender"
                :options="[
                  {
                    id: 1,
                    label: 'Male',
                  },
                  {
                    id: 2,
                    label: 'Female',
                  },
                  {
                    id: 3,
                    label: 'Do Not Disclose',
                  }
                ]"
              />
            </IFormGroup>
          </IColumn>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.patient_email"
              name="patient_email"
              label="Email"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Insurance</h3>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel>
                Any form of prescription drug coverage?
              </IFormLabel>
              <IRadioGroup
                v-model="metadata.patient_coverage"
                name="patient_coverage"
                :options="[
                  {
                    id: 1,
                    label: 'Yes',
                  },
                  {
                    id: 2,
                    label: 'No',
                  }
                ]"
              />
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.insurance_plan_name"
              name="insurance_plan_name"
              label="Plan Name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.insurance_plan_member_id"
              name="insurance_plan_member_id"
              label="Memeber ID"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.insurance_plan_phone_number"
              name="insurance_plan_phone_number"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <IFormGroup>
              <IFormLabel
                >Do you have insurance? (Check all that apply)</IFormLabel
              >
              <ICheckboxGroup
                v-model="metadata.patient_insurance"
                name="patient_insurance"
                :options="[
                  {
                    id: 'insurance_employer_supplied_coverage',
                    label: 'Employer-supplied or commercial/private drug coverage',
                  },
                  {
                    id: 'insurance_medicare',
                    label: 'Medicare Prescription Drug Coverage',
                  },
                  {
                    id: 'insurance_medicare_part_b',
                    label: 'Medicare Part B (medical benefit that covers some prescription medications)',
                  },
                  {
                    id: 'insurance_va_military_benefits',
                    label: 'VA or Military Benefits',
                  },
                  {
                    id: 'insurance_medicaid',
                    label: 'Medicaid Prescription Drug Coverage',
                  },
                  {
                    id: 'insurance_medicaid_low_income_subsidy',
                    label: 'Medicare Low Income Subsidy (LIS/Extra Help)',
                  },
                ]"
              />
              </IFormGroup>
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Authorized Representative</h3>
        </IRow>
        <IRow>
          <IColumn xs="5">
            <IFormGroup>
              <IFormLabel>
                Authorize a person to speak on patients behalf?
              </IFormLabel>
              <IRadioGroup
                v-model="metadata.patient_authorized_representative"
                name="patient_authorized_representative"
                :options="[
                  {
                    id: 1,
                    label: 'Yes',
                  },
                  {
                    id: 2,
                    label: 'No',
                  }
                ]"
              />
            </IFormGroup>
          </IColumn>
          <IColumn xs="3">
            <IFormGroup>
              <IFormLabel>
                Relationship to patient?
              </IFormLabel>
              <IRadioGroup
                v-model="metadata.patient_authorized_representative_family_other"
                name="patient_authorized_representative_family_other"
                :options="[
                  {
                    id: 1,
                    label: 'Family member/caregiver',
                  },
                  {
                    id: 2,
                    label: 'Other',
                  }
                ]"
              />
            </IFormGroup>
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.patient_authorized_representative_caregiver"
              name="patient_authorized_representative_caregiver"
              label="Other"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.patient_authorized_representative_name"
              name="patient_authorized_representative_name"
              label="Full Name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="6">
            <TextFieldInput
              v-model="metadata.patient_authorized_representative_phone_number"
              name="patient_authorized_representative_phone_number"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Fair Credit Reporting Act (FCRA) Consent</h3>
        </IRow>
        <IRow>
          <IColumn xs="12">
            <IFormGroup>
              <IFormLabel>
                Allow PAP to perform an electronic income verification to process application.
              </IFormLabel>
              <ICheckbox
                v-model="metadata.allow_electronic_income_verification"
                name="allow_electronic_income_verification">
                Yes
              </ICheckbox>
            </IFormGroup>
          </IColumn>
          <IColumn xs="4">
            <NumberFieldInput
              v-model="metadata.total_household_income"
              name="total_household_income"
              label="Total Household Annual Income $"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <NumberFieldInput
              v-model="metadata.number_of_people_in_household"
              name="number_of_people_in_household"
              label="# of people living in your household"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <NumberFieldInput
              v-model="metadata.number_of_dependents"
              name="number_of_dependents"
              label="# of dependents (under 18 years of age)"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Patient Medicare Prescription Drug Coverage (Part D) Enrollee Consent</h3>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.medicare_drug_coverage_consent_member_number"
              name="medicare_drug_coverage_consent_member_number"
              label="Memeber ID"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <NumberFieldInput
              v-model="metadata.medicare_drug_coverage_consent_enrollment_year"
              name="medicare_drug_coverage_consent_enrollment_year"
              label="PAP Application Enrollment Year"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>HIPAA Authorization</h3>
        </IRow>
        <IRow>
          <IColumn>
            <IFormGroup>
              <IFormLabel>
                Legal representative signing on behalf of the patient?
              </IFormLabel>
              <ICheckbox
                v-model="metadata.hipaa_legal_representative_authorization"
                name="hipaa_legal_representative_authorization">
                Yes
              </ICheckbox>
            </IFormGroup>
          </IColumn>
        </IRow>
        <IRow>
          <IColumn xs="5">
            <TextFieldInput
              v-model="metadata.hipaa_legal_representative"
              name="hipaa_legal_representative"
              label="Legal Representative"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="4">
            <TextFieldInput
              v-model="metadata.hipaa_legal_representative_relationship"
              name="hipaa_legal_representative_relationship"
              label="Legal Representative Relationship"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.hipaa_legal_representative_phone_number"
              name="hipaa_legal_representative_phone_number"
              label="Legal Representative Phone #"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Telephone Consumer Protection Act (“TCPA”) Communication Consent</h3>
        </IRow>
        <IRow>
          <IColumn xs="3">
            <TextFieldInput
              v-model="metadata.tcpa_phone_number"
              name="tcpa_phone_number"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <hr />
        <IRow>
          <h3>Prescriber</h3>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_first_name"
              name="prescriber_first_name"
              label="First Name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_last_name"
              name="prescriber_last_name"
              label="Last Name"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_designation"
              name="prescriber_designation"
              label="Designation"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.known_drug_allergies"
              name="known_drug_allergies"
              label="Known Patient Drug Allergies"
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
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_city"
              name="prescriber_city"
              label="City"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_state"
              name="prescriber_state"
              label="State"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
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
              v-model="metadata.prescriber_phone_number"
              name="prescriber_phone_number"
              label="Phone #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_state_license_number"
              name="prescriber_state_license_number"
              label="State Lisence #"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_state_license"
              name="prescriber_state_license"
              label="State Lisenced"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_fax"
              name="prescriber_fax"
              label="Fax Number"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_office_contact"
              name="prescriber_office_contact"
              label="Office Contact"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_office_email"
              name="prescriber_office_email"
              label="Office Email"
              :disabled="disabled"
            />
          </IColumn>
        </IRow>
        <IRow>
          <IColumn>
            <TextFieldInput
              v-model="metadata.prescriber_npi"
              name="prescriber_npi"
              label="NPI"
              :disabled="disabled"
            />
          </IColumn>
          <IColumn>
            <TextFieldInput
              v-model="metadata.days_closed_for_deliveries"
              name="days_closed_for_deliveries"
              label="Days closed for deliveries"
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
          >Delete
        </IButton>
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
    };
  },
  methods: {
    async submit() {
      this.$emit("submit", {
        id: this.id,
        type: "NOVO_NORDISK_V1",
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
        new_or_renewal: 1,
        patient_first_name: "John",
        patient_last_name: "Dough",
        patient_dob: "2022-03-04",
        patient_address: "123 North Pole",
        patient_city: "Baltimore",
        patient_state: "MD",
        patient_zip_code: "12332",
        patient_home_number: "444-555-1234",
        patient_mobile_number: "444-555-5555",
        patient_email: "hello@world.io",
        patient_gender: 2,
        patient_coverage: 2,
        insurance_plan_name: "PLan ABC",
        insurance_plan_member_id: "99298347",
        insurance_plan_phone_number: "444-555-1234",
        patient_insurance: [
          "insurance_employer_supplied_coverage",
          "insurance_medicare",
          "insurance_medicare_part_b",
          "insurance_va_military_benefits",
          "insurance_medicaid",
          "insurance_medicaid_low_income_subsidy",
        ],
        patient_authorized_representative: 1,
        patient_authorized_representative_name: "Steve",
        patient_authorized_representative_phone_number: "111-222-3333",
        patient_authorized_representative_family_other: 2,
        patient_authorized_representative_caregiver: "Care Giver",
        allow_electronic_income_verification: true,
        total_household_income: "10000",
        number_of_people_in_household: "4",
        number_of_dependents: "2",
        medicare_drug_coverage_consent_member_number: "1a2b3c",
        medicare_drug_coverage_consent_enrollment_year: "2023",
        hipaa_legal_representative_authorization: true,
        hipaa_legal_representative: "Hello",
        hipaa_legal_representative_relationship: "Father",
        hipaa_legal_representative_phone_number: "444 123 0980",
        tcpa_phone_number: "567 000 2343",
        program_authorization_and_certification: true,
        known_drug_allergies: "None",
        prescriber_first_name: "Saul",
        prescriber_last_name: "Goodman",
        prescriber_designation: "UNK",
        prescriber_address: "333 Beech Rd",
        prescriber_address_2: "Office 123",
        prescriber_city: "Baltimore",
        prescriber_state: "MD",
        prescriber_zip_code: "21111",
        prescriber_phone_number: "444-555-1234",
        prescriber_state_license_number: "MD444",
        prescriber_state_license: "MD",
        prescriber_fax: "333-202-1234",
        prescriber_office_contact: "Joe",
        prescriber_office_email: "hello@cool.io",
        prescriber_npi: "NPI3333",
        days_closed_for_deliveries: "all",
        fiasp_max: "AAA",
        fiasp_sig_directions: "BBB",
        fiasp_vial: "Y",
        fiasp_flex_touch: "Y",
        fiasp_cartridge: "Y",
        fiasp_qty: "CCC",
        tresiba_u100_max: "DDD",
        tresiba_u100_sig_directions: "EEE",
        tresiba_u100_vial: "Y",
        tresiba_u100_flex_touch: "Y",
        tresiba_u100_qty: "FFF",
        degludec_u100_max: "GGG",
        degludec_u100_sig_directions: "HHH",
        degludec_u100_vial: "Y",
        degludec_u100_flex_touch: "Y",
        degludec_u100_qty: "III",
        tresiba_u200_max: "JJJ",
        tresiba_u200_sig_directions: "LLL",
        tresiba_u200_flex_touch: "Y",
        tresiba_u200_qty: "MMM",
        degludec_u200_max: "NNN",
        degludec_u200_sig_directions: "OOO",
        degludec_u200_flex_touch: "Y",
        degludec_u200_qty: "PPP",
        novolog_u100_max: "QQQ",
        novolog_u100_sig_directions: "RRR",
        novolog_u100_vial: "Y",
        novolog_u100_flex_pen: "Y",
        novolog_u100_cartridge: "Y",
        novolog_u100_qty: "SSS",
        aspart_injection_u100_max: "TTT",
        aspart_injection_u100_sig_directions: "UUU",
        aspart_injection_u100_vial: "Y",
        aspart_injection_u100_flex_pen: "Y",
        aspart_injection_u100_cartridge: "Y",
        aspart_injection_u100_qty: "VVV",
        novolog_mix_70_30_u100_vial_max: "WWW",
        novolog_mix_70_30_u100_vial_sig_directions: "XXX",
        novolog_mix_70_30_u100_vial: "Y",
        novolog_mix_70_30_u100_flex_pen: "Y",
        novolog_mix_70_30_u100_vial_qty: "YYY",
        aspart_mix_70_30_u100_max: "ZZZ",
        aspart_mix_70_30_u100_sig_directions: "111",
        aspart_mix_70_30_u100_vial: "Y",
        aspart_mix_70_30_u100_flex_pen: "Y",
        aspart_mix_70_30_u100_qty: "222",
        novolin_r_u100_max: "333",
        novolin_r_u100_sig_directions: "444",
        novolin_r_u100_vial: "Y",
        novolin_r_u100_qty: "555",
        novolin_n_u100_max: "666",
        novolin_n_u100_sig_directions: "777",
        novolin_n_u100_vial: "Y",
        novolin_n_u100_qty: "888",
        novolin_70_30_u100_max: "999",
        novolin_70_30_u100_sig_directions: "000",
        novolin_70_30_u100_vial: "Y",
        novolin_70_30_u100_qty: "123",
        novo_fine_qty: "321",
        zegalogue_auto_1pack: "Y",
        zegalogue_auto_2pack: "Y",
        zegalogue_syringe_1pack: "Y",
        zegalogue_syringe_2pack: "Y",
        zegalogue_qty: "444",
        novopen_echo_pen: "Y",
        ozempic_0_25mg_0_5mg_1pack_sig_directions: "AAB",
        ozempic_0_25mg_0_5mg_1pack_qty: "BBA",
        ozempic_0_25mg_0_5mg_1pack: "Y",
        ozempic_1mg_1pack_sig_directions: "CAA",
        ozempic_1mg_1pack_qty: "AAC",
        ozempic_1mg_1pack: "Y",
        ozempic_2mg_1pack_sig_directions: "VVH",
        ozempic_2mg_1pack_qty: "HHV",
        ozempic_2mg_1pack: "Y",
        vicotoza_1_2mg_2pack_max: "OOM",
        vicotoza_1_2mg_2pack_sig_directions: "OMO",
        vicotoza_1_2mg_2pack: "Y",
        vicotoza_1_2mg_2pack_qty: "MOO",
        vicotoza_1_8mg_3pack_max: "XCC",
        vicotoza_1_8mg_3pack_sig_directions: "CXC",
        vicotoza_1_8mg_3pack: "Y",
        vicotoza_1_8mg_3pack_qty: "CCX",
        xultophy_5pack_max: "WEE",
        xultophy_5pack_sig_directions: "EWE",
        xultophy_5pack: "Y",
        xultophy_5pack_qty: "EEW",
        rybelsus_60_day_max: "GJ",
        rybelsus_60_day_sig_directions: "JG",
        rybelsus_60_day: 1,
        rybelsus_120_day_max: "LK",
        rybelsus_120_day_sig_directions: "KL",
        rybelsus_120_day: 1,
        auto_refill: "Y",
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
