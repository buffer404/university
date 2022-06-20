#include <iostream>

int main() {
    int a,b,c,d,k,count;
    std::cin >> a >> b >> c >> d >> k;
    for(int i=0; i<k; i++){
        count = a*b;
        if(count-c<=0){
            a=0;
            break;
        }
        else if(count-c>d){
            a=d;
        }
        else{
            a=count-c;
        }
        if(a*b==count){
            break;
        }
    }
    std::cout << a;
    return 0;
}