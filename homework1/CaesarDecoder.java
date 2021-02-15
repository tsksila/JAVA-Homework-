
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaesarDecoder {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String FileDirectory = "C:\\Users\\sila2\\Desktop\\CaesarEncode.txt";
        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };

        System.out.print("Input number for shift : ");
        int shift = input.nextInt();

        /* shift table */

        for (int i = 0; i < shift; i++) {

            String temp = alphabet[0];
            for (int j = 0; j < alphabet.length - 1; j++) {
                alphabet[j] = alphabet[j + 1];
            }
            alphabet[alphabet.length - 1] = temp;

        }

        /* Show table */

        System.out.print("Show table after shift : ");
        for (String c : alphabet) {
            System.out.print(c);
        }
        System.out.println();

        /* Input encoding message */

        String plaintext;
        Path path = Paths.get(FileDirectory);
        /* Check file was exist */
        if (Files.exists(path)) {
            System.out.print("Input the Encoding message : ");
            plaintext = ReadFile(FileDirectory);
        } else {
            System.out.print("Input the Encoding message : ");
            plaintext = input.next();
        }

        char[] plaintext_array = plaintext.toUpperCase().toCharArray();
        ArrayList<String> plaintext_array_index = new ArrayList<String>();

        /* Find and store index of plaintext */

        for (int i = 0; i < plaintext_array.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (String.valueOf(plaintext_array[i]).equals(alphabet[j])) {
                    plaintext_array_index.add(Integer.toString(j));
                    break;
                }
            }
        }

        /* reverse table */

        for (int i = 0; i < shift; i++) {
            String temp = alphabet[alphabet.length - 1];

            for (int j = alphabet.length - 1; j > 0; j--) {
                alphabet[j] = alphabet[j - 1];
            }
            alphabet[0] = temp;
        }

        /* show output decoding */

        System.out.print("Result : ");
        for (String index : plaintext_array_index) {
            System.out.print(alphabet[Integer.parseInt(index)]);
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
