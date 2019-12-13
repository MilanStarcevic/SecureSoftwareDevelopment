drop table if exists person;

create table person
(
    name varchar(255) not null
);

create table users
(
    username     varchar(255) not null,
    password varchar(255) not null
);
