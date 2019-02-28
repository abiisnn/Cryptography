vvoid getInverse(string keyS) {
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