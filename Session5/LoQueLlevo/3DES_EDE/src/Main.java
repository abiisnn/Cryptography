import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.util.Scanner;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {
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

    public static void outFile(String key, IvParameterSpec[] iv, byte[] message, String keyFile, String finalFile) throws IOException{
        File f = new File(finalFile);
        FileWriter fw = new FileWriter(keyFile);
        fw.write(key);
        fw.close();
        OutputStream out = new FileOutputStream(f);
        for(int i = 0; i < iv.length; i++)
            out.write(iv[i].getIV());
        out.write(message); 
        out.close();
    }

    // ---------------------------------------------------
    //                MENU PARA EDE      
    // ---------------------------------------------------
    public static void menuEDE(int k, String name) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
            IllegalBlockSizeException, IOException, BadPaddingException, InvalidAlgorithmParameterException {
        if(k == 2) {
            // Get bytes of message
            byte[] message = createByte(name);
            String[] files;
            String n = name.substring(0, name.indexOf("."));
            System.out.println("Mode of operation: ");
            String mode = sc.next();
            //Encryption and Decryption with especified mode
            files = EDEcipher_2Keys(message, n, mode.toLowerCase());
            EDEdecipher_2Keys(n, files[0], files[1], mode);
        }
        if(k == 3){
            // Get bytes of message
            byte[] message = createByte(name);
            String[] files;
            String n = name.substring(0, name.indexOf("."));
            System.out.println("Mode of operation: ");
            String mode = sc.next();
            //Encryption and Decryption with especified mode
            files = EDEcipher_3Keys(message, n, mode.toLowerCase());
            EDEdecipher_3Keys(n, files[0], files[1], mode);
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
    //              EDE Cipher 3 keys          
    // ---------------------------------------------------
    public static String[] EDEcipher_3Keys(byte[] message, String outFileName, String mode) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
                
        String sep = System.getProperty("file.separator");
        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();
       
        IvParameterSpec[] iv = new IvParameterSpec[1];
                
        // Generate key
        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        keygenerator.init(168); // Size of key
        // Get key 
        SecretKey desKey = keygenerator.generateKey();
        // Get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
        System.out.println(encodedKey);

        
        // Cipher with mode CBC
        Cipher desCipher = null;
        switch(mode){
            case "cbc":
                desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
                break;
            case "ofb":
                desCipher = Cipher.getInstance("DESede/OFB/PKCS5Padding");
                break;
            case "cfb":
                desCipher = Cipher.getInstance("DESede/CFB/PKCS5Padding");
                break;
            case "ctr":
                desCipher = Cipher.getInstance("DESede/CTR/PKCS5Padding");
                break;
            default:
                break;
        }
        //Cipher desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage = desCipher.doFinal(message);

        //File of the encrypted message
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_3keys." + mode;
        String kFile_Name = ".." + sep + "Encrypted" + sep + outFileName + "_3keys_key." + mode;
        iv[0] = new IvParameterSpec(desCipher.getIV());
        outFile(encodedKey, iv, encryptedMessage, kFile_Name, cName);
        System.out.println("Succesfully encrypted in " + mode.toUpperCase() + "mode");
        
        String[] files = {cName, kFile_Name};
        
        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
        
        return files;
    }

    // ---------------------------------------------------
    //              EDE Decipher 3 keys         
    // ---------------------------------------------------
    public static void EDEdecipher_3Keys(String outFileName, String cipherFile, String keyFile, String mode) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        //Obtention fo the key from the file
        char[] key = new char[32];
        FileReader fk =new FileReader(keyFile);
        fk.read(key);
        //Obtention of the IV and the ciphertext from the file
        byte[] message = null;
        byte[] iv = new byte[8];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(iv, 0, 8);
            message = new byte[(int)f.length() - 8];
            in.mark(8);
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }
        
        IvParameterSpec IVparam = new IvParameterSpec(iv);
        
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(String.copyValueOf(key));
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");

        // Decipher with mode CBC
        Cipher desCipher = null;
        switch(mode){
            case "cbc":
                desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
                break;
            case "ofb":
                desCipher = Cipher.getInstance("DESede/OFB/PKCS5Padding");
                break;
            case "cfb":
                desCipher = Cipher.getInstance("DESede/CFB/PKCS5Padding");
                break;
            case "ctr":
                desCipher = Cipher.getInstance("DESede/CTR/PKCS5Padding");
                break;
            default:
                break;
        }
        desCipher.init(Cipher.DECRYPT_MODE, originalKey, IVparam);
        byte[] decryptedMessage = desCipher.doFinal(message);

        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_3keys_d." + mode;
        if(createFile(decryptedMessage, newName))
            System.out.println("Succesfully decrypted in " + mode.toUpperCase() + " mode");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }
    
    
    // ---------------------------------------------------
    //              EDE Cipher 2 keys          
    // ---------------------------------------------------
    public static String[] EDEcipher_2Keys(byte[] message, String outFileName, String mode) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{

        String mc = "", md = "";
        switch(mode){
            case "cbc":
                mc = "DESede/CBC/PKCS5Padding";
                md = "DESede/CBC/NoPadding";
                break;
            case "ofb":
                mc = "DESede/OFB/PKCS5Padding";
                md = "DESede/OFB/NoPadding";
                break;
            case "cfb":
                mc = "DESede/CFB/PKCS5Padding";
                md = "DESede/CFB/NoPadding";
                break;
            case "ctr":
                mc = "DESede/CTR/PKCS5Padding";
                md = "DESede/CTR/NoPadding";
                break;
            default:
                break; 
        }
        
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
        
        Cipher first_encryption = Cipher.getInstance(mc);
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
        
        Cipher decryption = Cipher.getInstance(md);
        decryption.init(Cipher.DECRYPT_MODE, desKey_2, IV[1]);
        byte[] encryptedMessage_2 = decryption.doFinal(encryptedMessage_1);

                /**Second encryption**/
        Cipher second_encryption = Cipher.getInstance(mc);
        second_encryption.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] encryptedMessage_final = first_encryption.doFinal(encryptedMessage_2);
        IV[2] = new IvParameterSpec(second_encryption.getIV());
        
        //File of the encrypted message
        String keys = encodedKey.concat(encodedKey_2);
        String cName = ".." + sep + "Encrypted" + sep + outFileName + "_2keys." + mode;
        String kFile_Name = ".." + sep + "Encrypted" + sep + outFileName + "_2keys_key." + mode;
        
        outFile(keys, IV, encryptedMessage_final, kFile_Name, cName);
        System.out.println("Succesfully encrypted in " + mode.toUpperCase() + "mode ");
        
        String[] files = {cName, kFile_Name};
        
        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
        
        return files;
    }

    // ---------------------------------------------------
    //              EDE Decipher 2 keys          
    // ---------------------------------------------------
    public static void EDEdecipher_2Keys(String outFileName, String cipherFile, String keyFile, String mode) throws 
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException, IOException{

        String mc = "", md = "";
        switch(mode){
            case "cbc":
                mc = "DESede/CBC/PKCS5Padding";
                md = "DESede/CBC/NoPadding";
                break;
            case "ofb":
                mc = "DESede/OFB/PKCS5Padding";
                md = "DESede/OFB/NoPadding";
                break;
            case "cfb":
                mc = "DESede/CFB/PKCS5Padding";
                md = "DESede/CFB/NoPadding";
                break;
            case "ctr":
                mc = "DESede/CTR/PKCS5Padding";
                md = "DESede/CTR/NoPadding";
                break;
            default:
                break; 
        }
        
        String sep = System.getProperty("file.separator");

        // GET TIME
        long TInicio, TFin, tiempo; 
        // Take the begin 
        TInicio = System.currentTimeMillis();

        //Obtention of both keys from the file
        char[] key_1 = new char[12];
        char[] key_2 = new char[12];
        FileReader fk =new FileReader(keyFile);
        fk.read(key_1);
        fk.mark(12);
        fk.read(key_2);
        //Obtention of the IV and the ciphertext from the file
        byte[] message = null;
        byte[] iv_1 = new byte[8];
        byte[] iv_2 = new byte[8];
        byte[] iv_3 = new byte[8];
        File f =new File(cipherFile);
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(iv_1, 0, 8);
            in.mark(8);
            in.read(iv_2, 0, 8);
            in.mark(16);
            in.read(iv_3, 0, 8);
            in.mark(24);
            message = new byte[(int)f.length() - 24];
            in.read(message, 0, message.length);
            in.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }
        
        IvParameterSpec[] IV = new IvParameterSpec[3];
        IV[0] = new IvParameterSpec(iv_1);
        IV[1] = new IvParameterSpec(iv_2);
        IV[2] = new IvParameterSpec(iv_3);
        
        // decode the base64 encoded strings
        byte[] decodedKey_1 = Base64.getDecoder().decode(String.copyValueOf(key_1));
        byte[] decodedKey_2 = Base64.getDecoder().decode(String.copyValueOf(key_2));
        // rebuild the keys using SecretKeySpec
        SecretKey originalKey_1 = new SecretKeySpec(decodedKey_1, 0, decodedKey_1.length, "DES");
        SecretKey originalKey_2 = new SecretKeySpec(decodedKey_2, 0, decodedKey_2.length, "DES");
       
        /****Decipher with mode CBC***/
        
                /**First decryption**/
        Cipher first_decryption = Cipher.getInstance(mc);
        first_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IV[0]);
        byte[] decryptedMessage_1 = first_decryption.doFinal(message);
        
                /**Encryption**/
        Cipher encryption = Cipher.getInstance(md);
        encryption.init(Cipher.ENCRYPT_MODE, originalKey_2, IV[1]);
        byte[] decryptedMessage_2 = encryption.doFinal(decryptedMessage_1);
        
                /**Second decryption**/
        Cipher second_decryption = Cipher.getInstance(mc);
        second_decryption.init(Cipher.DECRYPT_MODE, originalKey_1, IV[2]);
        byte[] decryptedMessage_final = first_decryption.doFinal(decryptedMessage_2);
        
        //File of the decrypted message
        String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_2keys_d." + mode;
        if(createFile(decryptedMessage_final, newName))
            System.out.println("Succesfully decrypted in " + mode.toUpperCase() + " mode");
        else
            System.out.println("Error");

        // TAKE TIME
        // Take end
        TFin = System.currentTimeMillis(); 
        tiempo = TFin - TInicio; 
        System.out.println("Time in ms: " + tiempo);
    }
}
