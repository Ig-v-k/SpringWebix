create sequence hibernate_sequence start 1 increment 1;

create table logger
(
    id             int4 generated by default as identity,
    action_details varchar(1024) not null,
    action_type    varchar(128)  not null,
    atomic         bit           not null,
    company_id     int4,
    created        timestamp     not null,
    table_name     varchar(128)  not null,
    user_id        int4          not null,
    primary key (id)
);

create table payment
(
    id              int4 generated by default as identity,
    city            varchar(64) not null,
    country         varchar(64) not null,
    pay_date        timestamp   not null,
    left_pay        varchar(64) not null,
    method          varchar(64) not null,
    name            varchar(64) not null,
    number          varchar(64) not null,
    status          varchar(32) not null,
    sum             varchar(64) not null,
    type            int4        not null,
    payment_user_id int4,
    primary key (id)
);

create table role
(
    id   int4 generated by default as identity,
    name varchar(64) not null,
    primary key (id)
);

create table status
(
    id   int4 generated by default as identity,
    name varchar(64) not null,
    primary key (id)
);

create table usr
(
    id                   int4 generated by default as identity,
    company_id           int4,
    email                varchar(64) not null,
    first_name           varchar(64),
    last_name            varchar(64),
--     token                varchar(64),
    location_id          int4,
    notification_type_id int4        not null,
    password             varchar(128),
    registration_date    timestamp   not null,
    role_id              int4        not null,
    status_id            int4        not null,
    username             varchar(64),
    primary key (id)
);

alter table if exists payment
    add constraint FKp0w9s64qqbke712kr905butpo foreign key (payment_user_id) references usr