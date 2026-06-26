--liquibase formatted sql

--changeset dev:create-profile-schema
create schema if not exists profile;

--changeset dev:create-user-profile-table
create table if not exists profile.user_profile
(
    user_id uuid primary key references core.users (id) on delete cascade,
    title jsonb,
    description jsonb
);

--changeset dev:create-user-contacts-table
create table if not exists profile.user_contacts
(
    id integer generated always as identity primary key,
    user_id uuid references core.users (id) on delete cascade,
    title jsonb,
    subtitle jsonb,
    redirect_url text,
    icon_url text
);

--changeset dev:create-user-education-table
create table if not exists profile.user_education
(
    id integer generated always as identity primary key,
    user_id uuid references core.users (id) on delete cascade,
    institution jsonb not null,
    degree jsonb not null,
    start_date timestamp with time zone,
    end_date timestamp with time zone
);

--changeset dev:create-user-experience-table
create table if not exists profile.user_experience
(
    id integer generated always as identity primary key,
    user_id uuid references core.users (id) on delete cascade,
    company varchar(255) not null,
    position varchar(255),
    description jsonb,
    start_date timestamp with time zone,
    end_date timestamp with time zone
);