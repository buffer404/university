#include "stdio.h"

#define MAX_LINE_SIZE 64

int n;

char recieve_char[1];

int matrix[7][7];
int answer[7];
int result[7];
int permutations[7];
int check_filler[7] = {0};
int point_n = 0;
int point_m = 0;

void print_matrix(){
	for (int i = 0; i < n; i++){
		for (int j = 0; j < n ; j++){
			printf("%d ", matrix[i][j]);
		}
		printf("= %d", answer[i]);
		printf("\n\r");
	}
}

void fill_matrix(int *arr, int index){
	for (int i = 0; i < n; i++){
		matrix[index][i] = arr[i];
	}
	check_filler[index] = 1;
}

int check_matrix(){
	for(int i = 0; i < n; i++)
		if(!check_filler[i])
			return 0;
	return 1;
}

int get_index(int raw, int val) {
	return !raw ? 0 : (raw & 1 ? val : get_index(raw / 2, val + 1));
}

void swap(int i, int j) {
	int temp = permutations[i];
	permutations[i] = permutations[j];
	permutations[j] = temp;
}

int next_set() {
	int j = n - 2;
	while (j != -1 && permutations[j] >= permutations[j + 1]) j--;
	if (j == -1)
		return 0;
	int k = n - 1;
	while (permutations[j] >= permutations[k]) k--;
	swap(j, k);
	int l = j + 1, r = n - 1;
	while (l<r)
		swap(l++, r--);
	return 1;
}

int get_element(int i, int j, int exc) {
	return j == exc ? answer[i] : matrix[i][j];
}

int count_inversion() {
	int sum = 0;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (permutations[i] > permutations[j]) sum++;
		}
	}
	return sum;
}

void clear_perm(){
	for(int i = 0; i < 7; i++)
		permutations[i] = i;
}

int eval_elem(int param) {
	int res = count_inversion() % 2 ? -1 : 1;
	for(int i = 0; i < n; i++) {
		res *= get_element(i, permutations[i], param);
	}
	return  res;
}

int calc_det(int param){
	clear_perm();
	int res = eval_elem(param);
	while(next_set()){
		res += eval_elem(param);
	}
	return res;
}

void main(){
	int main_delta = calc_det(n + 1);
	for (int i = 0; i < n; i++) {
		int delta = calc_det(i);
		result[i] = delta / main_delta;
	}
}
