int main(int argc, char const *argv[]) {
	int i, j, numero;
	for(i = 0; i < inverseTable.size(); i++) {
		for(j = 0; j < inverseTable.size(); j++) {
			numero = elementSB(inverseTable[i][j]);
			decToHex(numero);
			cout << " ";
		}
		cout << endl;
	}
	return 0;
}
