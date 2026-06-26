--liquibase formatted sql

--changeset dev:create-content-schema
create schema if not exists content;

--changeset dev:create-posts-table
create table if not exists content.posts
(
    id integer generated always as identity primary key,
    user_id uuid references core.users (id) on delete cascade,
    title jsonb not null,
    content text not null,
    status varchar(20) not null default 'DRAFT'
        check (status in ('DRAFT', 'PUBLISHED')),
    created_at timestamp with time zone default current_timestamp,
    updated_at timestamp with time zone default current_timestamp
);

--changeset dev:create-tags-table
create table if not exists content.tags
(
    id integer generated always as identity primary key,
    name varchar(50) unique not null
);

--changeset dev:create-post-tags-table
create table if not exists content.post_tags
(
    post_id integer references content.posts (id) on delete cascade,
    tag_id integer references content.tags (id) on delete cascade,
    primary key (post_id, tag_id)
);