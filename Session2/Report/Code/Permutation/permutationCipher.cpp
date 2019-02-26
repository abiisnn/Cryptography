void permutationCipher(string blo, int m) {
	int i, pos;
	string cipher;
	for(i = 0; i < m; i++) 
		cipher.push_back(' ');

	for(i = 0; i < blo.length(); i++) 
		cipher[key.at(i)-1] = blo[i]; 

	cout << cipher;
}