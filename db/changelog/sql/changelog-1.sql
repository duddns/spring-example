--liquibase formatted sql

--changeset young:1  
create table liquibase_users  (  
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);  
--rollback alter table liquibase_users rename to liquibase_users_1;  