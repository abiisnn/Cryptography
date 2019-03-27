//#include<bits/stdc++.h>
#include <iostream>
#include<vector>

using namespace std;
vector<int> matrix(8);
void printBits(int n) {
	int i;
	for(i = sizeof(n) * 8 - 1; ~i; i--) 
		cout << ((n >> i) & 1);
	cout << endl;
}
void fillMatrix() {
	matrix[0] = 143;
	matrix[1] = 199;
	matrix[2] = 227;
	matrix[3] = 241;
	matrix[4] = 248;
	matrix[5] = 124;
	matrix[6] = 62;
	matrix[7] = 31;
}
void elementSB(int m) {
	//matrix = {143, 199, 227, 241, 248, 124, 62, 31};	
	vector<int> mul(8);	
	int i, j, k, n;
	int fijo = 198;
	int mult = 0, suma = 0;
	int r, aux, auxN, x;

	n = 0; j = 7;
	for(i = 0; i < 8; i++) {
		aux = (m >> i) & 1;
		if(aux == 1)
			n = n | (1 << j);
		j--;
	}
	cout << "Working with:" << endl;
	printBits(n);

	k = 7;
	for(i = 0; i < matrix.size(); i++) {
		// We make the multiplication
		r = matrix[i] & n;
		x = 0;
		for(j = 0; j < 8; j++) {
			aux = (r >> j) & 1;
			x = x xor aux;				
		} 
		if(x == 1)
			mult = mult | (x << k);
		k--;
	}
	cout << endl << endl << "FINAL MULT:" << endl;
	printBits(mult);
	//printBits(mult);
	//cout << mult << endl;
	//cout << endl;
	
	x = 0; j = 0;
	for(i = 7; i < mul.size(); i--) {
		// Get i bit of fijo
		aux = (fijo >> i) & 1;
		auxN = (mult >> i) & 1;
		x = !(auxN and aux);
		if(x == 1)
			suma = suma | (x << j);		
		x = 0;
		j++;
	}
	cout << "FINAL SUM" << endl;
	printBits(suma);

	// Find the ms using comp

	//cout << "suma " << suma << endl;
	aux = 31 - __builtin_clz(suma);

	// Change i bit 
	auxN = suma & (~(1 << aux));
	cout << "result in dec " << auxN << endl;	
}


int main(int argc, char const *argv[])
{
	fillMatrix();
	cout << "Element 77: " << endl;
	elementSB(77);

	cout << "Element 112: " << endl;
	elementSB(112);

	cout << "Element 0: " << endl;
	elementSB(0);	

	return 0;
}

