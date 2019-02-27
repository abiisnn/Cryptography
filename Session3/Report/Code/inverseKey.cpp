int inverseKey(int **K, int **K_inv) {
	int cofac[4];
	int cont, cont2, cont3, cont4, cont_cofac, det = 0, inv_det = 0, cofactor = 0, n = 95;
	//Obtention of the determinant modulo n
	det = matrixDeterminant(K);
	if(det < 0)
		det = n + (det % n);
	else
		det = det % n;
	//Ontention of the determinant inverse
	inv_det = inverse(n, det);
	//In case that the determinant doesn't have inverse, the function returns -1
	if(inv_det == -1)
		return -1;
	//In case that it has inverse
	else{
		// Getattached matrix
		// Two cycles to traverse all the elements of the matrix
		for(cont = 0; cont < 3; cont++) {
			for(cont2 = 0; cont2 < 3; cont2++) {
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
				// Evaluated if i+j is even or odd
				if ((cont + cont2) % 2 != 0)
					cofactor = cofactor * (-1);
				// Get modulo of the cofactor
				if(cofactor < 0)
					cofactor = n + (cofactor % n);
				else 
					cofactor = cofactor % n;
				// Cofactor element is multiplied by the inverse of the determinan
				// The module is obtained
				cofactor = (cofactor * inv_det) % n; 
				/* The matrix of cofactors is transposed to obtain attached 
				 (which will be the inverse of the key by the previous operation) */
				K_inv[cont2][cont] = cofactor;
			}
		}
		return 0;
	}
}