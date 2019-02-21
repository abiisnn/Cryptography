#include <iostream>
#include <cstring>
#include <string>
#include <fstream>
#include <stdio.h>

using namespace std;

//NOTA: Caracteres imprimibles de 32-126
// c++ keyWordCipher.cpp -o kwc
/* ./kwc > cip.out
	keyWordCipher.in*/

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
