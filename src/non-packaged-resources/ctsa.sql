create view composite as
select
	appl_id,
	project,
	grant_num_act,
	grant_num_ic,
	grant_num_serial,
	grant_num_act||grant_num_ic||grant_num_serial as grant_number,
	fy,
	institution,
	inst_geo_code_latitude,
	inst_geo_code_longitude,
	grants.perf_site_org,
	grants.perf_site_addr,
	coalesce(census_address.latitude::double precision,ror.latitude) as perf_site_latitude,
	coalesce(census_address.longitude::double precision,ror.longitude) as perf_site_longitude,
	id as ror_id
from
	ctsa.grants
left outer join ctsa.census_address on(grants.perf_site_addr = census_address.perf_site_addr)
left outer join (ctsa.org_mapping natural join ror.address) as ror on (ror.org = grants.perf_site_org and ror.address = grants.perf_site_addr)
;

create view collaboration as
select jsonb_pretty(jsonb_agg(org))
	from (select distinct
		grant_number,
		title,
		institution,
		inst_geo_code_latitude,
		inst_geo_code_longitude,
		(select jsonb_agg(perf)
			from (select distinct
					ror_id,
					perf_site_org,
					perf_site_addr,
					perf_site_latitude,
					perf_site_longitude
				  from ctsa.take_4 as foo where foo.grant_number = bar.grant_number
				  ) as perf
		) as perf_site_details
		from (select distinct
				grant_number,
				(select title from ctsa.grants where grants.appl_id=take_4.appl_id limit 1) as title,
				institution,
				inst_geo_code_latitude,
				inst_geo_code_longitude
			from ctsa.take_4) as bar
		) as org
;

