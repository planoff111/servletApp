DROP DATABASE Employee;

CREATE DATABASE Employee;

CREATE TABLE if not exists public.users
(
    id      serial
        primary key,
    name    varchar(255),
    email   varchar(255),
    country varchar(255)
);