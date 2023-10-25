create view n3c_dashboard_ph.environmental_temp as
select
	code,
	statename,
	countyname,
	sum(non_count) as non_count,
	sum(covid_count) as covid_count,
	sum(patient_count_died) as patient_count_died,
	sum(patient_count_died_cause_covid) as patient_count_died_cause_covid
from
	(select
		code,
		statename,
		countyname,
		coalesce(non_count, 0) as non_count,
		coalesce(covid_count, 0) as covid_count,
		patient_count_died,
		patient_count_died_cause_covid
		from (
			(select statename,countyname,
					case
						when patient_count_of_this_city = '<20' then 1
						else patient_count_of_this_city::int
					end as non_count,
					case
						when patient_count_died = '<20' then 1
						else patient_count_died::int
					end as patient_count_died,
					case
						when patient_count_died_cause_covid = '<20' then 1
						else patient_count_died_cause_covid::int
					end as patient_count_died_cause_covid
				from n3c_dashboard_ph.env_stcntctzipprmlist_cnt_csd where covid_indicator = 0) as foo
			natural full outer join
			(select statename,countyname,
					case
						when patient_count_of_this_city = '<20' then 1
						else patient_count_of_this_city::int
					end as covid_count,
					case
						when patient_count_died = '<20' then 1
						else patient_count_died::int
					end as patient_count_died,
					case
						when patient_count_died_cause_covid = '<20' then 1
						else patient_count_died_cause_covid::int
					end as patient_count_died_cause_covid
				from n3c_dashboard_ph.env_stcntctzipprmlist_cnt_csd where covid_indicator = 1) as bar
		) as foo2,
		n3c_maps.state_code
		where statename = state
	) as bar
group by 1,2,3
;
