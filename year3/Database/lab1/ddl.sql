create sequence weather_seq;
create table weather
	(weather_id bigint not null primary key default nextval('weather_seq'),
	type varchar(255),
	temp varchar(255));
	
create sequence place_seq;
create table place
	(place_id bigint not null primary key default nextval('place_seq'),
	weather_id bigint not null references weather,
	name varchar(255));
	
create sequence creature_seq;
create table creature
	(creature_id bigint not null primary key default nextval('creature_seq'),
	place_id bigint references place,
	name varchar(255));
	
create sequence clothes_seq;
create table clothes
	(clothes_id bigint not null primary key default nextval('clothes_seq'),
	creature_id bigint not null references creature,
	name varchar(255));
	
	
create sequence clothes_type_seq;
create table clothes_type
	(clothes_type_id bigint not null primary key default nextval('clothes_type_seq'),
	clothes_id bigint references clothes,
	form varchar(255),
	character varchar(255));
	
create sequence clothes_ingredients_seq;
create table clothes_ingredients
	(clothes_ingredients_id bigint not null primary key default nextval('clothes_ingredients_seq'),
	name varchar(255),
	count smallint);
	
create table clothes_ingredients_link
	(clothes_ingredients_id bigint not null references clothes_ingredients,
	clothes_id bigint not null references clothes);
	
	
	
	drop table weather;
	drop table place;
	drop table creature;
	drop table clothes;
	drop table clothes_type;
	drop table clothes_ingredients;
	drop table clothes_ingredients_link;
	drop sequence weather_seq;
	drop sequence place_seq;
	drop sequence creature_seq;
	drop sequence clothes_seq;
	drop sequence clothes_type_seq;
	drop sequence clothes_ingredients_seq;
	
	
	
	