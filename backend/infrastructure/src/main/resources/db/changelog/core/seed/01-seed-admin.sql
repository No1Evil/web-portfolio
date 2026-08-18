--liquibase formatted sql

--changeset dev:seed-admin-user
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(*) from core.admin_user
insert into core.admin_user (username, password_hash)
values ('${admin-username}', '${admin-password-hash}');