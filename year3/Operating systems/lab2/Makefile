obj-m += my_module.o
KDIR  := /lib/modules/$(shell uname -r)/build

all:
	gcc user.c -o user
	make -C $(KDIR) M=$(PWD) modules
	insmod my_module.ko

clean:
	make -C $(KDIR) M=$(PWD) clean
