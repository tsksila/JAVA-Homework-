import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MiniDesDecoder {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String FileDirectory = "C:\\Users\\sila2\\Desktop\\MiniDesEncode.txt";
        System.out.println("-------------------- MINI DES DECODER -------------------");

        /* Generation Key */

        System.out.print("Input 10 bit Key : ");
        String Key = input.next();

        String Key1 = Transposition(Shift(Transposition(Key, 10), 1), 8);
        String Key2 = Transposition(Shift(Shift(Transposition(Key, 10), 1), 2), 8);

        System.out.println("KEY 1 : " + Key1);
        System.out.println("KEY 2 : " + Key2);

        String message;
        Path path = Paths.get(FileDirectory);

        if (Files.exists(path)) {
            System.out.print("Input the Encoding message : ");
            message = ReadFile(FileDirectory);
        } else {
            System.out.print("Input the Encoding message : ");
            message = input.next();
        }

        ;

        ArrayList<String> message_array = new ArrayList<String>();

        for (int i = 0; i < message.toCharArray().length / 8; i++) {

            String Text = "";
            for (int j = i * 8; j < (i * 8) + 8; j++) {
                Text += String.valueOf(message.toCharArray()[j]);
            }

            message_array.add(Text);

        }

        String message_listString = "";

        for (int i = 0; i < message_array.size(); i++) {

            String SW = Transposition(message_array.get(i), 1).substring(4, 8) + ""
                    + XOR(Transposition(SBox(XOR(Transposition(Transposition(message_array.get(i), 1), 2), Key2)), 4),
                            Transposition(message_array.get(i), 1).substring(0, 4));

            message_listString += Transposition(
                    XOR(Transposition(SBox(XOR(Transposition(SW, 2), Key1)), 4), SW.substring(0, 4)) + ""
                            + SW.substring(4, 8),
                    -1);

        }

        System.out.println("Result : " + message_listString);
        System.out.println("Result Text :" + " " + BinaryToString(message_listString));

    }

    /* ALL FUNCTION */

    static String convToBinary(char message) {

        return String.format("%8s", Integer.toBinaryString(message)).replaceAll(" ", "0");
    }

    static String ConvIntToBinary(int number) {

        return String.format("%2s", Integer.toBinaryString(number)).replace(' ', '0');
    }

    static String Transposition(String message, int permute) {

        int[] KeyP10 = { 2, 4, 1, 6, 3, 9, 0, 8, 7, 5 }; // KEY FOR P10
        int[] KeyP8 = { 5, 2, 6, 3, 7, 4, 9, 8 }; // KEY FOR P8
        int[] KeyIP = { 1, 5, 2, 0, 3, 7, 4, 6 }; // KEY FOR IP
        int[] KeyIPM = { 3, 0, 2, 4, 6, 1, 7, 5 }; // KEY FOR IP-1
        int[] KeyEP = { 3, 0, 1, 2, 1, 2, 3, 0 }; // KEY FOR EP
        int[] KeyP4 = { 1, 3, 2, 0 }; // KEY FOR P4
        char[] message_array = message.toCharArray();
        String listString = "";

        ArrayList<Character> storeValue = new ArrayList<>();

        if (permute == 10) {

            for (int i = 0; i < KeyP10.length; i++) {
                storeValue.add(message_array[KeyP10[i]]);
            }

        } else if (permute == 8) {
            for (int i = 0; i < KeyP8.length; i++) {
                storeValue.add(message_array[KeyP8[i]]);
            }
        } else if (permute == 1) {

            for (int i = 0; i < KeyIP.length; i++) {
                storeValue.add(message_array[KeyIP[i]]);
            }

        } else if (permute == -1) {
            for (int i = 0; i < KeyIPM.length; i++) {
                storeValue.add(message_array[KeyIPM[i]]);
            }
        } else if (permute == 2) {

            char[] Ip_last = message.substring(4, 8).toCharArray();
            for (int i = 0; i < KeyEP.length; i++) {
                storeValue.add(Ip_last[KeyEP[i]]);
            }
        } else if (permute == 4) {
            for (int i = 0; i < KeyP4.length; i++) {
                storeValue.add(message_array[KeyP4[i]]);
            }
        }

        for (Character c : storeValue) {
            listString += c;
        }

        return listString;

    }

    static String Shift(String message, int key) {

        String[] partMessage = { message.substring(0, 5), message.substring(5, 10) };

        for (int i = 0; i < partMessage.length; i++) {

            char[] shitfMessage = partMessage[i].toCharArray();

            for (int j = 0; j < key; j++) {

                char temp = shitfMessage[0];

                for (int k = 0; k < shitfMessage.length - 1; k++) {
                    shitfMessage[k] = shitfMessage[k + 1];
                }
                shitfMessage[shitfMessage.length - 1] = temp;
            }

            partMessage[i] = String.valueOf(shitfMessage);
        }

        return partMessage[0] + partMessage[1];

    }

    static String XOR(String message, String Key) {

        char[] message_array = message.toCharArray();

        ArrayList<Integer> storeValue = new ArrayList<>();

        for (int i = 0; i < Key.toCharArray().length; i++) {
            storeValue.add(message_array[i] ^ Key.toCharArray()[i]);
        }

        String listString = "";
        for (Integer i : storeValue) {
            listString += i;
        }

        return listString;
    }

    static String SBox(String message) {

        int S0table[][] = { { 1, 0, 3, 2 }, { 3, 2, 1, 0 }, { 0, 2, 1, 3 }, { 3, 1, 0, 2 } };
        int S1table[][] = { { 0, 1, 2, 3 }, { 2, 0, 1, 3 }, { 3, 0, 1, 2 }, { 2, 1, 0, 3 } };

        String[] row = { "00", "01", "10", "11" };
        String[] col = { "00", "01", "10", "11" };

        String[] S = { message.substring(0, 4), message.substring(4, 8) };

        // System.out.println(message);

        ArrayList<ArrayList<Integer>> index = new ArrayList<>();

        for (int i = 0; i < S.length; i++) {

            ArrayList<Integer> match = new ArrayList<>();

            for (int j = 0; j < row.length; j++) {
                if (String.valueOf(S[i].toCharArray()[0] + "" + S[i].toCharArray()[3]).equals(row[j])) {
                    match.add(j);
                }
            }

            for (int j = 0; j < col.length; j++) {
                if (String.valueOf(S[i].toCharArray()[1] + "" + S[i].toCharArray()[2]).equals(col[j])) {
                    match.add(j);
                }
            }

            index.add(match);

        }

        String S_val = ConvIntToBinary(S0table[index.get(0).get(0)][index.get(0).get(1)]) + ""
                + ConvIntToBinary(S1table[index.get(1).get(0)][index.get(1).get(1)]);

        return S_val;

    }

    static String BinaryToString(String message) {

        String s = message;
        String str = "";

        for (int i = 0; i < s.length() / 8; i++) {

            int a = Integer.parseInt(s.substring(8 * i, (i + 1) * 8), 2);
            str += (char) (a);
        }

        return str;
    }

    static String ReadFile(String path) {
        File file = new File(path);

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String message = "" ;
            String line;
            while ((line = br.readLine()) != null) {
                message +=line ;
                System.out.println(line);
            }
            br.close();
            String bitmessage = "";
            for (int i = 0; i < message.toCharArray().length; i++) {
                    bitmessage += convToBinary(message.toCharArray()[i]);
            }
    
            return bitmessage ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "null" ;

    }

}
