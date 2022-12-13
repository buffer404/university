#include <stdlib.h>

int main(){

  int request;
  int iter;

  printf("%s\n", "Hello, select the structure type\n1: net_device\n2: pci_dev");

  scanf("%d", &request);

  if (request == 1){
    iter = system("echo 1 >> /sys/kernel/debug/request");
  } else if (request == 2){
    iter = system("echo 2 >> /sys/kernel/debug/request");
  }

  int r = system("cat /sys/kernel/debug/request");

  return r;
}
