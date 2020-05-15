insert into users(id, username, password)
values (1, 'bruce', 'wayne'),
       (2, 'peter', 'security_rules'),
       (3, 'tom', 'guessmeifyoucan');

insert into hashedUsers(id, username, passwordHash, salt)
values (1, 'bruce', 'qw8Uxa2fXimKruS9wYEm4qm3ZaIGw/hJNvOG3PemhoA=', 'MEI4PU5hcHhaRHZz'),
       (2, 'peter', 'qPWryBEWiWdHsC+67dmO+y5ugGrMVI2w4MSz0+CpDm4=', 'MnY1am14c2d1ZlBf'),
       (3, 'tom', 'FLmYMYmwSRxcy0n2uwysy39ax0TRWvKHswSCPMo+PiI=', 'OChoOitAKWE0TWlD');

insert into persons(id, firstName, lastName, personalNumber, address)
values (1, 'bruce', 'wayne', '1203992441123', 'NBG'),
       (2, 'Peter', 'Petigrew', '023348574839234', 'Diagon Alley'),
       (3, 'Tom', 'Riddle', '3234989332432', 'Bulgaria');

insert into cars(id, price, wholesalePrice, model, manufacturer)
values (1, 423, 300, 'Civic', 'Honda'),
       (2, 350, 200, 'Focus', 'Ford');

insert into comments(carId, userId, comment)
values (1, 1, 'Honda is great!');

insert into roles(id, name)
values (1, 'SELLER'),
       (2, 'BUYER');

insert into user_to_roles(userId, roleId)
values (1, 1),
       (2, 2);

insert into permissions(id, name)
values (1, 'CAR_LIST_VIEW'),
       (2, 'CAR_SEARCH'),
       (3, 'CAR_BUY'),
       (4, 'COMMENT_VIEW'),
       (5, 'COMMENT_ADD'),
       (6, 'CAR_DETAILS_VIEW'),
       (7, 'CAR_DETAILS_EDIT'),
       (8, 'USER_PROFILE_VIEW'),
       (9, 'USER_LIST_VIEW'),
       (10, 'USER_SEARCH'),
       (11, 'USER_PROMOTE_TO_SELLER'),
       (12, 'OWN_PROFILE_VIEW'),
       (13, 'OWN_PROFILE_EDIT'),
       (14, 'CAR_WHOLESALE_PRICE_VIEW');

insert into role_to_permissions(roleId, permissionId)
values (1, 1),
       (2, 1);

insert into scheduled_services(personId, date, carModel)
values (1, CURRENT_DATE, 'Mercedes S 560');
