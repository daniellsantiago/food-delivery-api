create table cuisine (
  id bigint not null auto_increment,
  name varchar(60) not null,
  
  primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment,
	cuisine_id bigint not null,
	name varchar(80) not null,
	shipping_cost decimal(10,2) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

alter table restaurant add constraint fk_restaurant_cuisine
foreign key (cuisine_id) references cuisine (id);