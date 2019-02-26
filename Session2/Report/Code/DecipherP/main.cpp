int main(int argc, char const *argv[]) {
	int i, j, auxK, m, size, block, space, end, num;
	string tam, cadena, aux;
    char *path, *pathAux;
    fstream f, fAux;
	vector<int> vec;

	// Read Key file
	string dir;
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	f.open(path);
	if(f.is_open()) {
		while(!f.eof()) {
			getline(f, cadena);
			keyS += cadena; 
		}
		f.close();
	}
	// Read cipher text
	string dirAux;
	getline(cin, dirAux);
	pathAux = const_cast<char*>(dirAux.c_str());
	fAux.open(pathAux);
	if(fAux.is_open()) {
		while(!fAux.eof()) {
			getline(fAux, aux);
			msj += aux;
		}
		fAux.close();
	}
	// Get inverse Key
	getInverse(keyS);	

	// Descipher 
	string cip = " ";
 	i = 0;
 	while(i < msj.length()) {
 		// Obtain a substring with size of m
		cip = msj.substr(i, keyS.length());
		// Send substring to cipher
		permutationDecipher(cip, keyS.length());
		i += keyS.length();
 	}
	return 0;
}