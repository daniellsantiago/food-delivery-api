set foreign_key_checks = 0;
delete from cuisine;
delete from payment_method;
delete from restaurant;
delete from restaurant_payment_method;
delete from city;
delete from state;

set foreign_key_checks = 1;

alter table cuisine auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table city auto_increment = 1;
alter table state auto_increment = 1;

insert into cuisine (id, name) values (1, 'Thai');
insert into cuisine (id, name) values (2, 'Indian');
insert into cuisine (id, name) values (3, 'Argentine');
insert into cuisine (id, name) values (4, 'Brazilian');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, address_additional_information, address_street, active)
values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', '1000', 'Bairro Centro', 'Rua João Pinheiro', true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, address_street, active)
values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 2, '48754-111', '90', 'Rua Murajuba', true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, address_street, active)
values (3, 'Tuk Tuk Indian Cusine', 15, 2, utc_timestamp, utc_timestamp, 1, '56487-121', '100', 'Rua Augusta', true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, address_additional_information, address_street, active)
values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, 3, '32323-444', '12', 'Av.Brasil', 'Rua das Rosas', true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, active)
values (5, 'Uncle Sam Cafeteria', 11, 4, utc_timestamp, utc_timestamp, 4, '12345-678', '120', false);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, active)
values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, 5, '66635-120', false);

insert into payment_method (id, name) values (1, 'Credit Card');
insert into payment_method (id, name) values (2, 'Debit Card');
insert into payment_method (id, name) values (3, 'Cash');

insert into restaurant_payment_method (restaurant_id, payment_method_id)
values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);