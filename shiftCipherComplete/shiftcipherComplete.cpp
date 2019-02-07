/*
	Author: Abigail Nicolas Sayago
			Guillermo Naranjo
	Description: We can cipher and decipher with the 
				 SHIFT CIPHER
				 We are working with the set of printable
				 characters in ASCII as the alphabet to 
				 write plaintext.
*/

#include <iostream>
#include <cstring>
using namespace std;

int getNumber(char letter);
void shiftCipher(int n);
void shiftDecipher(int n);
//void decipher(int n, int k, string cipher);

int main(int argc, char const *argv[]) {
    int n = 94, opc = 5;
    int i;
    char a = ' ';
    for(i = 33; i < 127; i++) {
    	a = i;
    	cout << i << " = " << a << endl;
    }


    while(opc != 0) {
	    cout << endl << "\n1 - Cipher" << endl << "2 - Decipher" << endl;
	    cin >> opc; 
	    if(opc == 1) 
		    shiftCipher(n);
        if(opc == 2)
		    shiftDecipher(n);
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

void shiftCipher(int n) {
	int i, k, numLetter, numCip[n];
	string message;
	char character = '!';

	cout << "\n -------------------------------\n";
	cout << "Write the message | Write the key \n";
	cin >> message >> k;

	for(i = 0; i < n; i++) {
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
		character = '!';
	}
}

//void decipher(int n, int k, string cipher) {
void shiftDecipher(int n) {
	int i, k, numLetter, numDec[n];
	string cipher;
	char character = '!';

	cout << "\n -------------------------------\n";
	cout << "Write the cipher | Write the key \n";
	cin >> cipher >> k;

	for(i = 0; i < n; i++) {
		numDec[i] = -1;
	}
	// For each character
	for(i = 0; i <  cipher.length(); i++) {
		numLetter = getNumber(cipher[i]);
		if(numDec[numLetter] == -1) {
			if((numLetter - k) < 0) {
				numDec[numLetter] = (numLetter - k + n) % n;
			}
			else
				numDec[numLetter] = (numLetter - k) % n; 
		}
		character = character + numDec[numLetter];
		cout << character;
		character = '!';
	}
}

int getNumber(char letter) {
	int i = letter - 33;
	return i;
}
