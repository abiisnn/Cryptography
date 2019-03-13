/*
	Author: Abigail Nicolas Sayago
			Guillermo Naranjo
	Description: We can cipher and decipher with the 
				 AFFINE CIPHER
				 We are working with the set of printable
				 characters in ASCII as the alphabet to 
				 write plaintext.
	Instructions:
	- Para el archivo .h g++ -c libraryCipher.cpp
	- Crear objeto estatica g++ libraryCipher.cpp -c
	- Ejecucion estatica g++ libraryCipher.cpp affineCipher.cpp -o affine  
						./affine <PRUEBA.in >out.afn
	Copy and paste this:
	g++ -c libraryCipher.cpp
	g++ libraryCipher.cpp -c
	g++ libraryCipher.cpp affineDecipher.cpp -o affineDecipher
	./affine > des.out
	9
	9
	cip.in
*/

#include <iostream>
#include <cstring>
#include <stdlib.h>
#include <fstream>
#include "libraryCipher.h"
using namespace std;

int main(int argc, char const *argv[]) {
    char d = ' ';
    int a, b, i, n = 95;
    char *path;
    string cadena, aAux, bAux, msj;
    fstream f;
    getline(cin, aAux);
    getline(cin, bAux);
	//Lectura del archivo
	string dir;
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	f.open(path);
	if(f.is_open()){
		while(!f.eof()){
			getline(f, cadena);
			msj += cadena; 
		}
		f.close();
	}
	a = atoi(aAux.c_str());
	b = atoi(bAux.c_str());
	// Verify if a is correct
	if(gcdRecursive(a, n) == 1)
		affineDecipher(n, msj, a, b);
	else
		cout << "Incorrect, try again";	 
    return 0;
}
