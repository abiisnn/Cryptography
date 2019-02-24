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
