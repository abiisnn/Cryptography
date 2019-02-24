void affineCipher(int n, string message, int a, int b) {
	int i, numLetter, numCip[n];
	char character = ' ';
	for(i = 0; i < n; i++) 
		numCip[i] = -1;
	// For each character
	for(i = 0; i < message.length(); i++) {
		numLetter = getNumber(message[i]);
		if(numCip[numLetter] == -1) {
			numCip[numLetter] = ((a * numLetter) + b) % n;
		}
		character = character + numCip[numLetter];
		cout << character;
		character = ' ';
	}
}