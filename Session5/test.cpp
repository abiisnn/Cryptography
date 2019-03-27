#include <iostream>
#include <cryptopp/des.h>
#include <cryptopp/osrng.h>
#include <cryptopp/filters.h>
#include <cryptopp/rng.h>
#include <cryptopp/randpool.h>
#include <cryptopp/rdrand.h>
#include <cryptopp/drbg.h>


using namespace std;

int main(int argc, char const *argv[])
{
    AutoSeededRandomPool prng;

    SecByteBlock key(0x00, DES_EDE2::DEFAULT_KEYLENGTH);
    prng.GenerateBlock(key, key.size());

    byte iv[DES_EDE2::BLOCKSIZE];
    prng.GenerateBlock(iv, sizeof(iv));

    string plain = "CBC Mode Test";
    string cipher, encoded, recovered;

    /*********************************\
    \*********************************/

    try
    {
        cout << "plain text: " << plain << endl;

        CBC_Mode< DES_EDE2 >::Encryption e;
        e.SetKeyWithIV(key, key.size(), iv);

        // The StreamTransformationFilter adds padding
        //  as required. ECB and CBC Mode must be padded
        //  to the block size of the cipher.
        StringSource ss1(plain, true, 
            new StreamTransformationFilter(e,
                new StringSink(cipher)
            ) // StreamTransformationFilter      
        ); // StringSource
    }
    catch(const CryptoPP::Exception& e)
    {
        cerr << e.what() << endl;
        exit(1);
    }

    /*********************************\
    \*********************************/

    // Pretty print
    StringSource ss2(cipher, true,
        new HexEncoder(
            new StringSink(encoded)
        ) // HexEncoder
    ); // StringSource

    cout << "cipher text: " << encoded << endl;

    /*********************************\
    \*********************************/

    try
    {
        CBC_Mode< DES_EDE2 >::Decryption d;
        d.SetKeyWithIV(key, key.size(), iv);

        // The StreamTransformationFilter removes
        //  padding as required.
        StringSource ss3(cipher, true, 
            new StreamTransformationFilter(d,
                new StringSink(recovered)
            ) // StreamTransformationFilter
        ); // StringSource

        cout << "recovered text: " << recovered << endl;
    }
    catch(const CryptoPP::Exception& e)
    {
        cerr << e.what() << endl;
        exit(1);
    }
    return 0;
}