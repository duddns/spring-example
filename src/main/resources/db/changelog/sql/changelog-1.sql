--liquibase formatted sql

--changeset young:1 logicalFilePath:path-independent-1
create table aggregate_line_items (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    name varchar(255),
    price bigint,
    product_id varchar(255),
    order_id bigint,
    primary key (id)
);
create table aggregate_orders (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    primary key (id)
);
create table aggregate_shipping_addresses (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    address varchar(255),
    zip_code varchar(255),
    order_id bigint,
    primary key (id)
);
create table audit_books (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    title varchar(255),
    primary key (id)
);
create table audit_books_aud (
    id bigint not null,
    rev integer not null,
    revtype tinyint,
    created_at timestamp,
    last_modified_at timestamp,
    title varchar(255),
    primary key (id, rev)
);
create table lombok_humans (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    name varchar(255),
    primary key (id)
);
create table lombok_persons (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    name varchar(255),
    primary key (id)
);
create table querydsl_squares (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
create table revinfo (
    rev integer generated by default as identity,
    revtstmp bigint,
    primary key (rev)
);
create table security_users (
    id bigint generated by default as identity,
    created_at timestamp not null,
    last_modified_at timestamp not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
alter table querydsl_squares 
    add constraint UK_6e6df1ud8657lhi9h8y5jqiqh unique (username)
;
alter table aggregate_line_items 
    add constraint FKjkxy191thy19ew34cdic1y992 
    foreign key (order_id) 
    references aggregate_orders
;
alter table aggregate_shipping_addresses 
    add constraint FKdyaeavvvn7fdf6ywwlj7g7djv 
    foreign key (order_id) 
    references aggregate_orders
;
alter table audit_books_aud 
    add constraint FK6xgv5wsn04wh1tfj7gh57ofeo 
    foreign key (rev) 
    references revinfo
;
--rollback drop table if exists aggregate_line_items CASCADE;
--rollback drop table if exists aggregate_orders CASCADE;
--rollback drop table if exists aggregate_shipping_addresses CASCADE;
--rollback drop table if exists audit_books CASCADE;
--rollback drop table if exists audit_books_aud CASCADE;
--rollback drop table if exists lombok_humans CASCADE;
--rollback drop table if exists lombok_persons CASCADE;
--rollback drop table if exists querydsl_squares CASCADE;
--rollback drop table if exists revinfo CASCADE;
--rollback drop table if exists security_users CASCADE;    