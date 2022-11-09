_______Создаем сущности________

create sequence corpus_seq;
create table КОРПУСА(
	ИД bigint not null primary key default nextval('corpus_seq'),
	АДРЕС varchar(255)); 
	
create sequence audiences_seq;
create table АУДИТОРИИ(
	ИД bigint not null primary key default nextval('audiences_seq'),
	НОМЕР integer not null,
	ВМЕСТИМОСТЬ integer not null,
	КОД_КОРПУСА bigint references КОРПУСА); 	
	
create sequence equipment_type_seq;
create table ТИПЫ_ОБОРУДОВАНИЯ(
	ИД bigint not null primary key default nextval('equipment_type_seq'),
	НАИМЕНОВАНИЕ varchar(255)); 	
	
create table ОСНАЩЕНИЕ_АУДИТОРИИ(
	ОБОРУД_ИД bigint not null references ТИПЫ_ОБОРУДОВАНИЯ,
	АУД_ИД bigint not null references АУДИТОРИИ);	
	
create sequence group_seq;
create table ГРУППЫ(
	ИД bigint not null primary key default nextval('group_seq'),
	КОД_ГРУППЫ varchar(255),
	КОЛВО_ЛЮДЕЙ smallint); 	
	
create sequence academic_hour_seq;
create table АКАДЕМ_ЧАС(
	ИД bigint not null primary key default nextval('academic_hour_seq'),
	НОМЕР smallint,
	ВРЕМЯ_НАЧ time, 
	ВРЕМЯ_КОН time);

create sequence disciplines_seq;
create table ДИСЦИПЛИНЫ(
	ИД bigint not null primary key default nextval('disciplines_seq'),
	НАИМЕНОВАНИЕ varchar(255));

create sequence teacher_seq;
create table УЧИТЕЛЯ(
	ИД bigint not null primary key default nextval('teacher_seq'),
	КОД_ПРЕДМЕТА bigint references ДИСЦИПЛИНЫ,
	ИМЯ varchar(255), 
	ФАМИЛИЯ varchar(255),
	ОТЧЕСТВО varchar(255));

create sequence timetable_seq;
create table РАСПИСАНИЕ(
	ИД bigint not null primary key default nextval('timetable_seq'),
	АУД_ИД bigint references АУДИТОРИИ,
	КОД_ПРЕДМЕТА bigint references ДИСЦИПЛИНЫ,
	ГРУППА bigint references ГРУППЫ,
	УЧ_ИД bigint references УЧИТЕЛЯ,
	НОМЕР_ПАРЫ bigint references АКАДЕМ_ЧАС,
	ДЕНЬ_НЕДЕЛИ smallint,
	ЧЕТНОСТЬ boolean,
	ДАТА date);

_______Заполнение тестовыми данными________

insert into КОРПУСА (АДРЕС) values ('Кронверкский проспект, 49');
insert into КОРПУСА (АДРЕС) values ('улица Ломоносова, 9');

insert into АУДИТОРИИ (НОМЕР, ВМЕСТИМОСТЬ, КОД_КОРПУСА) (SELECT 1216, 500, ИД FROM КОРПУСА WHERE АДРЕС = 'улица Ломоносова, 9');

insert into ТИПЫ_ОБОРУДОВАНИЯ (НАИМЕНОВАНИЕ) values ('Проектор');
insert into ТИПЫ_ОБОРУДОВАНИЯ (НАИМЕНОВАНИЕ) values ('Маркерная доска');
insert into ТИПЫ_ОБОРУДОВАНИЯ (НАИМЕНОВАНИЕ) values ('ZOOM');

insert into ОСНАЩЕНИЕ_АУДИТОРИИ (АУД_ИД, ОБОРУД_ИД) (SELECT А.ИД FROM АУДИТОРИИ AS А LEFT JOIN КОРПУСА AS К ON А.КОД_КОРПУСА = К.ИД WHERE А.НОМЕР = 1216 AND К.АДРЕС = 'улица Ломоносова, 9' UNION SELECT ИД FROM ТИПЫ_ОБОРУДОВАНИЯ WHERE НАИМЕНОВАНИЕ = 'ZOOM');
insert into ОСНАЩЕНИЕ_АУДИТОРИИ (АУД_ИД, ОБОРУД_ИД) (SELECT ИД FROM АУДИТОРИИ WHERE НОМЕР = 1216 AND АДРЕС = 'улица Ломоносова, 9' as c, ИД FROM ТИПЫ_ОБОРУДОВАНИЯ WHERE НАИМЕНОВАНИЕ = 'Проектор');

insert into ГРУППЫ (КОД_ГРУППЫ, КОЛВО_ЛЮДЕЙ) values ('P33301', 22);
insert into ГРУППЫ (КОД_ГРУППЫ, КОЛВО_ЛЮДЕЙ) values ('P33302', 29);

INSERT INTO АКАДЕМ_ЧАС (НОМЕР, ВРЕМЯ_НАЧ, ВРЕМЯ_КОН) values (2, '10:00:00', '11:30:00');

INSERT INTO ДИСЦИПЛИНЫ (НАИМЕНОВАНИЕ) values ('Операционные системы');

INSERT INTO УЧИТЕЛЯ (КОД_ПРЕДМЕТА, ИМЯ, ФАМИЛИЯ, ОТЧЕСТВО) values (SELECT ИД FROM ДИСЦИПЛИНЫ WHERE НАИМЕНОВАНИЕ = 'Операционные системы', 'Сергей', 'Викторович','Клименков');




DROP TABLE РАСПИСАНИЕ;
DROP TABLE УЧИТЕЛЯ;
DROP TABLE ОСНАЩЕНИЕ_АУДИТОРИИ;
DROP TABLE АУДИТОРИИ;
DROP TABLE КОРПУСА;	
DROP TABLE ТИПЫ_ОБОРУДОВАНИЯ;	
DROP TABLE ГРУППЫ;
DROP TABLE АКАДЕМ_ЧАС;
DROP TABLE ДИСЦИПЛИНЫ;
DROP SEQUENCE corpus_seq;
DROP SEQUENCE equipment_type_seq;
DROP SEQUENCE audiences_seq;
DROP SEQUENCE group_seq;
DROP SEQUENCE academic_hour_seq;
DROP SEQUENCE disciplines_seq;
DROP SEQUENCE teacher_seq;
DROP SEQUENCE timetable_seq;

SELECT А.ИД FROM АУДИТОРИИ AS А LEFT JOIN КОРПУСА AS К ON А.КОД_КОРПУСА = К.ИД 
WHERE А.НОМЕР = 1216 AND К.АДРЕС = 'улица Ломоносова, 9';