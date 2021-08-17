CREATE TABLE product(
	id bigserial primary key ,
	name varchar(30) not null,
	quantity int not null,
	price double precision not null
);
CREATE TABLE category(
	id bigserial primary key,
	name varchar(30) not null,
	parent_category_id bigserial,
	FOREIGN KEY (parent_category_id) references category(id)
);
CREATE TABLE store(
	id bigserial primary key ,
	name varchar(30) not null,
	address varchar(30) not null
);
CREATE TABLE product_category(
	product_id bigint,
	category_id bigint,
	FOREIGN KEY (product_id) references product(id),
	FOREIGN KEY (category_id) references category(id)

);
CREATE TABLE product_store(
	product_id bigint,
	store_id bigint,
	FOREIGN KEY (product_id) references product(id),
	FOREIGN KEY (store_id) references store(id)
);