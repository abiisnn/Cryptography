int gcdRecursive(int a, int b) {
	if(b == 0) 
		return a;
	else {
		return gcdRecursive(b, a % b);
	}
}