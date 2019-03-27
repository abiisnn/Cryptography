#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string>
#include <string.h>
#include <sstream>

using namespace std;

int binaryFieldMultiplication(unsigned long long m, unsigned long long a, unsigned long long b);
int modulo_m(unsigned long long m, unsigned long long a, unsigned long long b);

int main(){
	unsigned long long m, a, b;
	cout<<"Ingrese los datos representados como enteros: \n";
	cout<<"Ingrese m: ";
	cin>>m;
	cout<<"Ingrese a: ";
	cin>>a;
	cout<<"Ingrese b: ";
	cin>>b;

	string polynom, bit_rep;
	char aux[2];
	int i, corr;
	int mod = modulo_m(m, a, b);
	//In case that a and b belongs to the field
	if(mod != -1){
		for(i = 0, corr = 1; corr <= mod; corr = corr << 1, i++){
			//If the currently evaluationg bit is equals 1 
			if(corr & mod){
				//Adds he respectively x^i to the polynom string
				polynom += "x^";
				polynom += static_cast<std::ostringstream*>(&(std::ostringstream() << i))->str();
				polynom += " + ";
				//Adds 1 to the bit_rep string
				bit_rep.insert(0, "1");
			}
			else
				bit_rep.insert(0, "0");
		}
		polynom.erase(polynom.end() - 2);
		//Print polynome and bit representation
		cout<<polynom<<"\n";
		cout<<bit_rep;
	}
	else
		cout<<"Los polinomios a y/o b no pertenecen al campo";	
	return 0;
}

int binaryFieldMultiplication(unsigned long long m, unsigned long long a, unsigned long long b){
	//a is of grade 1 or 0
	if(a <= 0x1){
		return b;
	}
	//a is of grade greater than 1
	else if(a >= 0x2){
		b = b << 1;
		a = a >> 1;
		binaryFieldMultiplication(m, a, b);
	}
}

int modulo_m(unsigned long long m, unsigned long long a, unsigned long long b){
	int corr = 1, mod = 0;
	//Case that the grade of polynoms a or b is greater than the grade of polynom m
	if((int)log2(a) >= (int)log2(m) || (int)log2(b) >= (int)log2(m) || (int)log2(a) + (int)log2(b) >= (int)log2(m))
		return -1;
	//To evaluate every term of the polynom
	for(int i = 0; corr <= a; corr = corr << 1, i++){ 
		if(corr & a)
			mod = mod ^ binaryFieldMultiplication(m, corr, b);
	}
	return mod = mod ^ m;
}