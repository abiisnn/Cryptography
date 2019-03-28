int elementSB(int m) {
	vector<int> matrix = {143, 199, 227, 241, 248, 124, 62, 31};	
	vector<int> mul(8);	
	int i, j, k, n, r, aux, auxN, x;
	int fijo = 198, mult = 0, suma = 0;

	// Convert m to be used
	n = 0; j = 7;
	for(i = 0; i < 8; i++) {
		aux = (m >> i) & 1;
		if(aux == 1)
			n = n | (1 << j);
		j--;
	}

	k = 7;
	for(i = 0; i < matrix.size(); i++) {
		// We make the multiplication
		r = matrix[i] & n;
		x = 0;
		for(j = 0; j < 8; j++) {
			aux = (r >> j) & 1;
			x = x xor aux;				
		} 
		if(x == 1)
			mult = mult | (x << k);
		k--;
	}
	
	x = 0; j = 0;
	for(i = 7; i < mul.size(); i--) {
		// Get i bit of fijo
		aux = (fijo >> i) & 1;
		auxN = (mult >> i) & 1;
		x = auxN xor aux;
		if(x == 1)
			suma = suma | (x << j);		
		x = 0;
		j++;
	}
	return suma;
}