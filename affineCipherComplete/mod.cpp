#include <iostream>
using namespace std;

int mod(int a, int b) {
	if (a == 0)
		return 0;
	if(a > 0)
		return a % b;
	else
		return b - mod(a*(-1), b);
	
}
int main(int argc, char const *argv[])
{
	int a = mod(-15, 11);
	cout << a;
	return 0;
}

