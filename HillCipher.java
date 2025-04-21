import java.util.*;

public class HillCipher {

    private static final int MOD = 26;
    private int[][] keyMatrix = new int[2][2];

     // Utility functions
     private int charToInt(char c) {
        return c - 'A';
    }

    private char intToChar(int i) {
        return (char)(i + 'A');
    }
    public HillCipher(String keyStr) {
        if (keyStr.length() != 4) {
            throw new IllegalArgumentException("Key must be exactly 4 letters.");
        }

        keyStr = keyStr.toUpperCase();
        int k = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                keyMatrix[i][j] = keyStr.charAt(k++) - 'A';
    }

    public String encrypt(String text) {
        text = text.replaceAll("[^a-zA-Z]", "").toUpperCase();
        if (text.length() % 2 != 0) text += "X";

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pair = { charToInt(text.charAt(i)), charToInt(text.charAt(i + 1)) };
            int[] res = multiply(keyMatrix, pair);
            result.append(intToChar(res[0])).append(intToChar(res[1]));
        }
        return result.toString();
    }

    public String decrypt(String cipher) {
        int[][] inverse;
        try {
            inverse = inverseKey(keyMatrix);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Decryption failed: " + e.getMessage());
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cipher.length(); i += 2) {
            int[] pair = { charToInt(cipher.charAt(i)), charToInt(cipher.charAt(i + 1)) };
            int[] res = multiply(inverse, pair);
            result.append(intToChar(res[0])).append(intToChar(res[1]));
        }
        return result.toString();
    }

   

    private int[] multiply(int[][] matrix, int[] vector) {
        int[] result = new int[2];
        result[0] = (matrix[0][0] * vector[0] + matrix[0][1] * vector[1]) % MOD;
        result[1] = (matrix[1][0] * vector[0] + matrix[1][1] * vector[1]) % MOD;
        if (result[0] < 0) result[0] += MOD;
        if (result[1] < 0) result[1] += MOD;
        return result;
    }

    private int[][] inverseKey(int[][] matrix) {
        int det = (matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0]) % MOD;
        if (det < 0) det += MOD;

        int invDet = modInverse(det);
        if ((invDet == -1) || (invDet % 2==0)) {
            throw new IllegalArgumentException("Key matrix is not invertible (det has no inverse mod 26) OR the determinat is even.");
        }

        int[][] inv = new int[2][2];
        inv[0][0] = (matrix[1][1] * invDet) % MOD;
        inv[0][1] = ((-matrix[0][1] * invDet) % MOD + MOD) % MOD;
        inv[1][0] = ((-matrix[1][0] * invDet) % MOD + MOD) % MOD;
        inv[1][1] = (matrix[0][0] * invDet) % MOD;

        return inv;
    }

    private int modInverse(int a) {
        a = (a % MOD + MOD) % MOD;
        for (int x = 1; x < MOD; x++) {
            if ((a * x) % MOD == 1) return x;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Enter the Key:");
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter 4-letter key (A - Z): ");
            String keyInput = sc.nextLine();

            HillCipher cipher = new HillCipher(keyInput);

            System.out.print("Enter plaintext: ");
            String plainText = sc.nextLine();

            String encrypted = cipher.encrypt(plainText);
            System.out.println("Encrypted: " + encrypted);

            try {
                String decrypted = cipher.decrypt(encrypted);
                System.out.println("Decrypted: " + decrypted);
            } catch (IllegalArgumentException e) {
                System.out.println("" + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("" + e.getMessage());
        }

        sc.close();
    }
}
