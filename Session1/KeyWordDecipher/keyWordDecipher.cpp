#include <iostream>
#include <cstring>
#include <string>
#include <fstream>
#include <stdio.h>

using namespace std;

//NOTA: Caracteres imprimibles de 32-126
// g++ keyWordCipher.cpp -o Key
/* ./Key > des.out
	cip.in*/

void menu();
string descipher(string key, string msj);

int main(){
	string key, msj, cadena;
	fstream f;
	char *path;
	//Obtención de la clave
	getline(cin, key);
	string dir;
	getline(cin, dir);
	path = const_cast<char*>(dir.c_str());
	f.open(path);
	if(f.is_open()){
		while(!f.eof()){
			getline(f, cadena);
			msj += cadena; 
		}
		f.close();
	}
	cout << descipher(key, msj);
	return 0;
}

void menu(){
	cout<<"Escoja la opcion que desee realizar: ";
	cout<<"\n1.- Cifrar";
	cout<<"\n2.- Descifrar";
	cout<<"\n3.- Salir         ";
}

string descipher(string key, string msj){
	//Obtención del mensaje caracter a caracter
	int cont3, nuevo_val = 0, aux = 0, n = 95, tam_key = 0, val_c = 0;
	string msj_descip;
	char aux_msj;
	//Tamaño de la palabra clave
	tam_key = key.size();
	for(cont3 = 0; cont3 < msj.size(); cont3++){
		val_c = (int)msj[cont3] - 32;
		aux = (int)key[cont3 % tam_key] - 32;
		//Se obtiene el nuevo valor para la letra
		nuevo_val = val_c - aux;
		if(nuevo_val < 0)
			nuevo_val = n + (nuevo_val % n);
		else
			nuevo_val = nuevo_val % n;
		nuevo_val += 32;
		aux_msj = (char)nuevo_val;
		msj_descip += aux_msj;
	}
	return msj_descip;
}