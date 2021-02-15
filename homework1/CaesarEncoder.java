
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CaesarCipherEncoder
 */
public class CaesarEncoder {

    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
       
        String  FileDirectory = "C:\\Users\\sila2\\Desktop\\" ;  // file directory for create 
        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };

        System.out.print("Input the plaintext message : ");
        String plaintext = input.nextLine();

        System.out.print("Input number for shift : ");
        int shift = input.nextInt();

        char[] plaintext_array = plaintext.toUpperCase().toCharArray();
        ArrayList<String> plaintext_array_index = new ArrayList<String>();

        /* find and store index of plaintext */

        for (int i = 0; i < plaintext_array.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (String.valueOf(plaintext_array[i]).equals(alphabet[j])) {
                    plaintext_array_index.add(Integer.toString(j));
                    break;
                }
            }
        }

        /* shift table */

        for (int i = 0; i < shift; i++) {

            String temp = alphabet[0];
            for (int j = 0; j < alphabet.length - 1; j++) {
                alphabet[j] = alphabet[j + 1];
            }
            alphabet[alphabet.length - 1] = temp;
        }

        /* show table  */

        System.out.print("Show table after shift : ");
        for (String c : alphabet) {
            System.out.print(c);
        }
        System.out.println();

        String EncodeMessage  = "" ;

        /* show output encoding */

        System.out.print("Result : ");
        for (String index : plaintext_array_index) {

            EncodeMessage += alphabet[Integer.parseInt(index)] ;
            System.out.print(alphabet[Integer.parseInt(index)]);

        }
        System.out.println();

        CreateAndWriteFile(FileDirectory , EncodeMessage);

    }

    /* Function */

    static void CreateAndWriteFile (String Path , String message) {

        String filename = "CaesarEncode.txt" ;

         /* Create File */
        try {
            File myObj = new File(Path+filename);
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
            FileWriter myWriter = new FileWriter(Path+filename);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }

}