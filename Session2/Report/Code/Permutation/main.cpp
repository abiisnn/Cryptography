int main(int argc, char const *argv[]) {
	int i, j, auxK, m, size, block, space, end;
	string tam, cadena;
    char *path;
    fstream f;
	vector<int> vec;

	// Read m
    getline(cin, tam);

	// Read plain text
	string dir;
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	f.open(path);
	if(f.is_open()){
		while(!f.eof()){
			getline(f, cadena);
			msj += cadena; 
		}
		f.close();
	}

	// Convert m to integer
	m = atoi(tam.c_str());

	// Generate a valid Key
	generateKey(m);	

	// Use gcd to know if we need spaces
	size = msj.length();	
	if((gcdRecursive(m, size) == 1) && (m > 1)) {
		fillSpace(size, m);
	}

	// Cipher
 	string cip = " ";
 	i = 0;
 	while(i < msj.length()) {
 		// Obtain a substring with size of m
		cip = msj.substr(i, m);
		// Send substring to cipher
		permutationCipher(cip, m);
		i += m;
 	}
	return 0;
}