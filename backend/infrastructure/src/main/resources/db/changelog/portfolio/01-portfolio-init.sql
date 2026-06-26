--liquibase formatted sql

--changeset dev:create-portfolio-schema
create schema if not exists portfolio;

--changeset dev:create-user-projects-table
create table if not exists portfolio.user_projects
(
    id integer generated always as identity primary key,
    user_id uuid references core.users (id) on delete cascade,
    title jsonb,
    description jsonb,
    is_featured bool default false,
    project_url text,
    preview_image_url text
);

--changeset dev:create-skill-categories-table
create table if not exists portfolio.skill_categories
(
    id integer generated always as identity primary key,
    name varchar(50) unique not null,
    icon_url text
);

--changeset dev:create-skills-table
create table if not exists portfolio.skills
(
    id integer generated always as identity primary key,
    category_id integer references portfolio.skill_categories (id) on delete set null,
    name varchar(50) not null
);

--changeset dev:create-user-skills-table
create table if not exists portfolio.user_skills
(
    user_id uuid references core.users (id) on delete cascade,
    skill_id integer references portfolio.skills (id) on delete cascade,
    primary key (user_id, skill_id)
);

--changeset dev:create-project-associated-skills-table
create table if not exists portfolio.project_associated_skills
(
    project_id integer references portfolio.user_projects (id),
    skill_id integer references portfolio.skills (id) on delete cascade,
    primary key(project_id, skill_id)
);