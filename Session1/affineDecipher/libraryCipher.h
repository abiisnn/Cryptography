#include <iostream>
#include <cstring>
using namespace std;
int getNumber(char letter);
int gcdRecursive(int a, int b);
void affineCipher(int n, string message, int a, int b);
void affineDecipher(int n, string cipher, int a, int b);
int inverse(int z, int a);
int extendedAlgorithm(int **ecuaciones, int numEc);
int mod(int a, int b);