int **obtencionLlave(string msj, string cip_msj) {
	int cont3 = 0, ban, cont, cont2, aux=0;
	
	//Matrix corresponding to the plaintext
	int **X = (int**)malloc(sizeof(int*)*3);
	//Matrix corresponding to the ciphertext
	int **Y = (int**)malloc(sizeof(int*)*3);
	//Matrix corresponding to the key
	int **K = (int**)malloc(sizeof(int*)*3);
	int **Xinv = (int**)malloc(sizeof(int*)*3);
	
	for(int i = 0; i < 3; i++) {
		X[i] = (int*)malloc(sizeof(int)*3);
		Y[i] = (int*)malloc(sizeof(int)*3);
		K[i] = (int*)malloc(sizeof(int)*3);
		Xinv[i] = (int*)malloc(sizeof(int)*3);
	}
	
	// Is repeated till there's found a key that has inverse working in modulo 95 
	do{
		// Fill X, Y with the first 3 triads of the plaintext and ciphertext
		for(cont = 0; cont < 3; cont3+=3, cont++) {
			/* Each one of the if-else is in charge of verify 
			that the char specified belogs to the
			printable characters in ascii */
			if(msj[cont3] >= 32 && msj[cont3] <= 126) {
				X[cont][0] = (int)msj[cont3] - 32;
				Y[cont][0] = (int)cip_msj[cont3] - 32; 
			}
			else {
				cont3++;
				X[cont][0] = (int)msj[cont3] - 32;
				Y[cont][0] = (int)cip_msj[cont3] - 32;
			}
			
			if(msj[cont3 + 1] >= 32 && msj[cont3 + 1] <= 126){
				X[cont][1] = (int)msj[cont3 + 1] - 32;
				Y[cont][1] = (int)cip_msj[cont3 + 1] - 32;
			}
			else {
				cont3++;
				X[cont][1] = (int)msj[cont3 + 1] - 32;
				Y[cont][1] = (int)cip_msj[cont3 + 1] - 32;
			}
			
			if(msj[cont3 + 2] >= 32 && msj[cont3 + 2] <= 126){
				X[cont][2] = (int)msj[cont3 + 2] - 32;
				Y[cont][2] = (int)cip_msj[cont3 + 2] - 32;
			}
			else {
				cont3++;
				X[cont][2] = (int)msj[cont3 + 2] - 32;
				Y[cont][2] = (int)cip_msj[cont3 + 2] - 32;
			}
		}
		//The matrix X is inverted
		ban = inverseKey(X, Xinv);
	}while(ban != 0);
	
	// Multiplication to obtain K
	FILE *f=fopen("C:/Users/Dell/Documents/Crypto/Session3/key.txt", "w");
	for(cont = 0; cont < 3; cont++){
		fprintf(f, "(\t");
		for(cont3 = 0; cont3 < 3; cont3++){
			for(cont2 = 0; cont2 < 3; cont2++){
				//Obtains the coeficients Aij of the matrix
				aux = aux + (Xinv[cont][cont2] * Y[cont2][cont3]);
				if(cont2 == 2){
					//Calculate modulo 95 for each element in the matrix 
					if(aux < 0)
						aux = 95 - (aux % 95);
					else
						aux = aux % 95;
					K[cont][cont3] = aux;
					aux = 0;
					fprintf(f, "%d\t", K[cont][cont3]);
				}
			}
		}
		fprintf(f, ")\n");
	}
	fclose(f);
	return K;
}