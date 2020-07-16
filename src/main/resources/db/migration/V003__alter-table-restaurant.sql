ALTER TABLE restaurant
ADD COLUMN created_at datetime not null DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at datetime not null DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN address_city_id bigint,
ADD COLUMN address_zip varchar(9),
ADD COLUMN address_number varchar(20),
ADD COLUMN address_additional_information varchar(50),
ADD COLUMN address_street varchar(60);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);
