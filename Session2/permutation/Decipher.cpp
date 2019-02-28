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
	
	// Decipher 
	string cip = " ";
 	i = 0;
 	while(i < msj.length()) {
 		// Obtain a substring with size of m
		cip = msj.substr(i, key.size());
		// Send substring to cipher
		permutationDecipher(cip, key.size());
		i += key.size();
 	}
	return 0;
}

void getInverse(string keyS) {
	int i, aux, a, b;
	string c;
	char cAux, bAux;
	vector<int> keyAux;

	keyS = keyS + " ";
	for(i = 0; i < keyS.length()-1; i++) {
		a = i + 1;
		cAux = keyS.at(a);
		bAux = keyS.at(i);
		a = cAux;
		b = bAux;
		c = keyS.at(i);
		// Get the number
		if(a == 32) {
			aux = atoi(c.c_str());
			keyAux.push_back(aux);
		}
		else if (b != 32) {
			a = i + 1;
			c = c + keyS.at(a);
			aux = atoi(c.c_str());
			keyAux.push_back(aux);
			i++;
		}
	}

	for(i = 0; i < keyAux.size(); i++) 
		key.push_back(0);
	
	for(i = 0; i < keyAux.size(); i++) {
		aux = keyAux[i];
		key[aux-1] = i + 1;
	}
	// Print key 
	for(i = 0; i < key.size(); i++) 
		cout << key.at(i) << " ";
	cout << endl;
}

void permutationDecipher(string blo, int m) {
	int i, pos;
	string cipher;
	for(i = 0; i < m; i++) 
		cipher.push_back(' ');

	for(i = 0; i < blo.length(); i++) 
		cipher[i] = blo[key.at(i)-1]; 
	cout << cipher;
}
