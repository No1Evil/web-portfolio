--liquibase formatted sql

--changeset dev:create-profile-schema
create schema if not exists profile;

create table if not exists profile.user_contacts
(
    id integer generated always as identity primary key,
    title varchar(255) not null,
    redirect_url text,
    icon_url text
);