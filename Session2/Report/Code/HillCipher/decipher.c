string decipher(int **K, char *path) {
	fstream f;
	int car[3], new_val[3], cofac[4];
	int cont, aux = 0, cont2, cont3, n = 95;
	string aux_msj, msj, decip_msj;
	
	// Obtain inverse K
	int **K_inv = inverseKey(K);
	
	// Get cipher text
	f.open(path);
	if(f.is_open()) {
		while(!f.eof()) {
			getline(f, aux_msj);
			msj += aux_msj; 
		}
		f.close();
	}
	aux = 0;
	// DESCIPHER: Cycle obtained by the group of 3 characters
	for(cont3 = 0; cont3 < msj.size(); cont3+=3) {
		car[0] = (int)msj[cont3] - 32;
		car[1] = (int)msj[cont3 + 1] - 32;
		car[2] = (int)msj[cont3 + 2] - 32;
		// Multiplication of the matrices to obtain the new values
		for(cont = 0; cont < 3; cont++) {
			for(cont2 = 0; cont2 < 3; cont2++)
				// Get Aij
				aux=aux+(car[cont2] * K_inv[cont2][cont]);
			new_val[cont] = aux % n;
			aux = 0;
			decip_msj += (new_val[cont] + 32);
		}
	}
	return decip_msj;
}