#include "my_module.h"


ssize_t write_response(char __user *user_buff, size_t user_buffer_length, loff_t *offset){
	ssize_t ret = buffer_size;

	if (*offset >= buffer_size || copy_to_user(user_buff, buffer, buffer_size)) {
			pr_info("copy_to_user failed\n");
			ret = 0;
	} else {
			pr_info("procfile read %s\n");
			*offset += buffer_size;
	}

	return ret;
}

ssize_t get_net_device(char __user *user_buff, size_t user_buffer_length, loff_t *offset){

	net_dev = first_net_device(&init_net);

	buffer_size = sprintf(buffer, "The net_device structure was obtained successfully!\n{\n name: %s\n irq: %d\n type: %d\n dma: %d\n mtu: %d\n tx_queue_len: %d\n watchdog_timeo: %d\n mem_start: %d\n}\n",
  net_dev->name, net_dev->irq, net_dev->type, net_dev->dma, net_dev->mtu, net_dev->tx_queue_len, net_dev->watchdog_timeo, net_dev->mem_start);

	return write_response(user_buff, user_buffer_length, offset);
}

ssize_t get_pci_dev(char __user *user_buff, size_t user_buffer_length, loff_t *offset){

	my_pci_dev = pci_get_device(PCI_ANY_ID, PCI_ANY_ID, NULL);

	buffer_size = sprintf(buffer, "The pci_dev structure was obtained successfully!\n{\n device: %d\n vendor: %d\n devfn: %d\n subsystem_device: %d\n subsystem_vendor: %d\n}\n",
	my_pci_dev->device, my_pci_dev->vendor, my_pci_dev->devfn, my_pci_dev->subsystem_device, my_pci_dev->subsystem_vendor);

	return write_response(user_buff, user_buffer_length, offset);
}

ssize_t file_read(struct file *filePointer, char __user *user_buff,
                             size_t user_buffer_length, loff_t *offset){

		if (struct_num == NULL){
			pr_err("My_module: No input data, please enter them");
			return(-EFAULT);
		}

		if (struct_num == NET_DEVICE) {
			return get_net_device(user_buff, user_buffer_length, offset);
		}

		if (struct_num == PCI_DEV) {
			return get_pci_dev(user_buff, user_buffer_length, offset);
		}

    return -EFAULT;
}

ssize_t file_write(struct file *file, const char __user *user_buff,
                              size_t len, loff_t *off){
    buffer_size = len;
    if (buffer_size > BUFFER_MAX_SIZE)
        buffer_size = BUFFER_MAX_SIZE;

    if (copy_from_user(buffer, user_buff, buffer_size))
        return -EFAULT;

		int count_of_args = sscanf(buffer, "%d" , &struct_num);

		if(count_of_args != 1 || struct_num == NULL){
			pr_err("Invalid input format!");
			return -EFAULT;
		}

    return buffer_size;
}

struct file_operations add_ops = {
        .read = file_read,
        .write = file_write
};

int __init my_module_init(void) {
	pr_info("My_module start init\n");
	request = debugfs_create_file(MODULE_NAME, 0777, NULL, NULL, &add_ops);
	if (!request) {
			pr_err("debugfs_my_module: failed to create \n");
			return -1;
	}
	return 0;
}

void __exit my_module_exit(void) {
    debugfs_remove_recursive(request);
    pr_info("my_module unloaded\n");
}

module_init(my_module_init);
module_exit(my_module_exit);
