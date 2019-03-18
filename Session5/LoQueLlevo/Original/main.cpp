#include <stdio.h>
#include <stdlib.h>
#include <cryptlib.h>

void main(){
	byte bufer[50]
	byte *buf = (byte)malloc(sizeof(buf));
	GenerateBlock(bufer, 50);
	for(cont=0; cont<50; cont++)
		cout<<bufer;
}