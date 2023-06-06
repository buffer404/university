make clean

make

fdisk -l /dev/vramdisk

mkfs.vfat /dev/vramdisk1
mkfs.vfat /dev/vramdisk5
mkfs.vfat /dev/vramdisk6

mount -t vfat /dev/vramdisk1 /mnt/dev1
mount -t vfat /dev/vramdisk5 /mnt/dev5
mount -t vfat /dev/vramdisk6 /mnt/dev6

fallocate -l 7M /mnt/dev1/file

pv /mnt/dev1/file > /mnt/dev5/file

pv /mnt/dev1/file > ~/file

