#include<iostream>
#include<bits/stdc++.h>
using namespace std;

int inv(int n, int num);
void printMatrix(int **m, int total);

int **matrix;
int **newMatriz;

int  main(int argc, char const *argv[])
{
	int i, j;
	int n = 13;
	int num = 8;
	matrix = (int**)calloc(100, sizeof(int*));
	for (i = 0; i < 100; i++)
		matrix[i] = (int*)calloc(4, sizeof(int));

	newmatrix = (int**)calloc(100, sizeof(int*));
	for (i = 0; i < 100; i++)
		matrix[i] = (int*)calloc(100, sizeof(int));

	int inverse = inv(n, num);
	return 0;
}
int inv(int n, int num) {
	int i = 0, j = 0, total;
	int divisor, quotient, dividend, res;

	i = 0; j = 0, total = 0;
	while(num != 0) {
		dividend = n;
		divisor = num;
		quotient = dividend / divisor;
		res = n % num;
//		cout << dividend << " = " << divisor << " * " << quotient << " + " << res << endl;
//		cout << res << " = " << dividend << " + " << divisor << " * " << -1 * quotient << endl << endl;
		matrix[i][j] = res; j++;
		matrix[i][j] = dividend; j++;
		matrix[i][j] = divisor; j++;
		matrix[i][j] = -1 * quotient; j++;
		i++; j = 0;
		n %= num;
		swap(n, num); 
		total++;
	}
	printMatrix(matrix, total);	

	for(i = 1; i < total; i++) {
		for(j = 0; j < 4; j++) {
			if(matrix[i][j] == n)  

		}
	}

}


void printMatrix(int **m, int total) {
	int i, j;
	for(i = 0; i < total; i++) {
		for(j = 0; j < 4; j++) {
			cout << matrix[i][j] << " ";
		}
		cout << endl;
	}
}
