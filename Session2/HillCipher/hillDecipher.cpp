#include <iostream>
#include <stdlib.h>
#include <cstring>
#include <time.h>
#include <fstream>
#include "libraryCipher.h"

string decipher(int **K, char *path);
int matrixDeterminant(int **K);
int **inverseKey(int **K);
int **getKey(char *path);

int main(){
	int **K;
	int cont, cont2;
	char *path;
	string dir;
	//Obtención de la ubicación del archivo donde se encuentra la llave
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	//Se guarda la llave en la matriz K
	K = getKey(path);
	//Obtención de la ubicación del archivo donde se encuentra el archivo a decifrar
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	cout<<decipher(K, path);
	return 0;
}

string decipher(int **K, char *path){
	fstream f;
	int car[3], new_val[3], cofac[4];
	int cont, aux = 0, cont2, cont3, n = 95;
	string aux_msj, msj, decip_msj;
	
	//Obtención de la K inversa
	int **K_inv = inverseKey(K);
	
	//Obtención del mensaje cifrado
	f.open(path);
	if(f.is_open()){
		while(!f.eof()){
			getline(f, aux_msj);
			msj += aux_msj; 
		}
		f.close();
	}

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

int **inverseKey(int **K){
	int cofac[4];
	int cont, cont2, cont3, cont4, cont_cofac, det = 0, inv_det = 0, cofactor = 0, n = 95;
	//Obtención del determinante y su inverso
	det = matrixDeterminant(K);
	inv_det = inverse(n, det);
	//Creación matriz inversa
	int **K_inv = (int**)malloc(sizeof(int*) * 3);
	for(int i = 0; i < 3; i++)
		K_inv[i] = (int*)malloc(sizeof(int) * 3);
	//Obtención de la matriz adjunta
	//Primeros dos ciclos para recorrer todos los elementos de la matriz
	for(cont = 0; cont < 3; cont++){
		for(cont2 = 0; cont2 < 3; cont2++){
			//Últimos dos ciclos para obtener cofactor del elemento analizado
			cont_cofac = 0;
			for(cont3 = 0; cont3 < 3; cont3++){
				for(cont4 = 0; cont4 < 3; cont4++){
					if(cont != cont3 && cont2 != cont4){
						cofac[cont_cofac] = K[cont3][cont4];
						cont_cofac++;
					}
				}
			}
			//Obtencion del cofactor
			cofactor = cofac[0] * cofac [3] - cofac[1] * cofac[2];
			//Se evalua si i+j de la posición de la matriz es par o impar
			if ((cont + cont2) % 2 != 0)
				cofactor = cofactor * (-1);
			//Se obtiene módulo de cofactor
			if(cofactor < 0)
				cofactor = n + (cofactor % n);
			else 
				cofactor = cofactor % n;
			//Se multiplica el elemento cofactor por la inversa de la determinante y se obtiene módulo
			cofactor = (cofactor * inv_det) % n; 
			//Se transpone la matriz de cofactores para obtener adjunta (la cual será la inversa de la llave por la operación anterior)
			K_inv[cont2][cont] = cofactor;
		}
	}
	return K_inv;
}

int **getKey(char *path){
	fstream f;
	string aux_msj="", msj="";
	int cont=0, i, j;
	int **K = (int**)malloc(sizeof(int*) * 3);
	for(int i = 0; i < 3; i++)
		K[i] = (int*)malloc(sizeof(int) * 3);
	f.open(path);
	if(f.is_open()){
		while(!f.eof()){
			getline(f, aux_msj);
			for(i = 0, j = 0; i < aux_msj.size(); i++){
				if(aux_msj[i] >= 48 && aux_msj[i] <= 57){
					//if(aux_msj[i+1]=48 && aux_msj[i+1]<=57){
					K[cont][j] = (int)aux_msj[i] - 48;
					j++;
				}
			}
			cont++;
		}
		f.close();
	}
	return K;
}

int matrixDeterminant(int **K){
	int det = K[0][0] * K[1][1] * K[2][2] + K[0][1] * K[1][2] * K[2][0] + K[0][2] * K[1][0] * K[2][1] - (K[0][0] * K[1][2] * K[2][1] + K[0][1] * K[1][0] * K[2][2] + K[0][2] * K[1][1] * K[2][0]);
	return det;
}