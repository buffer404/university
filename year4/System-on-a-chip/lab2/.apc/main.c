#include "main.h"

//int n = 5;
//int matrix[7][7] = {{1, 0, 1, 0, 1},{0, 1, 0, 1, 0},{1, 1, 0, 0, 0},{0, 1, 1, 0, 1}, {1, 0, 0, 0, 1}};
//int answer[7] = {5, 6, 5, 6, 3};

int permutations[7];

int get_index(int raw, int val) {
	return !raw ? 0 : (raw & 1 ? val : get_index(raw / 2, val + 1));
}

void swap(int i, int j) {
	int temp = permutations[i];
	permutations[i] = permutations[j];
	permutations[j] = temp;
}

int next_set(int n) {
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

int get_element(int i, int j, int exc, int matrix[7][7], int y[7]) {
	return j == exc ? y[i] : matrix[i][j];
}

int count_inversion(int n) {
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

int eval_elem(int param, int n, int matrix[7][7], int y[7]) {
	int res = count_inversion(n) % 2 ? -1 : 1;
	for(int i = 0; i < n; i++) {
		res *= get_element(i, permutations[i], param, matrix, y);
	}
	return  res;
}

int calc_det(int param, int n, int matrix[7][7], int y[7]){
	clear_perm();
	int res = eval_elem(param, n, matrix, y);
	while(next_set(n)){
		res += eval_elem(param, n, matrix, y);
	}
	return res;
}

void calc_result(int n, int matrix[7][7], int y[7], int result[7]){
	int main_delta = calc_det(n + 1, n, matrix, y);
	for (int i = 0; i < n; i++) {
		int delta = calc_det(i, n, matrix, y);
		result[i] = delta / main_delta;
	}
}
