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
select foo.file,foo.updated,foo.rows - bar.rows as row_delta, foo.count - bar.count as count_delta
from n3c_dashboard.feed_updates as foo, n3c_dashboard.feed_updates as bar
where foo.file = bar.file
  and foo.updated > bar.updated
;

create view n3c_dashboard.feed_status as
select file, to_char(fetched, 'YYYY-MM-DD') as fetched, to_char(updated, 'YYYY-MM-DD') as updated, now() - updated as interval, row_delta, count_delta
from
	(select file,updated as fetched,(((metadata->>'modified')::jsonb)->>'time')::timestamp as updated from palantir.tiger_team_file_new) as foo
natural left outer join
	(select * from n3c_dashboard.feed_deltas) as bar
;
