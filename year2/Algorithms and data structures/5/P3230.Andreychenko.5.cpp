#include <iostream>

using namespace std;


int main() {
    int cow, stall;
    int left_stall = 0;
    cin >> stall >> cow;
    int array [stall];
    for (int i = 0; i < stall; ++i) {
        cin >> array[i];
    }
    int right_stall = array[stall - 1] - array[0] + 1;
    while (right_stall - left_stall > 1) {
        int mid_stall = (left_stall + right_stall) / 2;
        int cows = 1;
        int first_cow = array[0];
        for (int cow : array) {
            if (cow - first_cow >= mid_stall) {
                first_cow = cow;
                cows+=1;
            }
        }
        if(cows >= cow){
            left_stall=mid_stall;
        } else{
            right_stall=mid_stall;
        }
    }
    cout << left_stall;
    return 0;
}
