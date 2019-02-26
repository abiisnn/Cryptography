void llenadoMatriz(int **K) {
	srand(time(NULL));
	int num = 0, cont, cont2;
	FILE *f;
	// Create file to save key
	f = fopen("C:/Users/Dell/Documents/Crypto/Session2/key.txt", "w");
	for(cont = 0; cont < 3; cont++){
		fprintf(f, "(\t");
		for(cont2 = 0; cont2 < 3; cont2++){
			// Generated element in the matrix (random) and added to it
			num = rand() % 10;
			K[cont][cont2] = num;
			fprintf(f, "%d\t", K[cont][cont2]);
		}
		fprintf(f, ")\n");
	}
	fclose(f);
}