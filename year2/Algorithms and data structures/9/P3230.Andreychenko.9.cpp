#include <iostream>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int count = 0;
    int n, k, p;
    cin >> n >> k >> p;
    vector<int> floor;
    int stream [p];
    map<int, vector<int>> map;
    for (int i = 0; i < p; ++i) {
        int car;
        cin >> car;
        map[car].push_back(i);
        stream[i] = car;
    }

    for(int i = 0; i<p; i++){
        int cur_car = stream[i];
        if(find(floor.begin(), floor.end(), cur_car) == floor.end()){
            if (floor.size()<k){
                floor.push_back(cur_car);
            } else{
                int num_car, max = 0;
                for(int car : floor){
                    if(map[car].empty()){
                        max = 500001;
                        num_car = car;
                    }
                    else if(map[car].at(0) >= max){
                        max = map[car].at(0);
                        num_car = car;
                    }
                }
                floor.erase(find(floor.begin(), floor.end(), num_car));
                floor.push_back(cur_car);
            }
            count+=1;
        }
        map[cur_car].erase(map[cur_car].begin());
    }
    cout << count;
    return 0;
}
