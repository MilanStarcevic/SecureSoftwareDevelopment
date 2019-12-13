drop table if exists users;
drop table if exists person;
drop table if exists cars;

create table users
(
    id       int          NOT NULL AUTO_INCREMENT,
    username varchar(255) not null,
    password varchar(255) not null,
    PRIMARY KEY (ID)
);

create table persons
(
    id             int          NOT NULL AUTO_INCREMENT,
    firstName      varchar(255) not null,
    lastName       varchar(255) not null,
    personalNumber varchar(255) not null,
    address        varchar(255) not null,
    PRIMARY KEY (ID)
);

create table cars
(
    id             int          NOT NULL AUTO_INCREMENT,
    price          double       NOT NULL,
    wholesalePrice double       NOT NULL,
    model          varchar(255) NOT NULL,
    manufacturer   varchar(255) NOT NULL,
    PRIMARY KEY (ID)
)
