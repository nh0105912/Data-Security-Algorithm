import java.util.*;
import java.io.*;
import java.util.Base64;

public class FeistelCipher {
    public static String randKey(int p) {
        StringBuilder key = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < p; i++) {
            key.append(rand.nextInt(2));
        }
        return key.toString();
    }

    public static String exor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    public static String decimalToBinary(int num) {
        String binary = Integer.toBinaryString(num);
        while (binary.length() < 8) {
            binary = "0" + binary;
        }
        return binary;
    }

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static String binaryToHex(String binary) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 4) {
            String chunk = binary.substring(i, Math.min(i + 4, binary.length()));
            hex.append(Integer.toHexString(Integer.parseInt(chunk, 2)));
        }
        return hex.toString();
    }

    public static String binaryToBase64(String binary) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteStr = binary.substring(i, Math.min(i + 8, binary.length()));
            byteStream.write((byte) binaryToDecimal(byteStr));
        }
        return Base64.getEncoder().encodeToString(byteStream.toByteArray());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the PlainText: ");
        String plainText = sc.nextLine();

        // Convert to binary
        StringBuilder binaryPlainText = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            binaryPlainText.append(decimalToBinary((int) ch));
        }
        String PT_Bin = binaryPlainText.toString();

        // Split
        int n = PT_Bin.length() / 2;
        String L = PT_Bin.substring(0, n);
        String R = PT_Bin.substring(n);

        // Generate keys
        String[] keys = new String[16];
        for (int i = 0; i < 16; i++) {
            keys[i] = randKey(R.length());
        }

        System.out.println("\n---- Keys Used ----");
        for (int i = 0; i < 16; i++) {
            System.out.println("Key K" + (i + 1) + ": " + keys[i]);
        }

        // Encryption
        System.out.println("\n---- Encryption Rounds ----");
        for (int i = 0; i < 16; i++) {
            String f = exor(R, keys[i]);
            String newR = exor(f, L);
            L = R;
            R = newR;
            System.out.println("Round " + (i + 1) + " => L: " + L + ", R: " + R);
        }

        // Cipher binary
        String cipherBinary = L + R;

        // Convert to hex and base64
        String cipherHex = binaryToHex(cipherBinary);
        String cipherBase64 = binaryToBase64(cipherBinary);

        System.out.println("\nCipher Text (Hex)    : " + cipherHex.toUpperCase());
        System.out.println("Cipher Text (Base64) : " + cipherBase64);

        // Decryption
        System.out.println("\n---- Decryption Rounds ----");
        for (int i = 15; i >= 0; i--) {
            String f = exor(L, keys[i]);
            String newL = exor(f, R);
            R = L;
            L = newL;
            System.out.println("Round " + (16 - i) + " => L: " + L + ", R: " + R);
        }

        // Recover binary
        String recoveredBinary = L + R;

        // Convert binary to characters
        StringBuilder recoveredText = new StringBuilder();
        for (int i = 0; i < recoveredBinary.length(); i += 8) {
            String byteStr = recoveredBinary.substring(i, Math.min(i + 8, recoveredBinary.length()));
            recoveredText.append((char) binaryToDecimal(byteStr));
        }

        System.out.println("\nRetrieved Plain Text: " + recoveredText.toString());
        sc.close();
    }
}