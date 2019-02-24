/*
	Author: Abigail Nicolas Sayago
			Guillermo Naranjo
	Description: We can cipher and decipher with the 
				 AFFINE CIPHER
				 We are working with the set of printable
				 characters in ASCII as the alphabet to 
				 write plaintext.
	Instructions:
	- File .h g++ -c libraryCipher.cpp
	- Make a object g++ libraryCipher.cpp -c
	-  g++ libraryCipher.cpp affineCipher.cpp -o affine  
						./affine <PRUEBA.in >out.afn
	Copy and paste this:
	g++ -c libraryCipher.cpp
	g++ libraryCipher.cpp -c
	g++ libraryCipher.cpp affineCipher.cpp -o affine
	./affine <PRUEBA.in >out.afn
*/

#include <iostream>
#include <cstring>
#include "libraryCipher.h"
using namespace std;

int main(int argc, char const *argv[]) {
    char d = ' ';
    int a, b, i, n = 95;
    string cadena, aAux, bAux;
    getline(cin, aAux, '\n');
    getline(cin, bAux, '\n');
	getline(cin, cadena);
	a = atoi(aAux.c_str());
	b = atoi(bAux.c_str());
	cout << a << endl;
	cout << b << endl;
	// Verify if a is correct
	if(gcdRecursive(a, n) == 1)
		affineCipher(n, cad5ena, a, b);
	else
		cout << "Incorrect, try again";	 
    return 0;
}