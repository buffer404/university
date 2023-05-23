#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/version.h>
#include <linux/netdevice.h>
#include <linux/etherdevice.h>
#include <linux/moduleparam.h>
#include <linux/in.h>
#include <net/arp.h>
#include <linux/ip.h>
#include <linux/udp.h>
#include <linux/icmp.h>
#include <linux/string.h>
#include <linux/proc_fs.h>


#define IPPROTO_ICMP 1

static char* link = "enp0s3";
module_param(link, charp, 0);

static char* ifname = "vni%d";
static unsigned char data[1500];
static unsigned char stat_data[2048];
static int user_type = 8;
static unsigned char yadro_buff[128];
static struct proc_dir_entry *entry;

static ssize_t proc_write(struct file *file, const char __user * ubuf, size_t count, loff_t* ppos){
    
    
    copy_from_user(&yadro_buff, ubuf, count); 
    int temp = sscanf(yadro_buff, "%d", &user_type);
    printk(yadro_buff);
    return count;
}

static ssize_t proc_read(struct file *file, char __user * ubuf, size_t count, loff_t* ppos){
    size_t len = strlen(stat_data);
    if (*ppos > 0 || count < len){
        return 0;
    }
    if (copy_to_user(ubuf, stat_data, len) != 0){
        return -EFAULT;
    }
    *ppos = len;
    return len;
}

static struct proc_ops proc_ops = {
        .proc_read = proc_read,
        .proc_write = proc_write,
};


static struct net_device_stats stats;

static struct net_device *child = NULL;
struct priv {
    struct net_device *parent;
};

int strcmp(const char *str1, const char *str2) {
    while (*str1 && (*str1 == *str2)) {
        str1++;
        str2++;
    }

    return *(unsigned char*)str1 - *(unsigned char*)str2;
}

static char check_frame(struct sk_buff *skb, unsigned char data_shift) {
    unsigned char *user_data_ptr = NULL;
    struct iphdr *ip = (struct iphdr *)skb_network_header(skb);
    struct icmphdr *icmp = NULL;
    unsigned int data_len = 0;

    if (IPPROTO_ICMP == ip->protocol) {
        icmp = (struct icmphdr*)((unsigned char*)ip + (ip->ihl * 4));
        unsigned int packet_len = ntohs(ip->tot_len);
        data_len = packet_len - sizeof(struct icmphdr) - ip->ihl * 4;
        user_data_ptr = (unsigned char *)(skb->data + sizeof(struct iphdr)  + sizeof(struct icmphdr)) + data_shift;
        memcpy(data, user_data_ptr, data_len);
        data[data_len] = '\0';


        unsigned char tmp_buf[1024];

        if (icmp->type == user_type){
            printk(KERN_INFO "ICMP package data: %s", data);

            sprintf(tmp_buf, "Captured ICMP package, saddr: %d.%d.%d.%d\n",
                    ntohl(ip->saddr) >> 24, (ntohl(ip->saddr) >> 16) & 0x00FF,
                    (ntohl(ip->saddr) >> 8) & 0x0000FF, (ntohl(ip->saddr)) & 0x000000FF);
            strcat(stat_data, tmp_buf);
            sprintf(tmp_buf, "daddr: %d.%d.%d.%d\n",
                    ntohl(ip->daddr) >> 24, (ntohl(ip->daddr) >> 16) & 0x00FF,
                    (ntohl(ip->daddr) >> 8) & 0x0000FF, (ntohl(ip->daddr)) & 0x000000FF);
            strcat(stat_data, tmp_buf);
            sprintf(tmp_buf, "Data length: %d. Data:", data_len);
            strcat(stat_data, tmp_buf);
            sprintf(tmp_buf, "%s\n\n", data);
            strcat(stat_data, tmp_buf);
        }


        return 1;

    }
    return 0;
}

static rx_handler_result_t handle_frame(struct sk_buff **pskb) {

    if (check_frame(*pskb, 0)) {
        stats.rx_packets++;
        stats.rx_bytes += (*pskb)->len;
    }
    (*pskb)->dev = child;
    return RX_HANDLER_ANOTHER;
}

static int open(struct net_device *dev) {
    netif_start_queue(dev);
    printk(KERN_INFO "%s: device opened", dev->name);
    return 0;
}

static int stop(struct net_device *dev) {
    netif_stop_queue(dev);
    printk(KERN_INFO "%s: device closed", dev->name);
    return 0;
}

static netdev_tx_t start_xmit(struct sk_buff *skb, struct net_device *dev) {
    struct priv *priv = netdev_priv(dev);

    if (check_frame(skb, 14)) {
        stats.tx_packets++;
        stats.tx_bytes += skb->len;
    }

    if (priv->parent) {
        skb->dev = priv->parent;
        skb->priority = 1;
        dev_queue_xmit(skb);
        return 0;
    }
    return NETDEV_TX_OK;
}

static struct net_device_stats *get_stats(struct net_device *dev) {
    return &stats;
}

static struct net_device_ops net_device_ops = {
        .ndo_open = open,
        .ndo_stop = stop,
        .ndo_get_stats = get_stats,
        .ndo_start_xmit = start_xmit
};

static void setup(struct net_device *dev) {
    int i;
    ether_setup(dev);
    memset(netdev_priv(dev), 0, sizeof(struct priv));
    dev->netdev_ops = &net_device_ops;

    //fill in the MAC address
    for (i = 0; i < ETH_ALEN; i++)
        dev->dev_addr[i] = (char)i;
}

int __init vni_init(void) {
    int err = 0;
    struct priv *priv;
    entry = proc_create("virt_net_if", 0444, NULL, &proc_ops);

    child = alloc_netdev(sizeof(struct priv), ifname, NET_NAME_UNKNOWN, setup);
    if (child == NULL) {
        printk(KERN_ERR "%s: allocate error", THIS_MODULE->name);
        return -ENOMEM;
    }
    priv = netdev_priv(child);
    priv->parent = __dev_get_by_name(&init_net, link); //parent interface
    if (!priv->parent) {
        printk(KERN_ERR "%s: no such net: %s", THIS_MODULE->name, link);
        free_netdev(child);
        return -ENODEV;
    }
    if (priv->parent->type != ARPHRD_ETHER && priv->parent->type != ARPHRD_LOOPBACK) {
        printk(KERN_ERR "%s: illegal net type", THIS_MODULE->name);
        free_netdev(child);
        return -EINVAL;
    }

    //copy IP, MAC and other information
    memcpy(child->dev_addr, priv->parent->dev_addr, ETH_ALEN);
    memcpy(child->broadcast, priv->parent->broadcast, ETH_ALEN);
    if ((err = dev_alloc_name(child, child->name))) {
        printk(KERN_ERR "%s: allocate name, error %i", THIS_MODULE->name, err);
        free_netdev(child);
        return -EIO;
    }

    register_netdev(child);
    rtnl_lock();
    netdev_rx_handler_register(priv->parent, &handle_frame, NULL);
    rtnl_unlock();
    printk(KERN_INFO "Module %s loaded", THIS_MODULE->name);
    printk(KERN_INFO "%s: create link %s", THIS_MODULE->name, child->name);
    printk(KERN_INFO "%s: registered rx handler for %s", THIS_MODULE->name, priv->parent->name);
    return 0;
}

void __exit vni_exit(void) {
    proc_remove(entry);

    struct priv *priv = netdev_priv(child);
    if (priv->parent) {
        rtnl_lock();
        netdev_rx_handler_unregister(priv->parent);
        rtnl_unlock();
        printk(KERN_INFO "%s: unregister rx handler for %s", THIS_MODULE->name, priv->parent->name);
    }
    unregister_netdev(child);
    free_netdev(child);
    printk(KERN_INFO "Module %s unloaded", THIS_MODULE->name);
}

module_init(vni_init);
module_exit(vni_exit);

MODULE_AUTHOR("Leonid");
MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("Description");

