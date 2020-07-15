set foreign_key_checks = 0;
delete from cuisine;
delete from restaurant;

set foreign_key_checks = 1;

alter table cuisine auto_increment = 1;
alter table restaurant auto_increment = 1;

insert into cuisine (id, name) values (1, 'Thai');
insert into cuisine (id, name) values (2, 'Indian');
insert into cuisine (id, name) values (3, 'Argentine');
insert into cuisine (id, name) values (4, 'Brazilian');

insert into restaurant (id, name, shipping_cost, cuisine_id) values (1, 'Thai Gourmet', 10, 1);
insert into restaurant (id, name, shipping_cost, cuisine_id) values (2, 'Thai Delivery', 9.50, 1);
insert into restaurant (id, name, shipping_cost, cuisine_id) values (3, 'Tuk Tuk Indian Cusine', 15, 2);
insert into restaurant (id, name, shipping_cost, cuisine_id) values (4, 'Java Steakhouse', 12, 3);
insert into restaurant (id, name, shipping_cost, cuisine_id) values (5, 'Uncle Sam Cafeteria', 11, 4);
insert into restaurant (id, name, shipping_cost, cuisine_id) values (6, 'Bar da Maria', 6, 4);