public static void modeEEE(String nameFile, String mode, String nameFileKey, String nameFileIV) throws Exception {
    // Get bytes of message
    int i;
    byte[][] IV = new byte[3][];
    byte[] keysBytes, cipherText;
    SecretKey[] desKey;
    String keyString;

    byte[] message = createByte(nameFile);

    // Use a key generator
    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
    keygenerator.init(56); // Size of key
    desKey = new SecretKey[3];
    // Generate a key
    for(i = 0; i < 3; i++) 
      desKey[i] = keygenerator.generateKey();
    
    keyString = "";
    for(i = 0; i < 3; i++) 
      keyString = keyString + Base64.getEncoder().encodeToString(desKey[i].getEncoded());       
    
    keysBytes = keyString.getBytes();

    createFile(keysBytes, nameFileKey);
  
    cipherText = message;

    String c = "DES/"+ mode +"/PKCS5Padding";
    for(i = 0; i < 3; i++) {
      if(i > 0) 
        c = "DES/"+ mode +"/NoPadding";
      Cipher cipher = Cipher.getInstance(c);
      // generate an initialization vector (IV)
      SecureRandom secureRandom = new SecureRandom();
      byte[] ivspec = new byte[cipher.getBlockSize()];
      secureRandom.nextBytes(ivspec);
      cipher.init(Cipher.ENCRYPT_MODE, desKey[i], secureRandom);
      IV[i] = cipher.getIV();
      cipherText = cipher.doFinal(cipherText);
    }
    createFileIVText(cipherText, IV, nameFileIV);
  }