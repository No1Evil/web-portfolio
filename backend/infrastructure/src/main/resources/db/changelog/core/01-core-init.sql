--liquibase formatted sql

--changeset dev:create-core-schema
create schema if not exists core;

--changeset dev:create-users-table
create table if not exists core.users
(
    id          uuid primary key,
    first_name  varchar(50)  not null,
    second_name varchar(50)  not null,
    email       varchar(255) not null unique,
    avatar_url  text,
    password    varchar(255) not null,
    created_at  timestamp with time zone default current_timestamp,
    updated_at  timestamp with time zone default current_timestamp
);

--changeset dev:create-roles-table
create table if not exists core.roles
(
    id   integer generated always as identity primary key,
    name varchar(20)
);

insert into core.roles (name) values ('USER');
insert into core.roles (name) values ('ADMIN');

--changeset dev:create-user-roles-table
create table if not exists core.user_roles
(
    user_id uuid references core.users (id) on delete cascade,
    role_id integer references core.roles (id) on delete cascade,
    primary key (user_id, role_id)
)
