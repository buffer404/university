#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> graph;
vector<int> colorTable;
vector<bool> alreadyColored;
bool ans = true;

void paint(int index, int color){
    if (!ans||alreadyColored[index]) return;
    if (colorTable[index]==color || colorTable[index]==0){
        colorTable[index]=color;
        alreadyColored[index] = true;
    } else{
        ans = false;
    }
//    cout << color;
    color = 1 + ((color)%2);
//    cout << color << endl;
    for(int neib : graph[index]){
        if (colorTable[neib] == 0 || colorTable[neib] == color){
            colorTable[neib] = color;
        } else {
            ans = false;
        }
    }

    for(int i : graph[index]){
        if (!alreadyColored[i]) paint(i, color);
    }

}

int main() {
    int n, m;
    int color = 1;
    cin >> n >> m;
    graph.resize(n);
    colorTable.resize(n);
    for (int i = 0; i < n; i++){
        colorTable[i]=0;
        alreadyColored.push_back(false);
    }
    for (int i = 0; i < m; i++) {
        int k, l;
        cin >> k >> l;
        graph[--k].push_back(--l);
        graph[l].push_back(k);
    }
    for (int i = 0; i < n && ans; i++) {
        paint(i, color);
    }
    if(ans){
        cout << "YES";
    } else{
        cout << "NO";
    }
    return 0;
}