#include <iostream>
#include <vector>
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */ 
#include <cstring>
#include <string>
#include <fstream>
#include<math.h>
using namespace std;
vector<int> key;
string msj, keyS;
void getInverse(string keyS);
void permutationDecipher(string blo, int m);

int main(int argc, char const *argv[]) {
	int i, j, auxK, m, size, block, space, end, num;
	string tam, cadena, aux;
    char *path, *pathAux;
    fstream f, fAux;
	vector<int> vec;

	// Read Key file
	string dir;
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	f.open(path);
	if(f.is_open()) {
		while(!f.eof()) {
			getline(f, cadena);
			keyS += cadena; 
		}
		f.close();
	}
	// Read cipher text
	string dirAux;
	getline(cin, dirAux);
	pathAux = const_cast<char*>(dirAux.c_str());
	fAux.open(pathAux);
	if(fAux.is_open()) {
		while(!fAux.eof()) {
			getline(fAux, aux);
			msj += aux;
		}
		fAux.close();
	}
	// Get inverse Key
	getInverse(keyS);	

	// Descipher 
	string cip = " ";
 	i = 0;
 	while(i < msj.length()) {
 		// Obtain a substring with size of m
		cip = msj.substr(i, keyS.length());
		// Send substring to cipher
		permutationDecipher(cip, keyS.length());
		i += keyS.length();
 	}
	return 0;
}

void getInverse(string keyS) {
	int i, aux;
	string c;
	for(i = 0; i < keyS.length(); i++) 
		key.push_back(0);

	for(i = 0; i < keyS.length(); i++) {
		// Get the number
		c = keyS.at(i);
		aux = atoi(c.c_str());
		key[aux-1] = i + 1;
	}
}

void permutationDecipher(string blo, int m) {
	int i, pos;
	string cipher;
	for(i = 0; i < m; i++) 
		cipher.push_back(' ');

	for(i = 0; i < blo.length(); i++) 
		cipher[key.at(i)-1] = blo[i]; 

	cout << cipher;
}
