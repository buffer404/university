SELECT НТВ.ИД, НВ.ЧЛВК_ИД FROM Н_ТИПЫ_ВЕДОМОСТЕЙ AS НТВ 
INNER JOIN Н_ВЕДОМОСТИ AS НВ ON НТВ.ИД = НВ.ТВ_ИД
WHERE (НТВ.НАИМЕНОВАНИЕ > 'Перезачет')
AND (НВ.ДАТА > '2010-06-18')
AND (НВ.ДАТА = '1998-01-05');

CREATE INDEX ON "Н_ТИПЫ_ВЕДОМОСТЕЙ" USING HASH ("ИД");
CREATE INDEX ON "Н_ТИПЫ_ВЕДОМОСТЕЙ" USING BTREE ("НАИМЕНОВАНИЕ");
CREATE INDEX ON "Н_ВЕДОМОСТИ" USING BTREE ("ДАТА");

-------------------------------------------------------------

SELECT НЛ.ИМЯ, НО.ЧЛВК_ИД, НУ.НАЧАЛО FROM Н_ЛЮДИ AS НЛ 
LEFT JOIN Н_ОБУЧЕНИЯ AS НО ON НЛ.ИД = НО.ЧЛВК_ИД 
LEFT JOIN Н_УЧЕНИКИ AS НУ ON НЛ.ИД = НУ.ЧЛВК_ИД WHERE 
НЛ.ИМЯ = 'Роман' AND НО.ЧЛВК_ИД = 163276;

CREATE INDEX ON "Н_ЛЮДИ" USING HASH ("ИМЯ");
CREATE INDEX ON "Н_ЛЮДИ" USING HASH ("ИД");
CREATE INDEX ON "Н_ОБУЧЕНИЯ" USING HASH ("ЧЛВК_ИД");
CREATE INDEX ON "Н_УЧЕНИКИ" USING HASH ("ЧЛВК_ИД");

