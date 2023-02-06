# Низкоуровневое программирование
## Лабораторная работа №1
#### Задание
Вариант - документное дерево
##### Сборка *NIX
-  ```make build``` - сборка проекта
- ```./main```  - запуск 
##### Сборка Windows
-  ```make -f Makefile_win build``` - сборка проекта
- ```./main.exe```  - запуск 

##### Генератор данных
Расположен в папке ```data_generator/data_generator.py```
Передаваемые на вход значеия ```{count} {field1_type} {field2_type} ... {fieldn_type} ```
Доступные значения
-  ```string``` - строка
- ```integer```  - целое число 
- ```float```  - дробное число
- ```boolean``` - булевый тип
Пример запуска
```py data_generator.py 1000 string integer float boolean```