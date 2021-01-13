import java.util.ArrayList;
import java.util.Scanner;

public class CaesarVigenereEncoder {

    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        

        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Y", "Z" };


        //-------------------- Caesar  Encoder ----------------------//

        System.out.println(" ----------- Caesar Encoder by Formula ---------- ");

        System.out.print("Input the plaintext message : ");
        String plaintext = input.nextLine();

        System.out.print("Input number for shift : ");
        int shift = input.nextInt();


        char[] plaintext_array = plaintext.toUpperCase().toCharArray();
        ArrayList<Integer> plaintext_array_index = new ArrayList<Integer>();

        /* Find and store index of plaintext */
        for (int i = 0; i < plaintext_array.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (String.valueOf(plaintext_array[i]).equals(alphabet[j])) {
                    plaintext_array_index.add(j);
                    break;
                }
            }
        }

        /* Ceasar Encoding by Formula */

        ArrayList<String> CaesarOutput = new ArrayList<String>();

        System.out.print("Caesar Output Encoding text  : ");

       for (int i = 0; i < plaintext_array.length; i++) {
           System.out.print(alphabet[(plaintext_array_index.get(i)+shift)%alphabet.length]);
           CaesarOutput.add(alphabet[(plaintext_array_index.get(i)+shift)%alphabet.length]);
       }

       System.out.println();
       
       //-------------------- Vigenere Encoder ----------------------//

       System.out.println("------------ Vigenere Encoder by Formula ------------ ");

       System.out.print("Input the  Key : ");
       String key = input.next();

       ArrayList<String> key_array = new ArrayList<String>();

        for (int i = 0; i < key.toCharArray().length; i++) {
            key_array.add(String.valueOf(key.toUpperCase().toCharArray()[i]));
        }


        /* check size of array between plaintext and key */


        for (int i = 0; i < CaesarOutput.size() - 1; i++) {
            if (key_array.size() < CaesarOutput.size()) {
                key_array.add(key_array.get(i));
            }
        }

           /* match index plaintext and value form alphabet */
           ArrayList<ArrayList<Integer>> matchIndex = new ArrayList<>();

           for (int i = 0; i < CaesarOutput.size(); i++) {
   
               ArrayList<Integer> list = new ArrayList<>();
   
               for (int j = 0; j < alphabet.length; j++) {
   
                   if (CaesarOutput.get(i).equals(alphabet[j])) {
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
   
           System.out.print("Vigenere  Output Encoding Text : ");
   
           for (int i = 0; i < matchIndex.size(); i++) {
               System.out.print(alphabet[(matchIndex.get(i).get(0) + matchIndex.get(i).get(1)) % alphabet.length]);
   
           }



    }

}
