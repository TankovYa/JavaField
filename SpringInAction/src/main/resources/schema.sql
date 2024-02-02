create table if not exists Users(
id identity PRIMARY KEY,
username varchar(50) not null,
password varchar(50) not null,
fullname varchar(50) not null,
street varchar(50) not null,
city varchar(50) not null,
state varchar(2) not null,
zip varchar(10) not null,
phoneNumber varchar(12) not null
);
create table if not exists Taco_Order (
 id identity PRIMARY KEY,
 delivery_Name varchar(50) not null,
 delivery_Street varchar(50) not null,
 delivery_City varchar(50) not null,
 delivery_State varchar(2) not null,
 delivery_Zip varchar(10) not null,
 cc_number varchar(16) not null,
 cc_expiration varchar(5) not null,
 cc_cvv varchar(3) not null,
 placed_at timestamp not null
 );
create table if not exists Taco (
 id identity PRIMARY KEY,
 name varchar(50) not null,
 taco_order bigint not null,
 taco_order_key bigint not null,
 created_at timestamp not null,
 foreign key (taco_order) references Taco_Order(id)
);
create table if not exists Ingredient (
 id varchar(4) PRIMARY KEY,
 name varchar(25) not null,
 type varchar(10) not null
);
create table if not exists Ingredient_Ref (
 ingredient varchar(4) not null,
 taco bigint not null,
 taco_key bigint not null,
 foreign key (taco) references Taco(id),
 foreign key (ingredient) references Ingredient(id)
);