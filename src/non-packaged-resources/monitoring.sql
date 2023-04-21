create view n3c_dashboard.feed_no_updates as
select file
from palantir.tiger_team_file_new_metrics
group by file
having count(file) = 1
order by file;

create view n3c_dashboard.feed_updates as
select bar.*
from 
	(select distinct file from palantir.tiger_team_file_new) as foo
join lateral (
	select * from palantir.tiger_team_file_new_metrics as f002
	where foo.file = f002.file
	order by updated desc
	limit 2
	) as bar on true
where foo.file not in (select file from n3c_dashboard.feed_no_updates )
order by 1,2;

create view n3c_dashboard.feed_deltas as
select foo.file,foo.updated,foo.rows - bar.rows as row_delta, bar.count as previous_count, foo.count as current_count, foo.count - bar.count as count_delta
from n3c_dashboard.feed_updates as foo, n3c_dashboard.feed_updates as bar
where foo.file = bar.file
  and foo.updated > bar.updated
;

create view n3c_dashboard.feed_status as
select
	rid,
	file,
	to_char(fetched, 'YYYY-MM-DD') as fetched,
	to_char(updated, 'YYYY-MM-DD') as updated,
	now() - updated as interval,
	row_delta,
	previous_count,
	current_count,
	count_delta
from
	(select rid, file,updated as fetched,(((metadata->>'modified')::jsonb)->>'time')::timestamp as updated from palantir.tiger_team_file_new) as foo
natural left outer join
	(select * from n3c_dashboard.feed_deltas) as bar
;

-- first build the feed to table mappings
--
-- fgrep -R n3c_questions_new * | sed -e "s/:.*from / /" -e "s/:.*FROM / /" -e "s/  *where .*//" -e "s/ group .*//" | sort | uniq > ~/feed_table_mapping
-- fgrep -R n3c_questions. * | sed -e "s/:.*from / /" -e "s/:.*FROM / /" -e "s/  *where .*//" -e "s/ group .*//" | sort | uniq > ~/feed_table_mapping2
--
-- manual editing of the two mapping files
--
-- psql -h neuromancer -c "copy n3c_dashboard.feed_table_mapping_staging from stdin delimiter ' ';" cd2h < ~/feed_table_mapping2
-- sort ~/feed_table_mapping | uniq | psql -h neuromancer -c "copy n3c_dashboard.feed_table_mapping_staging from stdin delimiter ' ';" cd2h
--
-- then build the table to vis mapping
-- foreach a ( `cat ~/vis_feed_mapping` )
--    fgrep -R $a .
-- end
--
-- manual editing of the mapping file yielding mapping file 2
--
-- sort ~/vis_feed_mapping2 | uniq | psql -h neuromancer -c "copy n3c_dashboard.dashboard_feed_mapping from stdin delimiter ' ';" cd2h
