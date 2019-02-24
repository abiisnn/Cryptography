void affineDecipher(int n, string cipher, int a, int b) {
	int i, numLetter, numDec[n], inverso;
	char character = ' ';
	for(i = 0; i < n; i++) 
		numDec[i] = -1;
	// For each character
	for(i = 0; i <  cipher.length(); i++) {
		numLetter = getNumber(cipher[i]);
		if(numDec[numLetter] == -1) {
			inverso = inverse(n, a);
			numDec[numLetter] = numLetter - b;
			numDec[numLetter] = mod((numLetter - b) * inverso, n);
		}
		character = character + numDec[numLetter];
		cout << character;
		character = ' ';
	}
}