int **inverseKey(int **K){
	int cofac[4];
	int cont, cont2, cont3, cont4, cont_cofac, det = 0, inv_det = 0, cofactor = 0, n = 95;
	// Obtaining the determinant and its inverse
	det = matrixDeterminant(K);
	inv_det = inverse(n, det);
	// Create inverse matrix
	int **K_inv = (int**)malloc(sizeof(int*) * 3);
	for(int i = 0; i < 3; i++)
		K_inv[i] = (int*)malloc(sizeof(int) * 3);
	// Create attached matrix
	// First two cycles to traverse all the elements of the matrix
	for(cont = 0; cont < 3; cont++){
		for(cont2 = 0; cont2 < 3; cont2++){
			// Last two cycles to obtain cofactor of the element analyzed
			cont_cofac = 0;
			for(cont3 = 0; cont3 < 3; cont3++){
				for(cont4 = 0; cont4 < 3; cont4++){
					if(cont != cont3 && cont2 != cont4){
						cofac[cont_cofac] = K[cont3][cont4];
						cont_cofac++;
					}
				}
			}
			// Get cofactor
			cofactor = cofac[0] * cofac [3] - cofac[1] * cofac[2];
			// It is evaluated if i + j of the position of the matrix is even or odd
			if ((cont + cont2) % 2 != 0)
				cofactor = cofactor * (-1);
			// Get mod of cofactor
			if(cofactor < 0)
				cofactor = n + (cofactor % n);
			else 
				cofactor = cofactor % n;
			// The cofactor element is multiplied by the inverse of the determinant and the module is obtained
			cofactor = (cofactor * inv_det) % n; 
			/* The matrix of cofactors is transposed to obtain attached 
			   (which will be the inverse of the key by the previous operation) */
			K_inv[cont2][cont] = cofactor;
		}
	}
	return K_inv;
}