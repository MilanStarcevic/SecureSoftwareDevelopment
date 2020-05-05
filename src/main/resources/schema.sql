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
    totpKey varchar(255) null,
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
);

create table comments
(
    id             int          NOT NULL AUTO_INCREMENT,
    carId          int          NOT NULL,
    userId         int          NOT NULL,
    comment        varchar(500) NOT NULL,
    PRIMARY KEY (ID)
);

create table user_to_roles(
    userId         int          NOT NULL,
    roleId         int          NOT NULL
);

create table roles(
    id             int          NOT NULL,
    name           varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

create table permissions(
    id             int          NOT NULL,
    name           varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

create table role_to_permissions(
    roleId         int          NOT NULL,
    permissionId   int          NOT NULL
);

create table scheduled_services(
     id             int          NOT NULL AUTO_INCREMENT,
     personId       int          NOT NULL,
     date           date         NOT NULL,
     carModel       varchar(255) NOT NULL,
     remark         varchar(255),
     ticketNumber   varchar(255),
     time           time,
     PRIMARY KEY (ID)
);