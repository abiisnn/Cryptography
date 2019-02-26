int **generateKey() {
	int det=0, n=95;
	int **K = (int**)malloc(sizeof(int*)*3);
	//Se crea la matriz K
	for(int i = 0; i < 3; i++)
		K[i] = (int*)malloc(sizeof(int)*3);
	// Keys are generated until one is valid
	do{
		llenadoMatriz(K);
		det = matrixDeterminant(K);
	}while(gcdRecursive(det, n) != 1);
	return K;
}