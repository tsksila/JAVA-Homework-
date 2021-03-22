
import java.util.ArrayList;
import java.util.Scanner;

public class final2Decryption {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String[] alphabet = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", };

        // -------------------- Vigenere Decoder ----------------------//

        System.out.println("------------ Vigenere Decoder by Formula ------------ ");

        System.out.print("Input the Ciphertext : ");
        String Ciphertext = input.nextLine();
        ArrayList<String> Ciphertext_array = new ArrayList<String>();

        for (int i = 0; i < Ciphertext.toCharArray().length; i++) {
            Ciphertext_array.add(String.valueOf(Ciphertext.toCharArray()[i]));
        }
        String key = "012";
        System.out.println("Input the Vigenere Key : " + key);

        ArrayList<String> key_array = new ArrayList<String>();

        for (int i = 0; i < key.toCharArray().length; i++) {
            key_array.add(String.valueOf(key.toUpperCase().toCharArray()[i]));
        }

        /* check size of array between plaintext and key */

        for (int i = 0; i < Ciphertext_array.size() - 1; i++) {
            if (key_array.size() < Ciphertext_array.size()) {
                key_array.add(key_array.get(i));
            }
        }

        /* match index plaintext and value form alphabet */
        ArrayList<ArrayList<Integer>> matchIndex = new ArrayList<>();

        for (int i = 0; i < Ciphertext_array.size(); i++) {

            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < alphabet.length; j++) {

                if (Ciphertext_array.get(i).equals(alphabet[j])) {
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

        System.out.print("Vigenere Decrytion : ");

        String VigenereOutput = "";

        for (int i = 0; i < matchIndex.size(); i++) {

            System.out.print(alphabet[Math.abs(matchIndex.get(i).get(0) - matchIndex.get(i).get(1) + alphabet.length)
                    % alphabet.length]);

            VigenereOutput += (alphabet[Math.abs(matchIndex.get(i).get(0) - matchIndex.get(i).get(1) + alphabet.length)
                    % alphabet.length]);

        }

        // ------------------//
        while (VigenereOutput.charAt(VigenereOutput.length() - 1) == '0') {
            VigenereOutput = VigenereOutput.substring(0, VigenereOutput.length() - 1);
        }
        System.out.println();
        System.out.println("After delete padding :" + VigenereOutput);
        // ----------------//

        System.out.println();

        System.out.println(" ----------- Shift-Right  4  ---------- ");

        System.out.println("Input number for shift : 4 ");

        System.out.println("Decryption Result : " + Shift(VigenereOutput, 4));

    }

    static String Shift(String message, int key) {

        char[] shitfMessage = message.toCharArray();

        for (int i = 0; i < key; i++) {

            char temp = shitfMessage[0];

            for (int j = 0; j < shitfMessage.length - 1; j++) {
                shitfMessage[j] = shitfMessage[j + 1];
            }
            shitfMessage[shitfMessage.length - 1] = temp;
        }

        return String.valueOf(shitfMessage);

    }

}
