#include "main.h"

int main(){

	int n = 3;
	int matrix[7][7] = {{1, 2, -3}, {3, 2, -4}, {2, -1, 0}};
	int y[7] = {1, 0, -1};
	int result[7];

	calc_result(n, matrix, y, result);

	return 0;
}
