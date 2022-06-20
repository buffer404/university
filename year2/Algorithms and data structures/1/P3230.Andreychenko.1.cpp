#include <iostream>

int main() {
    int n;
    std::cin >> n;
    int top1[5] = {0, 1, 1, 0, 0}; // count, 1st index, last index, n-1, n
    int top2[5] = {0, 1, 1, 0, 0};
    for(int i=1; i<n+1; i++){
        int x;
        std::cin >> x;
        if(x==top2[4] && x==top2[3]){
            if(top2[0]>top1[0]){
                for (int j = 0; j < 4; ++j) {
                    top1[j]=top2[j];
                }
            }
            top2[0]=2;
            top2[1]=i-1;
            top2[2]=i;
        }
        else{
            top2[0]+=1;
            top2[2]=i;
            top2[3]=top2[4];
            top2[4]=x;
        }
    }
//    std::cout << top1[0] << ' ' << top1[1] << ' ' << top1[2] << ' ' << top1[3] << ' ' << top1[4] << '\n';
//    std::cout << top2[0] << ' ' << top2[1] << ' ' << top2[2] << ' ' << top2[3] << ' ' << top2[4] << '\n';
    if(top2[0]>top1[0]){
        std::cout << top2[1] << ' ' << top2[2];
    }
    else{
        std::cout << top1[1] << ' ' << top1[2];
    }

    return 0;
}
