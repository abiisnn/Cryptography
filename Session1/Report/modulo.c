int mod(int a, int b) {
	if (a == 0)
		return 0;
	if(a > 0)
		return a % b;
	else
		return b - mod(a*(-1), b);	
}