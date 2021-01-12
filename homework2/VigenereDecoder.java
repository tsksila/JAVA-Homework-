import java.util.ArrayList;
import java.util.Scanner;

public class VigenereDecoder {

    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {


        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };

        System.out.println("Vigenere  Encoder");

        /* Create Table Vigenere for decode */

        ArrayList<ArrayList<String>> table = new ArrayList<>();

        for (int i = 0; i <= alphabet.length; i++) {
            ArrayList<String> list = new ArrayList<>();

            for (int j = 0; j < alphabet.length; j++) {

                list.add(alphabet[Math.abs(i + j) % alphabet.length]);
            }

            table.add(list);

        }

        /*
         * for (int i = alphabet.length ; i >= 0 ;i--) { ArrayList<String> list = new
         * ArrayList<>();
         * 
         * for (int j = alphabet.length; j > 0; j--) {
         * 
         * if (i-j < 0) { list.add(alphabet[alphabet.length - Math.abs(i-j)]) ; }else {
         * list.add( alphabet[Math.abs(i - j)%alphabet.length ] ); }
         * 
         * 
         * }
         * 
         * table.add(list);
         * 
         * }
         */

      

        /* Input value */

        System.out.print("Input the Encode message : ");
        String plaintext = input.nextLine();
        ArrayList<String> plaintext_array = new ArrayList<String>();

        for (int i = 0; i < plaintext.toCharArray().length; i++) {
            plaintext_array.add(String.valueOf(plaintext.toUpperCase().toCharArray()[i]));
        }

        System.out.print("Input the Key : ");
        String key = input.nextLine();

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

        /* vegenere decoding process  and show massage*/


        System.out.print("Show Decoding value : ");

        for (int i = 0; i < key_array.size(); i++) {

                for (int j = 0; j < alphabet.length-1; j++) {
                    
                    if (key_array.get(i).equals(table.get(j).get(0))) {

                        for (int j2 = 0; j2 < table.get(j).size()-1 ;j2++) {
                            
                            if (plaintext_array.get(i).equals(table.get(j).get(j2))) {
                                
                                System.out.print(table.get(0).get(j2));
                            }

                        }
                        
                    }

                }


        }

     

    }

}
