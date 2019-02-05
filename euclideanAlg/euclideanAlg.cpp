#include<iostream>
using namespace std;

int  main(int argc, char const *argv[])
{
	int n = 27;
	int inv = 23;
	int a = n;
	int b = inv;
	int quotient;
	while (b) {
		quotient = a / b;
        a %= b;
        cout << b << " " << quotient << endl; 
        swap(a, b);
    }
	return 0;
}