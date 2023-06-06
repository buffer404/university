obj-m += my_module.o
KDIR  := /lib/modules/$(shell uname -r)/build

all:
	make -C $(KDIR) M=$(PWD) modules
	insmod my_module.ko

clean:
	make -C $(KDIR) M=$(PWD) clean
	rmmod my_module
