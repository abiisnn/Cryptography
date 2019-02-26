void fillSpace(int size, int m) {
	string aux = " ";
	double sized, md, bd;
	int i, block, end;

	// Calculate how many spaces we have
	sized = (double)size;
	md = (double)m; 
	bd = sized / md;
	bd = floor(bd); // Exact block
	block = (int)bd;
	end = m * (block + 1);
	// Fill spaces with " "
	for(i = size; i < end; i++) {
		msj = msj + aux;
	}
}