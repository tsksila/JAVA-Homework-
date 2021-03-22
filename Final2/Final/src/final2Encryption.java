
import java.util.ArrayList;
import java.util.Scanner;

public class final2Encryption {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String[] alphabet = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", };

        System.out.print("Plaintext : ");
        String plaintext = input.nextLine();

        System.out.println(" ----------- Shift-Right  4  ---------- ");

        System.out.println("Input number for shift : 4");

        ArrayList<String> shiftOutput = new ArrayList<String>();

        for (int i = 0; i < Shift(plaintext, 4).toCharArray().length; i++) {
            shiftOutput.add(String.valueOf(Shift(plaintext, 4).toCharArray()[i]));
        }

        System.out.println("After shift :" + Shift(plaintext, 4));

        // -------------------- Vigenere Encoder ----------------------//

        System.out.println("------------ Vigenere Encoder by Formula ------------ ");

        String key = "012";
        System.out.println("Input Vigenere Key : " + key);

        // padding
        while (shiftOutput.size() % key.length() != 0) {
            shiftOutput.add("0");
        }
        // System.out.println("After add padding : " + shiftOutput);
        // -- bug---///

        ArrayList<String> key_array = new ArrayList<String>();

        for (int i = 0; i < key.toCharArray().length; i++) {
            key_array.add(String.valueOf(key.toUpperCase().toCharArray()[i]));
        }

        /* check size of array between plaintext and key */

        for (int i = 0; i < shiftOutput.size() - 1; i++) {
            if (key_array.size() < shiftOutput.size()) {
                key_array.add(key_array.get(i));
            }
        }

        /* match index plaintext and value form alphabet */
        ArrayList<ArrayList<Integer>> matchIndex = new ArrayList<>();

        for (int i = 0; i < shiftOutput.size(); i++) {

            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < alphabet.length; j++) {

                if (shiftOutput.get(i).equals(alphabet[j])) {
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

        System.out.print("Ciphertext Result : ");

        for (int i = 0; i < matchIndex.size(); i++) {
            System.out.print(alphabet[(matchIndex.get(i).get(0) + matchIndex.get(i).get(1)) % alphabet.length]);

        }
        System.out.println();
        System.out.print("--------------------- END --------------------- ");

    }

    // all Function

    static String Shift(String message, int key) {

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
}
