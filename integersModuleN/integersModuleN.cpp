#include<iostream>
using namespace std;

void printMatrix(int **m, int t, int d);
void fillMatrix(int **m, int t, int d);


int **matrix;

int main(int argc, char const *argv[]) {
	int i, j, ti = 12, n = 21;
	int down = 6;

	matrix = (int**)calloc(ti, sizeof(int*));
	for (i = 0; i < ti; i++)
		matrix[i] = (int*)calloc(down, sizeof(double));
	
	int cofactors[12] = {1, 2, 4, 5, 8, 10, 11, 13, 16, 17, 19, 20};

	fillMatrix(matrix, ti, down);

	/*for(i = 0; i < column; i++) 
		matrix[0][i] = cofactors[i];

	for(i = 0; i < column; i++) {
		for(j = 0; j < row; j++) {
			if((i == j) || (j > i)) {
				matrix[i][j] = ((i - 1) * j) % n;

			}
			n = 2;
		}
	}*/
	printMatrix(matrix, ti, down);
/*
	for(i = 0; i < 12; i++) {
		for(j = 0; j <12; j++) {
			//cout << "[" << i << "," << j << "] ";
		}
		cout << endl;
	}
*/
	return 0;
}

void printMatrix(int **m, int t, int d) {
	int i, j;
	for(i = 0; i < t; i++) {
		for(j = 0; j < d; j++)
			cout << "[" << m[i][j] <<"] ";

		cout << endl;
	}
}

// Fill matrix with -1
void fillMatrix(int **m, int t, int d) {
	int i, j;
	for(i = 0; i < d; i++) 
		for(j = 0; j < ti; j++)
			m[i][j] = -1;
}

