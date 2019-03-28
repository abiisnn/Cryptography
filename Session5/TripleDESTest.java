import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.io.*;
import java.util.Scanner;
import java.security.SecureRandom;
import javax.crypto.spec.*;
import java.security.*;

public class TripleDESTest {
  static Scanner sc = new Scanner(System.in);
  static Scanner m = new Scanner(System.in);

  static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  // We use in decipher
  static byte[] originalKey_1;
  static byte[] originalKey_2;
  static byte[] originalKey_3;
  static byte[] iv_1;
  static byte[] iv_2;
  static byte[] iv_3;

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
    decodedKey = Base64.getDecoder().decode(aux);
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

  // ---------------------------------------------------
  //             CREATE BYTE KEY     
  // ---------------------------------------------------
  // Convert a File to byte[]
  public static byte[] createByteIVText(String nameFile)  { 
    boolean ok = false;  
    int i;
    File archivo = new File(nameFile);
    byte[] archivoB = new byte[(int)archivo.length() - 24];
    iv_1 = new byte[8];
    iv_2 = new byte[8];
    iv_3 = new byte[8];  
    try {
      // Read file
      FileInputStream docu = new FileInputStream(archivo);
      // Insert in new array
      docu.read(iv_1, 0, 8);
      docu.mark(8);
      docu.read(iv_2, 0, 8);
      docu.mark(16);
      docu.read(iv_3, 0, 8);
      docu.mark(24);
      docu.read(archivoB, 0, (int)archivo.length() - 24);
      docu.mark((int)archivo.length());
      docu.close(); // Close
    } 
    catch (FileNotFoundException e) {
      System.out.print("No se ha encontrado el archivo.");
    } 
    catch (IOException e) {
      System.out.print("No se ha podido leer el archivo.");
    }
    return archivoB;
  }
  // ---------------------------------------------------
  //                  CREATE FILE            
  // ---------------------------------------------------
  // Convert a btye[] to File
  public static boolean createFileIVText(byte[] fileBytes, byte[][] iv, String finalFile) { 
   boolean ok = false; 
   try { 
      OutputStream out = new FileOutputStream(finalFile); 
      out.write(iv[0]); 
      out.write(iv[1]);
      out.write(iv[2]);
      out.write(fileBytes);
      out.close();         
      ok = true; 
    } 
    catch (Exception e) { 
     e.printStackTrace(); 
    }         
    return ok;
  }

  // ---------------------------------------------------
  //             CREATE BYTE KEY     
  // ---------------------------------------------------
  // Convert a File to byte[]
  public static void createByteKey(String nameFile) { 
    boolean ok = false;  
    int i;
    File archivo = new File(nameFile);
    
    originalKey_1 = new byte[12];
    originalKey_2 = new byte[12];
    originalKey_3 = new byte[12];  
    try {
      // Read file
      FileInputStream docu = new FileInputStream(archivo);
      // Insert in new array
      docu.read(originalKey_1, 0, 12);
      docu.mark(12);
      docu.read(originalKey_2, 0, 12);
      docu.mark(24);
      docu.read(originalKey_3, 0, 12);
      docu.mark(36);
      docu.close(); // Close
    } 
    catch (FileNotFoundException e) {
      System.out.print("No se ha encontrado el archivo.");
    } 
    catch (IOException e) {
      System.out.print("No se ha podido leer el archivo.");
    }
  }
  // ---------------------------------------------------
  //                  CREATE BYTE[]            
  // ---------------------------------------------------
  // Convert a File to byte[]
  public static byte[] createByte(String nameFile) { 
    boolean ok = false;  
    String sep = System.getProperty("file.separator");
    // File have to be in ARCHIVOS dir
    String path = ".."+ sep + "Session5" + sep + "archivos" + sep + nameFile;

    File archivo = new File(path);
    byte[] archivoBytes = null;
    long tamanoArch = archivo.length(); // File size
  
    archivoBytes = new byte[(int) tamanoArch];
    try {
      // Read file
      FileInputStream docu = new FileInputStream(archivo);
      // Insert in new array
      int numBytes = docu.read(archivoBytes);
      System.out.println("\nFile: "+ nameFile +" Size: " + numBytes + " bytes.");
      docu.close(); // Close
    } 
    catch (FileNotFoundException e) {
      System.out.print("No se ha encontrado el archivo.");
    } 
    catch (IOException e) {
      System.out.print("No se ha podido leer el archivo.");
    }
    return archivoBytes;
  }

  // ---------------------------------------------------
  //                  CREATE FILE            
  // ---------------------------------------------------
  // Convert a btye[] to File
  public static boolean createFile(byte[] fileBytes, String finalFile) { 
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
  public static long timeBegin() {
    // GET TIME
    long TInicio = 0; 
    // Take the begin 
    TInicio = System.currentTimeMillis();
    return TInicio;
  }
  public static void timeEnd(long TInicio) {
    // TAKE TIME
    // Take end
    long TFin, tiempo;
    TFin = System.currentTimeMillis(); 
    tiempo = TFin - TInicio; 
    System.out.println("Time in MS: " + tiempo);    
  }
  // ---------------------------------------------------
  //                    MAIN          
  // ---------------------------------------------------
  public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException,
      IllegalBlockSizeException, InvalidKeyException, IOException {
    long timeB;
    String opc, file, mode, nameKey, nameFileIV;
    byte [] decrypt;
    try {
      //System.out.println("Choose 1 cipher, 2 Decipher");
      //opc = sc.nextLine();
      System.out.println("Enter file name");
      file = sc.nextLine();
      System.out.println("Enter mode");
      mode = m.nextLine();
      System.out.println("Enter name key");
      nameKey = m.nextLine();
      System.out.println("Enter name cipher name");
      nameFileIV = m.nextLine();

      modeEEE(file, mode, nameKey, nameFileIV);
      decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      createFile(decrypt, file);

/*      mode = "CFB";

      timeB = timeBegin();
      file = "sistemas_operativos.pdf";    
      nameKey = "keySO.txt";
      nameFileIV = "CipSO.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);


      timeB = timeBegin();
      file = "Mov_Pendulo_Simple.mov";    
      nameKey = "keyMPS.txt";
      nameFileIV = "CipMPS.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);
<<<<<<< HEAD

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);

      timeB = timeBegin();
      file = "ArquitecturaComputadoras.PDF";    
      nameKey = "keyAC.txt";
      nameFileIV = "CipAC.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);
    
      timeB = timeBegin();
      file = "Recursos_Apuntes_21_Vectorial.doc";    
      nameKey = "keyRAV.txt";
      nameFileIV = "CipRAV.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);
    

      timeB = timeBegin();
      file = "Aparato_Respiratorio.pptm";    
      nameKey = "keyAR.txt";
      nameFileIV = "CipAR.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);
      
      timeB = timeBegin();
      file = "ha.exe";    
      nameKey = "keyHA.txt";
      nameFileIV = "CipHA.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);

      timeB = timeBegin();
      file = "guia-tkinter.pdf";    
      nameKey = "keyGT.txt";
      nameFileIV = "CipGT.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);

      timeB = timeBegin();
      file = "ipn.PNG";    
      nameKey = "keyIPN.txt";
      nameFileIV = "CipIPN.txt";
      System.out.println("\n\n-----> Working with:" + file);
      // String nameFile, String mode, String nameFileKey, String nameFileIV
      modeEEE2Keys(file, mode, nameKey, nameFileIV);
      timeEnd(timeB);

      // String mode, String nameFileKey, String nameFileIV
      //decrypt = modeEEEdecipher(mode, nameKey, nameFileIV);
      //createFile(decrypt, file);
=======
*/
>>>>>>> 7f5faa04955b9515bfe14d05bb3be7d59f49b909
  
    }
    catch(Exception e) {
      e.printStackTrace(); 
    }   
    // Para leer cadenas
  }
}
