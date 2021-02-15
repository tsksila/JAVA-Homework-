import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CaesarVigenereDecoder {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String  FileDirectory = "C:\\Users\\sila2\\Desktop\\" ;
        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };

        // -------------------- Vigenere Decoder ----------------------//

        System.out.println("------------ Vigenere Decoder by Formula ------------ ");

        System.out.print("Input the Encode message : ");
        String plaintext = input.nextLine();
        ArrayList<String> plaintext_array = new ArrayList<String>();

        for (int i = 0; i < plaintext.toCharArray().length; i++) {
            plaintext_array.add(String.valueOf(plaintext.toUpperCase().toCharArray()[i]));
        }

        System.out.print("Input the Vigenere Key : ");
        String key = input.next();

        ArrayList<String> key_array = new ArrayList<String>();

        for (int i = 0; i < key.toCharArray().length; i++) {
            key_array.add(String.valueOf(key.toUpperCase().toCharArray()[i]));
        }

        /* check size of array between plaintext and key */

        for (int i = 0; i < plaintext_array.size() - 1; i++) {
            if (key_array.size() < plaintext_array.size()) {
                key_array.add(key_array.get(i));
            }
        }

        /* match index plaintext and value form alphabet */
        ArrayList<ArrayList<Integer>> matchIndex = new ArrayList<>();

        for (int i = 0; i < plaintext_array.size(); i++) {

            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < alphabet.length; j++) {

                if (plaintext_array.get(i).equals(alphabet[j])) {
                    list.add(j);
                }
            }
            for (int k = 0; k < alphabet.length; k++) {
                if (key_array.get(i).equals(alphabet[k])) {
                    list.add(k);
                }
            }
            matchIndex.add(list);
        }

        /* Vigenere Formula */

        System.out.print("Vigenere  Output Encoding Text : ");

        ArrayList<String> VigenereOutput = new ArrayList<String>();

        for (int i = 0; i < matchIndex.size(); i++) {

            System.out.print(alphabet[Math.abs(matchIndex.get(i).get(0) - matchIndex.get(i).get(1) + alphabet.length)
                    % alphabet.length]);

            VigenereOutput.add(alphabet[Math.abs(matchIndex.get(i).get(0) - matchIndex.get(i).get(1) + alphabet.length)
                    % alphabet.length]);

        }

        System.out.println();

        // -------------------- Caesar Encoder ----------------------//

        System.out.println(" ----------- Caesar Decoder by Formula ---------- ");

        System.out.print("Input number for shift : ");
        int shift = input.nextInt();

        /* Find and store index of plaintext */

        ArrayList<Integer> plaintext_array_index = new ArrayList<Integer>();
        for (int i = 0; i < VigenereOutput.size(); i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (String.valueOf(VigenereOutput.get(i)).equals(alphabet[j])) {
                    plaintext_array_index.add(j);
                    break;
                }
            }
        }

        System.out.print("Result : ");
        String message = "" ;
        for (int i = 0; i < plaintext_array_index.size(); i++) {
            message += alphabet[ Math.floorMod(plaintext_array_index.get(i) - shift, alphabet.length) ] ;
            System.out.print(alphabet[ Math.floorMod(plaintext_array_index.get(i) - shift, alphabet.length) ]);
        }

        CreateAndWriteFile(FileDirectory, message);

    }

    static void CreateAndWriteFile (String Path , String message) {

        String filename = "CaesarVigenereEncode.txt" ;
	 System.out.println();
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
