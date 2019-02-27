int main() {
	char *path, *path2;
	string dir, cip_msj, msj;
	int **K;
	// Read text plain path
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	leerArchivo(path, &msj);
	
	// Read ciphertext path
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	leerArchivo(path, &cip_msj);

	// Get valid key
	K = obtencionLlave(msj, cip_msj);
	cout << decipher(K, cip_msj);
	return 0;
}