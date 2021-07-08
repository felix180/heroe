--liquibase formatted sql

--changeset Liquibase heroe:1
--preconditions onFail:MARK_RAN onError:WARN
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM heroe h WHERE h.id =1
insert into heroe (id, name) values (1, 'Superman');

--changeset Liquibase heroe:2
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM heroe h WHERE h.id =2
insert into heroe (id,  name) values (2, 'Spiderman');