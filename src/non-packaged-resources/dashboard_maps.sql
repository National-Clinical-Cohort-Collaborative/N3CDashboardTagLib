-- maps

create view n3c_dashboard.alcohol_map as 
select alcohol_condition as secondary, alcohol_condition as secondary_abbrev, row_number() over(order by alcohol_condition) as secondary_seqnum
from (select distinct alcohol_condition from n3c_dashboard_ph.sub_alc_csd) as foo
;

create view n3c_dashboard.opioid_map as 
select opioids as secondary, opioids as secondary_abbrev, row_number() over(order by opioids) as secondary_seqnum
from (select distinct opioids from n3c_dashboard_ph.sub_opi_csd) as foo
;

create view n3c_dashboard.smoking_map as 
select smoking_status as secondary, smoking_status as secondary_abbrev, row_number() over(order by smoking_status) as secondary_seqnum
from (select distinct smoking_status from n3c_dashboard_ph.sub_smo_csd) as foo
;


-- alcohol / opioids

create view n3c_dashboard_ph.substance_alc_opi_all as
select
	alcohol_condition,
	opioids,
	patient_count as all_display,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	alcohol_map.alcohol_condition,opioid_map.opioids
from n3c_dashboard.alcohol_map, n3c_dashboard.opioid_map) as foo
natural left join
n3c_dashboard_ph.sub_opialc_csd
;

create view n3c_dashboard_ph.substance_alc_opi_covid as
select
	alcohol_condition,
	opioids,
	patient_count as covid_display,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	alcohol_condition, opioids
from n3c_dashboard.alcohol_map, n3c_dashboard.opioid_map) as foo
natural left join
n3c_dashboard_ph.sub_covalcopi_csd
;

create view n3c_dashboard_ph.substance_alc_opi_combined as
select * from
(select alcohol_condition, opioids, all_display, patient_count as all_count from n3c_dashboard_ph.substance_alc_opi_all) as foo
natural join
(select alcohol_condition, opioids, covid_display, patient_count as covid_count from n3c_dashboard_ph.substance_alc_opi_covid) as bar
;

-- alcohol / smoking - currently missing ******************

create view n3c_dashboard_ph.substance_alc_smo_all as
select
	alcohol_condition,
	smoking_status,
	patient_count as all_display,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	alcohol_condition,smoking_map.secondary as smoking_status
from n3c_dashboard.alcohol_map, n3c_dashboard.smoking_map) as foo
natural left join
n3c_dashboard_ph.sub_smoalc_csd
;

create view n3c_dashboard_ph.substance_alc_smo_covid as
select
	alcohol_condition,
	smoking_status,
	patient_count as covid_display,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	alcohol_condition,smoking_map.secondary as smoking_status
from n3c_dashboard.alcohol_map, n3c_dashboard.smoking_map) as foo
natural left join
n3c_dashboard_ph.sub_covalcsmo_csd
;

create view n3c_dashboard_ph.substance_alc_smo_combined as
select * from
(select alcohol_condition, smoking_status, all_display, patient_count as all_count from n3c_dashboard_ph.substance_alc_smo_all) as foo
natural join
(select alcohol_condition, smoking_status, covid_display, patient_count as covid_count from n3c_dashboard_ph.substance_alc_smo_covid) as bar
;

-- opioids / smoking

create view n3c_dashboard_ph.substance_opi_smo_all as
select
	opioids,
	smoking_status,
	patient_count as all_display,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	opioid_map.opioids,smoking_map.secondary as smoking_status
from n3c_dashboard.opioid_map, n3c_dashboard.smoking_map) as foo
natural left join
n3c_dashboard_ph.sub_opismo_csd
;

create view n3c_dashboard_ph.substance_opi_smo_covid as
select
	opioids,
	smoking_status,
	patient_count as covid_display,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	opioid_map.opioids,smoking_map.secondary as smoking_status
from n3c_dashboard.opioid_map, n3c_dashboard.smoking_map) as foo
natural left join
n3c_dashboard_ph.sub_covsmoopi_csd
;

create view n3c_dashboard_ph.substance_opi_smo_combined as
select * from
(select opioids, smoking_status, all_display, patient_count as all_count from n3c_dashboard_ph.substance_opi_smo_all) as foo
natural join
(select opioids, smoking_status, covid_display, patient_count as covid_count from n3c_dashboard_ph.substance_opi_smo_covid) as bar
;

-- alcohol / opioids / smoking

create view n3c_dashboard_ph.substance_all as
select
	alcohol_condition,
	opioids,
	smoking_status,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	alcohol_map.secondary as alcohol_condition,opioid_map.secondary as opioids,smoking_map.secondary as smoking_status
from n3c_dashboard.alcohol_map, n3c_dashboard.opioid_map, n3c_dashboard.smoking_map) as foo
natural left join
n3c_dashboard_ph.sub_smoopialc_csd
;

create view n3c_dashboard_ph.substance_covid as
select
	alcohol_condition,
	opioids,
	smoking_status,
	case
		when patient_count = '<20' then 1
		else coalesce(patient_count, '0')::int
	end as patient_count
from
(select
	alcohol_map.secondary as alcohol_condition,opioid_map.secondary as opioids,smoking_map.secondary as smoking_status
from n3c_dashboard.alcohol_map, n3c_dashboard.opioid_map, n3c_dashboard.smoking_map) as foo
natural left join
n3c_dashboard_ph.sub_covopismoalc_csd
;

create view n3c_dashboard_ph.substance_combined as
select * from
(select alcohol_condition, opioids, smoking_status, patient_count as all_count from n3c_dashboard_ph.substance_all) as foo
natural join
(select alcohol_condition, opioids, smoking_status, patient_count as covid_count from n3c_dashboard_ph.substance_covid) as bar
;
