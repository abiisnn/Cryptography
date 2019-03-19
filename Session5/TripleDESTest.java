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
  static Scanner num = new Scanner(System.in);

  static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  static byte[] originalKey_1;
  static byte[] originalKey_2;
  static byte[] originalKey_3;
  static byte[] iv_1;
  static byte[] iv_2;
  static byte[] iv_3;

  public static void modeEEE() throws Exception {
    // Get bytes of message
    int i;
    byte[][] IV = new byte[3][];

    String name = "Servidor.java";
    byte[] message = createByte(name);
    SecretKey[] desKey;

    // Generate keys
    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
    keygenerator.init(56); // Size of key
    // Get keys
    //SecretKey[] desKey;
    desKey = new SecretKey[3];
    for(i = 0; i < 3; i++) {
      desKey[i] = keygenerator.generateKey();
    }
    String keyString = "";
    for(i = 0; i < 3; i++) {
      keyString = keyString + Base64.getEncoder().encodeToString(desKey[i].getEncoded());       
    }
    byte[] keysBytes = keyString.getBytes();

    String nameFileKey = "key.txt";
    createFile(keysBytes, nameFileKey);

   // Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
  
    byte[] cipherText = message;

    String c = "DES/CBC/PKCS5Padding";
    for(i = 0; i < 3; i++) {
      if(i > 0) 
        c = "DES/CBC/NoPadding";
      Cipher cipher = Cipher.getInstance(c);
      // generate an initialization vector (IV)
      SecureRandom secureRandom = new SecureRandom();
      byte[] ivspec = new byte[cipher.getBlockSize()];
      secureRandom.nextBytes(ivspec);
      cipher.init(Cipher.ENCRYPT_MODE, desKey[i], secureRandom);
      IV[i] = cipher.getIV();
      cipherText = cipher.doFinal(cipherText);
    }

    //createFile(cipherText, "ServidorCip.java");
    createFileIVText(cipherText, IV, "ServidorCip.java");
    byte[] decrypt = modeEEEdecipher(cipherText);
    createFile(decrypt, "servidor.java");
  }

  public static byte[] modeEEEdecipher(byte[] toDecrypt) throws Exception {
    int i, j;
    byte[][] IV;
    IV = new byte[3][];

    String c = "DES/CBC/NoPadding";
    byte[] plainText = toDecrypt;
    String name = "key.txt";
    SecretKey[] desKey = new SecretKey[3];
    createByteKey(name);
    System.out.println("Se crearon correctamente");
    String aux = "";
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

    toDecrypt = createByteIVText("ServidorCip.java");

    IV[0] = iv_1;
    IV[1] = iv_2;
    IV[2] = iv_3;

    for(i = 1; i <= 3; i++) {
      if(i - 3 == 0) {
        c = "DES/CBC/PKCS5Padding";
      }
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
  public static void createByteKey(String nameFile)  { 
    System.out.println("Creo bytes para la llave");
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
  public static byte[] createByte(String nameFile)  { 
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
      System.out.print("El archivo tiene " + numBytes + " de bytes.");
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

 /* public void encryptEEE() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {

  }*/
  // ---------------------------------------------------
  //                    MAIN          
  // ---------------------------------------------------
  public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException,
      IllegalBlockSizeException, InvalidKeyException, IOException {

    // GET TIME
    long TInicio, TFin, tiempo; 
    // Take the begin 
    TInicio = System.currentTimeMillis();
    
    try {
      modeEEE();
    }
    catch(Exception e) {
      e.printStackTrace(); 
    }
   
    // TAKE TIME
    // Take end
    TFin = System.currentTimeMillis(); 
    tiempo = TFin - TInicio; 
    System.out.println("Time in ms: " + tiempo);


    // Para leer cadenas
  }
}
