/*
	Description: We can cipher and decipher with the 
				 AFIN CIPHER
*/

#include <iostream>
#include <cstring>
using namespace std;

int getNumber(char letter);
void afinCipher(int n);
void afinDecipher(int n);

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
	int i, a, b, numLetter, numDec[26];
	string cipher;
	char character = 'A';

	cout << "\n -------------------------------\n";
	cout << "Write the message | Write a | Write b \n";
	cin >> cipher >> a >> b;

	for(i = 0; i < 26; i++) {
		numDec[i] = -1;
	}
	// For each character
	for(i = 0; i <  cipher.length(); i++) {
		numLetter = getNumber(cipher[i]);
		if(numDec[numLetter] == -1) {
			numDec[numLetter] = numLetter - b;
			if(numDec[numLetter] < 0) {
				numDec[numLetter] = (n + (numDec[numLetter] / a)) % n;
			}
			else
				numDec[numLetter] = (numDec[numLetter] / a) % n; 
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
