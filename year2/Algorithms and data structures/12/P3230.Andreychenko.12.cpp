#include <iostream>
using namespace std;
int main() {
    int n, k;
    cin >> n >> k;
    int nums [n];
    int min [2] = {-1, -1}; // value, index
    for (int i = 0; i < n; ++i) {
        cin >> nums[i];
    }
    for (int i = 0; i < n-k+1; ++i) {
        int local_end = i+k;
        if (min[1] < i){
            min[0] = nums[i];
            for (int j = i+1; j < local_end; ++j) {
                if (nums[j] <= min[0]) {
                    min[0] = nums[j];
                    min[1] = j;
                }
            }
        }
        else if (nums[local_end - 1] <= min[0]) {
            min[0] = nums[local_end - 1];
            min[1] = local_end - 1;
        }
        cout << min[0] << ' ';
    }
    return 0;
}
