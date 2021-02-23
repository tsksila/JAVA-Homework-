package homework11;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class RSA_Encryption {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String Path = "C:\\Users\\silal\\Desktop\\";
        String Input_filename = "ciphertext.txt";
        String filename_PublicKey = "PublicKey.txt";
        String filename_encryption = "Encryption.txt";

        System.out.println("-------- Genarating Key ---------");
        System.out.println(
                "Ex.prime number 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 113 127 131 137 139 149 151 157 163 167 173");

        int p;
        int q;

        System.out.print("Type prime number P:");
        p = input.nextInt();

        while (!checkPrime(p)) {
            System.out.println(p + " is not a prime number !");
            System.out.print("Please type new prime number : ");
            p = input.nextInt();

        }
        System.out.print("Type prime number Q:");
        q = input.nextInt();

        while (!checkPrime(q)) {
            System.out.println(q + " is not a prime number !");
            System.out.print("Please type new prime number : ");
            q = input.nextInt();

        }

        long n = p * q; // N
        long delta_n = (p - 1) * (q - 1); // Delta N
        long e = GCD(delta_n); // E
        BigInteger d = Find_D(e, delta_n); // D

        System.out.println("Private Key : (" + e + "," + n + ")");
        System.out.println("Public  Key : (" + d + "," + n + ")");
        CreateAndWriteFile(Path, filename_PublicKey , d+" "+n);

        System.out.println("------------ Encription ---------------");
        String Input_text = Read_File(Path, Input_filename);
        System.out.println("Input text : "+Input_text);
        char[] ch = new char[Input_text.length()];
        for (int i = 0; i < Input_text.length(); i++) {
            ch[i] = Input_text.charAt(i);
        }

        // calculate by formula c = m^e mod n

        String encrypttext = "";
        String encrypt_array = "";

        for (int i = 0; i < ch.length; i++) {
            long ascii = (int) ch[i];

            BigInteger nc = BigInteger.valueOf(ascii).pow((int) e).mod(BigInteger.valueOf(n));
            encrypt_array += nc.toString() + " ";
            encrypttext += (char) nc.intValue();
        }

        System.out.println("Result :" + encrypt_array);
        System.out.println("Result Text Encryption :" + encrypttext);

        CreateAndWriteFile(Path, filename_encryption ,encrypt_array);

    }

    /* Function */

    static BigInteger Find_D(long e, long delta_n) {

        e = e % delta_n;
        long d = 0L;
        while (d < delta_n) {
            Boolean re = BigInteger.valueOf(d).multiply(BigInteger.valueOf(e)).mod(BigInteger.valueOf(delta_n))
                    .equals(BigInteger.valueOf(1L));
            if (re == true) {
                return BigInteger.valueOf(d);
            }
            d++;
        }

        return BigInteger.valueOf(0);
    }

    static long GCD(long num) {

        long gcd = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 1 && checkPrime(i)) {
                gcd = i;
            }
        }
        return gcd;

    }

    static boolean checkPrime(int num) {

        boolean isPrime = true;

        if (num == 0 || num == 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= num / 2; ++i) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;

    }

    static String Read_File(String path, String filename) {
        File file = new File(path + filename);
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String message = "";
            String line;
            while ((line = br.readLine()) != null) {
                message += line;
            }
            br.close();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "null";

    }

    static void CreateAndWriteFile(String Path, String filename ,String message) {

        /* Create File */
        try {
            File myObj = new File(Path + filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /* Write file */
        try {
            FileWriter myWriter = new FileWriter(Path + filename);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
