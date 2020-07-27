create table `order` (
  id bigint not null auto_increment,
  subtotal decimal(10,2) not null,
  shipping_rate decimal(10,2) not null,
  total_price decimal(10,2) not null,

  restaurant_id bigint not null,
  user_customer_id bigint not null,
  payment_method_id bigint not null,

  address_city_id bigint,
  address_zip varchar(9),
  address_number varchar(20),
  address_additional_information varchar(50),
  address_street varchar(60),

  status varchar(10) not null,
  created_at datetime not null,
  confirmed_at datetime null,
  cancelled_at datetime null,
  delivered_at datetime null,

  primary key (id),

  constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id),
  constraint fk_order_user_customer foreign key (user_customer_id) references `user` (id),
  constraint fk_order_payment_method foreign key (payment_method_id) references payment_method (id)
) engine=InnoDB default charset=utf8;

create table order_item (
  id bigint not null auto_increment,
  quantity smallint(6) not null,
  unit_price decimal(10,2) not null,
  total_price decimal(10,2) not null,
  observation varchar(255) null,
  order_id bigint not null,
  product_id bigint not null,

  primary key (id),
  unique key uk_item_order_product (order_id, product_id),

  constraint fk_item_order_order foreign key (order_id) references `order` (id),
  constraint fk_item_order_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;