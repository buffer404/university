#include <iostream>
#include <queue>
#include <map>
#include <set>
using namespace std;
int main() {
    int n, k, p;
    cin >> n >> k >> p;
    map<int, int> map;
    queue<int> pQueue[n];
    set<int> floor;
    int count = 0;
    int stream[p];
    auto car_to_remove = map.rbegin();
    int maxPriority = 500001;
    for (int i = 0; i < p; ++i){
        int x;
        cin >> x;
        stream[i] = x;
        pQueue[x - 1].push(i);
    }
    for (int j = 0; j < p; ++j){
        if (floor.find(stream[j]) == floor.end()){
            if (floor.size() == k){
                car_to_remove = map.rbegin();
                floor.erase((*car_to_remove).second);
                map.erase((*car_to_remove).first);
            }
            floor.insert(stream[j]);
            count+=1;
        }
        map.erase(pQueue[stream[j] - 1].front());
        pQueue[stream[j] - 1].pop();
        if (pQueue[stream[j] - 1].empty()){
            map[maxPriority++] = stream[j];
        }
        else{
            map[pQueue[stream[j] - 1].front()] = stream[j];
        }
    }
    cout << count;
    return 0;
}
