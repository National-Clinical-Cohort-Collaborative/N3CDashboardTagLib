CREATE SCHEMA IF NOT EXISTS n3c_dashboard;
CREATE SEQUENCE n3c_dashboard.seqnum
;
CREATE DOMAIN n3c_dashboard.Image AS BYTEA;
CREATE TABLE n3c_dashboard.category (
       id INT NOT NULL
     , seqnum INT
     , label TEXT
     , PRIMARY KEY (id)
);

CREATE TABLE n3c_dashboard.dashboard (
       id INT NOT NULL
     , id2 INT NOT NULL
     , seqnum INT
     , title TEXT
     , description TEXT
     , path TEXT
     , thumbnail_path TEXT
     , thumbnail Image
     , thumbnail_name TEXT
     , PRIMARY KEY (id, id2)
     , CONSTRAINT FK_dashboard_1 FOREIGN KEY (id)
                  REFERENCES n3c_dashboard.category (id)
);

CREATE TABLE n3c_dashboard.topic (
       id INT NOT NULL
     , id2 INT NOT NULL
     , id3 INT NOT NULL
     , seqnum INT
     , title TEXT
     , path TEXT
     , PRIMARY KEY (id, id2, id3)
     , CONSTRAINT FK_topic_1 FOREIGN KEY (id, id2)
                  REFERENCES n3c_dashboard.dashboard (id, id2)
);

