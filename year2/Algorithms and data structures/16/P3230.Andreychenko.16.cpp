#include <iostream>
#include <vector>

using namespace std;

void check(int n, int start, int& counter, const vector<vector<bool>>& matrix, vector<bool>& visited, bool type) {
    visited[start] = true;
    counter+=1;
    for (int i = 0; i < n; ++i) {
        if (type){
            if (matrix[start][i] && !visited[i]) {
                check(n, i, counter, matrix, visited, type);
            }
        } else{
            if (matrix[i][start] && !visited[i]) {
                check(n, i, counter, matrix, visited, type);
            }
        }
    }
}

int main() {
    int n;
    int max = -1;
    int min = 2147483647;
    vector<vector<int>> matrix;
    vector<vector<bool>> searchMatrix;
    vector<bool> visited;
    cin >> n;
    matrix.resize(n);
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            int k;
            cin >> k;
            matrix[i].push_back(k);
            if (k > max) {
                max = k;
            }
            if (k < min) {
                min = k;
            }
        }
    }
    while (min<max){
        int middle = (max+min)/2;
        int counter = 0;
        searchMatrix.clear();
        searchMatrix.resize(n);
        visited.clear();
        visited.resize(n, false);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                searchMatrix[i].push_back(matrix[i][j] <= middle);
            }
        }
        check(n, 0, counter, searchMatrix, visited, true);
        visited.clear();
        visited.resize(n, false);
        check(n, 0, counter, searchMatrix, visited, false);
        if (counter==(n*2)){
            max = middle;
        } else {
            min = middle + 1;
        }
    }
    cout << min;
    return 0;
}
