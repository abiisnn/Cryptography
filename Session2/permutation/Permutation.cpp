#include <iostream>
#include <vector>
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */ 
#include <cstring>
#include <fstream>
#include<math.h>
using namespace std;
vector<int> key;
string msj;

void generateKey(int n);
int gcdRecursive(int a, int b);
void permutationCipher(string blo, int m);
void fillSpace(int size, int m);

int main(int argc, char const *argv[]) {
	int i, j, auxK, m, size, block, space, end;
	string tam, cadena;
    char *path;
    fstream f;
	vector<int> vec;

	// Read m
    getline(cin, tam);

	// Read plain text
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

	// Convert m to integer
	m = atoi(tam.c_str());

	// Generate a valid Key
	generateKey(m);	

	// Use gcd to know if we need spaces
	size = msj.length();	
	if((gcdRecursive(m, size) == 1) && (m > 1)) {
		fillSpace(size, m);
	}

	// Cipher
 	string cip = " ";
 	i = 0;
 	while(i < msj.length()) {
 		// Obtain a substring with size of m
		cip = msj.substr(i, m);
		// Send substring to cipher
		permutationCipher(cip, m);
		i += m;
 	}
	return 0;
}
void fillSpace(int size, int m) {
	string aux = " ";
	double sized, md, bd;
	int i, block, end;

	// Calculate how many spaces we have
	sized = (double)size;
	md = (double)m; 
	bd = sized / md;
	bd = floor(bd); // Exact block
	block = (int)bd;
	end = m * (block + 1);
	// Fill spaces with " "
	for(i = size; i < end; i++) {
		msj = msj + aux;
	}
}
void permutationCipher(string blo, int m) {
	int i, pos;
	string cipher;
	for(i = 0; i < m; i++) 
		cipher.push_back(' ');

	for(i = 0; i < blo.length(); i++) 
		cipher[i] = blo[key.at(i)-1]; 

	cout << cipher;
}

int gcdRecursive(int a, int b) {
	if(b == 0) 
		return a;
	else {
		return gcdRecursive(b, a % b);
	}
}

void generateKey(int n) {
	int i, iSecret;
	srand(time(NULL));
	// Fill vector Key
	i = 0;
	while(i < n) {
		// Generate secret number between 1 and m:
	    iSecret = 1 + rand() % (n);
	    if(find(key.begin(), key.end(), iSecret) == key.end()) {
	   		//cout << "Soy un RANDOM diferente: " << iSecret << endl;
			key.push_back(iSecret); i++;
		}
	}
	// Print key 
	for(i = 0; i < n; i++) 
		cout << key.at(i) << " ";
	cout << endl;
}
