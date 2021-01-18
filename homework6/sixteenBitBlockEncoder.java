import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class sixteenBitBlockEncoder {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("-------------- 16 Bit-Block  Encoder--------------");

        System.out.print("Input your Message : ");
        String plaintext = input.next();

        ArrayList<String> plaintext_array = new ArrayList<String>();

        for (int i = 0; i < plaintext.toCharArray().length; i++) {
            plaintext_array.add(String.valueOf(plaintext.toUpperCase().toCharArray()[i]));
        }

        /* Add Padding */
        if (plaintext_array.size() % 2 != 0) {
            plaintext_array.add("X");
        }

        /* Encode  by function */

        System.out.print("Result : ");

        for (int i = 0; i < plaintext_array.size() / 2; i++) {

            int index = i * 2;

            String encodeText = XOR(SubstitionBit(Transposition(Shift(convToBinary(plaintext_array.get(index).charAt(0))
            + convToBinary(plaintext_array.get(index + 1).charAt(0)))))) ;

            System.out.print(encodeText);

        }



    }

    /* ------------ FUNCTION --------------- */

    static String convToBinary(char message) {

        return String.format("%8s", Integer.toBinaryString(message)).replaceAll(" ", "0");
    }

    static String Shift(String message) {

        int key = 5; // KEY FOR SHIFT

        char[] shitfMessage = message.toCharArray();

        for (int i = 0; i < key; i++) {

            char temp = shitfMessage[shitfMessage.length - 1];

            for (int j = shitfMessage.length - 1; j > 0; j--) {
                shitfMessage[j] = shitfMessage[j - 1];
            }
            shitfMessage[0] = temp;
        }

        return String.valueOf(shitfMessage);

    }

    static String Transposition(String message) {

        int[] Key = { 7, 4, 3, 5, 2, 1, 6, 0, 11, 15, 13, 10, 12, 8, 9, 14 }; // KEY FOR TRANSPOSITION
        char[] message_array = message.toCharArray();
        char[] storeValue = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', };

        for (int i = 0; i < message_array.length; i++) {
            storeValue[i] = message_array[Key[i]];
        }

        return String.valueOf(storeValue);

    }

    static String SubstitionBit(String message) {

        String[] str_val = { message.substring(0, 8), message.substring(8, 16) };
        String[] col = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011","1100", "1101", "1110", "1111" };
        String[] row = { "00", "01", "10", "11" };
        String[][] table = {
            {"0010", "1100", "0100", "0001", "0111", "1010", "1011", "0110", "1000", "0101", "0011", "1111", "1101", "0000", "1110", "1001"},
            {"1110", "1011", "0010", "1100", "0100", "0111", "1101", "0001", "0101", "0000", "1111", "1010", "0011", "1001", "1000", "0110"},
            {"0100", "0010", "0001", "1011", "1010", "1101", "0111", "1000", "1111", "1001", "1100", "0101", "0110", "0011", "0000", "1110"},
            {"1011", "1000", "1100", "0111", "0001", "1110", "0010", "1101", "0110", "1111", "0000", "1001", "1010", "0100", "0101", "0011"},};
            // KEY SubstitionBit

        ArrayList<ArrayList<Integer>> index = new ArrayList<>();

        for (int i = 0; i < str_val.length; i++) {

            ArrayList<Integer> match = new ArrayList<>();

            for (int j = 0; j < row.length; j++) {
                if (str_val[i].substring(0, 2).equals(row[j])) {
                    match.add(j);
                }
            }

            for (int j = 0; j < col.length; j++) {
                if (str_val[i].substring(4, 8).equals(col[j])) {
                    match.add(j);
                }
            }

            index.add(match);

        }

        String value = str_val[0].substring(0, 4) + table[index.get(0).get(0)][index.get(0).get(1)];
        String value2 = str_val[1].substring(0, 4) + table[index.get(1).get(0)][index.get(1).get(1)];

        return value + value2;
    }

    static String XOR(String message) {

        char[] message_array = message.toCharArray();
        char[] Key = { '1', '0', '1', '0', '0', '1', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0' }; //KEY  XOR
        int[] val = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };
        for (int i = 0; i < Key.length; i++) {
            val[i] = message_array[i] ^ Key[i];
        }
        String Text = "";
        for (int i = 0; i < val.length; i++) {
            Text += String.valueOf(val[i]);
        }

        return Text;
    }

    static String BinaryToChar  (String message) {

        String [] input = {message.substring(0,8) , message.substring(8,16)};// Binary input as String
        StringBuilder sb = new StringBuilder(); //  store the chars

        for (int i = 0; i < input.length; i++) {
            
            Arrays.stream( // Create a Stream
            input[i].split("(?<=\\G.{8})") // Splits the input string into 8-char-sections 
            ).forEach(s -> // Go through each 8-char-section...
                sb.append((char) Integer.parseInt(s, 2)) // ...and turn it into an int and then to a char
            );

        }   
        String output =  sb.toString(); 
        return output ;
    }

}