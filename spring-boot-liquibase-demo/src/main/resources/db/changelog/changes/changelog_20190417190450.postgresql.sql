--liquibase formatted sql

--changeset vagrant:1555527897540-1
CREATE TABLE hello (id BIGSERIAL NOT NULL, greeting VARCHAR(255), CONSTRAINT hello_pkey PRIMARY KEY (id));

