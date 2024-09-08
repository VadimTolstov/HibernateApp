CREATE schema one_to_one;

create table one_to_one.person
(
    id   int primary key generated by default as identity,
    name varchar(100) not null,
    age  int
);

create table one_to_one.passport
(
    person_id       int primary key references one_to_one.person (id) on delete cascade,--первичный и внешний ключ
    passport_number int not null
);