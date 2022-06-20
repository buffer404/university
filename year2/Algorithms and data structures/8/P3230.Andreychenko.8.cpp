#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int count, step, sum = 0;
    cin >> count >> step;
    int array[count];
    for (int i = 0; i < count; i++){
        cin >> array[i];
    }
    sort(array, array + count);
    for(size_t i = count; i>0; i--){
        if((count-i+1)%step!=0){
            sum+=array[i-1];
        }
    }
    cout << sum;
    return 0;
}