create table `group` (
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

create table group_permission (
	group_id bigint not null,
	permission_id bigint not null,

	primary key (group_id, permission_id)
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
	email varchar(255) not null,
	password varchar(255) not null,
	created_at datetime not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_group (
	user_id bigint not null,
	group_id bigint not null,

	primary key (user_id, group_id)
) engine=InnoDB default charset=utf8;

alter table group_permission add constraint fk_permission_group
foreign key (permission_id) references permission (id);

alter table group_permission add constraint fk_group_permission
foreign key (group_id) references `group` (id);

alter table product add constraint fk_restaurant_product
foreign key (restaurant_id) references restaurant (id);

alter table user_group add constraint fk_group_user
foreign key (group_id) references `group` (id);

alter table user_group add constraint fk_user_group
foreign key (user_id) references user (id);