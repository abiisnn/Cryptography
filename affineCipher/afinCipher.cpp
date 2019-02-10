/*
	Description: We can cipher and decipher with the 
				 AFIN CIPHER
*/

#include <iostream>
#include <cstring>
using namespace std;

int getNumber(char letter);
int getInv(int num, int n);
void fillMatrix(int **m, int t);
void printMatrix(int **m, int t);
void fillTriangle(int **m, int cof[], int t, int n);
void fillTable(int **m, int cof[], int t, int n);
void afinCipher(int n);
void afinDecipher(int n);

int **matrix;

int main(int argc, char const *argv[]) {
    int n = 26, opc = 5;
    //int i;
    while(opc != 0) {
	    cout << endl << "\n1 - Cipher" << endl << "2 - Decipher" << endl;
	    cin >> opc; 
	    if(opc == 1) 
		    afinCipher(n);
        if(opc == 2)
		    afinDecipher(n);
	}
	/*
	string dec;
	cin >> dec;
	for(i = 0; i < 26; i++) {
		decipher(n, i, dec);
		cout << endl << endl;
	}*/
	return 0;
}

void afinCipher(int n) {
	int i, a, b, numLetter, numCip[26];
	string message;
	char character = 'A';

	cout << "\n -------------------------------\n";
	cout << "Write the message | Write a | Write b \n";
	cin >> message >> a >> b;

	for(i = 0; i < 26; i++) {
		numCip[i] = -1;
	}
	// For each character
	for(i = 0; i < message.length(); i++) {
		numLetter = getNumber(message[i]);
		if(numCip[numLetter] == -1) {
			numCip[numLetter] = ((a * numLetter) + b) % n; 
		}
		character = character + numCip[numLetter];
		cout << character;
		character = 'A';
	}
}

//void decipher(int n, int k, string cipher) {
void afinDecipher(int n) {
	int i, a, b, numLetter, numDec[n], inverso;
	string cipher;
	char character = 'A';

	cout << "\n -------------------------------\n";
	cout << "Write the message | Write a | Write b \n";
	cin >> cipher >> a >> b;

	for(i = 0; i < n; i++) {
		numDec[i] = -1;
	}
	// For each character
	for(i = 0; i <  cipher.length(); i++) {
		numLetter = getNumber(cipher[i]);
		if(numDec[numLetter] == -1) {
			numDec[numLetter] = numLetter - b;
			inverso = getInv(a, n);
			if(numDec[numLetter] < 0) {
				numDec[numLetter] = (n + (numDec[numLetter] * inverso)) % n;
			}
			else
				numDec[numLetter] = (numDec[numLetter] * inverso) % n; 
		}
		character = character + numDec[numLetter];
		cout << character;
		character = 'A';
	}
}

int getNumber(char letter) {
	int i = letter - 65;
	return i;
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
		for(j = 0; j < t; j++) {
			if( matrix[i][j] < 10) {
				cout << "0";
			}
			cout << matrix[i][j] << " ";	
		}
		cout << endl;
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

int getInv(int num, int n) {
	int a, b, aux, i, j = 0;
	int ti = 0; // Columns size

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
	fillTable(matrix, cofactors, ti, n);

	for(i = 0; i < ti; i++) {
		if(matrix[0][i] == num) {
			for(j = 0; j < ti; j++) {
				aux = matrix[j][i];
				if(aux == 1) 
					return matrix[j][0];
			}
		}
	}
}