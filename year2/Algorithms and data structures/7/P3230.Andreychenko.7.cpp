#include <iostream>
#include <map>
#include <algorithm>
#include <vector>

using namespace std;

map<char, int> alphabet;
map<char, int> char_count;


bool comp (char a, char b) {
    return alphabet[a] >= alphabet[b];
}

int main() {
    vector<char> vector;
    string s;
    string result;
    cin >> s;

    for (int i = 0; i <= 25; ++i) {
        cin >> alphabet[(char)(i + 97)];
    }

    for(char c : s){
        if(char_count.find(c)==char_count.end()){
            char_count[c]=1;
        }
        else{
            char_count[c]++;
        }
    }

    for(auto& item : char_count){
        if(item.second>=2){
            if(std::find(vector.begin(), vector.end(), item.first)==vector.end()){
                vector.push_back(item.first);
                char_count[item.first]-=2;
            }
        }
    }

    std::sort(vector.begin(), vector.end(), comp);

    for(char c : vector){
        result+=c;
    }

    reverse(vector.begin(), vector.end());

    for(auto& item : char_count){
        for(int i = 0; i<item.second; i++){
            result += item.first;
        }
    }

    for(char c : vector){
        result+=c;
    }
    cout << result;
    return 0;
}
