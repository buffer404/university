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

	buffer_size = sprintf(buffer, "The net_device structure was obtained successfully!\n{\n\
		 name: %s (The name of this interface)\n \
		 dev_id: %d (Used to distinguish between devices that share the same address)\n \
		 flags: %u (Interface flags (a la BSD))\n \
		 dev_port: %d (Used to differentiate between devices that share a port)\n \
		 mtu: %d (The maximum transmission unit that is installed now)\n \
		 proto_down: %d (Protocol port state information can be sent to the switch driver)\n \
		 tx_queue_len: %d (The maximum number of frames that can be queued in the device transfer queue)\n}\n",
  net_dev->name, net_dev->dev_id, net_dev->flags, net_dev->dev_port, net_dev->mtu, net_dev->proto_down, net_dev->tx_queue_len);

	return write_response(user_buff, user_buffer_length, offset);
}

ssize_t get_pci_dev(char __user *user_buff, size_t user_buffer_length, loff_t *offset){

	my_pci_dev = pci_get_device(PCI_ANY_ID, PCI_ANY_ID, NULL);

	buffer_size = sprintf(buffer, "The pci_dev structure was obtained successfully!\n{\n\
		device: %d (Device id)\n \
		vendor: %d (Manufacturer's id)\n \
		devfn: %u (Encoded device & function index)\n \
		cfg_size: %d (Size of config space)\n \
		dma_mask: %lx (Mask of the bits of bus address this device implements.  Normally this is 0xffffffff.)\n}\n",
	my_pci_dev->device, my_pci_dev->vendor, my_pci_dev->devfn, my_pci_dev->cfg_size, my_pci_dev->dma_mask);

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