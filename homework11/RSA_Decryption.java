package homework11  ;

//import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class RSA_Decryption {


    public static void main(String[] args) {

        String Path = "C:\\Users\\silal\\Desktop\\";
        String filename_PublicKey = "PublicKey.txt";
        String filename_encryption = "Encryption.txt";
        String filename_Decryption = "Decryption.txt" ;

        // Read File Public Key
        String[] Public_key = Read_File(Path, filename_PublicKey).split(" ");
        
        long d =  Long.parseLong(Public_key[0])  ;  //D
        long n =  Long.parseLong(Public_key[1])  ;  //N

        System.out.println("--------------- Decrypttion ------------------");
        System.out.println("Public  Key : (" + d + "," + n + ")");

        String[] Encryption = Read_File(Path, filename_encryption).split(" ") ;
        long[] Encription_array = new long[Encryption.length] ;

        System.out.print("Encryption Text : ");
        for (int i = 0; i < Encryption.length; i++) {
            System.out.print((char)Long.parseLong(Encryption[i]));
            Encription_array[i] = Long.parseLong(Encryption[i]) ;
        }
        System.out.println();

        String Decryption_Text = "";
        for (int i = 0; i < Encryption.length; i++) {
            
            BigInteger mc = BigInteger.valueOf(Encription_array[i]).pow((int)d).mod(BigInteger.valueOf(n)); 
            Decryption_Text += (char)mc.intValue() ;

           
   
        }

        System.out.println("Decrytion Result : " +Decryption_Text);
        CreateAndWriteFile(Path, filename_Decryption, Decryption_Text);

        
        
    }

    static String Read_File(String Path, String filename) {
        File file = new File(Path + filename);
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String message = "";
            String line;
            while ((line = br.readLine()) != null) {
                message += line;
            }
            br.close();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "null";

    }

    static void CreateAndWriteFile(String Path, String filename ,String message) {

        /* Create File */
        try {
            File myObj = new File(Path + filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /* Write file */
        try {
            FileWriter myWriter = new FileWriter(Path + filename);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


}