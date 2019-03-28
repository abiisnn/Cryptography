public static byte[] modeEEEdecipher(String mode, String nameFileKey, String nameFileIV) throws Exception {
    int i, j;
    byte[][] IV;
    IV = new byte[3][];
    byte[] plainText, toDecrypt; 
    SecretKey[] desKey;
    String aux = "";
    String c = "DES/"+ mode +"/NoPadding";
    
    desKey = new SecretKey[3];
    createByteKey(nameFileKey);

    // Get key from file
    aux = new String(originalKey_1);
    byte[] decodedKey = Base64.getDecoder().decode(aux);
    desKey[0] = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");    // decode the base64 encoded string
    aux = new String(originalKey_2);
    decodedKey = TBase64.getDecoder().decode(aux);
    desKey[1] = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");    // decode the base64 encoded string
    aux = new String(originalKey_3);
    decodedKey = Base64.getDecoder().decode(aux);
    desKey[2] = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");    // decode the base64 encoded string

    IV[0] = new byte[8];
    IV[1] = new byte[8];
    IV[2] = new byte[8];

    // Get bytes to decrypt
    toDecrypt = createByteIVText(nameFileIV);
    plainText = toDecrypt;
    IV[0] = iv_1;
    IV[1] = iv_2;
    IV[2] = iv_3;

    for(i = 1; i <= 3; i++) {
      if(i - 3 == 0) 
        c = "DES/"+ mode +"/PKCS5Padding";
      
      Cipher decipher = Cipher.getInstance(c);
      decipher.init(Cipher.DECRYPT_MODE, desKey[3-i], new IvParameterSpec(IV[3-i]));
      plainText = decipher.doFinal(plainText);
    }
    return plainText;
  }