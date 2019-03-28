public static void modeEEE2Keys(String nameFile, String mode, String nameFileKey, String nameFileIV) throws Exception {
    int i;
    byte[][] IV = new byte[3][];
    byte[] keysBytes, cipherText, decrypt;
    SecretKey[] desKey;
    String c, keyString, nameIV;

    // Get bytes of message
    byte[] message = createByte(nameFile);
    
    // Generate keys
    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
    keygenerator.init(56); // Size of key

    // Get keys, we use the same key for i = 2, 3
    desKey = new SecretKey[3];
    for(i = 0; i < 3; i++) {
      if(i == 2)
        desKey[i] = desKey[1];
      else
        desKey[i] = keygenerator.generateKey();
    }
    keyString = "";
    for(i = 0; i < 3; i++) 
      keyString = keyString + Base64.getEncoder().encodeToString(desKey[i].getEncoded());       

    keysBytes = keyString.getBytes();

    createFile(keysBytes, nameFileKey);
  
    cipherText = message;
    c = "DES/" + mode + "/PKCS5Padding";
    for(i = 0; i < 3; i++) {
      if(i > 0) 
        c = "DES/" + mode + "/NoPadding";
      Cipher cipher = Cipher.getInstance(c);
      
      // Generate an initialization vector (IV)
      SecureRandom secureRandom = new SecureRandom();
      byte[] ivspec = new byte[cipher.getBlockSize()];
      secureRandom.nextBytes(ivspec);

      cipher.init(Cipher.ENCRYPT_MODE, desKey[i], secureRandom);
      IV[i] = cipher.getIV();
      cipherText = cipher.doFinal(cipherText);       
    }
    createFileIVText(cipherText, IV, nameFileIV);
  }