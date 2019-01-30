#include<iostream>
using namespace std;

void fillMatrix(int **m, int t);
void printMatrix(int **m, int t);
void fillTriangle(int **m, int cof[], int t, int n);
void fillTable(int **m, int cof[], int t, int n);

int **matrix;
int main(int argc, char const *argv[]) {
	int n, a, b, i, j = 0;
	int ti = 0; // Columns size
	cin >> n;
	int cofactors[n];
	for(i = 0; i < n; i++) {
		if(__gcd(i, n) == 1) {
			cofactors[j] = i;
			j++;
			ti++;
			//cout << i << endl;
		}
	}

	matrix = (int**)calloc(ti, sizeof(int*));
	for (i = 0; i < ti; i++)
		matrix[i] = (int*)calloc(ti, sizeof(int));

	fillMatrix(matrix, ti);
	//fillTriangle(matrix, cofactors, ti, n);
	fillTable(matrix, cofactors, ti, n);

	cout << endl;
	
	printMatrix(matrix, ti);

	return 0;
}

void fillMatrix(int **m, int t) {
	int i, j;
	for(i = 0; i < (t / 2); i++) 
		for(j = 0; j < t; j++) 
			matrix[i][j] = -1;
}
void printMatrix(int **m, int t) {
	int i, j;
	for(i = 0; i < t; i++) {
		for(j = 0; j < t; j++) 
			cout << "[" << matrix[i][j] << "]";
		cout << endl;
	}
}

void fillTriangle(int **m, int cof[], int t, int n) {
	int i, j;
	int aux = 1;
	int lim = 0;
	int f = 0;
	for(j = 0; j < t; j++) {
		m[0][j] = cof[j]; 
	} 
	for(i = 1; i < (t / 2); i++) {
		lim = aux;
		for(j = aux; j < (t - lim); j++) {
			//cout << "Multiplico:" << cof[j] << " , " << cof[aux] << endl;
			//m[i][j] = 0;
			m[i][j] = (cof[j] * cof[aux]) % n;
		}
		aux++;
	}
}

void fillTable(int **m, int cof[], int t, int n) {
	int i, j;
	int aux = 0;

	for(i = 0; i < t; i++) {
		for(j = 0; j < t; j++) {
			m[i][j] = (cof[j] * cof[aux]) % n;
		}
		aux++;
	}
}