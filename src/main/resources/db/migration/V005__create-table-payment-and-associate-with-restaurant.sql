create table payment_method (
	id bigint not null auto_increment,
	name varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_method (
	restaurant_id bigint not null,
	payment_method_id bigint not null,

	primary key (restaurant_id, payment_method_id)
) engine=InnoDB default charset=utf8;

alter table restaurant_payment_method add constraint fk_payment_method
foreign key (payment_method_id) references payment_method (id);

alter table restaurant_payment_method add constraint fk_restaurant
foreign key (restaurant_id) references restaurant (id);