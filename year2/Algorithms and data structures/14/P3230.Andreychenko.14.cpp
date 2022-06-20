#include <iostream>
#include <vector>
using namespace std;

int calculate(const vector<vector<int>>& moneybox, int start, vector<bool>& visit, vector<bool>& cache) {
    cache[start] = true;
    for (int pig : moneybox[start]) {
        if (visit[pig]) {
            return 0;
        }
        if (cache[pig]) {
            return 1;
        }
        return calculate(moneybox, pig, visit, cache);
    }
}

void calc(vector<bool>& cache, vector<bool>& visit){
    for (int j = 0; j < cache.size(); ++j){
        if (cache[j]) {
            visit[j] = true;
        }
    }
}

int main() {
    int n;
    int answer = 0;
    cin >> n;
    vector<vector<int>> moneybox(n);
    vector<bool> cache;
    vector<bool> visit;

    for (int i = 0; i < n; i++) {
        int j;
        cin >> j;
        moneybox[i].push_back(--j);
    }

    visit.resize(n, false);
    for (int i = 0; i < n; ++i){
        cache.clear();
        cache.resize(n, false);
        answer += calculate(moneybox, i, visit, cache);
        calc(cache, visit);
    }
    cout << answer;
    return 0;
}