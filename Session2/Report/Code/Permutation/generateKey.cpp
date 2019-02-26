void generateKey(int n) {
	int i, iSecret;
	srand(time(NULL));
	// Fill vector Key
	i = 0;
	while(i < n) {
		// Generate secret number between 1 and m:
	    iSecret = 1 + rand() % (n);
	    if(find(key.begin(), key.end(), iSecret) == key.end()) {
			key.push_back(iSecret); i++;
		}
	}
	// Print key 
	for(i = 0; i < n; i++) 
		cout << key.at(i);
	cout << endl;
}