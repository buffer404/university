#include <linux/module.h>
#include <linux/version.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/types.h>
#include <linux/kdev_t.h>
#include <linux/fs.h>
#include <linux/device.h>
#include <linux/cdev.h>
#include <linux/proc_fs.h>
#include <linux/string.h>
#include <linux/uaccess.h>
#include <linux/vmalloc.h>

#define SPACE_CHAR ' '
#define MAX_BUFFER_SIZE 1024
#define MAX_SIZE_T_LEN 22
#define FILE_NAME "var1"

MODULE_LICENSE("GPL");
MODULE_AUTHOR("ITMO");
MODULE_VERSION("1.0");

static dev_t first;
static struct cdev c_dev; 
static struct class *cl;
static struct proc_dir_entry *entry;
struct device_list *device_list_head = NULL;


struct device_list {
	size_t count;
  	struct device_list *next;
};
