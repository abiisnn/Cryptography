#include <iostream>
using namespace std;

void algorithm1(int u, int v);
void algorithm2(int u, int v);

int main(int argc, char const *argv[])
{
	int a, b;
	int A1, A2;

	/*cout << "ALGORITHM 1:" << endl;
	a = 11, b = 30 ;
	cout << "Para:" << a << "," << b << endl;
	algorithm1(a, b);

	a = 2, b = 27;
	cout << "Para:" << a << "," << b << endl;
	algorithm1(a, b);
	
	a = 7, b = 30;
	cout << "Para:" << a << "," << b << endl;
	algorithm1(a, b);
	
	a = 15, b = 8;
	cout << "Para:" << a << "," << b << endl;
	algorithm1(a, b);
*/
	cout << "ALGORITHM 2:" << endl;
	a = 11, b = 30 ;
	cout << "Para:" << a << "," << b << endl;
	algorithm2(a, b);

	a = 2, b = 27;
	cout << "Para:" << a << "," << b << endl;
	algorithm2(a, b);
	
	a = 7, b = 30;
	cout << "Para:" << a << "," << b << endl;
	algorithm2(a, b);
	
	a = 15, b = 8;
	cout << "Para:" << a << "," << b << endl;
	algorithm2(a, b);
	return 0;
}

void algorithm1(int u, int v) {
	int x, y, x1, y1, x2, y2, q, d, p, r;
	x1 = 1; y1 = 0; x2 = 0; y2 = 1;
	while(u != 0) {
		q = u / v; r = v - (q * u); x = x2 - (q * x1); y = y2 - (q * y1);
		v = u; u = r; x2 = x1; x1 = x; y2 = y1; y1 = y;
	}
	d = v; x = x2; y = y2;
	cout << "d: " << d << "x: " << x << "y: " << y;
}

void algorithm2(int u, int v) {
	int x, y, x1, y1, x2, y2, q, d, p, r;
	x1 = 1; x2 = 0;
	while(u != 1) {
		q = u / v; r = v - (q * u); x = x2 - (q * x1);
		v = u; u = r; x2 = x1; x1 = x; 
	}
	d = x1 % p;
	cout << x1 << " mod " << p << " : " << d;
}
