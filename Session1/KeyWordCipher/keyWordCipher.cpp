#include <iostream>
#include <cstring>
#include <string>
#include <fstream>
#include <stdio.h>

using namespace std;

//NOTA: Caracteres imprimibles de 32-126
// c++ keyWordCipher.cpp -o Key
// ./Key > KWC.in < KWC.out
void menu();
string cipher(string key, string msj);

int main(){
	string key, msj, cadena;
	fstream f;
	char *path;
	//Obtención de la clave
	getline(cin, key);
	//Lectura del archivo
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
	cout << cipher(key, msj);
	return 0;
}

void menu(){
	cout<<"Escoja la opcion que desee realizar: ";
	cout<<"\n1.- Cifrar";
	cout<<"\n2.- Descifrar";
	cout<<"\n3.- Salir         ";
}

string cipher(string key, string msj){
	//Obtención del mensaje caracter a caracter
	int cont3, nuevo_val = 0, aux = 0, n = 95, tam_key = 0, val_c = 0;
	string msj_cip;
	char aux_msj;
	//Tamaño de la palabra clave
	tam_key = key.size();
	for(cont3 = 0; cont3 < msj.size(); cont3++){
		val_c = (int)msj[cont3] - 32;
		aux = (int)key[cont3 % tam_key] - 32;
		//Se obtiene el nuevo valor para la letra
		nuevo_val = (val_c + aux) % n;
		nuevo_val += 32;
		aux_msj = (char)nuevo_val;
		msj_cip += aux_msj;
	}
	return msj_cip;
}
