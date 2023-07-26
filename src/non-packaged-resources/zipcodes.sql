-- a hack to get huge json data into postgres

create table raw3_staging(raw text);

psql -h neuromancer -c "copy zipcode.raw3_staging from stdin;" cd2h < zip3.geojson

-- This loaded the file as text lines, fortunately, our target data are all on one line each

insert into raw3 select regexp_replace(raw, '(.*)(}),?$', '\1\2')::jsonb from raw3_staging where raw~'^{ "';

select raw->>'properties' from raw3;

┌──────────────────────────────────────────────────────────────────────────────────────────┐
│                                         ?column?                                         │
├──────────────────────────────────────────────────────────────────────────────────────────┤
│ {"SQMI": 251853.4, "ZIP3": "000", "STATE": "US", "POP2012": -99, "POP12_SQMI": -99.0}    │
│ {"SQMI": 15531.1, "ZIP3": "001", "STATE": "US", "POP2012": -99, "POP12_SQMI": -99.0}     │

create materialized view zipcode.zip3 as
select 
	((raw->>'properties')::jsonb)->>'STATE' as state,
	((raw->>'properties')::jsonb)->>'ZIP3' as zipcode,
	((raw->>'properties')::jsonb)->>'SQMI' as square_miles,
	((raw->>'properties')::jsonb)->>'POP2012' as population,
	((raw->>'properties')::jsonb)->>'POP12_SQMI' as population_per_sq_mile,
	raw as full
from zipcode.raw3
;

create materialized view zipcode.zip5 as
select 
	((raw->>'properties')::jsonb)->>'STATE' as state,
	((raw->>'properties')::jsonb)->>'ZIP' as zipcode,
	((raw->>'properties')::jsonb)->>'SQMI' as square_miles,
	((raw->>'properties')::jsonb)->>'POP2012' as population,
	((raw->>'properties')::jsonb)->>'POP12_SQMI' as population_per_sq_mile,
	((raw->>'properties')::jsonb)->>'FAMILIES' as families,
	((raw->>'properties')::jsonb)->>'HOUSEHOLDS' as households,
	raw as full
from zipcode.raw5
;


// handling some problem child zip polygons

create table suppress(state text, zipcode text);
insert into suppress values('KS','660');
insert into suppress values('CO','809');
insert into suppress values('MO','641');
insert into suppress values('TX','77905');
insert into suppress values('WY','82501');
