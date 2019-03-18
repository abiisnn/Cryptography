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
  public static boolean createFile(byte[] fileBytes, String key, String finalFile) throws IOException { 
   boolean ok = false; 
   FileWriter fw = new FileWriter(finalFile);
   fw.write(key);
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
      File f = new File("../p.txt");
      FileWriter fw = new FileWriter(f);
      OutputStream out = new FileOutputStream(f); 
      fw.write(key);
      out.write(message); 
      out.close();
      fw.close();
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
        IvParameterSpec iv = EDEcipher_modeCBC(message, n);
        EDEdecipher_modeCBC(name, iv);
        /*System.out.println("Encrypted in mode CBC");
        EDE_modeOFB(message, n);
        System.out.println("Encrypted in mode OFB");
        EDE_modeCFB(message, n);
        System.out.println("Encrypted in mode CFB");*/
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

 /* public void encryptEEE() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
  
  }*/
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
    
    //Generate IV
    KeyGenerator ivGenerator = KeyGenerator.getInstance("DESede");
    ivGenerator.init(168);
    // Get IV
    SecretKey iv = keygenerator.generateKey();
    byte[] iv2 = iv.getEncoded();
    System.out.println(iv2);
    //Only use the first 8 bytes for the IV
    IvParameterSpec IVparam = new IvParameterSpec(iv2, 0, 8);
    
    // Cipher with mode CBC
    Cipher desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
    desCipher.init(Cipher.ENCRYPT_MODE, desKey, IVparam);
    byte[] encryptedMessage = desCipher.doFinal(message);
    //File of the encrypted message
    
    String cName = ".." + sep + "Encrypted" + sep + outFileName + ".cbc";
    if(createFile(encryptedMessage, encodedKey, cName))
        System.out.println("Encryption succesful");
    else
        System.out.println("Error");
    
    //encodedKey = encodedKey.concat("\n");
    outFile(encodedKey, encryptedMessage, cName);

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
  public static void EDEdecipher_modeCBC(String outFileName, IvParameterSpec IVparam) throws NoSuchAlgorithmException, NoSuchPaddingException,
          InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
    String sep = System.getProperty("file.separator");
    
    // GET TIME
    long TInicio, TFin, tiempo; 
    // Take the begin 
    TInicio = System.currentTimeMillis();
    
    byte[] message = createByte("../p.txt");
    StringBuffer k = new StringBuffer();
    byte[] key = null;
    for(int i = 0; i < 16; i++)
        k.append(Byte.toString(message[i]));
    key = k.toString().getBytes();
    /*try {
        FileInputStream in = new FileInputStream(f);
        in.read(key, 0, 32);
        in.read(message, 32, (int)f.length()-32);
        in.close();
    }
    catch (Exception e) { 
        e.printStackTrace(); 
    }*/
    // decode the base64 encoded string
    byte[] decodedKey = Base64.getDecoder().decode(key);
    // rebuild key using SecretKeySpec
    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DESede");
    System.out.println(originalKey);
    // Decipher with mode CBC
    Cipher desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
    desCipher.init(Cipher.DECRYPT_MODE, originalKey, IVparam);
    byte[] decryptedMessage = desCipher.doFinal(message);
    //File of the decrypted message
    String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_d.cbc";
    if(createFile(decryptedMessage, key.toString(), newName))
      System.out.println("Decryption succesful");
    else
        System.out.println("Error");
    
    // TAKE TIME
    // Take end
    TFin = System.currentTimeMillis(); 
    tiempo = TFin - TInicio; 
    System.out.println("Time in ms: " + tiempo);
  }
  // ---------------------------------------------------
  //                 EDE OFB MODE          
  // ---------------------------------------------------
  public static void EDE_modeOFB(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
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

    //Generate IV
    KeyGenerator ivGenerator = KeyGenerator.getInstance("DESede");
    ivGenerator.init(168);
    // Get IV
    SecretKey iv = keygenerator.generateKey();
    byte[] iv2 = iv.getEncoded();
    System.out.println(iv2);
    //Only use the first 8 bytes for the IV
    IvParameterSpec IVparam = new IvParameterSpec(iv2, 0, 8);

    // Cipher with mode CBC
    Cipher desCipher = Cipher.getInstance("DESede/OFB/PKCS5Padding");
    desCipher.init(Cipher.ENCRYPT_MODE, desKey, IVparam);
    byte[] encryptedMessage = desCipher.doFinal(message);
    //File of the encrypted message
    String cName = ".." + sep + "Encrypted" + sep + outFileName + ".ofb";
    if(createFile(encryptedMessage, encodedKey, cName))
        System.out.println("Mensaje encriptado correctamente");
    else
        System.out.println("Error");

    // Decipher with mode CBC
    desCipher.init(Cipher.DECRYPT_MODE, desKey, IVparam);
    byte[] decryptedMessage = desCipher.doFinal(encryptedMessage);
    //File of the decrypted message
    String newName = ".." + sep + "Decrypted" + sep + outFileName + "_d.ofb";
    if(createFile(decryptedMessage, encodedKey, newName))
      System.out.println("LISTO");
    else
        System.out.println("Error");

    // TAKE TIME
    // Take end
    TFin = System.currentTimeMillis(); 
    tiempo = TFin - TInicio; 
    System.out.println("Time in ms: " + tiempo);
  }
  
  // ---------------------------------------------------
  //                 EDE CFB MODE          
  // ---------------------------------------------------
  public static void EDE_modeCFB(byte[] message, String outFileName) throws NoSuchAlgorithmException, NoSuchPaddingException,
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

    //Generate IV
    KeyGenerator ivGenerator = KeyGenerator.getInstance("DESede");
    ivGenerator.init(168);
    // Get IV
    SecretKey iv = keygenerator.generateKey();
    byte[] iv2 = iv.getEncoded();
    System.out.println(iv2);
    //Only use the first 8 bytes for the IV
    IvParameterSpec IVparam = new IvParameterSpec(iv2, 0, 8);

    // Cipher with mode CBC
    Cipher desCipher = Cipher.getInstance("DESede/CFB/PKCS5Padding");
    desCipher.init(Cipher.ENCRYPT_MODE, desKey, IVparam);
    byte[] encryptedMessage = desCipher.doFinal(message);
    //File of the encrypted message
    String cName = ".." + sep + "Encrypted" + sep + outFileName + ".cfb";
    if(createFile(encryptedMessage, encodedKey, cName))
        System.out.println("Mensaje encriptado correctamente");
    else
        System.out.println("Error");

    // Decipher with mode CBC
    desCipher.init(Cipher.DECRYPT_MODE, desKey, IVparam);
    byte[] decryptedMessage = desCipher.doFinal(encryptedMessage);
    //File of the decrypted message
    String newName = ".." + sep+ "Decrypted" + sep + outFileName + "_d.cfb";
    if(createFile(decryptedMessage, encodedKey, newName))
      System.out.println("LISTO");
    else
        System.out.println("Error");

    // TAKE TIME
    // Take end
    TFin = System.currentTimeMillis(); 
    tiempo = TFin - TInicio; 
    System.out.println("Time in ms: " + tiempo);
  }
}