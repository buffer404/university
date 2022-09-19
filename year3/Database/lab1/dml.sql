insert into weather (type, temp) values ('обычная', 'неизменная');
insert into weather (type, temp) values ('обычная', 'изменчивая');
insert into place (name, weather_id) (select 'Диаспара', weather_id from weather where temp = 'неизменная');
insert into creature (name, place_id) (select 'Олвин', place_id from place where name = 'Диаспара');
insert into place (name, weather_id) (select 'Поселок', weather_id from weather where temp = 'изменчивая');
insert into creature (name, place_id) (select 'Они', place_id from place where name = 'Поселок');
insert into clothes (name, creature_id) (select 'Плащ', creature_id from creature where name = 'Олвин');
insert into clothes (name, creature_id) (select 'Свитер', creature_id from creature where name = 'Они');
insert into clothes_type (form, clothes_id, character) (select 'Сложная', clothes_id, 'Декоративный' from clothes where name = 'Плащ');
insert into clothes_type (form, clothes_id, character) (select 'Простая', clothes_id, 'Функциональная' from clothes where name = 'Свитер');
begin;
insert into clothes_ingredients (name, count) values ('Кусок ткани', 1);
insert into clothes_ingredients_link (clothes_ingredients_id, clothes_id)
 (select a.clothes_ingredients_id, c.clothes_id from clothes as c, clothes_ingredients as a where c.name = 'Плащ' and a.name='Кусок ткани');
insert into clothes_ingredients_link (clothes_ingredients_id, clothes_id)
 (select a.clothes_ingredients_id, c.clothes_id from clothes as c, clothes_ingredients as a where c.name = 'Свитер' and a.name='Кусок ткани');
commit;
begin;
insert into clothes_ingredients (name, count) values ('Пуговица', 4);
insert into clothes_ingredients_link (clothes_ingredients_id, clothes_id)
 (select a.clothes_ingredients_id, c.clothes_id from clothes as c, clothes_ingredients as a where c.name = 'Плащ' and a.name='Пуговица');
commit;

SELECT * FROM WEATHER;
SELECT * FROM PLACE;
SELECT * FROM CLOTHES;
SELECT * FROM CLOTHES_TYPE;
SELECT * FROM clothes_ingredients;
SELECT * FROM clothes_ingredients_link;