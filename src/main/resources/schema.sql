drop table if exists users;
drop table if exists person;
drop table if exists cars;
drop table if exists comments;

create table users
(
    id       int          NOT NULL AUTO_INCREMENT,
    username varchar(255) not null,
    password varchar(255) not null,
    PRIMARY KEY (ID)
);

create table hashedUsers
(
    id       int          NOT NULL AUTO_INCREMENT,
    username varchar(255) not null,
    passwordHash varchar(64) not null,
    salt varchar(64) not null,
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
    year           int          NOT NULL,
    PRIMARY KEY (ID)
);

create table comments
(
    id             int          NOT NULL AUTO_INCREMENT,
    carId          int          NOT NULL,
    userId         int          NOT NULL,
    comment        varchar(500) NOT NULL,
    PRIMARY KEY (ID)
);