CREATE TABLE IF NOT EXISTS buch(
                                   uuid VARCHAR(250),
                                   titel VARCHAR(100),
                                   CONSTRAINT buch_pk PRIMARY KEY(uuid)
);
CREATE TABLE IF NOT EXISTS kapitel(
                                   uuid VARCHAR(250),
                                   nr INTEGER,
                                   text TEXT,
                                   status INTEGER,
                                   buch VARCHAR(250),
                                   CONSTRAINT kapitel_pk PRIMARY KEY(uuid),
                                   CONSTRAINT kapitel_fk FOREIGN KEY (buch) references buch (uuid)
)