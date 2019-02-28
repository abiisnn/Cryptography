void permutationDecipher(string blo, int m) {
	int i, pos;
	string cipher;
	for(i = 0; i < m; i++) 
		cipher.push_back(' ');

	for(i = 0; i < blo.length(); i++) 
		cipher[i] = blo[key.at(i)-1]; 
	cout << cipher;
}
