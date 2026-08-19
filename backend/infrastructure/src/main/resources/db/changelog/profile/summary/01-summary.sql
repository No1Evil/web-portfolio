--liquibase formatted sql

--changeset dev:create-profile-schema
create schema if not exists profile;

create table if not exists profile.summary
(
    id             int primary key       default 1 check ( id = 1 ),
    first_name     varchar(255) not null,
    last_name      varchar(255) not null,
    proficiency    varchar(255) not null,
    description    jsonb        not null,
    hero_image_url text,
    created_at     timestamptz           default current_timestamp,
    updated_at     timestamptz           default current_timestamp,
    version        bigint       not null default 1,

    constraint chk_description_is_object check (
        jsonb_typeof(description) = 'object'
        ),

    constraint chk_description_not_empty check (
        description <> '{}'::jsonb
        ),

    constraint chk_description_has_default_lang check (
        description ->> 'en' is not null
        )
);