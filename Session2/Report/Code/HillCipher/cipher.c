string cipher(int **K, char *path) {
	fstream f;
	int car[3], new_val[3];
	int cont, ck = 0, aux = 0, cont2, cont3 = 0;
	string aux_msj, msj, cip_msj;
	// Read file
	f.open(path);
	if(f.is_open()) {
		while(!f.eof()) {
			getline(f, aux_msj);
			msj += aux_msj; 
		}
		f.close();
	}
	// CHIPER : Cycle obtained by the group of 3 characters 
	for(cont3 = 0; cont3 < msj.size(); cont3+=3){
		car[0] = (int)msj[cont3] - 32;
		car[1] = (int)msj[cont3 + 1] - 32;
		car[2] = (int)msj[cont3 + 2] - 32;
		
		// Multiplication of the matrices to obtain the new values
		for(cont = 0; cont < 3; cont++){
			for(cont2 = 0; cont2 < 3; cont2++)
			// Obtain Aij
				aux=aux+(car[cont2] * K[cont2][cont]);
			new_val[cont] = aux % 95;
			aux = 0;
			cip_msj += (new_val[cont] + 32);
		}
	}
	return cip_msj;
}