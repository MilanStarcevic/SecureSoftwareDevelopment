insert into users(id, username, password)
values (1, 'bruce', 'wayne'),
       (2, 'peter', 'security_rules'),
       (3, 'tom', 'guessmeifyoucan');

insert into hashedUsers(id, username, passwordHash, salt)
values (1, 'milan', '3+thm5xNrzwp8/uQ1vxIe6d/K3HoRAeyebX3xEj8SM4=', 'u1NtkyeLp77WDcL4f6EYgQ==');

insert into persons(id, firstName, lastName, personalNumber, address)
values (1, 'bruce', 'wayne', 'jmbg', 'NBG'),
       (2, 'Peter', 'Petigrew', '023348574839234', 'Diagon Alley'),
       (3, 'Tom', 'Riddle', '3234989332432', 'Bulgaria');

insert into cars(price, wholesalePrice, model, manufacturer)
values (423, 300, 'Civic', 'Honda'),
       (350, 200, 'Focus', 'Ford');

insert into comments(carId, userId, comment)
values (1, 1, 'Honda is great!');
