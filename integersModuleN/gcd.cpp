#include<iostream>
using namespace std;

int gcdRecursive(int a, int b);
int gcdNoRecursive(int a, int b);
int main(int argc, char const *argv[])
{
	int a, b;
	int n;
	n =	gcdRecursive(a, b);
	return 0;
}

int gcdRecursive(int a, int b) {
	if(b == 0) 
		return a;
	else {
		return gcdRecursive(b, a % b);
	}
}

int gcdNoRecursive(int a, int b) {
    while (b) {
        a %= b;
        swap(a, b);
    }
    return a;
}