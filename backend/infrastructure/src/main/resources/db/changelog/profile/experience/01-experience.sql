--liquibase formatted sql

--changeset dev:create-profile-schema
create schema if not exists profile;

create table if not exists profile.experience_path
(
    id           uuid primary key,
    title        varchar(255) not null,
    company_name varchar(255) not null,
    location     varchar(100) not null,
    description  jsonb        not null,
    start_date   timestamptz,
    end_date     timestamptz,
    present      boolean      not null,
    created_at   timestamptz           default current_timestamp,
    updated_at   timestamptz           default current_timestamp,
    version      bigint       not null default 1,

    constraint chk_experience_dates check (
        (present = true and end_date is null) or
        (present = false and end_date >= start_date)
        ),

    constraint chk_description_is_object check (
        jsonb_typeof(description) = 'object'
        ),

    constraint chk_description_en_is_array check (
        jsonb_typeof(description -> 'en') = 'array'
        )
)