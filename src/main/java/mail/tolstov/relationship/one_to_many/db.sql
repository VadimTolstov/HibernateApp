drop table person;

create table person
(
    id   int primary key generated by default as identity,
    name varchar,
    age  int
);

create table my_person
(
    id   int primary key,
    name varchar,
    age  int
);

create sequence my_person_id_seq;

create schema one_to_many;
drop schema one_to_many;

CREATE table one_to_many.person
(
    id   int primary key generated by default as identity,
    name varchar(100) not null,
    age  int check ( age < 100 )
);

CREATE table one_to_many.item
(
    id        int primary key generated by default as identity,
    person_id int          references one_to_many.person (id) on delete set null,
    item_name varchar(100) not null
);

insert into one_to_many.person(name, age)
values ('Tome', 35);
insert into one_to_many.person(name, age)
values ('Bob', 52);
insert into one_to_many.person(name, age)
values ('Kate', 14);

insert into one_to_many.item(person_id, item_name)
VALUES (1, 'Book');
insert into one_to_many.item(person_id, item_name)
VALUES (1, 'AirPods');
insert into one_to_many.item(person_id, item_name)
VALUES (2, 'Iphone');
insert into one_to_many.item(person_id, item_name)
VALUES (3, 'Kindle');
insert into one_to_many.item(person_id, item_name)
VALUES (3, 'TV');
insert into one_to_many.item(person_id, item_name)
VALUES (3, 'PlayStation');