	-- Удаление

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

	-- Создаем сущности

	create sequence corpus_seq;
	create table КОРПУСА(
		ИД bigint not null primary key default nextval('corpus_seq'),
		АДРЕС varchar(255)); 
		
	create sequence audiences_seq;
	create table АУДИТОРИИ(
		ИД bigint not null primary key default nextval('audiences_seq'),
		НОМЕР integer not null,
		ВМЕСТИМОСТЬ integer not null DEFAULT 30,
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
		ЧЕТНОСТЬ INTEGER DEFAULT 2,
		ДАТА date DEFAULT NULL);


-- Запросы

			-- Вывод расписания преподавателя
SELECT *
FROM РАСПИСАНИЕ
WHERE УЧ_ИД = 8;

			-- Единичный перенос занятия по дате
INSERT INTO РАСПИСАНИЕ(АУД_ИД, КОД_ПРЕДМЕТА, ГРУППА, УЧ_ИД, НОМЕР_ПАРЫ, ДЕНЬ_НЕДЕЛИ, ЧЕТНОСТЬ)
SELECT АУД_ИД, КОД_ПРЕДМЕТА, ГРУППА, УЧ_ИД, НОМЕР_ПАРЫ, ДЕНЬ_НЕДЕЛИ, ЧЕТНОСТЬ from РАСПИСАНИЕ
WHERE (УЧ_ИД,
       НОМЕР_ПАРЫ,
       ДЕНЬ_НЕДЕЛИ,
       ЧЕТНОСТЬ) = (8, 2, 4, TRUE);

UPDATE РАСПИСАНИЕ
SET (ДЕНЬ_НЕДЕЛИ,
     ЧЕТНОСТЬ,
     ДАТА) = (NULL,
              NULL,
              '11/11/2022')
WHERE ИД =
    (SELECT ИД
     FROM РАСПИСАНИЕ
     WHERE (УЧ_ИД,
            НОМЕР_ПАРЫ,
            ДЕНЬ_НЕДЕЛИ,
            ЧЕТНОСТЬ) = (8, 2, 4, TRUE)
     LIMIT 1);

			-- Единичный перенос занятия по дате и аудитории

SELECT * FROM РАСПИСАНИЕ WHERE ЧЕТНОСТЬ = 1;

CREATE FUNCTION rescheduling_a_class () RETURNS void AS $$
    SELECT ИД, НОМЕР
	FROM АУДИТОРИИ
	WHERE ВМЕСТИМОСТЬ >=
		(SELECT ВМЕСТИМОСТЬ
		FROM АУДИТОРИИ
		INNER JOIN РАСПИСАНИЕ ON АУДИТОРИИ.ИД = РАСПИСАНИЕ.АУД_ИД
		WHERE (УЧ_ИД,
			НОМЕР_ПАРЫ,
            ДЕНЬ_НЕДЕЛИ,
            ЧЕТНОСТЬ) = (2, 4, 1, 1))
		AND КОД_КОРПУСА =
			(SELECT КОД_КОРПУСА
			FROM АУДИТОРИИ
			INNER JOIN РАСПИСАНИЕ ON АУДИТОРИИ.ИД = РАСПИСАНИЕ.АУД_ИД
			WHERE (УЧ_ИД,
				НОМЕР_ПАРЫ,
				ДЕНЬ_НЕДЕЛИ,
				ЧЕТНОСТЬ) = (2, 4, 1, 1));

	update РАСПИСАНИЕ set (ДАТА, АУД_ИД) = ('13/11/2022', 4) where 
		(УЧ_ИД, НОМЕР_ПАРЫ, ДЕНЬ_НЕДЕЛИ, ЧЕТНОСТЬ) = (2, 4, 1, 1);
$$ LANGUAGE SQL;

SELECT rescheduling_a_class();

SELECT * FROM РАСПИСАНИЕ WHERE ЧЕТНОСТЬ = 1;


			-- Запрос аудитории с оборудованием
SELECT НОМЕР
FROM АУДИТОРИИ
WHERE ВМЕСТИМОСТЬ >= 50
  AND ИД IN
    (SELECT АУД_ИД
     FROM ОСНАЩЕНИЕ_АУДИТОРИИ
     INNER JOIN ТИПЫ_ОБОРУДОВАНИЯ ON ОБОРУД_ИД = ИД
     WHERE НАИМЕНОВАНИЕ = 'ZOOM');

			-- Вывод расписания группы
					
DROP FUNCTION group_schedule();
			
CREATE FUNCTION group_schedule () RETURNS TABLE (
			ИД bigint, 
			УЧ_ИД bigint)
  LANGUAGE plpgsql  AS $$
    SELECT ИД, УЧ_ИД
	FROM РАСПИСАНИЕ
	WHERE ГРУППА = (SELECT ИД FROM ГРУППЫ WHERE КОД_ГРУППЫ = 'P33301');
$$;	

SELECT * from group_schedule();		
	
--Индексы

CREATE INDEX capacity_idx ON АУДИТОРИИ (ВМЕСТИМОСТЬ);
CREATE INDEX teacher_idx ON РАСПИСАНИЕ (УЧ_ИД);
CREATE INDEX lesson_index_idx ON РАСПИСАНИЕ (НОМЕР_ПАРЫ);
CREATE INDEX group_idx ON РАСПИСАНИЕ (ГРУППА);




