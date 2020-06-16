--liquibase formatted sql

--changeset young:2
alter table liquibase_users add column name VARCHAR(255) NOT NULL;
--rollback alter table liquibase_users drop column name;