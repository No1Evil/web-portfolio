--liquibase formatted sql

--changeset dev:create-profile-schema
create schema if not exists profile;

create table if not exists profile.user_skills
(
    user_id  int not null default 1 check ( user_id = 1 ),
    skill_id integer references core.skills (id) on delete cascade,
    primary key (user_id, skill_id)
);