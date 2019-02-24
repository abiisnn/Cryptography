#include <iostream>
#include <cstring>
#include <string>
#include <fstream>
#include <stdio.h>

using namespace std;

//NOTA: Caracteres imprimibles de 32-126
<<<<<<< HEAD
// c++ keyWordCipher.cpp -o Key
// ./K < KWC.in > KWC.out
void menu();
=======
// c++ keyWordCipher.cpp -o kwc
/* ./kwc > cip.out
	keyWordCipher.in*/

>>>>>>> ca89a08e0d3f786b7b79d07e93a0ba825954697c
string cipher(string key, string msj);

int main(){
<<<<<<< HEAD
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
=======
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
>>>>>>> ca89a08e0d3f786b7b79d07e93a0ba825954697c
	cout << cipher(key, msj);
	return 0;
}

string cipher(string key, string msj){
	//Obtención del mensaje caracter a caracter
	int cont3, cont2, nuevo_val = 0, aux = 0, n = 95, tam_key = 0, val_c = 0;
	string msj_cip;
	char aux_msj;
	//Tamaño de la palabra clave
	tam_key = key.size();
	for(cont3 = 0, cont2 = 0; cont3 < msj.size(); cont3++){
		if(msj[cont3] >= 32 && msj[cont3] <= 126){
			val_c = (int)msj[cont3] - 32;
			aux = (int)key[cont2 % tam_key] - 32;
			//Se obtiene el nuevo valor para la letra
			nuevo_val = (val_c + aux) % n;
			nuevo_val += 32;
			aux_msj = (char)nuevo_val;
			msj_cip += aux_msj;
			cont2++;
		}
	}
	return msj_cip;
}
