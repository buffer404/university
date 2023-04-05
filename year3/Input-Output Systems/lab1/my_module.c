#include "my_module.h"

static void create_device_list_node(size_t value) {
	if (device_list_head == NULL) {
    		device_list_head = vmalloc(sizeof(struct device_list));
  	} else {
  		struct device_list *device_list_temp = vmalloc(sizeof(struct device_list));
    		device_list_temp->next = device_list_head;
    		device_list_head = device_list_temp;
  	}
  	device_list_head->count = value;
}


static int my_open(struct inode *i, struct file *f)
{
  return 0;
}

static int my_close(struct inode *i, struct file *f)
{
  return 0;
}

static ssize_t my_read(struct file *f, char __user *buf, size_t len, loff_t *off)
{
  return 0;
}

static ssize_t my_write(struct file *f, const char __user *buf,  size_t len, loff_t *off)
{
  	size_t char_count = len - 1;

  	create_device_list_node(char_count);
  	
  	return len;
} 

static int my_dev_uevent(struct device *dev, struct kobj_uevent_env *env)
{
    	add_uevent_var(env, "DEVMODE=%#o", 0666);
    	return 0;
}

static struct file_operations mychdev_fops =
{
  .owner      = THIS_MODULE,
  .open       = my_open,
  .release    = my_close,
  .read       = my_read,
  .write      = my_write
};



static ssize_t proc_write(struct file *file, const char __user * ubuf, size_t count, loff_t* ppos) 
{
	return -1;
}

static ssize_t proc_read(struct file *file, char __user * ubuf, size_t count, loff_t* ppos) 
{
	char buf[MAX_BUFFER_SIZE];
  	char tmp_buf[MAX_SIZE_T_LEN];
  	
  	sprintf(buf, "History buffer: ");
  	
  	struct device_list *tmp_head = device_list_head;
  	size_t len = strlen(buf);
  	while(tmp_head != NULL && len + MAX_SIZE_T_LEN + 2 < MAX_BUFFER_SIZE) {
  		sprintf(tmp_buf, "%zu, ", tmp_head->count);
    		strcat(buf, tmp_buf);
   		tmp_head = tmp_head->next;
  		len = strlen(buf);
  	}
  	strcat(buf, "\n");
  	buf[len-2] = ' ';
  	len = strlen(buf);
  	
	if (*ppos > 0 || count < len){
		return 0;
	}
	if (copy_to_user(ubuf, buf, len) != 0)
	{
		return -EFAULT;
	}
	*ppos = len;
	
	pr_info("%s", ubuf);
	return len;
}

static struct proc_ops proc_ops = {
	.proc_read = proc_read,
	.proc_write = proc_write,
};



static int __init ch_drv_init(void){

	pr_info("My_module start init\n");
    	entry = proc_create(FILE_NAME, 0444, NULL, &proc_ops);
    	if(entry == NULL) return -1;
    	
     	pr_info("File /proc/var1 added successfully!\n");
     	
    	if (alloc_chrdev_region(&first, 0, 1, "ch_dev") < 0)
		return -1;
	  
    	if ((cl = class_create(THIS_MODULE, "chardrv")) == NULL)
	{
		unregister_chrdev_region(first, 1);
		return -1;
	}

    	cl->dev_uevent = my_dev_uevent;

    	if (device_create(cl, NULL, first, NULL, FILE_NAME) == NULL)
	{
		  class_destroy(cl);
		  unregister_chrdev_region(first, 1);
		  return -1;
	}
	
	pr_info("File /dev/var1 added successfully!\n");
	
    	cdev_init(&c_dev, &mychdev_fops);
    	if (cdev_add(&c_dev, first, 1) == -1)
	{
		device_destroy(cl, first);
		class_destroy(cl);
		unregister_chrdev_region(first, 1);
		return -1;
	}
	
	pr_info("Initialization was successful!\n");
	
    	return 0;
}

static void __exit ch_drv_exit(void){
    	proc_remove(entry);
    	cdev_del(&c_dev);
    	device_destroy(cl, first);
    	class_destroy(cl);
    	unregister_chrdev_region(first, 1);
    	pr_info("My_module unloaded successfully\n");
}
 
module_init(ch_drv_init);
module_exit(ch_drv_exit);
