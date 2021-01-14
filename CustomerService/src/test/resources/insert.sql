insert into address (id, contry, region, city, street, house, flat, created, modified) values
	(95, 'Russia', 'NSK', 'Novosibirsk', 'Mira', '5a', '6', '2018-01-01', '2018-01-01'),
	(96, 'Russia', 'NSK', 'Novosibirsk', 'Lenina', '12', '12', '2018-01-01', '2018-01-01'),
	(97, 'Russia', 'NSK', 'Novosibirsk', 'Snezhinoi', '87', '3', '2018-01-01', '2018-01-01');

insert into customer (id, registred_address_id, actual_address_id, first_name, last_name, middle_name, sex)	values
	(95, 95, 95, 'Ivan', 'Ivanov', 'Ivanovich', 'male'),
	(96, 96, 97, 'Irina', 'Sidorova', 'Alexandrovna', 'female'),
	(97, 96, 95, 'Ivan', 'Ivanov', 'Alexandrovich', 'male');