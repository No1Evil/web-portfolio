--liquibase formatted sql

--changeset dev:create-core-schema
create schema if not exists core;

--changeset dev:create-admin_user-table
create table if not exists core.admin_user
(
    id            int primary key       default 1 check ( id = 1 ),
    username      varchar(255) not null unique,
    password_hash varchar(255) not null,
    version       bigint       not null default 1,
    created_at    timestamptz           default current_timestamp,
    updated_at    timestamptz           default current_timestamp
);

create table if not exists core.skill_categories
(
    id         integer generated always as identity primary key,
    name       varchar(50) unique not null,
    icon_url   text,
    created_at timestamptz        not null default current_timestamp,
    updated_at timestamptz        not null default current_timestamp,
    version    bigint             not null default 1
);

create table if not exists core.skills
(
    id          integer generated always as identity primary key,
    category_id integer     references core.skill_categories (id) on delete set null,
    name        varchar(50) not null,
    created_at  timestamptz not null default current_timestamp,
    updated_at  timestamptz not null default current_timestamp,
    version     bigint      not null default 1,
    constraint uq_skills_category_name unique (category_id, name)
);