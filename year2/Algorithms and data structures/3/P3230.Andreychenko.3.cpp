#include <iostream>
#include <map>
#include <set>
#include <vector>
#include <string>

using namespace std;

bool intSigned(const string &s) {
    size_t offset = 0;
    if (s[offset] == '-')
        ++offset;
    return s.find_first_not_of("0123456789", offset) == string::npos;
}

int main() {
    int step = 0;
    map<int , set<string>> map_step;
    map<string, vector<int>> map_num;
    string expression;
    while (cin >> expression) {
        if (expression == "}") {
            for (string s: map_step[step]) {
                map_num[s].pop_back();
            }
            map_step.erase(step);
            step--;
        } else if (expression == "{") {
            step++;
        }
        else if(intSigned(expression.substr(expression.find('=') + 1, expression.size()))){
            string var = expression.substr(0, expression.find('='));
            int num = stoi(expression.substr(expression.find('=') + 1, expression.size()));
            if(map_step[step].find(var)==map_step[step].end()){
                map_num[var].push_back(num);
            } else{
                map_num[var][map_num[var].size()-1]=num;
            }
            map_step[step].insert(var);
        }
        else {
            string var1 = expression.substr(0, expression.find('='));
            string var2 = expression.substr(expression.find('=') + 1, expression.size());
            if(map_num[var2].size()==0){
                map_num[var2].push_back(0);
            }
            if(map_num[var1].size()==0){
                map_num[var1].push_back(0);
            }
            if(map_step[step].find(var1)==map_step[step].end()){
                map_num[var1].push_back(map_num[var2][map_num[var2].size()-1]);
            } else{
                map_num[var1][map_num[var1].size()-1]=map_num[var2][map_num[var2].size()-1];
            }
            map_step[step].insert(var1);
            cout << map_num[var2][map_num[var2].size()-1] << endl;
        }
    }
}