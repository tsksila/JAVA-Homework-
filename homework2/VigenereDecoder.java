package homework2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class VigenereDecoder {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };

        System.out.println("Vigenere  Decoder");

        /* Create Table Vigenere for decode */

        ArrayList<ArrayList<String>> table = new ArrayList<>();

        for (int i = alphabet.length; i >= 0; i--) {
            ArrayList<String> list = new ArrayList<>();

            for (int j = alphabet.length; j > 0; j--) {

                if (i - j < 0) {
                    list.add(alphabet[alphabet.length - Math.abs(i - j)]);
                } else {
                    list.add(alphabet[Math.abs(i - j) % alphabet.length]);
                }

            }

            table.add(list);

        }
        /* Show Table Vigenere */
        for (int i = 0; i < table.size() - 1; i++) {
            System.out.println(table.get(i));
        }

        /* Input value */
        String plaintext;

        String FileDirectory = "C:\\Users\\sila2\\Desktop\\VigenereEncode.txt";
        Path path = Paths.get(FileDirectory);
        /* Check file was exist */
        if (Files.exists(path)) {
            System.out.print("Input the Encoding message : ");
            plaintext = ReadFile(FileDirectory);
        } else {
            System.out.print("Input the Encoding message : ");
            plaintext = input.nextLine();
        }

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

        /* vegenere decoding process and show massage */

        System.out.print("Show Decoding value : ");

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

        /* show value Decoding */
        System.out.print("Result : ");
        for (int i = 0; i < plaintext_array.size(); i++) {
            System.out.print(table.get(matchIndex.get(i).get(1)).get(matchIndex.get(i).get(0)));
        }

    }

    static String ReadFile(String path) {
        File file = new File(path);

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String message = "";
            String line;
            while ((line = br.readLine()) != null) {
                message += line;
                System.out.println(line);
            }
            br.close();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "null";

    }

}
