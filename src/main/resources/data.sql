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

insert into cars(price, wholesalePrice, model, manufacturer, manufactureYear)
values (423, 300, 'Civic', 'Honda', 2015),
       (350, 200, 'Focus', 'Ford', 2011);

insert into comments(carId, userId, comment)
values (1, 1, 'Honda is great!');
