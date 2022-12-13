#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/types.h>
#include<linux/slab.h>
#include <linux/debugfs.h>
#include <linux/pid.h>
#include <linux/sched.h>
#include <linux/moduleparam.h>
#include <linux/pci.h>
#include <linux/netdevice.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Leonid Andreychenko");
MODULE_DESCRIPTION("Get PCI devices info");

#define MODULE_NAME "request"
#define BUFFER_MAX_SIZE 1024
#define NET_DEVICE 1
#define PCI_DEV 2

struct dentry* request = NULL;
u64 pid = NULL;
u64 struct_num = NULL;

char buffer [BUFFER_MAX_SIZE];
size_t buffer_size = 0;

struct pci_dev* my_pci_dev;
struct net_device* net_dev;
