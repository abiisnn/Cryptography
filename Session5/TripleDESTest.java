import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.io.*;
import java.util.Scanner;

public class TripleDESTest {
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

  // ---------------------------------------------------
  //                MENU PARA EDE      
  // ---------------------------------------------------
  public static void menuEDE(int k) {
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
      IllegalBlockSizeException, InvalidKeyException, IOException {

    // GET TIME
    long TInicio, TFin, tiempo; 
    // Take the begin 
    TInicio = System.currentTimeMillis();
    
    // Get bytes of message
    String name = "ipn.PNG";
    byte[] message = createByte(name);

    // Generate key
    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
    keygenerator.init(56); // Size of key
    // Get key 
    SecretKey desKey = keygenerator.generateKey();
    // Get base64 encoded version of the key
    String encodedKey = Base64.getEncoder().encodeToString(desKey.getEncoded());
    System.out.println(encodedKey);

    /*
      IF I NEED STRING TO SECRET KEY
      // decode the base64 encoded string
      byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
      // rebuild key using SecretKeySpec
      SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    */

    // Cipher with mode ECB
    Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    desCipher.init(Cipher.ENCRYPT_MODE, desKey);

    byte[] encryptedMessage = desCipher.doFinal(message);
    //System.out.println(Base64.getEncoder().encodeToString(encryptedMessage));
    String cad = in.readLine();

    desCipher.init(Cipher.DECRYPT_MODE, desKey);
    byte[] decryptedMessage = desCipher.doFinal(encryptedMessage);
    //System.out.println(new String(decryptedMessage));

    String newName = "ipn.PNG";
    if(createFile(decryptedMessage, newName))
      System.out.println("LISTO");

    // TAKE TIME
    // Take end
    TFin = System.currentTimeMillis(); 
    tiempo = TFin - TInicio; 
    System.out.println("Time in ms: " + tiempo);

    int opc = 0;
    while(opc != 3) {
      System.out.print("Choose a tipe of DES\n");
      System.out.print("1. EDE\n");
      System.out.print("2. EEE\n");
      System.out.print("3. Exit\n");
      opc = sc.nextInt();
      
      int k = 0;
      if(opc == 1) {
        System.out.print("Write 2 keys or 3 keys\n");    
        k = num.nextInt();
        if(k == 2 || k == 3)
          menuEDE(k);
      }
      if(opc == 2){
        System.out.print("Write 2 keys or 3 keys\n");    
        k = num.nextInt();
        if(k == 2 || k == 3)
          menuEDE(k);
      }
    }

    // Para leer cadenas

  }
}
