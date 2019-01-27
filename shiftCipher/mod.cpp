#include <iostream>
using namespace std;

int mod(int a, int b);
int main(int argc, char const *argv[])
{	
	int r, a, b;
	cin >> a >> b;
	r = a % b;
	cout << r;
	return 0;
}

