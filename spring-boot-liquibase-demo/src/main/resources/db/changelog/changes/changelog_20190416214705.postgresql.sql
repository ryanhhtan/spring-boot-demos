--liquibase formatted sql

--changeset vagrant:1555451231099-1
CREATE TABLE roles (id BIGSERIAL NOT NULL, name VARCHAR(255), CONSTRAINT roles_pkey PRIMARY KEY (id));
--rollback DROP TABLE roles;

--changeset vagrant:1555451231099-2
CREATE TABLE user_roles (user_id BIGINT NOT NULL, role_id BIGINT NOT NULL, CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id));
--rollback DROP TABLE user_roles;

--changeset vagrant:1555451231099-3
CREATE TABLE users (id BIGSERIAL NOT NULL, name VARCHAR(255), username VARCHAR(255), CONSTRAINT users_pkey PRIMARY KEY (id));
--rollback DROP TABLE users;

--changeset vagrant:1555451231099-4
ALTER TABLE user_roles ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
--rollback ALTER TABLE user_roles DROP CONSTRAINT fk_role_id;

--changeset vagrant:1555451231099-5
ALTER TABLE user_roles ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
--rollback ALTER TABLE user_roles DROP CONSTRAINT fk_user_id;
