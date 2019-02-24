#include <iostream>
#include <cstring>
#include <string>
#include <stdio.h>

using namespace std;

//NOTA: Caracteres imprimibles de 32-126
// c++ keyWordCipher.cpp -o Key
// ./K < KWC.in > KWC.out
void menu();
string cipher(string key, string msj);
string descipher(string key, string msj);

int main(){
	string key;
	string msj;
	int op = 0;
	getline(cin, key, '\n');
	getline(cin, msj);
	cout<<"Hola";
	for(int c=0; c<msj.size(); c++){
		if(msj[c]=='\n')
			cout<<"SI";
	}
	//cout << key << endl;
	//cout << msj << endl;
	cout << cipher(key, msj);
	return 0;
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
