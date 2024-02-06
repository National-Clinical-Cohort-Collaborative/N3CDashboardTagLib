create view n3c_pubs.publication_feed as
SELECT jsonb_pretty(jsonb_agg(foo))
FROM (
	SELECT ROW_NUMBER() OVER (ORDER BY title) as id, *
	FROM (
		SELECT
			title,
			'Publication' as type,
			concat('https://doi.org/', doi) as url,
			value as outlet,
			(select value from scholar_profile.metadata where metadata.id=citation.id and field='Publication date') as date,
			(select string_agg(author, ', ') from scholar_profile.authorship where authorship.id=citation.id and seqnum<4)||' ...' as authors
		FROM scholar_profile.citation natural join scholar_profile.linkage natural join scholar_profile.metadata
		WHERE field='Journal'
		  AND value !~* '^arxiv'
		  AND value !~* '^medrxiv'
		  AND value !~* '^biorxiv'
	UNION ALL
		select
			title,
			'Preprint' as type,
			concat('https://doi.org/', doi) as url,
			value as outlet,
			(select value from scholar_profile.metadata where metadata.id=citation.id and field='Publication date') as date,
			(select string_agg(author, ', ') from scholar_profile.authorship where authorship.id=citation.id and seqnum<4)||' ...' as authors
		FROM scholar_profile.citation natural join scholar_profile.linkage natural join scholar_profile.metadata
		WHERE field='Journal'
		  AND (value ~* '^arxiv'
		   OR value ~* '^medrxiv'
		   OR value ~* '^biorxiv')
	UNION ALL
		select
			title,
			'Presentation' as type,
			'' as url,
			'' as outlet,
			pub_date::text as date,
			correspondingauthor as authors
		from n3c_pubs.conference_cache
	UNION ALL
		select
			title,
			'Presentation' as type,
			'' as url,
			'' as outlet,
			pub_date::text as date,
			correspondingauthor as authors
		from n3c_pubs.manual_cache
		) x
	) AS foo
;

truncate aws_scholar_profile.author;
insert into aws_scholar_profile.author select * from scholar_profile.author;

truncate aws_scholar_profile.authorship_map;
insert into aws_scholar_profile.authorship_map select * from scholar_profile.authorship_map;

truncate aws_scholar_profile.citation;
insert into aws_scholar_profile.citation select * from scholar_profile.citation;

truncate aws_scholar_profile.cite;
insert into aws_scholar_profile.cite select * from scholar_profile.cite;

truncate aws_scholar_profile.linkage;
insert into aws_scholar_profile.linkage select * from scholar_profile.linkage;

truncate aws_scholar_profile.metadata;
insert into aws_scholar_profile.metadata select * from scholar_profile.metadata;


--
-- This is the old feed query
--
SELECT jsonb_pretty(jsonb_agg(foo))
FROM (
	SELECT ROW_NUMBER() OVER (ORDER BY title) as id, *
	FROM (
		SELECT
			title,
			'Publication' as type,
			concat('https://pubmed.ncbi.nlm.nih.gov/', pmid, '/') as url,
			journal as outlet,
			published as date,
			string_agg(concat(value->>'first_name', ' ', value->>'last_name'), ', ') as authors
		FROM n3c_pubs.litcovid_cache, jsonb_array_elements(n3c_pubs.litcovid_cache.authors)
		group by title, url, outlet, date, type
	UNION ALL
		select
			title,
			'Preprint' as type,
			concat('https://doi.org/', doi) as url,
			site as outlet,
			pub_date as date,
			string_agg(value->>'name', ', ') as authors
		from n3c_pubs.biorxiv_cache, jsonb_array_elements(n3c_pubs.biorxiv_cache.authors)
		group by title, url, outlet, date, type
	UNION ALL
		select
			title,
			'Presentation' as type,
			'' as url,
			'' as outlet,
			pub_date as date,
			correspondingauthor as authors
		from n3c_pubs.conference_cache
	UNION ALL
		select
			title,
			'Presentation' as type,
			'' as url,
			'' as outlet,
			pub_date as date,
			correspondingauthor as authors
		from n3c_pubs.manual_cache
		) x
	) AS foo
;
