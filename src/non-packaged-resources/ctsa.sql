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
	