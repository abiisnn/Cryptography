package tripledes_ede;

import java.io.*;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Dell
 */
public class TripleDES_EDE {

    static Scanner sc = new Scanner(System.in);
    static Scanner num = new Scanner(System.in);
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  
    // ---------------------------------------------------
    //                  CREATE BYTE[]            
    // ---------------------------------------------------
    // Convert a File to byte[]
    public static byte[] createByte(String nameFile) { 
        String sep = System.getProperty("file.separator");
        // File have to be in ARCHIVOS dir
        String path = ".." + sep + "Original" + sep + nameFile;

        File archivo = new File(path);
        byte[] archivoBytes = null;
        long tamanoArch = archivo.length(); // File size

        archivoBytes = new byte[(int) tamanoArch];
        try {
            // Read file
            FileInputStream docu = new FileInputStream(archivo);
            // Insert in new array
            int numBytes = docu.read(archivoBytes);
            System.out.println("El archivo tiene " + numBytes + " de bytes.");
            docu.close(); // Close
        } 
        catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");
        } 
        catch (IOException e) {
            System.out.println("No se ha podido leer el archivo.");
        }
        return archivoBytes;
    }

    // ---------------------------------------------------
    //                  CREATE FILE            
    // ---------------------------------------------------
    // Convert a btye[] to File
    public static boolean createFile(byte[] fileBytes, String finalFile) throws IOException { 
        boolean ok = false; 
        try { 
        OutputStream out = new FileOutputStream(finalFile); 
        out.write(fileBytes); 
        out.close();
        ok = true; 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return ok;
    }
  
    public static void outFile(String key, byte[] message, String finalFile) throws IOException{
        File f = new File(finalFile);
        //FileWriter fw = new FileWriter(f);
        OutputStream out = new FileOutputStream(f);
        //fw.write(key);
        out.write(key.getBytes());
        out.write(message); 
        out.close();
        //fw.close();
        //BufferedOutputStream bf;
        //bf.
    }

    // ---------------------------------------------------
    //                MENU PARA EDE      
    // ---------------------------------------------------
    public static void menuEDE(int k, String name) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
            IllegalBlockSizeException, IOException, BadPaddingException, InvalidAlgorithmParameterException {
        if(k == 2) {

        }
        if(k == 3){
            // Get bytes of message
            byte[] message = createByte(name);
            String n = name.substring(0, name.indexOf("."));
            //Encryption and Decryption with CBC mode
            IvParameterSpec[] iv_cbc = EDEcipher_modeCBC(message, n);
            EDEdecipher_modeCBC(n, "../Encrypted/ipn_2keys.cbc", iv_cbc);
            //Encryption and Decryption with OFB mode
            IvParameterSpec[] iv_ofb = EDEcipher_modeOFB(message, n);
            EDEdecipher_modeOFB(n, "../Encrypted/ipn_2keys.ofb", iv_ofb);
            //Encryption and Decryption with CFB mode
            IvParameterSpec[] iv_cfb = EDEcipher_modeCFB(message, n);
            EDEdecipher_modeCFB(n, "../Encrypted/ipn_2keys.cfb", iv_cfb);
            //Encryption and Decryption with CTR mode
            IvParameterSpec[] iv_ctr = EDEcipher_modeCTR(message, n);
            EDEdecipher_modeCTR(n, "../Encrypted/ipn_2keys.ctr", iv_ctr);
        }
    }
  
    // ---------------------------------------------------
    //                MENU PARA EEE      
    // ---------------------------------------------------
    public static void menuEEE(int k) {
        if(k == 2) {

        }
        if(k == 3){

        }
    }

    // ---------------------------------------------------
    //                    MAIN          
    // ---------------------------------------------------
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException,
        IllegalBlockSizeException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {

        int opc = 0;
        while(opc != 3) {
            System.out.print("Choose a tipe of TripleDES\n");
            System.out.print("1. EDE\n");
            System.out.print("2. EEE\n");
            System.out.print("3. Exit\n");
            opc = sc.nextInt();

            int k = 0;
            if(opc == 1) {
                System.out.println("Name of the file to encrypt: ");
                String name = sc.next();
                System.out.print("Select the number of keys (2 or 3)\n");    
                k = num.nextInt();
                if(k == 2 || k == 3)
                    menuEDE(k, name);
            }
            if(opc == 2){
                System.out.print("Select the number of keys (2 or 3)\n");    
                k = num.nextInt();
                if(k == 2 || k == 3)
                    menuEEE(k);
            }
        }
    }
  
    // ---------------------------------------------------
    //              EDE Cipher CBC MODE          
    // ---------------------------------------------------
    public static IvParameterSpec[] EDEcipher_modeCBC(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");
        
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        
        /**** Cipher with mode CBC****/
        
        IvParameterSpec[] IV = new IvParameterSpec[3];
        
                /**First encryption**/
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        keygenerator.init(56); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);
        
        Cipher first_encryption = Cipher.getInstance("DES/CBC/PKCS5Padding");
        first_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_1 = first_encryption.doFinal(message);
        IV[0] = new IvParameterSpec(first_encryption.getIV());
        
                /**Decryption**/
        // Get key 
        SecretKey desKey_2 = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey_2 = Base64.getEncoder().encodeToString(desKey_2.getEncoded());
        System.out.println(encodedKey_2);

        //Generate IV
        KeyGenerator ivGenerator = KeyGenerator.getInstance("DESede");
        ivGenerator.init(168);
        // Get IV
        SecretKey iv = keygenerator.generateKey();
        byte[] iv2 = iv.getEncoded();
        //Only use the first 8 bytes for the IV
        IV[1] = new IvParameterSpec(iv2, 0, 8);
        
        Cipher decryption = Cipher.getInstance("DES/CBC/NoPadding");
        decryption.init(Cipher.DECRYPT_MODE, desKey_2, IV[1]);
        byte[] encryptedMessage_2 = decryption.doFinal(encryptedMessage_1);

                /**Second encryption**/
        Cipher second_encryption = Cipher.getInstance("DES/CBC/PKCS5Padding");
        second_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_final = first_encryption.doFinal(encryptedMessage_2);
        IV[2] = new IvParameterSpec(second_encryption.getIV());
        
        //File of the encrypted message
        String keys = encodedKey.concat(encodedKey_2);
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_2keys.cbc";
        outFile(keys, encryptedMessage_final, cName);
        System.out.println("Succesfully encrypted in mode CBC");
        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IV; 
    }

    // ---------------------------------------------------
    //              EDE Decipher CBC MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeCBC(String outFileName, String cipherFile, IvParameterSpec[] IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key_1 = new byte[12];
        byte[] key_2 = new byte[12];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key_1, 0, 12);
            in.mark(12);
            in.read(key_2, 0, 12);
            in.mark(24);
            message = new byte[(int)f.length() - 24];
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k_1 = new char[12];
        char[] k_2 = new char[12];
        for(int i = 0; i < 12; i++){
            k_1[i] = (char) Byte.toUnsignedInt(key_1[i]);
            k_2[i] = (char) Byte.toUnsignedInt(key_2[i]);
        }
        
        // decode the base64 encoded strings
        byte[] decodedKey_1 = Base64.getDecoder().decode(String.copyValueOf(k_1));
        byte[] decodedKey_2 = Base64.getDecoder().decode(String.copyValueOf(k_2));
        // rebuild the keys using SecretKeySpec
        SecretKey originalKey_1 = new SecretKeySpec(decodedKey_1, 0, decodedKey_1.length, "DES");
        SecretKey originalKey_2 = new SecretKeySpec(decodedKey_2, 0, decodedKey_2.length, "DES");
       
        /****Decipher with mode CBC***/
        
                /**First decryption**/
        Cipher first_decryption = Cipher.getInstance("DES/CBC/PKCS5Padding");
        first_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_1 = first_decryption.doFinal(message);
        
                /**Encryption**/
        Cipher encryption = Cipher.getInstance("DES/CBC/NoPadding");
        encryption.init(Cipher.ENCRYPT_MODE, originalKey_2, IVparam[1]);
        byte[] decryptedMessage_2 = encryption.doFinal(decryptedMessage_1);
        
                /**Second decryption**/
        Cipher second_decryption = Cipher.getInstance("DES/CBC/PKCS5Padding");
        second_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_final = first_decryption.doFinal(decryptedMessage_2);
        
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_2keys_d.cbc";
        if(createFile(decryptedMessage_final, newName))
          System.out.println("Succesfully decrypted in mode CBC");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }


    // ---------------------------------------------------
    //              EDE Cipher OFB MODE          
    // ---------------------------------------------------
    public static IvParameterSpec[] EDEcipher_modeOFB(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");
        
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        
        /**** Cipher with mode CBC****/
        
        IvParameterSpec[] IV = new IvParameterSpec[3];
        
                /**First encryption**/
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        keygenerator.init(56); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);
        
        Cipher first_encryption = Cipher.getInstance("DES/OFB/PKCS5Padding");
        first_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_1 = first_encryption.doFinal(message);
        IV[0] = new IvParameterSpec(first_encryption.getIV());
        
                /**Decryption**/
        // Get key 
        SecretKey desKey_2 = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey_2 = Base64.getEncoder().encodeToString(desKey_2.getEncoded());
        System.out.println(encodedKey_2);

        //Generate IV
        KeyGenerator ivGenerator = KeyGenerator.getInstance("DESede");
        ivGenerator.init(168);
        // Get IV
        SecretKey iv = keygenerator.generateKey();
        byte[] iv2 = iv.getEncoded();
        //Only use the first 8 bytes for the IV
        IV[1] = new IvParameterSpec(iv2, 0, 8);
        
        Cipher decryption = Cipher.getInstance("DES/OFB/NoPadding");
        decryption.init(Cipher.DECRYPT_MODE, desKey_2, IV[1]);
        byte[] encryptedMessage_2 = decryption.doFinal(encryptedMessage_1);

                /**Second encryption**/
        Cipher second_encryption = Cipher.getInstance("DES/OFB/PKCS5Padding");
        second_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_final = first_encryption.doFinal(encryptedMessage_2);
        IV[2] = new IvParameterSpec(second_encryption.getIV());
        
        //File of the encrypted message
        String keys = encodedKey.concat(encodedKey_2);
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_2keys.ofb";
        outFile(keys, encryptedMessage_final, cName);
        System.out.println("Succesfully encrypted in mode OFB");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IV; 
    }

    // ---------------------------------------------------
    //              EDE Decipher OFB MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeOFB(String outFileName, String cipherFile, IvParameterSpec[] IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key_1 = new byte[12];
        byte[] key_2 = new byte[12];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key_1, 0, 12);
            in.mark(12);
            in.read(key_2, 0, 12);
            in.mark(24);
            message = new byte[(int)f.length() - 24];
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k_1 = new char[12];
        char[] k_2 = new char[12];
        for(int i = 0; i < 12; i++){
            k_1[i] = (char) Byte.toUnsignedInt(key_1[i]);
            k_2[i] = (char) Byte.toUnsignedInt(key_2[i]);
        }
        
        // decode the base64 encoded strings
        byte[] decodedKey_1 = Base64.getDecoder().decode(String.copyValueOf(k_1));
        byte[] decodedKey_2 = Base64.getDecoder().decode(String.copyValueOf(k_2));
        // rebuild the keys using SecretKeySpec
        SecretKey originalKey_1 = new SecretKeySpec(decodedKey_1, 0, decodedKey_1.length, "DES");
        SecretKey originalKey_2 = new SecretKeySpec(decodedKey_2, 0, decodedKey_2.length, "DES");

        /****Decipher with mode CBC***/
        
                /**First decryption**/
        Cipher first_decryption = Cipher.getInstance("DES/OFB/PKCS5Padding");
        first_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_1 = first_decryption.doFinal(message);
        
                /**Encryption**/
        Cipher encryption = Cipher.getInstance("DES/OFB/NoPadding");
        encryption.init(Cipher.ENCRYPT_MODE, originalKey_2, IVparam[1]);
        byte[] decryptedMessage_2 = encryption.doFinal(decryptedMessage_1);
        
                /**Second decryption**/
        Cipher second_decryption = Cipher.getInstance("DES/OFB/PKCS5Padding");
        second_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_final = first_decryption.doFinal(decryptedMessage_2);
        
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_2keys_d.ofb";
        if(createFile(decryptedMessage_final, newName))
          System.out.println("Succesfully decrypted in mode OFB");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }
    
    
    // ---------------------------------------------------
    //              EDE Cipher CFB MODE          
    // ---------------------------------------------------
    public static IvParameterSpec[] EDEcipher_modeCFB(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");
        
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        
        /**** Cipher with mode CBC****/
        
        IvParameterSpec[] IV = new IvParameterSpec[3];
        
                /**First encryption**/
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        keygenerator.init(56); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);
        
        Cipher first_encryption = Cipher.getInstance("DES/CFB/PKCS5Padding");
        first_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_1 = first_encryption.doFinal(message);
        IV[0] = new IvParameterSpec(first_encryption.getIV());
        
                /**Decryption**/
        // Get key 
        SecretKey desKey_2 = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey_2 = Base64.getEncoder().encodeToString(desKey_2.getEncoded());
        System.out.println(encodedKey_2);

        //Generate IV
        KeyGenerator ivGenerator = KeyGenerator.getInstance("DESede");
        ivGenerator.init(168);
        // Get IV
        SecretKey iv = keygenerator.generateKey();
        byte[] iv2 = iv.getEncoded();
        //Only use the first 8 bytes for the IV
        IV[1] = new IvParameterSpec(iv2, 0, 8);
        
        Cipher decryption = Cipher.getInstance("DES/CFB/NoPadding");
        decryption.init(Cipher.DECRYPT_MODE, desKey_2, IV[1]);
        byte[] encryptedMessage_2 = decryption.doFinal(encryptedMessage_1);

                /**Second encryption**/
        Cipher second_encryption = Cipher.getInstance("DES/CFB/PKCS5Padding");
        second_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_final = first_encryption.doFinal(encryptedMessage_2);
        IV[2] = new IvParameterSpec(second_encryption.getIV());
        
        //File of the encrypted message
        String keys = encodedKey.concat(encodedKey_2);
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_2keys.cfb";
        outFile(keys, encryptedMessage_final, cName);
        System.out.println("Succesfully encrypted in mode CFB");
        
        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IV; 
    }

    // ---------------------------------------------------
    //              EDE Decipher CFB MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeCFB(String outFileName, String cipherFile, IvParameterSpec[] IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key_1 = new byte[12];
        byte[] key_2 = new byte[12];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key_1, 0, 12);
            in.mark(12);
            in.read(key_2, 0, 12);
            in.mark(24);
            message = new byte[(int)f.length() - 24];
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k_1 = new char[12];
        char[] k_2 = new char[12];
        for(int i = 0; i < 12; i++){
            k_1[i] = (char) Byte.toUnsignedInt(key_1[i]);
            k_2[i] = (char) Byte.toUnsignedInt(key_2[i]);
        }
        
        // decode the base64 encoded strings
        byte[] decodedKey_1 = Base64.getDecoder().decode(String.copyValueOf(k_1));
        byte[] decodedKey_2 = Base64.getDecoder().decode(String.copyValueOf(k_2));
        // rebuild the keys using SecretKeySpec
        SecretKey originalKey_1 = new SecretKeySpec(decodedKey_1, 0, decodedKey_1.length, "DES");
        SecretKey originalKey_2 = new SecretKeySpec(decodedKey_2, 0, decodedKey_2.length, "DES");

        /****Decipher with mode CBC***/
        
                /**First decryption**/
        Cipher first_decryption = Cipher.getInstance("DES/CFB/PKCS5Padding");
        first_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_1 = first_decryption.doFinal(message);
        
                /**Encryption**/
        Cipher encryption = Cipher.getInstance("DES/CFB/NoPadding");
        encryption.init(Cipher.ENCRYPT_MODE, originalKey_2, IVparam[1]);
        byte[] decryptedMessage_2 = encryption.doFinal(decryptedMessage_1);
        
                /**Second decryption**/
        Cipher second_decryption = Cipher.getInstance("DES/CFB/PKCS5Padding");
        second_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_final = first_decryption.doFinal(decryptedMessage_2);
        
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_2keys_d.cfb";
        if(createFile(decryptedMessage_final, newName))
          System.out.println("Succesfully decrypted in mode CFB");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }
    
    
    // ---------------------------------------------------
    //              EDE Cipher CTR MODE          
    // ---------------------------------------------------
    public static IvParameterSpec[] EDEcipher_modeCTR(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");
        
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        
        /**** Cipher with mode CBC****/
        
        IvParameterSpec[] IV = new IvParameterSpec[3];
        
                /**First encryption**/
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        keygenerator.init(56); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);
        
        Cipher first_encryption = Cipher.getInstance("DES/CTR/PKCS5Padding");
        first_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_1 = first_encryption.doFinal(message);
        IV[0] = new IvParameterSpec(first_encryption.getIV());
        
                /**Decryption**/
        // Get key 
        SecretKey desKey_2 = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey_2 = Base64.getEncoder().encodeToString(desKey_2.getEncoded());
        System.out.println(encodedKey_2);

        //Generate IV
        KeyGenerator ivGenerator = KeyGenerator.getInstance("DESede");
        ivGenerator.init(168);
        // Get IV
        SecretKey iv = keygenerator.generateKey();
        byte[] iv2 = iv.getEncoded();
        //Only use the first 8 bytes for the IV
        IV[1] = new IvParameterSpec(iv2, 0, 8);
        
        Cipher decryption = Cipher.getInstance("DES/CTR/NoPadding");
        decryption.init(Cipher.DECRYPT_MODE, desKey_2, IV[1]);
        byte[] encryptedMessage_2 = decryption.doFinal(encryptedMessage_1);

                /**Second encryption**/
        Cipher second_encryption = Cipher.getInstance("DES/CTR/PKCS5Padding");
        second_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_final = first_encryption.doFinal(encryptedMessage_2);
        IV[2] = new IvParameterSpec(second_encryption.getIV());
        
        //File of the encrypted message
        String keys = encodedKey.concat(encodedKey_2);
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_2keys.ctr";
        outFile(keys, encryptedMessage_final, cName);
        System.out.println("Succesfully encrypted in mode CTR");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IV; 
    }

    // ---------------------------------------------------
    //              EDE Decipher CBC MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeCTR(String outFileName, String cipherFile, IvParameterSpec[] IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key_1 = new byte[12];
        byte[] key_2 = new byte[12];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key_1, 0, 12);
            in.mark(12);
            in.read(key_2, 0, 12);
            in.mark(24);
            message = new byte[(int)f.length() - 24];
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k_1 = new char[12];
        char[] k_2 = new char[12];
        for(int i = 0; i < 12; i++){
            k_1[i] = (char) Byte.toUnsignedInt(key_1[i]);
            k_2[i] = (char) Byte.toUnsignedInt(key_2[i]);
        }
        
        // decode the base64 encoded strings
        byte[] decodedKey_1 = Base64.getDecoder().decode(String.copyValueOf(k_1));
        byte[] decodedKey_2 = Base64.getDecoder().decode(String.copyValueOf(k_2));
        // rebuild the keys using SecretKeySpec
        SecretKey originalKey_1 = new SecretKeySpec(decodedKey_1, 0, decodedKey_1.length, "DES");
        SecretKey originalKey_2 = new SecretKeySpec(decodedKey_2, 0, decodedKey_2.length, "DES");

        /****Decipher with mode CBC***/
        
                /**First decryption**/
        Cipher first_decryption = Cipher.getInstance("DES/CTR/PKCS5Padding");
        first_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_1 = first_decryption.doFinal(message);
        
                /**Encryption**/
        Cipher encryption = Cipher.getInstance("DES/CTR/NoPadding");
        encryption.init(Cipher.ENCRYPT_MODE, originalKey_2, IVparam[1]);
        byte[] decryptedMessage_2 = encryption.doFinal(decryptedMessage_1);
        
                /**Second decryption**/
        Cipher second_decryption = Cipher.getInstance("DES/CTR/PKCS5Padding");
        second_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IVparam[0]);
        byte[] decryptedMessage_final = first_decryption.doFinal(decryptedMessage_2);
        
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_2keys_d.ctr";
        if(createFile(decryptedMessage_final, newName))
          System.out.println("Succesfully decrypted in mode CTR");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }
}
