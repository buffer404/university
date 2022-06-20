#include <iostream>
#include <fstream>
#include <stack>

int main() {
    std::ifstream fin;
    std::ofstream fout;
    fin.open("input.txt");
    fout.open("output.txt");

    int animal_count = 0;
    int trap_count = 0;
    std::stack<int> animal_stack;
    std::stack<int> trap_stack;
    std::string zoo;
    std::stack<char> stack;
    fin >> zoo;

    int answer[zoo.size()/2];

    for(char c : zoo){
        if(islower(c)){
            animal_count+=1;
            animal_stack.push(animal_count);
        } else{
            trap_count+=1;
            trap_stack.push(trap_count-1);
        }
        if(stack.empty() || abs(int(stack.top()) - int(c))!=32){
            stack.push(c);
        } else{
            answer[trap_stack.top()] = animal_stack.top();
            stack.pop();
            animal_stack.pop();
            trap_stack.pop();
        }
    }
    if(stack.empty()){
        fout << "Possible\n";
        for (int i = 0; i < (zoo.size()/2); ++i) {
            fout << answer[i] << ' ';
        }
    } else{
        fout << "Impossible";
    }
    fout.close();
    fin.close();
}