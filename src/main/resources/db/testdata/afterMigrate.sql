set foreign_key_checks = 0;
delete from cuisine;
delete from payment_method;
delete from `role`;
delete from role_permission;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_method;
delete from city;
delete from state;
delete from user;
delete from user_role;

set foreign_key_checks = 1;

alter table cuisine auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table city auto_increment = 1;
alter table state auto_increment = 1;
alter table `role` auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table user auto_increment = 1;

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
address_city_id, address_zip, address_number, address_additional_information, address_street, active, open)
values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', '1000', 'Bairro Centro', 'Rua João Pinheiro', true, true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, address_street, active, open)
values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 2, '48754-111', '90', 'Rua Murajuba', true, true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, address_street, active, open)
values (3, 'Tuk Tuk Indian Cusine', 15, 2, utc_timestamp, utc_timestamp, 1, '56487-121', '100', 'Rua Augusta', true, true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, address_additional_information, address_street, active, open)
values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, 3, '32323-444', '12', 'Av.Brasil', 'Rua das Rosas', true, true);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, address_number, active, open)
values (5, 'Uncle Sam Cafeteria', 11, 4, utc_timestamp, utc_timestamp, 4, '12345-678', '120', false, false);

insert into restaurant (id, name, shipping_cost, cuisine_id, created_at, updated_at,
address_city_id, address_zip, active, open)
values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, 5, '66635-120', false, false);

insert into payment_method (id, name) values (1, 'Cartão de crédito');
insert into payment_method (id, name) values (2, 'Cartão de débito');
insert into payment_method (id, name) values (3, 'Dinheiro');

insert into permission (id, name, description) values (1, 'CONSULTAR_CUISINES', 'Permite consultar cuisines');
insert into permission (id, name, description) values (2, 'EDITAR_CUISINES', 'Permite editar cuisines');

insert into restaurant_payment_method (restaurant_id, payment_method_id)
values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into product (name, description, price, active, restaurant_id)
values ('Pork with sweet and sour sauce', 'Delicious pork in special sauce', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id)
values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into product (name, description, price, active, restaurant_id)
values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into product (name, description, price, active, restaurant_id)
values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id)
values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id)
values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id)
values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into product (name, description, price, active, restaurant_id)
values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into product (name, description, price, active, restaurant_id)
values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);


insert into `role` (name) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');
insert into role_permission (role_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into `user` (id, name, email, password, created_at) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);