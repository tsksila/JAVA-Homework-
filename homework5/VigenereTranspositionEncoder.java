import java.util.ArrayList;
import java.util.Scanner;

public class VigenereTranspositionEncoder {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };

        // -------------------- Vigenere Encoder ----------------------//

        System.out.println("------------ Vigenere Encoder by Formula ------------ ");

        System.out.print("Input the plaintext message : ");
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

        /* vegenere encoding by formula process */

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

        System.out.print("Output Encoding Text : ");

        ArrayList<String> Vigenereoutput = new ArrayList<String>();

        /*  vigenere encode formula */

        for (int i = 0; i < matchIndex.size(); i++) {
            System.out.print(alphabet[(matchIndex.get(i).get(0) + matchIndex.get(i).get(1)) % alphabet.length]);

            Vigenereoutput.add(alphabet[(matchIndex.get(i).get(0) + matchIndex.get(i).get(1)) % alphabet.length]);
        }
        System.out.println();

        // -------------------- Transposition Encoder ----------------------//

        System.out.println("----------- Transposition Encoding --------------");

        System.out.print("This is your key scorp : ");

        for (int i = 1; i <= Vigenereoutput.size(); i++) {
            System.out.print(i + ",");
        }
        System.out.println();

        /* Fill in Key for transposition */

        ArrayList<Integer> Tran_key = new ArrayList<Integer>();

        while (Tran_key.size() < Vigenereoutput.size()) {

            System.out.print("Input Key No." + (Tran_key.size() + 1) + " : ");

            int key_num = input.nextInt();

            
            if (key_num <= Vigenereoutput.size()) {

                if (Tran_key.size() == 0) {
                    Tran_key.add(key_num);
                } else if (Tran_key.size() > 0) {

                    boolean check = true;

                    for (int i = 0; i < Tran_key.size(); i++) {
                        if (key_num == Tran_key.get(i)) {
                            System.out.println("Duplicate!!!");
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        Tran_key.add(key_num);
                    }

                }

            } else {
                System.out.println("Not Number in Scope");
            }

        }

        System.out.println("Transposition Key is : " + Tran_key);

        System.out.print("Result : ");
        for (int i = 0; i < Vigenereoutput.size(); i++) {

            System.out.print(Vigenereoutput.get(Tran_key.get(i) - 1));

        }

    }

}