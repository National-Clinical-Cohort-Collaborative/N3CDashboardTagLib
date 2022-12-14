CREATE SCHEMA IF NOT EXISTS n3c_dashboard;
CREATE SEQUENCE n3c_dashboard.seqnum
;
CREATE DOMAIN n3c_dashboard.Image AS BYTEA;
CREATE TABLE n3c_dashboard.category (
       cid INT NOT NULL
     , seqnum INT
     , label TEXT
     , PRIMARY KEY (cid)
);

CREATE TABLE n3c_dashboard.dashboard (
       did INT NOT NULL
     , title TEXT
     , description TEXT
     , path TEXT
     , thumbnail_path TEXT
     , thumbnail Image
     , thumbnail_name TEXT
     , PRIMARY KEY (did)
);

CREATE TABLE n3c_dashboard.topic (
       did INT NOT NULL
     , tid INT NOT NULL
     , seqnum INT
     , title TEXT
     , path TEXT
     , PRIMARY KEY (tid, did)
     , CONSTRAINT FK_topic_1 FOREIGN KEY (did)
                  REFERENCES n3c_dashboard.dashboard (did)
);

CREATE TABLE n3c_dashboard.binding (
       cid INT NOT NULL
     , did INT NOT NULL
     , seqnum INT
     , PRIMARY KEY (cid, did)
     , CONSTRAINT FK_binding_1 FOREIGN KEY (cid)
                  REFERENCES n3c_dashboard.category (cid)
     , CONSTRAINT FK_binding_2 FOREIGN KEY (did)
                  REFERENCES n3c_dashboard.dashboard (did)
);

