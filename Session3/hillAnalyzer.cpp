#include <iostream>
#include <stdlib.h>
#include <cstring>
#include <time.h>
#include <fstream>
#include <string>
#include "EuclidesAlgorithm.h"

using namespace std;

void leerArchivo(char *path, string *msj);
int **obtencionLlave(string msj, string cip_msj);
int inverseKey(int **K, int **K_inv);
string decipher(int **K, string msj);
int matrixDeterminant(int **K);

int main() {
	char *path;
	string dir, cip_msj, msj;
	int **K;
	
	// Read text plain path
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	leerArchivo(path, &msj);
	
	// Read ciphertext path
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	leerArchivo(path, &cip_msj);

	// Get valid key
	K = obtencionLlave(msj, cip_msj);
	
	//Print the decryption of the ciphertext to prove the key obtained is correct 
	cout << decipher(K, cip_msj);
	return 0;
}

void leerArchivo(char *path, string *msj) {
	fstream f;
	string aux_msj, ms;
	// Read file
	f.open(path);
	if(f.is_open()) {
		while(!f.eof()) {
			getline(f, aux_msj);
			ms += aux_msj;
		}
		f.close();
	}
	*msj = ms;
}

int inverseKey(int **K, int **K_inv) {
	int cofac[4];
	int cont, cont2, cont3, cont4, cont_cofac, det = 0, inv_det = 0, cofactor = 0, n = 95;
	//Obtention of the determinant modulo n
	det = matrixDeterminant(K);
	if(det < 0)
		det = n + (det % n);
	else
		det = det % n;
	//Ontention of the determinant inverse
	inv_det = inverse(n, det);
	//In case that the determinant doesn't have inverse, the function returns -1
	if(inv_det == -1)
		return -1;
	//In case that it has inverse
	else{
		// Getattached matrix
		// Two cycles to traverse all the elements of the matrix
		for(cont = 0; cont < 3; cont++) {
			for(cont2 = 0; cont2 < 3; cont2++) {
				// Last two cycles to obtain cofactor of the element analyzed
				cont_cofac = 0;
				for(cont3 = 0; cont3 < 3; cont3++){
					for(cont4 = 0; cont4 < 3; cont4++){
						if(cont != cont3 && cont2 != cont4){
							cofac[cont_cofac] = K[cont3][cont4];
							cont_cofac++;
						}
					}
				}
				// Get cofactor
				cofactor = cofac[0] * cofac [3] - cofac[1] * cofac[2];
				// Evaluated if i+j is even or odd
				if ((cont + cont2) % 2 != 0)
					cofactor = cofactor * (-1);
				// Get modulo of the cofactor
				if(cofactor < 0)
					cofactor = n + (cofactor % n);
				else 
					cofactor = cofactor % n;
				// Cofactor element is multiplied by the inverse of the determinan
				// The module is obtained
				cofactor = (cofactor * inv_det) % n; 
				/* The matrix of cofactors is transposed to obtain attached 
				 (which will be the inverse of the key by the previous operation) */
				K_inv[cont2][cont] = cofactor;
			}
		}
		return 0;
	}
}

int **obtencionLlave(string msj, string cip_msj) {
	int cont3 = 0, ban, cont, cont2, aux=0;
	//Matrix corresponding to the plaintext
	int **X = (int**)malloc(sizeof(int*)*3);
	//Matrix corresponding to the ciphertext
	int **Y = (int**)malloc(sizeof(int*)*3);
	//Matrix corresponding to the key
	int **K = (int**)malloc(sizeof(int*)*3);
	int **Xinv = (int**)malloc(sizeof(int*)*3);
	for(int i = 0; i < 3; i++) {
		X[i] = (int*)malloc(sizeof(int)*3);
		Y[i] = (int*)malloc(sizeof(int)*3);
		K[i] = (int*)malloc(sizeof(int)*3);
		Xinv[i] = (int*)malloc(sizeof(int)*3);
	}
	
	//
	do{
		// X and Y are filled with the first 3 triads of the plaintext and ciphertext respectively
		for(cont = 0; cont < 3; cont3+=3, cont++) {
			
			if(msj[cont3] >= 32 && msj[cont3] <= 126) {
				X[cont][0] = (int)msj[cont3] - 32;
				Y[cont][0] = (int)cip_msj[cont3] - 32; 
			}
			else {
				cont3++;
				X[cont][0] = (int)msj[cont3] - 32;
				Y[cont][0] = (int)cip_msj[cont3] - 32;
			}
			if(msj[cont3 + 1] >= 32 && msj[cont3 + 1] <= 126){
				X[cont][1] = (int)msj[cont3 + 1] - 32;
				Y[cont][1] = (int)cip_msj[cont3 + 1] - 32;
			}
			else {
				cont3++;
				X[cont][1] = (int)msj[cont3 + 1] - 32;
				Y[cont][1] = (int)cip_msj[cont3 + 1] - 32;
			}
			if(msj[cont3 + 2] >= 32 && msj[cont3 + 2] <= 126){
				X[cont][2] = (int)msj[cont3 + 2] - 32;
				Y[cont][2] = (int)cip_msj[cont3 + 2] - 32;
			}
			else {
				cont3++;
				X[cont][2] = (int)msj[cont3 + 2] - 32;
				Y[cont][2] = (int)cip_msj[cont3 + 2] - 32;
			}
		}
		//The matrix X is inverted
		ban = inverseKey(X, Xinv);
	}while(ban != 0);
	
	// Multiplication to obtain K
	FILE *f=fopen("C:/Users/Dell/Documents/Crypto/Session3/key.txt", "w");
	for(cont = 0; cont < 3; cont++){
		fprintf(f, "(\t");
		for(cont3 = 0; cont3 < 3; cont3++){
			for(cont2 = 0; cont2 < 3; cont2++){
				//Se obtiene Aij
				aux = aux + (Xinv[cont][cont2] * Y[cont2][cont3]);
				if(cont2 == 2){
					if(aux < 0)
						aux = 95 - (aux % 95);
					else
						aux = aux % 95;
					K[cont][cont3] = aux;
					aux = 0;
					fprintf(f, "%d\t", K[cont][cont3]);
				}
			}
		}
		fprintf(f, ")\n");
	}
	fclose(f);
	return K;
}

string decipher(int **K, string msj) {
	fstream f;
	int car[3], new_val[3], cofac[4];
	int cont, aux = 0, cont2, cont3, n = 95;
	string aux_msj, decip_msj;
	
	int **K_inv = (int**)malloc(sizeof(int*)*3);
	for(int i = 0; i < 3; i++)
		K_inv[i] = (int*)malloc(sizeof(int)*3);
	//Obtención de la K inversa
	inverseKey(K, K_inv);
	
	aux = 0;
	/*Decifrado del mensaje
	Ciclo que obtiene el grupo de 3 caracteres*/
	for(cont3 = 0; cont3 < msj.size(); cont3+=3){
		car[0] = (int)msj[cont3] - 32;
		car[1] = (int)msj[cont3 + 1] - 32;
		car[2] = (int)msj[cont3 + 2] - 32;
		//Se realiza la multiplicacion de las matrices para obtener los nuevos valores
		for(cont = 0; cont < 3; cont++){
			for(cont2 = 0; cont2 < 3; cont2++)
				//Se obtiene Aij
				aux=aux+(car[cont2] * K_inv[cont2][cont]);
			new_val[cont] = aux % n;
			aux = 0;
			decip_msj += (new_val[cont] + 32);
		}
	}
	return decip_msj;
}

int matrixDeterminant(int **K){
	int det = K[0][0] * K[1][1] * K[2][2] + K[0][1] * K[1][2] * K[2][0] + K[0][2] * K[1][0] * K[2][1] - (K[0][0] * K[1][2] * K[2][1] + K[0][1] * K[1][0] * K[2][2] + K[0][2] * K[1][1] * K[2][0]);
	return det;
}
