/*
	Author: Abigail Nicolas Sayago
			Guillermo Naranjo
	Description: We can cipher and decipher with the 
				 AFFINE CIPHER
				 We are working with the set of printable
				 characters in ASCII as the alphabet to 
				 write plaintext.
*/

#include <iostream>
#include <cstring>
using namespace std;

int getNumber(char letter);
int gcdRecursive(int a, int b);
void affineCipher(int n, string message, int a, int b);
void affineDecipher(int n, string cipher, int a, int b);
int inverse(int z, int a);
int extendedAlgorithm(int **ecuaciones, int numEc);
int mod(int a, int b);
int main(int argc, char const *argv[]) {
    int n = 95;
    char d = ' ';
    int a, b, i;
    string cadena = " ";
	//string cadena = "<< ttt >>";
	getline(cin, cadena);
	// Read a and b
	a = 9; b = 9;
	// Verify if a is correct
	if((gcdRecursive(a, n) == 1) && (gcdRecursive(b, n) == 1))
		affineCipher(n, cadena, a, b);
	else
		cout << "Incorrect, try again";	 

	/*if(gcdRecursive(a, n) == 1)
		affineDecipher(94, cadena, a, b);
	else
		cout << "Incorrect, try again";	 
*/
    return 0;
}

void affineCipher(int n, string message, int a, int b) {
	cout << endl << "La cadena es: " << message << endl;
	int i, numLetter, numCip[n];
	char character = ' ';
	for(i = 0; i < n; i++) 
		numCip[i] = -1;
	// For each character
	for(i = 0; i < message.length(); i++) {
		numLetter = getNumber(message[i]);
		if(numCip[numLetter] == -1) {
			numCip[numLetter] = ((a * numLetter) + b) % n;
		}
		character = character + numCip[numLetter];
		cout << character;
		character = ' ';
	}
}
void affineDecipher(int n, string cipher, int a, int b) {
	int i, numLetter, numDec[n], inverso;
	char character = ' ';
	for(i = 0; i < n; i++) 
		numDec[i] = -1;
	// For each character
	for(i = 0; i <  cipher.length(); i++) {
		numLetter = getNumber(cipher[i]);
		if(numDec[numLetter] == -1) {
			inverso = inverse(n, a);
			numDec[numLetter] = numLetter - b;
			numDec[numLetter] = mod((numLetter - b) * inverso, n);
		}
		character = character + numDec[numLetter];
		cout << character;
		character = ' ';
	}
}

int getNumber(char letter) {
	int i = letter - 32;
	return i;
}

int gcdRecursive(int a, int b) {
	if(b == 0) 
		return a;
	else {
		return gcdRecursive(b, a % b);
	}
}
int mod(int a, int b) {
	if (a == 0)
		return 0;
	if(a > 0)
		return a % b;
	else
		return b - mod(a*(-1), b);
	
}

int inverse(int z, int a) {
	int res = 0, cont = 0, cociente = 0, inverso = 0, val_z_inicial = 0;

	//Matriz donde se guardarán las ecuaciones generadas
	int **ecuaciones = (int**)malloc(sizeof(int*));
	//Obtención de ecuaciones a partir del Algoritmo de Euclides
	val_z_inicial = z;
	do {
		res = z % a;
		cociente = z / a;
		ecuaciones[cont] = (int*)malloc(sizeof(int) * 4);
		ecuaciones[cont][0] = res;
		ecuaciones[cont][1] = a;
		ecuaciones[cont][2] = z;
		ecuaciones[cont][3] = cociente;
		z = a; a = res; cont++;
	}while(res != 0);

	//Sustitución de ecuaciones a partit del Algoritmo Extendido de Euclides
	cont -= 2;
	if(ecuaciones[cont][0] == 1) {
		if(cont == -1)
			inverso = 1;
		else
			inverso = extendedAlgorithm(ecuaciones, cont);
			if((cont % 2) == 0)
				inverso = val_z_inicial - (inverso % val_z_inicial);
		return inverso;
	}
	else
		return -1;
}

//Función recursiva
int extendedAlgorithm(int **ecuaciones, int numEc) {
	//Variable aux recupera el valor acumulado de b's hasta el momento en el caso de la ecuación 1
	int aux = 0, cont = 0;
	//Tratándose de la primera ecuación
	if(numEc == 0)
		return ecuaciones[numEc][3];
	//Tratándose de la segunda ecuación
	else if(numEc == 1) {
		aux = extendedAlgorithm(ecuaciones, numEc-1);
		return ecuaciones[numEc][3] = aux * ecuaciones[numEc][3] + 1;
	}
	//En el caso de las demás ecuaciones
	else {
		aux = extendedAlgorithm(ecuaciones, numEc-1);
		ecuaciones[numEc][3] = ecuaciones[numEc][3] * ecuaciones[numEc-1][3];
		ecuaciones[numEc][3] = ecuaciones[numEc][3] + ecuaciones[numEc-2][3];
		return ecuaciones[numEc][3];
	}
}