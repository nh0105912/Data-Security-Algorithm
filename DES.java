import java.util.Arrays;

public class DES {
    
    // Initial Permutation Table (IP)
    private static final int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };
    
    // Final Permutation Table (FP)
    private static final int[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    // Expansion Permutation Table (E)
    private static final int[] E = {
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    };

    // S-boxes for substitution
    private static final int[][][] S = {
        {
            {14, 4, 13, 1, 2, 15, 11, 8, 16, 5, 3, 10, 7, 9, 12, 6},
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 5, 10, 0},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 8, 3},
            {8, 14, 7, 11, 1, 10, 13, 4, 9, 5, 0, 15, 12, 3, 6, 2}
        },
        {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 5, 10, 0},
            {3, 9, 10, 13, 2, 5, 14, 12, 6, 8, 4, 7, 15, 1, 11, 0},
            {6, 7, 15, 10, 13, 3, 14, 9, 11, 1, 0, 8, 5, 12, 4, 2},
            {8, 4, 1, 10, 15, 3, 12, 5, 14, 2, 11, 7, 9, 0, 13, 6}
        },
        // Add remaining S-boxes here
    };

    // Permutation Table (P4)
    private static final int[] P4 = {
        2, 4, 3, 1
    };

    // Initial Key (56 bits)
    private static final String KEY = "10101010101110110000101010101111"; // Example key
    
    public static void main(String[] args) {
        String plaintext = "01100001011000100110001101100100"; // Example plaintext (64 bits)
        String key = KEY;

        // Perform DES encryption
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);
    }

    public static String encrypt(String plaintext, String key) {
        // Perform initial permutation on plaintext
        String permutedText = permute(plaintext, IP);

        // Divide the permuted text into left and right halves
        String left = permutedText.substring(0, 32);
        String right = permutedText.substring(32, 64);

        // Generate subkeys (round keys)
        String[] subkeys = generateSubkeys(key);

        // Perform 16 rounds of DES
        for (int i = 0; i < 16; i++) {
            String expandedRight = permute(right, E);  // Expansion permutation on the right half
            String xored = xor(expandedRight, subkeys[i]);  // XOR with subkey
            String substituted = applySBoxes(xored);  // Apply S-box substitution
            String p4 = permute(substituted, P4);  // P4 permutation
            String newLeft = xor(p4, left);  // XOR the result with the left half

            // Update left and right for the next round
            left = right;
            right = newLeft;
        }

        // After 16 rounds, concatenate the left and right halves
        String combined = left + right;

        // Perform final permutation on the result
        return permute(combined, FP);
    }

    public static String permute(String input, int[] permutationTable) {
        StringBuilder output = new StringBuilder();
        for (int i : permutationTable) {
            output.append(input.charAt(i - 1));  // Permute based on the table
        }
        return output.toString();
    }

    public static String xor(String input1, String input2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input1.length(); i++) {
            result.append(input1.charAt(i) == input2.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    public static String[] generateSubkeys(String key) {
        // Key generation is a complex part of DES. You can implement it here
        // For simplicity, return a dummy set of subkeys for now.
        String[] subkeys = new String[16];
        for (int i = 0; i < 16; i++) {
            subkeys[i] = key; // This is just a placeholder
        }
        return subkeys;
    }

    public static String applySBoxes(String input) {
        StringBuilder output = new StringBuilder();

        // Split input into 8 blocks of 6 bits
        for (int i = 0; i < 8; i++) {
            String block = input.substring(i * 6, (i + 1) * 6);
            output.append(sboxSubstitution(block, i));
        }

        return output.toString();
    }

    public static String sboxSubstitution(String input, int sboxIndex) {
        // Calculate row and column for S-box
        int row = Integer.parseInt(input.charAt(0) + "" + input.charAt(5), 2);  // First and last bit
        int col = Integer.parseInt(input.substring(1, 5), 2);  // Middle 4 bits

        // Get the corresponding value from the S-box
        int sboxValue = S[sboxIndex][row][col];

        // Convert the integer value to a 4-bit binary string
        return String.format("%4s", Integer.toBinaryString(sboxValue)).replace(' ', '0');
    }
}
