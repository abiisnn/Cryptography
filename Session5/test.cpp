//#include <QtCore/QCoreApplication>

#include <cryptopp810/des.h>

#include <stdio.h>

// keyString 是一个密钥，必须保证长度要超过 16
// block 是要处理的数据，处理后的数据也同时存放在 block 里，必须保证它的长度为 8 的整倍数
// length 是 block 的长度，必须保证它为 8 的整倍数
// direction 是表示是否是加密还是解密，若是加密，则用 CryptoPP::ENCRYPTION, 解密用 CryptoPP::DECRYPTION
void DES_Process(const char *keyString, byte *block, size_t length, CryptoPP::CipherDir direction){
    using namespace CryptoPP;

    byte key[DES_EDE2::KEYLENGTH];
    memcpy(key, keyString, DES_EDE2::KEYLENGTH);
    BlockTransformation *t = NULL;

    if(direction == ENCRYPTION)
        t = new DES_EDE2_Encryption(key, DES_EDE2::KEYLENGTH);
    else
        t = new DES_EDE2_Decryption(key, DES_EDE2::KEYLENGTH);

    int steps = length / t->BlockSize();
    for(int i=0; i<steps; i++){
        int offset = i * t->BlockSize();
        t->ProcessBlock(block + offset);
    }

    delete t;
}

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    byte block[1024] = "++++++++--------********////////";

    const char *key = "http://qsanguosha.org/forum";

    printf("original text: %s\n", block);

    DES_Process(key, block, 16, CryptoPP::ENCRYPTION);

    printf("Encrypt: %s\n", block);

    DES_Process(key, block, 16, CryptoPP::DECRYPTION);

    printf("Decrypt: %s\n", block);

    return a.exec();
}