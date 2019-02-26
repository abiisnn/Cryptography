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