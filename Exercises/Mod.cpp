#include <iostream>
#include <cstring>
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
	cout << mod(-6, 27);
	return 0;
}
