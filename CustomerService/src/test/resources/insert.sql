insert into address (id, contry, region, city, street, house, flat, created, modified) values
	(1, 'Russia', 'NSK', 'Novosibirsk', 'Mira', '5a', '6', '2018-01-01', '2018-01-01'),
	(2, 'Russia', 'NSK', 'Novosibirsk', 'Lenina', '12', '12', '2018-01-01', '2018-01-01'),
	(3, 'Russia', 'NSK', 'Novosibirsk', 'Snezhinoi', '87', '3', '2018-01-01', '2018-01-01');

insert into customer (id, registred_address_id, actual_address_id, first_name, last_name, middle_name, sex)	values
	(1, 1, 1, 'Ivan', 'Ivanov', 'Ivanovich', 'male'),
	(2, 2, 3, 'Irina', 'Sidorova', 'Alexandrovna', 'female'),
	(3, 2, 1, 'Ivan', 'Ivanov', 'Alexandrovich', 'male');