#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

#include <linux/debugfs.h>

#include <linux/pid.h>
#include <linux/sched.h>

MODULE_LICENSE("Dual BSD/GPL");
MODULE_DESCRIPTION("Stab linux module for labs");
MODULE_VERSION("1.0");

static struct dentry *kmod_root;
static struct dentry *pid1_task_struct;
static struct debugfs_blob_wrapper pid1_wrapper;
static struct task_struct *ts1;

static int __init kmod_init(void) {
    printk(KERN_INFO "kmod: module loading.\n");

    kmod_root = debugfs_create_dir("kmod", NULL);
    ts1 = get_pid_task(find_get_pid(1), PIDTYPE_PID);
    pid1_wrapper.data = (void*)ts1;
    pid1_wrapper.size = sizeof(struct task_struct);

    pid1_task_struct = debugfs_create_blob("init", 0644, kmod_root, &pid1_wrapper);
    if (pid1_task_struct == NULL) return 1;

    printk(KERN_INFO "kmod: data size is %ld.\n", pid1_wrapper.size);

    return 0;
}

static void __exit kmod_exit(void) {
    debugfs_remove_recursive(kmod_root);
    printk(KERN_INFO "kmod: module unloaded\n");
}

module_init(kmod_init);
module_exit(kmod_exit);