#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {
    vector<string> big_num;
    string num;
    while (cin >> num){
        big_num.push_back(num);
        cout << endl;
        for(size_t i = big_num.size()-1; i>0; i--) {
            for (size_t j = 0; j < i; j++) {
                if (big_num[j + 1] + big_num[j] > big_num[j] + big_num[j + 1]) {
                    swap(big_num[j], big_num[j + 1]);
                }
            }
        }
    }
    for(string & i : big_num){
        cout << i;
    }
    return 0;
}

//2
//20
//004
//66
