/*
	Description: We can cipher and decipher

*/

#include <iostream>
#include <cstring>
using namespace std;

int getNumber(char letter);
void cipher(int n);
void decipher(int n);
int main(int argc, char const *argv[])
{
	int n = 26, opc = 5;

	while(opc != 0) {
		cout << endl << "\n1 - Cipher" << endl << "2 - Decipher" << endl;
		cin >> opc; 
		if(opc == 1) 
			cipher(n);
		if(opc == 2)
			decipher(n);
	}
	
	return 0;
}

void cipher(int n) {
	int i, k, numLetter, numCip[26];
	string message;
	char character = 'A';

	cout << "\n -------------------------------\n";
	cout << "Write the message | Write the key \n";
	cin >> message >> k;

	for(i = 0; i < 26; i++) {
		numCip[i] = -1;
	}
	// For each character
	for(i = 0; i < message.length(); i++) {
		numLetter = getNumber(message[i]);
		if(numCip[numLetter] == -1) {
			numCip[numLetter] = (numLetter + k) % n; 
		}
		character = character + numCip[numLetter];
		cout << character;
		character = 'A';
	}
}

int getNumber(char letter) {
	int i = letter - 65;
	return i;
}
