#include <iostream>
#include <cstring>
#include <string>
#include <stdio.h>

using namespace std;

//NOTA: Caracteres imprimibles de 32-126
	
void menu();
string cipher(string key, string msj);
string descipher(string key, string msj);

int main(){
	string key = " ";
	string msj = " ";
	int op = 0;

	getline(cin, key);
	getline(cin, msj);
	cout<<cipher(key, msj) + "\n";
/*	do{
		//Despliegue del menu de opciones
		menu();
		cin>>op;
		//Cifrado del mensaje
		if(op == 1){
			getline(cin, key);
			getline(cin, msj);
			cout<<cipher(key, msj) + "\n";
		}
		//Descifrado del mensaje
		else if(op == 2){
			getline(cin, key);
			getline(cin, msj);
			cout<<descipher(key, msj) + "\n";
		}
	}while(op != 3);
*/	return 0;
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
