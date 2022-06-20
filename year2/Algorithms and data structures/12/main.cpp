#include <iostream>

using namespace std;

int main() {
    int n, k;
    cin >> n >> k;

    if(k==1){
        for (int i = 0; i < n; ++i) {
            int x;
            cin >> x;
            cout << x << ' ';
        }
    }
    else if(k==2){
        int last;
        cin >> last;
        for (int i = 0; i < n-1; ++i) {
            int x;
            cin >> x;
            if(x>=last){
                cout << last << ' ';
            } else{
                cout << x << ' ';
            }
            last = x;
        }
    }
//    else{
//        int min, second_min, ast_min [2];
//
//
//    }
    return 0;
}
