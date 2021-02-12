SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE person;
ALTER TABLE person ALTER COLUMN id RESTART WITH 1;

INSERT INTO person(first_name, last_name, age, favourite_colour)
VALUES('Reetesh', 'Kumar', '29', 'red');
INSERT INTO person(first_name, last_name, age, favourite_colour)
VALUES('David', 'Weston', '30', 'black');
INSERT INTO person(first_name, last_name, age, favourite_colour)
VALUES('John', 'Kennedy', '45', 'blue');
INSERT INTO person(first_name, last_name, age, favourite_colour)
VALUES('Paul', 'Adams', '35', 'green');
INSERT INTO person(first_name, last_name, age, favourite_colour)
VALUES('Tom', 'Graham', '36', 'red');
INSERT INTO person(first_name, last_name, age, favourite_colour)
VALUES('Jyoti', 'Chauhan', '37', 'pink');