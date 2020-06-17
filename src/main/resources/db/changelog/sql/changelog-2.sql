--liquibase formatted sql

--changeset young:2 logicalFilePath:path-independent-2
create table liquibase_users  (  
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);  
--rollback alter table liquibase_users rename to liquibase_users_1;  