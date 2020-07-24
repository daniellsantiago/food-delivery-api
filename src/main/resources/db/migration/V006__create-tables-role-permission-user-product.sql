create table `role` (
	id bigint not null auto_increment,
	name varchar(60) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment,
	description varchar(60) not null,
	name varchar(100) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table role_permission (
	role_id bigint not null,
	permission_id bigint not null,

	primary key (role_id, permission_id)
) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment,
	restaurant_id bigint not null,
	name varchar(80) not null,
	description text not null,
	price decimal(10,2) not null,
	active tinyint(1) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment,
	name varchar(80) not null,
	email varchar(255) unique not null,
	password varchar(255) not null,
	created_at datetime not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_role (
	user_id bigint not null,
	role_id bigint not null,

	primary key (user_id, role_id)
) engine=InnoDB default charset=utf8;

alter table role_permission add constraint fk_permission_role
foreign key (permission_id) references permission (id);

alter table role_permission add constraint fk_role_permission
foreign key (role_id) references `role` (id);

alter table product add constraint fk_restaurant_product
foreign key (restaurant_id) references restaurant (id);

alter table user_role add constraint fk_role_user
foreign key (role_id) references `role` (id);

alter table user_role add constraint fk_user_role
foreign key (user_id) references user (id);