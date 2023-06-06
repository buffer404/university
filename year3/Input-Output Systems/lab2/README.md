# Лабораторная работа 2

**Название:** "Разработка драйверов блочных устройств"

**Цель работы:**  получить знания и навыки разработки драйверов блочных
устройств для операционной системы Linux. 

## Описание функциональности драйвера

Создать один первичный раздел размером 10Мбайт и один
расширенный раздел, содержащий два логических раздела
размером 20Мбайт каждый.

## Инструкция по сборке

`make` - собрать проект 
`make clean` - очистить проект

## Инструкция пользователя

Для начала проверим правильности диска:

```
sudo fdisk -l /dev/vramdisk
```



```
Disk /dev/vramdisk: 50 MiB, 52428800 bytes, 102400 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x36e5756d

Device         Boot Start    End Sectors Size Id Type
/dev/vramdisk1          1  20480   20480  10M 83 Linux
/dev/vramdisk2      20481 102400   81920  40M  5 Extended
/dev/vramdisk5      20481  61440   40960  20M 83 Linux
/dev/vramdisk6      61442 102401   40960  20M 83 Linux
```

Создадим файловую систему:

```
mkfs.vfat /dev/vramdisk1
mkfs.vfat /dev/vramdisk5
mkfs.vfat /dev/vramdisk6 
```

И с монтируем:

```
mount -t vfat /dev/vramdisk1 /mnt/dev1
mount -t vfat /dev/vramdisk5 /mnt/dev5
mount -t vfat /dev/vramdisk6 /mnt/dev6
```

## Примеры использования

Создадим тестовый большой файл:

```
fallocate -l 7M /mnt/dev1/file
```

Проверим скорость:

```
root@IO-System:/home/leonid/Desktop/lab2# pv /mnt/dev1/file > /mnt/dev5/file
7,00MiB 0:00:00 [ 189MiB/s] [==========================================================================================>] 100%            
```

```
root@IO-System:/home/leonid/Desktop/lab2# pv /mnt/dev1/file > ~/file
7,00MiB 0:00:00 [ 264MiB/s] [==========================================================================================>] 100%            
```