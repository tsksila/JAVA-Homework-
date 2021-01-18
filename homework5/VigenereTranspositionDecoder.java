import java.util.ArrayList;
import java.util.Scanner;

public class VigenereTranspositionDecoder {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };

        // -------------------- Transposition Decoder ----------------------//

        System.out.println("----------- Transposition Decoding --------------");

        System.out.print("Input Encode Message : ");

        String plaintext = input.nextLine();
        ArrayList<String> Vigenereoutput = new ArrayList<String>();

        for (int i = 0; i < plaintext.toCharArray().length; i++) {
            Vigenereoutput.add(String.valueOf(plaintext.toUpperCase().toCharArray()[i]));
        }

        System.out.print("This is your key scorp : ");

        for (int i = 1; i <= Vigenereoutput.size(); i++) {
            System.out.print(i + ",");
        }
        System.out.println();

        /* Fill in Key for transposition */
        /* Check input key value */

        ArrayList<Integer> Tran_key = new ArrayList<Integer>();

        while (Tran_key.size() < Vigenereoutput.size()) {

            System.out.print("Input Key No." + (Tran_key.size() + 1) + " : ");

            int key_num = input.nextInt();

            if (key_num <= Vigenereoutput.size()) { // เช็คว่าเลขไม่เกิน scope

                if (Tran_key.size() == 0) { // มาตัวแรก เพิ่มได้เลย
                    Tran_key.add(key_num);
                } else if (Tran_key.size() > 0) { // ตัวที่สองและอื่นๆ loop หาตัวซ้ำ ถ้าซ้ำจะเปลี่ยน ตัวแปร check เป็น
                                                  // false บรรทัดถัดไปจะไม่ทำการเพิ่ม

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

        System.out.print("Transposition decode message : ");

        // สร้าง array ปลอมขึ้นมามีขนาดเท่า ข้อความ

        ArrayList<String> Message_sortbykey = new ArrayList<String>();

        for (int i = 0; i < Vigenereoutput.size(); i++) {
            Message_sortbykey.add("0");
        }

        // แก้ไข แต่ละตำแหน่ง โดย set ตามตำแหน่งของ key

        for (int j = 0; j < Message_sortbykey.size(); j++) {
            Message_sortbykey.set(Tran_key.get(j) - 1, Vigenereoutput.get(j));
        }

        System.out.println(Message_sortbykey);

        // -------------------- Vigenere Decoder ----------------------//

        System.out.println("----------- Vigenere Decoding by Formula --------------");

        System.out.print("Input the Key : ");
        String key = input.next();

        ArrayList<String> key_array = new ArrayList<String>();

        for (int i = 0; i < key.toCharArray().length; i++) {
            key_array.add(String.valueOf(key.toUpperCase().toCharArray()[i]));
        }

        /* check size of array between plaintext and key */

        for (int i = 0; i < Message_sortbykey.size() - 1; i++) {
            if (key_array.size() < Message_sortbykey.size()) {
                key_array.add(key_array.get(i));
            }
        }

        /* vegenere encoding by formula process */

        /* Match index plaintext and value form alphabet */
        ArrayList<ArrayList<Integer>> matchIndex = new ArrayList<>();

        for (int i = 0; i < Message_sortbykey.size(); i++) {

            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < alphabet.length; j++) {

                if (Message_sortbykey.get(i).equals(alphabet[j])) {
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

        /* Vigenere decode Formula */

        System.out.print("Result : ");

        for (int i = 0; i < matchIndex.size(); i++) {
            System.out.print(
                    alphabet[Math.floorMod(matchIndex.get(i).get(0) - matchIndex.get(i).get(1), alphabet.length)]);
        }

    }

}
