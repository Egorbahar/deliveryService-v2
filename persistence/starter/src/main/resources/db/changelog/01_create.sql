CREATE TABLE product(
	id bigserial primary key ,
	name varchar(30) not null,
	quantity int not null,
	price double precision not null
);
CREATE TABLE category(
	id bigserial primary key,
	name varchar(30) not null,
	parent_category_id bigint,
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
	PRIMARY KEY (product_id, category_id),
	CONSTRAINT fk_product FOREIGN KEY (product_id) references product(id),
	CONSTRAINT fk_category FOREIGN KEY (category_id) references category(id)

);
CREATE TABLE product_store(
	product_id bigint,
	store_id bigint,
	PRIMARY KEY (product_id, store_id),
	CONSTRAINT fk_product FOREIGN KEY (product_id) references product(id),
	CONSTRAINT fk_store FOREIGN KEY (store_id) references store(id)
);