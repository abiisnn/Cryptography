import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.io.*;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CBC {
    static Scanner sc = new Scanner(System.in);
    static Scanner num = new Scanner(System.in);

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    // ---------------------------------------------------
    //                  CREATE BYTE[]            
    // ---------------------------------------------------
    // Convert a File to byte[]
    public static byte[] createByte(String nameFile) { 
        boolean ok = false;  
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
            IvParameterSpec iv_cbc = EDEcipher_modeCBC(message, n);
            EDEdecipher_modeCBC(n, "../Encrypted/ipn_3keys.cbc", iv_cbc);
            //Encryption and Decryption with OFB mode
            IvParameterSpec iv_ofb = EDEcipher_modeOFB(message, n);
            EDEdecipher_modeOFB(n, "../Encrypted/ipn_3keys.ofb", iv_ofb);
            //Encryption and Decryption with CFB mode
            IvParameterSpec iv_cfb = EDEcipher_modeCFB(message, n);
            EDEdecipher_modeCFB(n, "../Encrypted/ipn_3keys.cfb", iv_cfb);
            //Encryption and Decryption with CTR mode
            IvParameterSpec iv_ctr = EDEcipher_modeCTR(message, n);
            EDEdecipher_modeCTR(n, "../Encrypted/ipn_3keys.ctr", iv_ctr);
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
    public static IvParameterSpec EDEcipher_modeCBC(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
      
        String sep = System.getProperty("file.separator");
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        keygenerator.init(168); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);

        // Cipher with mode CBC
        Cipher desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage = desCipher.doFinal(message);
        IvParameterSpec IVparam = new IvParameterSpec(desCipher.getIV());

        //File of the encrypted message
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_3keys.cbc";
        outFile(encodedKey, encryptedMessage, cName);
        System.out.println("Succesfully encrypted in CBC mode");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IVparam; 
    }

    // ---------------------------------------------------
    //              EDE Decipher CBC MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeCBC(String outFileName, String cipherFile, IvParameterSpec IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key = new byte[32];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key, 0, 32);
            message = new byte[(int)f.length() - 32];
            in.mark(32);
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k = new char[32];
        for(int i = 0; i < 32; i++)
            k[i] = (char) Byte.toUnsignedInt(key[i]);

        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(String.copyValueOf(k));
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");

        // Decipher with mode CBC
        Cipher desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, originalKey, IVparam);
        byte[] decryptedMessage = desCipher.doFinal(message);

        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_3keys_d.cbc";
        if(createFile(decryptedMessage, newName))
            System.out.println("Succesfully decrypted in CBC mode");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }


    // ---------------------------------------------------
    //               EDE Cipher OFB MODE          
    // ---------------------------------------------------
    public static IvParameterSpec EDEcipher_modeOFB(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
      
        String sep = System.getProperty("file.separator");
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        keygenerator.init(168); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);

        // Cipher with mode OFB
        Cipher desCipher = Cipher.getInstance("DESede/OFB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage = desCipher.doFinal(message);
        IvParameterSpec IVparam = new IvParameterSpec(desCipher.getIV());
        //File of the encrypted message
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_3keys.ofb";
        outFile(encodedKey, encryptedMessage, cName);
        System.out.println("Succesfully encrypted in OFB mode");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IVparam;
    }


    // ---------------------------------------------------
    //              EDE Decipher OFB MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeOFB(String outFileName, String cipherFile, IvParameterSpec IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key = new byte[32];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key, 0, 32);
            message = new byte[(int)f.length() - 32];
            in.mark(32);
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k = new char[32];
        for(int i = 0; i < 32; i++)
            k[i] = (char) Byte.toUnsignedInt(key[i]);

        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(String.copyValueOf(k));
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");

        // Decipher with mode CBC
        Cipher desCipher = Cipher.getInstance("DESede/OFB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, originalKey, IVparam);
        byte[] decryptedMessage = desCipher.doFinal(message);
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_3keys_d.ofb";
        if(createFile(decryptedMessage, newName))
          System.out.println("Succesfully decrypted in OFB mode");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }


    // ---------------------------------------------------
    //               EDE Cipher CFB MODE          
    // ---------------------------------------------------
    public static IvParameterSpec EDEcipher_modeCFB(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
      
        String sep = System.getProperty("file.separator");
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        keygenerator.init(168); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);

        // Cipher with mode CBC
        Cipher desCipher = Cipher.getInstance("DESede/CFB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage = desCipher.doFinal(message);
        IvParameterSpec IVparam = new IvParameterSpec(desCipher.getIV());

        //File of the encrypted message
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_3keys.cfb";
        outFile(encodedKey, encryptedMessage, cName);
        System.out.println("Succesfully encrypted in CFB mode");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IVparam;
    }


    // ---------------------------------------------------
    //              EDE Decipher CFB MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeCFB(String outFileName, String cipherFile, IvParameterSpec IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key = new byte[32];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key, 0, 32);
            message = new byte[(int)f.length() - 32];
            in.mark(32);
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k = new char[32];
        for(int i = 0; i < 32; i++)
            k[i] = (char) Byte.toUnsignedInt(key[i]);

        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(String.copyValueOf(k));
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");

        // Decipher with mode CBC
        Cipher desCipher = Cipher.getInstance("DESede/CFB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, originalKey, IVparam);
        byte[] decryptedMessage = desCipher.doFinal(message);
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_3keys_d.cfb";
        if(createFile(decryptedMessage, newName))
          System.out.println("Succesfully decrypted in CFB mode");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }
    
    
    // ---------------------------------------------------
    //               EDE Cipher CTR MODE          
    // ---------------------------------------------------
    public static IvParameterSpec EDEcipher_modeCTR(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
      
        String sep = System.getProperty("file.separator");
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        keygenerator.init(168); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);

        // Cipher with mode CBC
        Cipher desCipher = Cipher.getInstance("DESede/CTR/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage = desCipher.doFinal(message);
        IvParameterSpec IVparam = new IvParameterSpec(desCipher.getIV());

        //File of the encrypted message
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_3keys.ctr";
        outFile(encodedKey, encryptedMessage, cName);
        System.out.println("Succesfully encrypted in CTR mode");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);

        return IVparam;
    }


    // ---------------------------------------------------
    //              EDE Decipher CTR MODE          
    // ---------------------------------------------------
    public static void EDEdecipher_modeCTR(String outFileName, String cipherFile, IvParameterSpec IVparam) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        byte[] message = null;
        byte[] key = new byte[32];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(key, 0, 32);
            message = new byte[(int)f.length() - 32];
            in.mark(32);
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }

        char[] k = new char[32];
        for(int i = 0; i < 32; i++)
            k[i] = (char) Byte.toUnsignedInt(key[i]);

        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(String.copyValueOf(k));
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");

        // Decipher with mode CBC
        Cipher desCipher = Cipher.getInstance("DESede/CTR/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, originalKey, IVparam);
        byte[] decryptedMessage = desCipher.doFinal(message);
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_3keys_d.ctr";
        if(createFile(decryptedMessage, newName))
          System.out.println("Succesfully decrypted in CTR mode");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }
}