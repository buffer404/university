1
SELECT НТ.ИД, НВ.ИД 
FROM Н_ТИПЫ_ВЕДОМОСТЕЙ AS НТ LEFT JOIN Н_ВЕДОМОСТИ AS НВ 
ON НТ.ИД = НВ.ТВ_ИД 
WHERE НТ.НАИМЕНОВАНИЕ < 'Перезачет' AND НВ.ЧЛВК_ИД > 117219 AND НВ.ЧЛВК_ИД > 142390;

select НАИМЕНОВАНИЕ from Н_ТИПЫ_ВЕДОМОСТЕЙ;

------------------------------------------------
2 
SELECT НЛ.ИД, НО.ЧЛВК_ИД, НУ.ИД 
FROM Н_ЛЮДИ AS НЛ LEFT JOIN Н_ОБУЧЕНИЯ AS НО 
ON НЛ.ИД = НО.ЧЛВК_ИД
LEFT JOIN Н_УЧЕНИКИ AS НУ 
ON НЛ.ИД = НУ.ЧЛВК_ИД
WHERE НЛ.ИМЯ < 'Ярослав' AND НО.ЧЛВК_ИД > 163484;

SELECT ИД FROM Н_ЛЮДИ order by ИД DESC;
------------------------------------------------
3
SELECT EXISTS( 
	SELECT FROM Н_ЛЮДИ AS НЛ
		JOIN Н_УЧЕНИКИ AS НУ ON НЛ.ИД = НУ.ЧЛВК_ИД
	WHERE (НУ.ГРУППА = '3102' AND НЛ.ДАТА_РОЖДЕНИЯ < '1997-01-01 00:00:00' ));
------------------------------------------------	
4

-- Получаем id людей, которые проходят очную форму обучения

SELECT ЧЛВК_ИД FROM Н_УЧЕНИКИ WHERE ПЛАН_ИД IN(
  SELECT ИД FROM Н_ПЛАНЫ
  WHERE ФО_ИД = 1); 
  
-- Найти фамилии которые встречаются в списке менее 50 раз
SELECT ФАМИЛИЯ, count_lastname FROM (
  SELECT ФАМИЛИЯ, COUNT(ФАМИЛИЯ) AS count_lastname
  FROM Н_ЛЮДИ GROUP BY ФАМИЛИЯ  
  )AS f1 
WHERE count_lastname < 50;


-- Объеденяем 


SELECT ФАМИЛИЯ, count_lastname FROM (
  SELECT ФАМИЛИЯ, COUNT(ФАМИЛИЯ) AS count_lastname
  FROM (
	SELECT ЧЛВК_ИД, ФАМИЛИЯ FROM Н_УЧЕНИКИ JOIN Н_ЛЮДИ ON Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД WHERE ПЛАН_ИД IN(
		SELECT ИД FROM Н_ПЛАНЫ
		WHERE ФО_ИД = 1)) f2
	GROUP BY ФАМИЛИЯ  
  )AS f1 
WHERE count_lastname < 50;


------------------------------------------------

5
-- Максимальный возраст (дата рождения) в группе 3100

SELECT 2022 - round(date_part('year', (MIN(ДАТА_РОЖДЕНИЯ)))) as max_age FROM (
	SELECT ДАТА_РОЖДЕНИЯ FROM Н_ЛЮДИ AS НЛ 
		JOIN Н_УЧЕНИКИ AS НУ ON НЛ.ИД = НУ.ЧЛВК_ИД 
		WHERE (ГРУППА = '3100')) AS f1;		
		

-- Таблица со средним возрастом студентов во всех группах

SELECT ГРУППА, (2022 - round(avg(date_part('year', ДАТА_РОЖДЕНИЯ)))) as avg_age
FROM Н_УЧЕНИКИ AS НУ JOIN Н_ЛЮДИ AS НЛ
ON НЛ.ИД = НУ.ЧЛВК_ИД GROUP BY ГРУППА;

-- Запрос

SELECT * FROM (
	SELECT ГРУППА, (2022 - round(avg(date_part('year', ДАТА_РОЖДЕНИЯ)))) as avg_age
	FROM Н_УЧЕНИКИ AS НУ JOIN Н_ЛЮДИ AS НЛ
	ON НЛ.ИД = НУ.ЧЛВК_ИД GROUP BY ГРУППА
	) AS F2 
WHERE avg_age < (
	SELECT (2022 - round(date_part('year', (MIN(ДАТА_РОЖДЕНИЯ))))) FROM (
		SELECT ДАТА_РОЖДЕНИЯ FROM Н_ЛЮДИ AS НЛ 
		JOIN Н_УЧЕНИКИ AS НУ 
		ON НЛ.ИД = НУ.ЧЛВК_ИД 
		WHERE (ГРУППА = '3100')) AS f1
);

-------------------------------------------------

6 

1) 618
2) 34
-- заочно -> Н_ПЛАНЫ.ФО_ИД = 3 НЕТ ЗАОЧКИ
-- Получить отчисленных студентов с заочной формы обучения до 1го сенября 2012

SELECT ЧЛВК_ИД FROM Н_УЧЕНИКИ AS НУ 
JOIN Н_ПЛАНЫ AS НП ON НУ.ПЛАН_ИД = НП.ИД
WHERE (ПРИЗНАК = 'отчисл') AND (ФО_ИД = 3) AND (КОНЕЦ < '2012-09-01 00:00:00');

--Запрос

SELECT ГРУППА, НЛ.ИД, ИМЯ, ФАМИЛИЯ, ОТЧЕСТВО, П_ПРКОК_ИД 
FROM Н_ЛЮДИ AS НЛ JOIN Н_УЧЕНИКИ AS НУ ON НЛ.ИД = НУ.ЧЛВК_ИД 
WHERE НЛ.ИД IN (
	SELECT ЧЛВК_ИД FROM Н_УЧЕНИКИ AS НУ 
	JOIN Н_ПЛАНЫ AS НП ON НУ.ПЛАН_ИД = НП.ИД
	WHERE (ПРИЗНАК = 'отчисл') AND (ФО_ИД = 3) AND (КОНЕЦ < '2012-09-01 00:00:00'));


-------------------------------------------------

7

-- Вывести имена людей, и количество встречаний

SELECT ИМЯ, COUNT(ИМЯ) AS count_name FROM Н_ЛЮДИ GROUP BY ИМЯ;	

-- Запрос
	
SELECT ИМЯ, ИД FROM Н_ЛЮДИ WHERE ИМЯ IN (
	SELECT ИМЯ FROM (
		SELECT ИМЯ, COUNT(ИМЯ) AS count_name 
		FROM Н_ЛЮДИ GROUP BY ИМЯ) AS f1 
	WHERE f1.count_name	> 1)
ORDER BY ИМЯ;


SELECT COUNT(*) AS COUNT
 FROM (
  SELECT Н_УЧЕНИКИ.ИД
  FROM Н_УЧЕНИКИ
  JOIN Н_ВЕДОМОСТИ ON Н_УЧЕНИКИ.ЧЛВК_ИД=Н_ВЕДОМОСТИ.ЧЛВК_ИД
  WHERE Н_УЧЕНИКИ.ГРУППА='3100' 
  AND (Н_ВЕДОМОСТИ.ОЦЕНКА='5' OR Н_ВЕДОМОСТИ.ОЦЕНКА='зачет')
 ) as otlichniki;
 
SELECT * FROM Н_ЛЮДИ 
WHERE ИД IN(
	SELECT Н_УЧЕНИКИ.ИД
	FROM Н_УЧЕНИКИ
	JOIN Н_ВЕДОМОСТИ ON Н_УЧЕНИКИ.ЧЛВК_ИД=Н_ВЕДОМОСТИ.ЧЛВК_ИД
	WHERE Н_УЧЕНИКИ.ГРУППА='3100') 
AND ИД IN (
	SELECT DISTINCT ЧЛВК_ИД 
	FROM Н_ВЕДОМОСТИ 
	WHERE (ОЦЕНКА = '5') OR (ОЦЕНКА = 'зачет')); 




SELECT DISTINCT ЧЛВК_ИД FROM Н_ВЕДОМОСТИ WHERE (ОЦЕНКА = '5') OR (ОЦЕНКА = 'зачет');