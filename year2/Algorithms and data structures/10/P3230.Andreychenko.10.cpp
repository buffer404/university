#include <iostream>
#include <deque>
using namespace std;
int main() {
    deque<int> firstDeque;
    deque<int> secondDeque;
    int n;
    cin >> n;
    for(int i = 0; i < n; ++i){
        char act;
        cin >> act;
        if (act == '-') {
            cout << firstDeque.front() << endl;
            firstDeque.pop_front();
        }
        else {
            int num;
            cin >> num;
            if (act == '*') {
                secondDeque.push_front(num);
            } else {
                secondDeque.push_back(num);
            }
        }
        if(secondDeque.size() - firstDeque.size()==1) {
            firstDeque.push_back(secondDeque.front());
            secondDeque.pop_front();
        }
        else if (secondDeque.size() - firstDeque.size()==2) {
            secondDeque.push_back(firstDeque.front());
            firstDeque.pop_front();
        }
//        cout << "-----------------" << endl;
//        cout << "first"<< endl;
//        for(int k : firstDeque){
//            cout << k << ' ';
//        }
//        cout << "second" << endl;
//        for(int k : secondDeque){
//            cout << k << ' ';
//        }
//        cout << "-----------------" << endl;
    }
    return 0;
}