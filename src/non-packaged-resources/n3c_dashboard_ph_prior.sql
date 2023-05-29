CREATE SCHEMA n3c_dashboard_ph;

CREATE TABLE n3c_dashboard_ph.cancer (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.chf (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.daibetesmetformin_agerac_csd (
    bin text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.dementia (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.demoirb_covdemo_csd (
    race text,
    sex text,
    count_hispanic text,
    count_non_hispanic text,
    count_ethnicity_unknown text
);

CREATE TABLE n3c_dashboard_ph.demoirb_demo_csd (
    race text,
    gender_concept_name text,
    count_hispanic text,
    count_non_hispanic text,
    count_ethnicity_unknown text
);

CREATE TABLE n3c_dashboard_ph.diabetes_age_cov_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_age_csd (
    age_bin text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_ageracesex_csd (
    bin text,
    sex text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_agesex_csd (
    age_bin text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_demo_all_csd (
    bin text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_demo_cov_csd (
    bin text,
    sex text,
    race text,
    ethnicity text,
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_eth_cov_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_ethnicity_csd (
    ethnicity text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_mellitus (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_mellitus_complicated (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_mellitus_type1 (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_mellitus_type2 (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_mellitus_uncomplicated (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_race_csd (
    race text,
    race_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_rc_cov_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_sev_cov_csd (
    severity_type text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_sex_cov_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_sex_csd (
    sex text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_age_all_csd (
    age_bin text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_agesex_csd (
    age_bin text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_demo_all_csd (
    bin text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_ethnicity_csd (
    ethnicity text,
    ethnicity_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_race_csd (
    race text,
    race_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_sex_csd (
    sex text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_vaccmort_all_csd (
    vaccinated integer,
    patient_death_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.ds_cancercovid_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_chfcov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_dementiacovid_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetescompcov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetesmellituscov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetestype1cov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetestype2cov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetesuncompcov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_hivcov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_liverdiseasemildcov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_liverdiseaseseverecov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_metastasiscov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_myocardialinfarctioncovid_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_paralysiscov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_pepticulcercov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_peripheralvascularcov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_pulmonarycov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_renaldiseasecov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_rheumatologiccov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_strokecov_csd (
    age_bracket text,
    gender_concept_name text,
    count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.env_agesec_all_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_agesec_cov_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_allcnt_all_csd (
    environmental_factor text,
    patient_count_exposed text
);

CREATE TABLE n3c_dashboard_ph.env_allcnt_cov_csd (
    environmental_factor text,
    patient_count_exposed text
);

CREATE TABLE n3c_dashboard_ph.env_demoageidl_all_csd (
    age_bin_ideal text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoageidl_cov_csd (
    age_bin_ideal text,
    severity text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagemin_all_csd (
    age_bin_min text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagemin_cov_csd (
    age_bin_min text,
    severity text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagescd_all_csd (
    age_bin_secondary text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagescnd_cov_csd (
    age_bin text,
    severity text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_eth_all_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_eth_cov_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_mortcnt_all_csd (
    environmental_factor text,
    total_patient_count text,
    num_of_patients_died text
);

CREATE TABLE n3c_dashboard_ph.env_mortcnt_cov_csd (
    environmental_factor text,
    total_patient_count text,
    num_of_patients_died text
);

CREATE TABLE n3c_dashboard_ph.env_rc_all_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_rc_cov_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_sev_cov_csd (
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_sx_all_csd (
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_sx_cov_csd (
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_zipcnt_all_csd (
    postal_code bigint,
    total_patients_exposed text
);

CREATE TABLE n3c_dashboard_ph.env_zipcnt_cov_csd (
    postal_code bigint,
    total_patients_exposed text
);

CREATE TABLE n3c_dashboard_ph.env_ziptemp_envcnt_all (
    postal_code integer,
    mean_precip double precision,
    mean_dewpoint double precision,
    mean_temp double precision,
    count_of_environmental_factor_in_2021 integer
);

CREATE TABLE n3c_dashboard_ph.hiv (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.liver_disease_mild (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.liver_disease_severe (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.longcov_bindatediffpos_sym_csd (
    datediff_bw_covid_and_symptom text,
    symptom text,
    count text
);

CREATE TABLE n3c_dashboard_ph.longcov_consetdemoageideal_csd (
    severity_type text,
    gender_concept_name text,
    age_bin_ideal text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.longcov_consetdemoagemin_csd (
    severity_type text,
    gender_concept_name text,
    age_bin text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.longcov_consetdemoagesec_csd (
    severity_type text,
    gender_concept_name text,
    age_bin_sec text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10_symptoms_csd (
    age_bin text,
    gender_concept_name text,
    race_concept_name text,
    ethnicity_concept_name text,
    symptom text,
    num_patients text,
    observation text
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10individualsymptoms_csd (
    age_bin text,
    gender_concept_name text,
    race text,
    ethnicity_concept_name text,
    symptom text,
    num_patients text,
    observation text,
    same_agg integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10indsymptomcts_csd (
    age_bin text,
    gender_concept_name text,
    race text,
    ethnicity_concept_name text,
    symptom text,
    count text,
    observation text,
    same_agg integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10sympcounts_csd (
    age_bin text,
    gender_concept_name text,
    race text,
    ethnicity_concept_name text,
    count text,
    observation text,
    same_agg integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10symptom_csd (
    age_bin text,
    gender_concept_name text,
    race text,
    ethnicity_concept_name text,
    symptom text,
    count text,
    observation text
);

CREATE TABLE n3c_dashboard_ph.longcov_icddemoageideal_csd (
    severity_type text,
    gender_concept_name text,
    age_bin_ideal text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icddemoagemin_csd (
    severity_type text,
    gender_concept_name text,
    age_bin text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icddemoagesec_csd (
    severity_type text,
    gender_concept_name text,
    age_bin_sec text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.longcov_symbeforeoraftercov_csd (
    symptom text,
    condition_after_covid_positive boolean,
    count integer
);

CREATE TABLE n3c_dashboard_ph.longcov_vaccsevmorsex_csd (
    vaccinated boolean,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotr_ageidl_csd (
    age_bin text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotr_sx_csd (
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agemin_csd (
    age_bin_min text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agercsxsev_csd (
    vaccinated boolean,
    bin text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agesev_csd (
    bin text,
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_cci_sev_csd (
    cci_score integer,
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_eth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_rc_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_sev_csd (
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_sevmort_csd (
    death_or_not integer,
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_vacagercsev_csd (
    vaccinated boolean,
    bin text,
    race text,
    sex text,
    severity_type text,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_vaccsev_csd (
    vaccinated boolean,
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_vaccsevmorsex_cov_csd (
    vaccinated boolean,
    sex text,
    severity_type text,
    covid_patient_death_indicator integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.meds_ageideal_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_agemin_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_agesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covageideal_csd (
    age_bin text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covagemin_csd (
    age_bin_min text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covagesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covdemo_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    ethnicity text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_covdemoageideal_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    covid_patient_death_indicator integer,
    severity_type text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_covdemoagemin_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_covdemoagesec_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_covdemomedsclassageideal_csd (
    age_bin text,
    drug_domain text,
    race text,
    gender_concept_name text,
    ethnicity text,
    severity_type text,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemomedsclassagemin_csd (
    age_bin text,
    drug_domain text,
    race text,
    gender_concept_name text,
    ethnicity text,
    severity_type text,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemomedsclassagesec_csd (
    age_bin text,
    drug_domain text,
    race text,
    gender_concept_name text,
    ethnicity text,
    severity_type text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_coveth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covmor_csd (
    covid_patient_death_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covrac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covsev_csd (
    medication_names text,
    severity_type text,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.meds_covsex_csd (
    gender_concept_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covvacc_csd (
    covid_total_vaccine integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covvacmorsevsex_csd (
    vaccinated boolean,
    sex text,
    severity_type text,
    covid_patient_death_indicator integer,
    medication_names text,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.meds_demo_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    ethnicity text,
    patient_death_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_demoageideal_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_demoagemin_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_demoagesec_csd (
    age_bin text,
    medication_names text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_demomedsclassageideal_csd (
    age_bin text,
    drug_domain text,
    race text,
    gender_concept_name text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_demomedsclassagemin_csd (
    age_bin text,
    drug_domain text,
    race text,
    gender_concept_name text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_demomedsclassagesec_csd (
    age_bin text,
    drug_domain text,
    race text,
    gender_concept_name text,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.meds_eth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_rac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_sex_csd (
    gender_concept_name text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_vacc_csd (
    total_number_of_covid_vaccine_doses integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_vaccsevsex_csd (
    vaccinated boolean,
    sex text,
    patient_death_indicator integer,
    medication_names text,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_age_cov_csd (
    concept_set_name text,
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_ageidl_all_csd (
    concept_set_name text,
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_ageidl_cov_csd (
    concept_set_name text,
    age_bin text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_ageimin_cov_csd (
    concept_set_name text,
    age_bin_min text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agemin_all_csd (
    concept_set_name text,
    age_bin_min text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesec_all_csd (
    concept_set_name text,
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesev_cov_csd (
    concept_set_name text,
    age_bin text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesx_all_csd (
    concept_set_name text,
    age_bin text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesx_cov_csd (
    concept_set_name text,
    age_bin text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_cnt_all_csd (
    concept_set_name text,
    total_people_taking_medicine_and_got_covid integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_cnt_cov_csd (
    beta_blocker text,
    total_people_taking_medicine_and_got_covid integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoageidl_all_csd (
    concept_set_name text,
    age_bin_ideal text,
    race text,
    sex text,
    total_people_took_carvedilol text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoageidl_cov_csd (
    drug_name text,
    age_bin_ideal text,
    race text,
    sex text,
    severity text,
    total_people_ook_carvedilol_got_covid text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagemin_all_csd (
    concept_set_name text,
    age_bin text,
    race text,
    sex text,
    total_people_took_carvedilol text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagemin_cov_csd (
    drug_name text,
    age_bin_min text,
    race text,
    sex text,
    severity text,
    total_people_ook_carvedilol_got_covid text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagesec_all_csd (
    concept_set_name text,
    age_bin text,
    race text,
    sex text,
    total_people_took_carvedilol text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagesec_cov_csd (
    drug_name text,
    age_bin text,
    race text,
    sex text,
    severity text,
    total_people_ook_carvedilol_got_covid text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_eth_all_csd (
    concept_set_name text,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_eth_cov_csd (
    concept_set_name text,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_rc_all_csd (
    concept_set_name text,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_rc_cov_csd (
    concept_set_name text,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_sev_cov_csd (
    concept_set_name text,
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_sx_all_csd (
    concept_set_name text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_sx_cov_csd (
    concept_set_name text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medtimeser_drug_cnt_smry_csd (
    drug_name text,
    count integer
);

CREATE TABLE n3c_dashboard_ph.medtimeser_drug_demo_csd (
    drug_name text,
    age_bracket text,
    sex text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medtimeser_drug_monthcnt_csd (
    exposure_month integer,
    exposure_year integer,
    count_per_month text,
    drug_name text
);

CREATE TABLE n3c_dashboard_ph.metastasis (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.metformin_age_cov_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_agesex_csd (
    age_bin text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_agesexrac_csd (
    bin text,
    sex text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_demo_all_csd (
    bin text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_demo_cov_csd (
    bin text,
    sex text,
    race text,
    ethnicity text,
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_eth_cov_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_race_csd (
    race text,
    race_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_rc_cov_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_sev_cov_csd (
    severity_type text,
    count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_sex_cov_csd (
    sex text,
    count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_age_cov_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_demo_cov_csd (
    bin text,
    sex text,
    race text,
    ethnicity text,
    severity_type text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_eth_cov_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_rc_cov_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_sev_cov_csd (
    severity_type text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_sx_cov_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_vaccmort_cov_csd (
    vaccinated integer,
    severity_type text,
    patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metfromin_age_csd (
    age_bin text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.metfromin_sex_csd (
    sex text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.mor_alldemomor_csd (
    vaccinated boolean,
    patient_death_indicator integer,
    age_bin text,
    gender_concept_name text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mor_bindiffdeathhos_csd (
    datediff_bw_death_and_hos text,
    count text
);

CREATE TABLE n3c_dashboard_ph.mor_icd10ageracsexgrouped_csd (
    age_bin text,
    sex text,
    race text,
    count text
);

CREATE TABLE n3c_dashboard_ph.mor_icd10demogrouped_csd (
    age_bin text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.mor_vaccsevsex_csd (
    vaccinated boolean,
    severity_type text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.myocardial_infarction (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.paralysis (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_age_csd (
    age_at_covid_binned text,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_agesex_csd (
    age_at_covid_binned text,
    gender_concept_name text,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_agesexrac_csd (
    age_at_covid_binned text,
    gender_concept_name text,
    race text,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_cciprecovutil_csd (
    cci_as_of_index_binned text,
    covid_w_pax integer,
    count integer,
    mean_precovid_visits double precision
);

CREATE TABLE n3c_dashboard_ph.pax_demoageideal_csd (
    age_bin text,
    covid_w_pax integer,
    race text,
    gender_concept_name text,
    severity_type text,
    ethnicity text,
    total_patients integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.pax_demoagemin_csd (
    age_bin text,
    covid_w_pax integer,
    race text,
    gender_concept_name text,
    severity_type text,
    ethnicity text,
    total_patients integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.pax_demoagesec_csd (
    age_bin text,
    covid_w_pax integer,
    race text,
    gender_concept_name text,
    severity_type text,
    ethnicity text,
    total_patients integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.pax_demomor_csd (
    age_bin text,
    covid_w_pax integer,
    race text,
    gender_concept_name text,
    severity_type text,
    ethnicity text,
    covid_patient_death_indicator integer,
    total_patients integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.pax_eth_csd (
    ethnicity text,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_ethsex_csd (
    ethnicity text,
    gender_concept_name text,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_hosp_csd (
    hospitalization_delta integer,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_paxadm_csd (
    pax_covid_delta integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_postcovutil_csd (
    number_of_visits_post_covid integer,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_precovutil_csd (
    number_of_visits_before_covid integer,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_rac_csd (
    race text,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_sev_csd (
    severity_type text,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_sex_csd (
    gender_concept_name text,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_sexhosp_csd (
    hospitalization_delta integer,
    covid_w_pax integer,
    sex text,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_vacc_csd (
    number_of_covid_vaccine_doses_before_or_day_of_covid integer,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_vaccmorsevsex_csd (
    vaccinated boolean,
    sex text,
    severity_type text,
    covid_patient_death_indicator integer,
    covid_w_pax integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.pax_vaccmorsevsex_csd_2 (
    vaccinated boolean,
    sex text,
    severity_type text,
    covid_patient_death_indicator integer,
    covid_w_pax integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.peptic_ulcer (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.peripheral_vascular_disease (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.pulmonary (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_age_csd (
    divisions text,
    age_bin text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_div_mortagesxrcsev_csd (
    divisions text,
    death_or_not integer,
    age_bin text,
    sex text,
    race text,
    severity_type text,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_divi_mortvacagesxrcethsev_csd (
    divisions text,
    death_or_not integer,
    vaccinated integer,
    age_bin text,
    sex text,
    race text,
    ethnicity text,
    severity_type text,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_divi_mortvacagesxrcsev_csd (
    divisions text,
    death_or_not integer,
    vaccinated integer,
    age_bin text,
    sex text,
    race text,
    severity_type text,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_rc_csd (
    divisions text,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.regcovcase_reg_mortvacagesxrcethsev_csd (
    region text,
    death_or_not integer,
    vaccinated integer,
    age_bin text,
    sex text,
    race text,
    ethnicity text,
    severity_type text,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_sev_csd (
    divisions text,
    severity_type text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.regcovcase_sev_reg_regdist_csd (
    state text,
    age_bin text,
    sex text,
    race text,
    ethnicity text,
    severity_type text,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_sx_csd (
    divisions text,
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.rein_30days_csd (
    test_date_diff_range text,
    count text,
    range_start bigint,
    range_end bigint
);

CREATE TABLE n3c_dashboard_ph.rein_covdatecounts_csd (
    c_date text,
    first_diagnosis_count text,
    original_infection_date_for_reinfected_count text,
    subsequent_test_count text
);

CREATE TABLE n3c_dashboard_ph.rein_covdatecountscohort_csd (
    c_date text,
    first_diagnosis_count text,
    original_infection_date_for_reinfected_count text,
    subsequent_test_date text,
    subsequent_test_count text
);

CREATE TABLE n3c_dashboard_ph.reints_alltstsmonth_csd (
    initial_month integer,
    initial_year integer,
    subsequent_month integer,
    subsequent_year integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.reints_monthyear_csd (
    subsequent_month integer,
    subsequent_year integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.renal_disease (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.rheumatology (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.stroke (
    age_bracket text,
    sex text,
    race text,
    ethnicity text,
    count text
);

CREATE TABLE n3c_dashboard_ph.sub_alc_csd (
    alcohol_condition text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alcageideal_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_alcagemin_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_alcagesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_alcdemoageideal_csd (
    age_bin text,
    alcohol_condition text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_alcdemoagemin_csd (
    age_bin text,
    alcohol_condition text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_alcdemoagesec_csd (
    age_bin text,
    alcohol_condition text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_alceth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_alcrac_csd (
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alcsex_csd (
    gender_concept_name text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalc_csd (
    alcohol_condition text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcageideal_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcagemin_csd (
    age_bin_min text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcagesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemo_csd (
    age_bin text,
    alcohol_condition text,
    race text,
    gender_concept_name text,
    ethnicity text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemoageideal_csd (
    age_bin text,
    alcohol_condition text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemoagemin_csd (
    age_bin text,
    alcohol_condition text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemoagesec_csd (
    age_bin text,
    alcohol_condition text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covalceth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcopi_csd (
    alcohol_condition text,
    opioids text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcrac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcsev_csd (
    alcohol_condition text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcsex_csd (
    gender_concept_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopi_csd (
    opioids text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covopiageideal_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopiagemin_csd (
    age_bin_min text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopiagesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopiagesexrcsev_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covopidemo_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    ethnicity text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covopidemoageideal_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covopidemoagesec_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covopieth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopirac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopisev_csd (
    opioids text,
    severity_type text,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covopisex_csd (
    gender_concept_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopismoalc_csd (
    opioids text,
    alcohol_condition text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmo_csd (
    smoking_status text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmoageideal_csd (
    age_bin text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmoagemin_csd (
    age_bin_min text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmoagesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemo_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemoageideal_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemoagemin_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    ethnicity text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemoagesec_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    severity_type text,
    covid_patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmoeth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmoopi_csd (
    opioids text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmorac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmosex_csd (
    gender_concept_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_demosmo_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    ethnicity text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_demosmoageideal_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_demosmoagemin_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_demosmoagesec_csd (
    age_bin text,
    smoking_status text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_opi_csd (
    opioids text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opiageideal_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opiagemin_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opiagesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opialc_csd (
    alcohol_condition text,
    opioids text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemo_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    ethnicity text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemoageideal_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemoagemin_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemoagesec_csd (
    age_bin text,
    opioids text,
    race text,
    gender_concept_name text,
    patient_death_indicator integer,
    num_patients text
);

CREATE TABLE n3c_dashboard_ph.sub_opieth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opirac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opisex_csd (
    gender_concept_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opismo_csd (
    opioids text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smo_csd (
    smoking_status text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smoageideal_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smoagemin_csd (
    age_bin text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smoagesec_csd (
    age_bin_secondary text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smoeth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smoopialc_csd (
    opioids text,
    alcohol_condition text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smorac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smosex_csd (
    gender_concept_name text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sxmortsev_csd (
    drug_name text,
    sex text,
    patient_death_indicator integer,
    severity_type text,
    patient_count text
);

