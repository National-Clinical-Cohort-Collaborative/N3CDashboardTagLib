CREATE SCHEMA n3c_dashboard_ph;

CREATE TABLE n3c_dashboard_ph.sub_alc_csd (
    alcohol_condition text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opi_csd (
    opioids text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smo_csd (
    smoking_status text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_agmin_csd (
    cms_type text,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_avg_counts_all (
    cms_type text,
    variable_type text,
    avg_in_ehr double precision,
    avg_cms double precision,
    common_cnt double precision,
    additional_in_cms double precision,
    total_avg double precision,
    percentage_increase double precision
);

CREATE TABLE n3c_dashboard_ph.cms_cnt_csd (
    cms_type text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_covid_csd (
    cms_type text,
    covid_indicator bigint,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_demo_csd (
    cms_type text,
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.cms_eth_csd (
    cms_type text,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_hosp_csd (
    cms_type text,
    covid_associated_hospitalization_indicator bigint,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_longcovid_csd (
    cms_type text,
    long_covid_indicator bigint,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_mort_csd (
    cms_type text,
    death_indicator bigint,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_rc_csd (
    cms_type text,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_rcmorvac_csd (
    cms_type text,
    race text,
    death_indicator bigint,
    severity text,
    vaccinated bigint,
    long_covid_indicator bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.cms_sev_csd (
    cms_type text,
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cms_sevhosvacmort_csd (
    cms_type text,
    severity text,
    death_indicator bigint,
    covid_associated_hospitalization_indicator bigint,
    vaccinated bigint,
    long_covid_indicator bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.cms_sx_csd (
    cms_type text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.cms_sxmorvaclc_csd (
    cms_type text,
    sex text,
    death_indicator bigint,
    covid_associated_hospitalization_indicator bigint,
    vaccinated bigint,
    long_covid_indicator bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.cms_vacc_csd (
    cms_type text,
    vaccinated bigint,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.cumavgcov_poscasebyday_cumsum_csd (
    first_diagnosis_date text,
    positive_cases text,
    cumsum_positivecases integer,
    seven_day_rolling_avg double precision
);

CREATE TABLE n3c_dashboard_ph.demo_ageidl_all_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_ageidl_cov_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_agemin_all_csd (
    death_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_agemin_all_csdd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_agemin_cov_csd (
    long_covid_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_agesec_all_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_agesec_cov_csd (
    covid_associated_hospitalization_indicator bigint,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_demo_ageidl_all_covid_csd (
    death_indicator integer,
    covid_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_ageidl_cov_csd (
    covid_patient_death_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_agemin_all_covid_csd (
    death_indicator integer,
    covid_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_agemin_cov_csd (
    covid_patient_death_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_agesnd_all_covid_csd (
    death_indicator integer,
    covid_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_agesnd_all_csd (
    death_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_agesnd_cov_csd (
    covid_patient_death_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_all_csd (
    death_indicator integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_mort_sev_vacc_all_covid_csd (
    covid_indicator integer,
    death_indicator integer,
    long_covid_indicator integer,
    vaccinated integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_demo_mort_vacc_sev_cov_csd (
    covid_patient_death_indicator integer,
    long_covid_indicator integer,
    vaccinated boolean,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_eth_all_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_eth_cov_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_grouped_cci_all_covid_csd (
    covid_indicator integer,
    comorbidity_list text,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.demo_grouped_cci_all_csd (
    comorbidity_list text,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_grouped_cci_cov_csd (
    comorbidity_list text,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_mort_all_csd (
    death_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_mort_cov_csd (
    covid_patient_death_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_rc_all_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_rc_cov_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_sev_cov_csd (
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_sx_all_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_sx_cov_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_ungrouped_cci_all_covid_csd (
    covid_indicator integer,
    comorbidity_name text,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.demo_ungrouped_cci_all_csd (
    comorbidity_name text,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_ungrouped_cci_cov_csd (
    comorbidity_name text,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_vac_status_all_covid_csd (
    covid_indicator integer,
    vaccinated integer,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.demo_vac_status_gcci_all_covid_csd (
    covid_indicator integer,
    vaccinated integer,
    comorbidity_list text,
    severity_type text,
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.demo_vacc_all_csd (
    vaccine_count integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_vacc_cov_csd (
    vaccine_count integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.demo_vacc_stat_cov_csd (
    vaccinated boolean,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_vacc_status_all_csd (
    vaccinated boolean,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_vacc_status_gcci_all_csd (
    vaccinated boolean,
    comorbidity_list text,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
);

CREATE TABLE n3c_dashboard_ph.demo_vacc_status_gcci_cov_csd (
    vaccinated boolean,
    comorbidity_list text,
    age text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count integer,
    percentage_of_total_patient text
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
    sex text,
    count_hispanic text,
    count_non_hispanic text,
    count_ethnicity_unknown text
);

CREATE TABLE n3c_dashboard_ph.diabetes_age_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_age_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_ageracesex_csd (
    age text,
    sex text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_agesex_csd (
    age text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_demo_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_demo_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_demosevlc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    long_covid_diagnosis_post_covid_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_demosevvacmorlc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    diabetes_before_after_covid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_eth_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_ethnicity_csd (
    ethnicity text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_lc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_race_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_rc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_sev_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetes_sex_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_sex_csd (
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_sxvaccmort_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    diabetes_before_after_covid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetes_vaccsevmortsexlc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    age text,
    diabetes_before_after_covid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_age_all_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_agerc_csd (
    age text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_agesex_csd (
    age text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_demo_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_ethnicity_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_lc_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_race_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_sex_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.diabetesmetformin_vaccmort_all_csd (
    sex text,
    vaccinated integer,
    patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_cancer_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_cancercovid_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_chf_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_chfcov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_dementia_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_dementiacovid_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetescompcov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetescomplicated_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_diabetesmellitus_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_diabetesmellituscov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetestype1_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_diabetestype1cov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetestype2_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_diabetestype2cov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetesuncompcov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_diabetesuncomplicated_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_hiv_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_hivcov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_liverdiseasemild_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_liverdiseasemildcov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_liverdiseasesevere_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_liverdiseaseseverecov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_metastasis_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_metastasiscov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_myocardialinfarction_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_myocardialinfarctioncovid_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_paralysis_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_paralysiscov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_pepticulcer_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_pepticulcercov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_peripheralvascularcov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_peripheralvasculardisease_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_pulmonary_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_pulmonarycov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_renaldisease_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_renaldiseasecov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_rheumatologiccov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.ds_rheumatology_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_stroke_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.ds_strokecov_csd (
    age text,
    sex text,
    patient_count text,
    observation text,
    n_observation integer
);

CREATE TABLE n3c_dashboard_ph.env_agesec_all_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_agesec_cov_csd (
    covid_indicator integer,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_allcnt_all_csd (
    metric text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_allcnt_cov_csd (
    covid_indicator integer,
    env_factor text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoageidl_all_csd (
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoageidl_cov_csd (
    covid_indicator integer,
    age text,
    severity text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagemin_all_csd (
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagemin_cov_csd (
    covid_indicator integer,
    age text,
    severity text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagescd_all_csd (
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_demoagescnd_cov_csd (
    covid_indicator integer,
    age text,
    severity text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_env_eth_all_csd (
    env_factor text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envage_all_csd (
    env_factor text,
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envage_csd (
    covid_indicator integer,
    env_factor text,
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envagercsx_all_csd (
    env_factor text,
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envagesxrcsev_cov_csd (
    covid_indicator integer,
    env_factor text,
    age text,
    race text,
    sex text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_enveth_cov_csd (
    covid_indicator integer,
    env_factor text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envmort_cov_csd (
    covid_indicator integer,
    env_factor text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envmort_csd (
    env_factor text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envrc_all_csd (
    env_factor text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envrc_cov_csd (
    covid_indicator integer,
    env_factor text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envsev_cov (
    covid_indicator integer,
    env_factor text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envsx_all_csd (
    env_factor text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envsx_cov_csd (
    covid_indicator integer,
    env_factor text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envsxmortvac_all_csd (
    covid_indicator integer,
    covid_patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    vaccinated integer,
    sex text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envsxvacmortsev_cov_csd (
    covid_indicator integer,
    env_factor text,
    covid_patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    vaccinated integer,
    sex text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_envvac_cov_csd (
    covid_indicator integer,
    env_factor text,
    vaccinated integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_eth_all_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_eth_cov_csd (
    covid_indicator integer,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_mortcnt_all_csd (
    env_factor text,
    patient_count text,
    patient_count_died text
);

CREATE TABLE n3c_dashboard_ph.env_mortcnt_cov_csd (
    covid_indicator integer,
    env_factor text,
    patient_count text,
    patient_count_died text
);

CREATE TABLE n3c_dashboard_ph.env_rc_all_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_rc_cov_csd (
    covid_indicator integer,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_sev_cov_csd (
    covid_indicator integer,
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_stcntctprmlist_cnt_csd (
    covid_indicator integer,
    state text,
    county text,
    param_list text,
    patient_count_of_this_city text,
    patient_count_died text,
    patient_count_died_cause_covid text
);

CREATE TABLE n3c_dashboard_ph.env_stcntctzipprmlist_cnt_csd (
    covid_indicator integer,
    statename text,
    countyname text,
    cityname text,
    postal_code text,
    ruca1_value text,
    param_list text,
    patient_count_of_this_city text,
    patient_count_died text,
    patient_count_died_cause_covid text
);

CREATE TABLE n3c_dashboard_ph.env_sx_all_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.env_sx_cov_csd (
    covid_indicator integer,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_zipcnt_all_csd (
    postal_code text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_zipcnt_cov_csd (
    covid_indicator integer,
    postal_code text,
    env_factor_list text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.env_ziptemp_envcnt_all (
    postal_code text,
    mean_precip double precision,
    mean_dewpoint double precision,
    mean_temp double precision,
    count_of_environmental_factor_in_2021 integer
);

CREATE VIEW n3c_dashboard_ph.environmental_temp AS
 SELECT bar.code,
    bar.statename,
    bar.countyname,
    sum(bar.non_count) AS non_count,
    sum(bar.covid_count) AS covid_count,
    sum(bar.patient_count_died) AS patient_count_died,
    sum(bar.patient_count_died_cause_covid) AS patient_count_died_cause_covid
   FROM ( SELECT state_code.code,
            foo2.statename,
            foo2.countyname,
            COALESCE(foo2.non_count, 0) AS non_count,
            COALESCE(foo2.covid_count, 0) AS covid_count,
            foo2.patient_count_died,
            foo2.patient_count_died_cause_covid
           FROM (( SELECT env_stcntctzipprmlist_cnt_csd.statename,
                    env_stcntctzipprmlist_cnt_csd.countyname,
                        CASE
                            WHEN (env_stcntctzipprmlist_cnt_csd.patient_count_of_this_city = '<20'::text) THEN 1
                            ELSE (env_stcntctzipprmlist_cnt_csd.patient_count_of_this_city)::integer
                        END AS non_count,
                        CASE
                            WHEN (env_stcntctzipprmlist_cnt_csd.patient_count_died = '<20'::text) THEN 1
                            ELSE (env_stcntctzipprmlist_cnt_csd.patient_count_died)::integer
                        END AS patient_count_died,
                        CASE
                            WHEN (env_stcntctzipprmlist_cnt_csd.patient_count_died_cause_covid = '<20'::text) THEN 1
                            ELSE (env_stcntctzipprmlist_cnt_csd.patient_count_died_cause_covid)::integer
                        END AS patient_count_died_cause_covid
                   FROM n3c_dashboard_ph.env_stcntctzipprmlist_cnt_csd
                  WHERE (env_stcntctzipprmlist_cnt_csd.covid_indicator = 0)) foo
             FULL JOIN ( SELECT env_stcntctzipprmlist_cnt_csd.statename,
                    env_stcntctzipprmlist_cnt_csd.countyname,
                        CASE
                            WHEN (env_stcntctzipprmlist_cnt_csd.patient_count_of_this_city = '<20'::text) THEN 1
                            ELSE (env_stcntctzipprmlist_cnt_csd.patient_count_of_this_city)::integer
                        END AS covid_count,
                        CASE
                            WHEN (env_stcntctzipprmlist_cnt_csd.patient_count_died = '<20'::text) THEN 1
                            ELSE (env_stcntctzipprmlist_cnt_csd.patient_count_died)::integer
                        END AS patient_count_died,
                        CASE
                            WHEN (env_stcntctzipprmlist_cnt_csd.patient_count_died_cause_covid = '<20'::text) THEN 1
                            ELSE (env_stcntctzipprmlist_cnt_csd.patient_count_died_cause_covid)::integer
                        END AS patient_count_died_cause_covid
                   FROM n3c_dashboard_ph.env_stcntctzipprmlist_cnt_csd
                  WHERE (env_stcntctzipprmlist_cnt_csd.covid_indicator = 1)) bar_1 USING (statename, countyname, patient_count_died, patient_count_died_cause_covid)) foo2,
            n3c_maps.state_code
          WHERE (foo2.statename = state_code.state)) bar
  GROUP BY bar.code, bar.statename, bar.countyname;

CREATE TABLE n3c_dashboard_ph.longcov_bindatediffpos_sym_csd (
    datediff_bw_covid_and_symptom text,
    symptom text,
    count text
);

CREATE TABLE n3c_dashboard_ph.longcov_consetdemoageideal_csd (
    race text,
    sex text,
    age text,
    ethnicity text,
    vaccinated boolean,
    covid_patient_death_indicator integer,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.longcov_consetdemoagemin_csd (
    race text,
    sex text,
    age text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.longcov_consetdemoagesec_csd (
    race text,
    sex text,
    age text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.longcov_consetvaccmorrac_csd (
    vaccinated boolean,
    covid_patient_death_indicator integer,
    race text,
    severity text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10_symptoms_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    symptom text,
    patient_count text,
    long_covid_indicator integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10individualsymptoms_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    symptom text,
    patient_count text,
    long_covid_indicator integer,
    same_agg integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10indsymptomcts_csd (
    covid_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    symptom text,
    patient_count text,
    long_covid_indicator integer,
    same_agg integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10sympcounts_csd (
    covid_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text,
    long_covid_indicator integer,
    same_agg integer
);

CREATE TABLE n3c_dashboard_ph.longcov_icd10symptom_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    symptom text,
    covid_indicator integer,
    long_covid_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.longcov_icddemoageideal_csd (
    race text,
    sex text,
    age text,
    ethnicity text,
    vaccinated boolean,
    covid_patient_death_indicator integer,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.longcov_icddemoagemin_csd (
    race text,
    sex text,
    age text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.longcov_icddemoagesec_csd (
    race text,
    sex text,
    age text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.longcov_icdvaccmorrac_csd (
    vaccinated boolean,
    covid_patient_death_indicator integer,
    race text,
    severity text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.longcov_symbeforeoraftercov_csd (
    symptom text,
    condition_after_covid_positive boolean,
    count integer
);

CREATE TABLE n3c_dashboard_ph.longcov_vaccsevmorsex_csd (
    vaccinated boolean,
    sex text,
    severity text,
    covid_patient_death_indicator bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckageideal_csd (
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckagemin_csd (
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckagercsxsev_csd (
    vaccinated boolean,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckagesec_csd (
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckagesev_csd (
    age text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckccisev_csd (
    cci_score bigint,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_mercketh_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckrac_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_mercksev_csd (
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_mercksevmort_csd (
    covid_patient_death_indicator integer,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_mercksex_csd (
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckvacagercsev_csd (
    vaccinated boolean,
    age text,
    race text,
    sex text,
    severity text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_merckvaccsev_csd (
    vaccinated boolean,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotr_ageidl_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotr_sx_csd (
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agemin_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agercsxsev_csd (
    vaccinated boolean,
    age text,
    race text,
    sex text,
    ethnicity text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agesec_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_agesev_csd (
    age text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_cci_sev_csd (
    cci_score text,
    severity text,
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
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_sevmort_csd (
    covid_patient_death_indicator integer,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_vacagercsev_csd (
    vaccinated boolean,
    age text,
    race text,
    sex text,
    severity text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_sotro_vaccsev_csd (
    vaccinated boolean,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.med_snpsht_vaccsevmorsex_cov_csd (
    vaccinated boolean,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_ageideal_csd (
    age text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_agemin_csd (
    age text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_agesec_csd (
    age text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covageideal_csd (
    age text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covagemin_csd (
    age text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covagesec_csd (
    age text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covdemo_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    covid_patient_death_indicator integer,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemoageideal_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    covid_patient_death_indicator integer,
    severity text,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemoagemin_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemoagesec_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemomedsclassageideal_csd (
    age text,
    drug_domain text,
    medication_names text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemomedsclassagemin_csd (
    age text,
    drug_domain text,
    medication_names text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_covdemomedsclassagesec_csd (
    age text,
    drug_domain text,
    medication_names text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_coveth_csd (
    ethnicity text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covmor_csd (
    covid_patient_death_indicator integer,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covrac_csd (
    race text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covsev_csd (
    drug_name text,
    severity text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covsex_csd (
    sex text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_covvacc_csd (
    vaccine_count integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_covvacmorsevsex_csd (
    vaccinated boolean,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    drug_name text,
    drug_domain text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_demo_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    ethnicity text,
    death_indicator integer,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_demoageideal_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    death_indicator integer,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_demoagemin_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    death_indicator integer,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_demoagesec_csd (
    age text,
    drug_name text,
    race text,
    sex text,
    death_indicator integer,
    drug_domain text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_demomedsclassageideal_csd (
    age text,
    drug_domain text,
    medication_names text,
    race text,
    sex text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_demomedsclassagemin_csd (
    age text,
    drug_domain text,
    medication_names text,
    race text,
    sex text,
    patient_count text,
    total_patient_count bigint
);

CREATE TABLE n3c_dashboard_ph.meds_demomedsclassagesec_csd (
    age text,
    drug_domain text,
    medication_names text,
    race text,
    sex text,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_eth_csd (
    ethnicity text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_rac_csd (
    race text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_sex_csd (
    sex text,
    medication_names text,
    drug_domain text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.meds_vacc_csd (
    vaccine_count integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.meds_vaccsevsex_csd (
    vaccinated boolean,
    age_bin text,
    death_indicator integer,
    drug_name text,
    drug_domain text,
    covid_indicator integer,
    long_covid_indicator integer,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_age_cov_csd (
    drug_name text,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_ageidl_all_csd (
    drug_name text,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_ageidl_cov_csd (
    drug_name text,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_ageimin_cov_csd (
    drug_name text,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agemin_all_csd (
    drug_name text,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesec_all_csd (
    drug_name text,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesev_cov_csd (
    drug_name text,
    age text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesx_all_csd (
    drug_name text,
    age text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_agesx_cov_csd (
    drug_name text,
    age text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_cnt_all_csd (
    drug_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_cnt_cov_csd (
    drug_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoageidl_all_csd (
    drug_name text,
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoageidl_cov_csd (
    drug_name text,
    vaccinated integer,
    age text,
    race text,
    sex text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagemin_all_csd (
    drug_name text,
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagemin_cov_csd (
    drug_name text,
    vaccinated integer,
    age text,
    race text,
    sex text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagesec_all_csd (
    drug_name text,
    age text,
    race text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_demoagesec_cov_csd (
    drug_name text,
    vaccinated integer,
    age text,
    race text,
    sex text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_eth_all_csd (
    drug_name text,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_eth_cov_csd (
    drug_name text,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_rc_all_csd (
    drug_name text,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_rc_cov_csd (
    drug_name text,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_sev_cov_csd (
    drug_name text,
    cci_score text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_sx_all_csd (
    drug_name text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medsnapsht_bb_sx_cov_csd (
    drug_name text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medtimeser_drug_cnt_smry_csd (
    drug_name text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.medtimeser_drug_demo_csd (
    drug_name text,
    age text,
    sex text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.medtimeser_drug_monthcnt_csd (
    exposure_month integer,
    exposure_year integer,
    patient_count text,
    drug_name text
);

CREATE TABLE n3c_dashboard_ph.metformin_age_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_age_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_agesex_csd (
    age text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_agesexrac_csd (
    age text,
    sex text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_demo_all_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_demo_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_demosevlc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    long_covid_diagnosis_post_covid_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_demosevvacmorlc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    metformin_before_after_covid text,
    cci_score_range text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_eth_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_hosp_csd (
    hospitalization_delta integer,
    metformin_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_lc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_race_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_rc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_sev_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_sex_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_sex_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformin_sxvaccmort_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    age text,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    metformin_before_after_covid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformin_vaccsevmortsexlc_cov_csd (
    covid_indicator integer,
    metformin_indicator integer,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    age text,
    metformin_before_after_covid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_age_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_demo_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_demosev_lc_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    long_covid_diagnosis_post_covid_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_demosevvacmorlc_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    metformin_before_after_covid text,
    diabetes_before_after_covid text,
    metformindiabetes_before_after_covid text,
    cci_score_range text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_eth_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_rc_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_sev_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_sx_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_vaccmort_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    age text,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    metformin_before_after_covid text,
    diabetes_before_after_covid text,
    metformindiabetes_before_after_covid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_vaccsevmortlc_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    metformin_before_after_covid text,
    diabetes_before_after_covid text,
    metformindiabetes_before_after_covid text,
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.metformindiabetes_vaccsevmortsexlc_cov_csd (
    covid_indicator integer,
    diabetes_indicator integer,
    metformin_indicator integer,
    vaccinated integer,
    severity text,
    patient_death_indicator integer,
    long_covid_diagnosis_post_covid_indicator integer,
    metformin_before_after_covid text,
    diabetes_before_after_covid text,
    metformindiabetes_before_after_covid text,
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mh_all_patient_age_ideal_demographics_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    death_mortality_indicator integer,
    concept_set_name text,
    covid_indicator integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.mh_all_patient_age_min_demographics_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    death_mortality_indicator integer,
    concept_set_name text,
    covid_indicator integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.mh_all_patient_age_sec_demographics_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    death_mortality_indicator integer,
    concept_set_name text,
    covid_indicator integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.mh_allcnt_all_csd (
    metric text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.mh_sexvaccmor_csd (
    sex text,
    vaccinated integer,
    death_mortality_indicator integer,
    concept_set_name text,
    covid_indicator integer,
    long_covid_indicator integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.mh_sexvaccmor_other_csd (
    sex text,
    vaccinated integer,
    death_mortality_indicator integer,
    concept_set_name text,
    covid_indicator integer,
    long_covid_indicator integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.mor_all_cnt (
    type text,
    total_patient integer,
    ehr_death_table integer,
    pprl_3_source_mortality_and_death integer,
    additional_in_pprl integer,
    cms_mortality integer,
    aditional_cms_not_in_mort_death integer,
    additional_in_mort_death_not_in_cms integer,
    total_mort integer,
    percentage_increase double precision
);

CREATE TABLE n3c_dashboard_ph.mor_alldemomor_csd (
    vaccinated boolean,
    death_indicator integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mor_bindiffdeathhos_csd (
    datediff_bw_death_and_hos text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mor_demosev_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mor_icd10ageracsexgrouped_csd (
    age text,
    sex text,
    race text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mor_icd10demogrouped_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mor_vaccsevsex_csd (
    vaccinated boolean,
    severity text,
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mort_agemin_all_csdd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.mort_eth_all_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.mort_mort_all_csd (
    death_indicator integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.mort_rc_all_csd (
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.mort_rcvachoslc_csd (
    race text,
    vaccinated integer,
    severity text,
    long_covid_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.mort_sx_all_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.mort_vacc_all_csd (
    vaccinated integer,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.only_one_other_condition_csd (
    list_of_conditions text,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.pax_age_csd (
    age text,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_agesex_csd (
    age text,
    sex text,
    covid_w_pax integer,
    count text
);

CREATE TABLE n3c_dashboard_ph.pax_agesexrac_csd (
    age text,
    sex text,
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
    age text,
    covid_w_pax integer,
    race text,
    sex text,
    severity text,
    ethnicity text,
    long_covid_indicator integer,
    total_patient_count bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.pax_demoagemin_csd (
    age text,
    covid_w_pax integer,
    race text,
    sex text,
    severity text,
    ethnicity text,
    long_covid_indicator integer,
    total_patient_count bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.pax_demoagesec_csd (
    age text,
    covid_w_pax integer,
    race text,
    sex text,
    severity text,
    ethnicity text,
    long_covid_indicator integer,
    total_patient_count bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.pax_demomor_csd (
    age text,
    covid_w_pax integer,
    race text,
    sex text,
    severity text,
    ethnicity text,
    covid_patient_death_indicator integer,
    total_patient_count bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.pax_eth_csd (
    ethnicity text,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_ethsex_csd (
    ethnicity text,
    sex text,
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
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_sev_csd (
    severity text,
    covid_w_pax integer,
    count integer
);

CREATE TABLE n3c_dashboard_ph.pax_sex_csd (
    sex text,
    covid_w_pax integer,
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
    severity text,
    covid_patient_death_indicator integer,
    covid_w_pax integer,
    patient_count text,
    total_patient_count integer
);

CREATE TABLE n3c_dashboard_ph.regcovcase_age_csd (
    divisions text,
    age text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_div_mortagesxrcsev_csd (
    divisions text,
    covid_patient_death_indicator integer,
    age text,
    sex text,
    race text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_divi_mortvacagesxrcethsev_csd (
    divisions text,
    covid_patient_death_indicator integer,
    vaccinated integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_divi_mortvacagesxrcsev_csd (
    divisions text,
    covid_patient_death_indicator integer,
    vaccinated integer,
    age text,
    sex text,
    race text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_rc_csd (
    divisions text,
    race text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.regcovcase_reg_mortvacagesxrcethsev_csd (
    region text,
    covid_patient_death_indicator integer,
    vaccinated integer,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_sev_csd (
    divisions text,
    severity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.regcovcase_sev_reg_regdist_csd (
    state text,
    age text,
    sex text,
    race text,
    ethnicity text,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.regcovcase_sx_csd (
    divisions text,
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.rein_30days_csd (
    test_date_diff_range text,
    patient_count text,
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
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.reints_monthyear_csd (
    subsequent_month integer,
    subsequent_year integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alcageideal_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_alcagemin_csd (
    state text,
    county text,
    alcohol_indicator integer,
    smoking_indicator integer,
    opioid_indicator integer,
    cannabis_indicator integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.sub_alcagesec_csd (
    state text,
    county text,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.sub_alcdemoageideal_csd (
    age text,
    alcohol_condition text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alcdemoagemin_csd (
    age text,
    alcohol_condition text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alcdemoagesec_csd (
    age text,
    alcohol_condition text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alceth_csd (
    opioids text,
    anti_opioid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alcrac_csd (
    opioids text,
    anti_opioid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_alcsex_csd (
    opioids text,
    anti_opioid text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_all_demo_age_ideal_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    alcohol_indicator integer,
    smoking_indicator integer,
    opioid_indicator integer,
    cannabis_indicator integer,
    covid_indicator integer,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.sub_all_demo_age_min_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    alcohol_indicator integer,
    smoking_indicator integer,
    opioid_indicator integer,
    cannabis_indicator integer,
    covid_indicator integer,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.sub_all_demo_age_sec_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    alcohol_indicator integer,
    smoking_indicator integer,
    opioid_indicator integer,
    cannabis_indicator integer,
    covid_indicator integer,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.sub_all_sexvaccmorlc_csd (
    sex text,
    vaccinated integer,
    death_mortality_indicator integer,
    long_covid_indicator integer,
    covid_indicator integer,
    alcohol_indicator integer,
    opioid_indicator integer,
    smoking_indicator integer,
    cannabis_indicator integer,
    num_patients text,
    total_patients integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalc_csd (
    alcohol_condition text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcageideal_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcagemin_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcagesec_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemo_csd (
    age text,
    alcohol_condition text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemoageideal_csd (
    age text,
    alcohol_condition text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemoagemin_csd (
    age text,
    alcohol_condition text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcdemoagesec_csd (
    age text,
    alcohol_condition text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
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
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covalcsex_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covalcsmo_csd (
    alcohol_condition text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covopi_csd (
    opioids text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covopiageideal_csd (
    no_of_opioid integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopiagemin_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopiagesec_csd (
    list_of_opiod text,
    no_of_opioid integer,
    patient_count text,
    total_patient integer
);

CREATE TABLE n3c_dashboard_ph.sub_covopiagesexrcsev_csd (
    age text,
    opioids text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covopidemo_csd (
    age text,
    opioids text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covopidemoageideal_csd (
    age text,
    opioids text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covopidemoagesec_csd (
    age text,
    opioids text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
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
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covopisex_csd (
    sex text,
    patient_count text
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
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmoagemin_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmoagesec_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemo_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemoageideal_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemoagemin_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    ethnicity text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_covsmodemoagesec_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    severity text,
    covid_patient_death_indicator integer,
    patient_count text
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
    sex text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_demosmo_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    ethnicity text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_demosmoageideal_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_demosmoagemin_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_demosmoagesec_csd (
    age text,
    smoking_status text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opiageideal_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    alcohol_indicator integer,
    smoking_indicator integer,
    opioid_indicator integer,
    cannabis_indicator integer,
    confirmed_covid_patient bigint,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.sub_opiagemin_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    alcohol_indicator integer,
    smoking_indicator integer,
    opioid_indicator integer,
    cannabis_indicator integer,
    confirmed_covid_patient bigint,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.sub_opiagesec_csd (
    age_bin text,
    race text,
    sex text,
    ethnicity text,
    alcohol_indicator integer,
    smoking_indicator integer,
    opioid_indicator integer,
    cannabis_indicator integer,
    confirmed_covid_patient bigint,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.sub_opialc_csd (
    alcohol_condition text,
    opioids text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemo_csd (
    age text,
    opioids text,
    race text,
    sex text,
    ethnicity text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemoageideal_csd (
    age text,
    opioids text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemoagemin_csd (
    age text,
    opioids text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opidemoagesec_csd (
    age text,
    opioids text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_opieth_csd (
    ethnicity text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opirac_csd (
    sex text,
    vaccinated bigint,
    death_mortality_indicator bigint,
    long_covid_indicator bigint,
    confirmed_covid_patient bigint,
    alcohol_indicator integer,
    opioid_indicator integer,
    smoking_indicator integer,
    cannabis_indicator integer,
    num_patients text,
    total_patients bigint
);

CREATE TABLE n3c_dashboard_ph.sub_opisex_csd (
    sex text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_opismo_csd (
    opioids text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smoageideal_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smoagemin_csd (
    age text,
    patient_count integer
);

CREATE TABLE n3c_dashboard_ph.sub_smoagesec_csd (
    age text,
    cannabioids text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smoalc_csd (
    alcohol_condition text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smoeth_csd (
    age text,
    cannabioids text,
    race text,
    sex text,
    ethnicity text,
    covid_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smoopialc_csd (
    opioids text,
    alcohol_condition text,
    smoking_status text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smorac_csd (
    age text,
    cannabioids text,
    race text,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.sub_smosex_csd (
    vaccinated boolean,
    cannabioids text,
    covid_indicator integer,
    sex text,
    death_indicator integer,
    patient_count text
);

CREATE VIEW n3c_dashboard_ph.substance_alc_opi_all AS
 SELECT foo.alcohol_condition,
    foo.opioids,
    sub_opialc_csd.patient_count AS all_display,
        CASE
            WHEN (sub_opialc_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_opialc_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT alcohol_map.secondary AS alcohol_condition,
            opioid_map.secondary AS opioids
           FROM n3c_dashboard.alcohol_map,
            n3c_dashboard.opioid_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_opialc_csd USING (alcohol_condition, opioids));

CREATE VIEW n3c_dashboard_ph.substance_alc_opi_covid AS
 SELECT foo.alcohol_condition,
    foo.opioids,
    sub_covalcopi_csd.patient_count AS covid_display,
        CASE
            WHEN (sub_covalcopi_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_covalcopi_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT alcohol_map.secondary AS alcohol_condition,
            opioid_map.secondary AS opioids
           FROM n3c_dashboard.alcohol_map,
            n3c_dashboard.opioid_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_covalcopi_csd USING (alcohol_condition, opioids));

CREATE VIEW n3c_dashboard_ph.substance_alc_opi_combined AS
 SELECT foo.alcohol_condition,
    foo.opioids,
    foo.all_display,
    foo.all_count,
    bar.covid_display,
    bar.covid_count
   FROM (( SELECT substance_alc_opi_all.alcohol_condition,
            substance_alc_opi_all.opioids,
            substance_alc_opi_all.all_display,
            substance_alc_opi_all.patient_count AS all_count
           FROM n3c_dashboard_ph.substance_alc_opi_all) foo
     JOIN ( SELECT substance_alc_opi_covid.alcohol_condition,
            substance_alc_opi_covid.opioids,
            substance_alc_opi_covid.covid_display,
            substance_alc_opi_covid.patient_count AS covid_count
           FROM n3c_dashboard_ph.substance_alc_opi_covid) bar USING (alcohol_condition, opioids));

CREATE VIEW n3c_dashboard_ph.substance_alc_smo_all AS
 SELECT foo.alcohol_condition,
    foo.smoking_status,
    sub_smoalc_csd.patient_count AS all_display,
        CASE
            WHEN (sub_smoalc_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_smoalc_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT alcohol_map.secondary AS alcohol_condition,
            smoking_map.secondary AS smoking_status
           FROM n3c_dashboard.alcohol_map,
            n3c_dashboard.smoking_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_smoalc_csd USING (alcohol_condition, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_alc_smo_covid AS
 SELECT foo.alcohol_condition,
    foo.smoking_status,
    sub_covalcsmo_csd.patient_count AS covid_display,
        CASE
            WHEN (sub_covalcsmo_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_covalcsmo_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT alcohol_map.secondary AS alcohol_condition,
            smoking_map.secondary AS smoking_status
           FROM n3c_dashboard.alcohol_map,
            n3c_dashboard.smoking_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_covalcsmo_csd USING (alcohol_condition, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_alc_smo_combined AS
 SELECT foo.alcohol_condition,
    foo.smoking_status,
    foo.all_display,
    foo.all_count,
    bar.covid_display,
    bar.covid_count
   FROM (( SELECT substance_alc_smo_all.alcohol_condition,
            substance_alc_smo_all.smoking_status,
            substance_alc_smo_all.all_display,
            substance_alc_smo_all.patient_count AS all_count
           FROM n3c_dashboard_ph.substance_alc_smo_all) foo
     JOIN ( SELECT substance_alc_smo_covid.alcohol_condition,
            substance_alc_smo_covid.smoking_status,
            substance_alc_smo_covid.covid_display,
            substance_alc_smo_covid.patient_count AS covid_count
           FROM n3c_dashboard_ph.substance_alc_smo_covid) bar USING (alcohol_condition, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_all AS
 SELECT foo.alcohol_condition,
    foo.opioids,
    foo.smoking_status,
        CASE
            WHEN (sub_smoopialc_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_smoopialc_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT alcohol_map.secondary AS alcohol_condition,
            opioid_map.secondary AS opioids,
            smoking_map.secondary AS smoking_status
           FROM n3c_dashboard.alcohol_map,
            n3c_dashboard.opioid_map,
            n3c_dashboard.smoking_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_smoopialc_csd USING (alcohol_condition, opioids, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_covid AS
 SELECT foo.alcohol_condition,
    foo.opioids,
    foo.smoking_status,
        CASE
            WHEN (sub_covopismoalc_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_covopismoalc_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT alcohol_map.secondary AS alcohol_condition,
            opioid_map.secondary AS opioids,
            smoking_map.secondary AS smoking_status
           FROM n3c_dashboard.alcohol_map,
            n3c_dashboard.opioid_map,
            n3c_dashboard.smoking_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_covopismoalc_csd USING (alcohol_condition, opioids, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_combined AS
 SELECT foo.alcohol_condition,
    foo.opioids,
    foo.smoking_status,
    foo.all_count,
    bar.covid_count
   FROM (( SELECT substance_all.alcohol_condition,
            substance_all.opioids,
            substance_all.smoking_status,
            substance_all.patient_count AS all_count
           FROM n3c_dashboard_ph.substance_all) foo
     JOIN ( SELECT substance_covid.alcohol_condition,
            substance_covid.opioids,
            substance_covid.smoking_status,
            substance_covid.patient_count AS covid_count
           FROM n3c_dashboard_ph.substance_covid) bar USING (alcohol_condition, opioids, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_opi_smo_all AS
 SELECT foo.opioids,
    foo.smoking_status,
    sub_opismo_csd.patient_count AS all_display,
        CASE
            WHEN (sub_opismo_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_opismo_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT opioid_map.secondary AS opioids,
            smoking_map.secondary AS smoking_status
           FROM n3c_dashboard.opioid_map,
            n3c_dashboard.smoking_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_opismo_csd USING (opioids, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_opi_smo_covid AS
 SELECT foo.opioids,
    foo.smoking_status,
    sub_covsmoopi_csd.patient_count AS covid_display,
        CASE
            WHEN (sub_covsmoopi_csd.patient_count = '<20'::text) THEN 1
            ELSE (COALESCE(sub_covsmoopi_csd.patient_count, '0'::text))::integer
        END AS patient_count
   FROM (( SELECT opioid_map.secondary AS opioids,
            smoking_map.secondary AS smoking_status
           FROM n3c_dashboard.opioid_map,
            n3c_dashboard.smoking_map) foo
     LEFT JOIN n3c_dashboard_ph.sub_covsmoopi_csd USING (opioids, smoking_status));

CREATE VIEW n3c_dashboard_ph.substance_opi_smo_combined AS
 SELECT foo.opioids,
    foo.smoking_status,
    foo.all_display,
    foo.all_count,
    bar.covid_display,
    bar.covid_count
   FROM (( SELECT substance_opi_smo_all.opioids,
            substance_opi_smo_all.smoking_status,
            substance_opi_smo_all.all_display,
            substance_opi_smo_all.patient_count AS all_count
           FROM n3c_dashboard_ph.substance_opi_smo_all) foo
     JOIN ( SELECT substance_opi_smo_covid.opioids,
            substance_opi_smo_covid.smoking_status,
            substance_opi_smo_covid.covid_display,
            substance_opi_smo_covid.patient_count AS covid_count
           FROM n3c_dashboard_ph.substance_opi_smo_covid) bar USING (opioids, smoking_status));

CREATE TABLE n3c_dashboard_ph.sxmortsev_csd (
    drug_name text,
    sex text,
    death_indicator integer,
    severity text,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.viral_agmin_csd (
    age text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_avg (
    cms_type text,
    avg_device_cnt double precision,
    avg_condition_cnt double precision,
    avg_total_visit_cnt double precision,
    avg_drug_cnt double precision,
    avg_labs_cnt double precision,
    avg_procedure_cnt double precision
);

CREATE TABLE n3c_dashboard_ph.viral_cnt_csd (
    who_lineage text,
    covid_indicator bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.viral_demo_csd (
    age text,
    sex text,
    race text,
    ethnicity text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_eth_csd (
    ethnicity text,
    patient_count integer,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_hosp_csd (
    covid_associated_hospitalization_indicator bigint,
    patient_count integer,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_longcovid_csd (
    long_covid_indicator integer,
    patient_count integer,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_mort_csd (
    who_lineage text,
    covid_indicator bigint,
    death_mortality_indicator bigint,
    patient_count text
);

CREATE TABLE n3c_dashboard_ph.viral_pango_cnt_csd (
    pango_lineage text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_pango_grp_csd (
    who_lineage text,
    pango_sublineage text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_pango_sub_csd (
    pango_lineage text,
    pango_sublineage text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_rc_csd (
    race text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_rcvacmor_csd (
    race text,
    severity text,
    vaccinated integer,
    death_indicator integer,
    long_covid_indicator integer,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_sev_csd (
    severity text,
    patient_count integer,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_sevvacmorthos_csd (
    sex text,
    severity text,
    vaccinated integer,
    death_indicator integer,
    long_covid_indicator integer,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_sub_cnt_csd (
    pango_sublineage text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_sx_csd (
    sex text,
    patient_count integer,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_total_csd (
    covid_indicator integer,
    patient_count integer,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_typesev_csd (
    who_lineage text,
    covid_indicator integer,
    severity text,
    patient_count text,
    total_viral_patient integer
);

CREATE TABLE n3c_dashboard_ph.viral_vacc_csd (
    vaccinated integer,
    patient_count integer,
    total_viral_patient integer
);

