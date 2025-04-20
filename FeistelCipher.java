import java.util.*;
public class FeistelCipher {
    public static String randKey(int p) {
        StringBuilder key = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < p; i++) {
            key.append(rand.nextInt(2)); // 0 or 1
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

    public static void main(String[] args) {

        System.out.println();
        System.out.print("Enter the PLainText: ");
        Scanner sc =new Scanner(System.in);

        String plainText = sc.nextLine();
        System.out.println("Plain Text is: " + plainText);

        // Convert text to binary
        StringBuilder binaryPlainText = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            binaryPlainText.append(decimalToBinary((int) ch));
        }
        String PT_Bin = binaryPlainText.toString();
        System.out.println("PT  :"+PT_Bin);

        // Split into Left and Right
        int n = PT_Bin.length() / 2;
        String L0 = PT_Bin.substring(0, n);
        String R0 = PT_Bin.substring(n);
        int m = R0.length();
        System.out.println("L0  : "+L0);
        System.out.println("R0  :"+R0);

        // Generate keys
        String K1 = randKey(m);
        String K2 = randKey(m);
        String K3 = randKey(m);
        String K4 = randKey(m);
        String K5 = randKey(m);
        String K6 = randKey(m);
        String K7 = randKey(m);
        String K8 = randKey(m);
        String K9 = randKey(m);
        String K10 = randKey(m);
        String K11 = randKey(m);
        String K12 = randKey(m);
        String K13 = randKey(m);
        String K14 = randKey(m);
        String K15 = randKey(m);
        String K16 = randKey(m);

        System.out.println("-------------------Show keys used----------------");
        System.out.println("Key K1: " + K1);
        System.out.println("Key K2: " + K2);
        System.out.println("Key K3: " + K3);
        System.out.println("Key K4: " + K4);
        System.out.println("Key K5: " + K5);
        System.out.println("Key K6: " + K6);
        System.out.println("Key K7: " + K7);
        System.out.println("Key K8: " + K8);
        System.out.println("Key K9: " + K9);
        System.out.println("Key K10: " + K10);
        System.out.println("Key K11: " + K11);
        System.out.println("Key K12: " + K12);
        System.out.println("Key K13: " + K13);
        System.out.println("Key K14: " + K14);
        System.out.println("Key K15: " + K15);
        System.out.println("Key K16: " + K16);

        System.out.println("-----------------Encryption-------------------");

        System.out.println("Round 0");
        String f1 = exor(R0, K1);
        String R1= exor(f1, L0);
        String L1 = R0;
        System.out.println("L1 :" + L1);
        System.out.println("R1 :" + R1);


        System.out.println("Round 1");
        String f2 = exor(R1, K2);
        String R2 = exor(f2, L1);
        String L2 = R1;
        System.out.println("L2 :" + L2);
        System.out.println("R2 :" + R2);

        System.out.println("Round 2");
        String f3 = exor(R2, K3);
        String R3 = exor(f3, L2);
        String L3 = R2;
        System.out.println("L3 :" + L3);
        System.out.println("R3 :" + R3);

        System.out.println("Round 3");
        String f4 = exor(R3, K4);
        String R4 = exor(f4, L3);
        String L4 = R3;
        System.out.println("L4 :" + L4);
        System.out.println("R4 :" + R4);


        System.out.println("Round 4");
        String f5 = exor(R4, K5);
        String R5 = exor(f5, L4);
        String L5= R4;
        System.out.println("L5 :" + L5);
        System.out.println("R5 :" + R5);

        System.out.println("Round 5");
        String f6 = exor(R5, K6);
        String R6 = exor(f6, L5);
        String L6 = R5;
        System.out.println("L6 :" + L6);
        System.out.println("R6 :" + R6);

        System.out.println("Round 6");
        String f7 = exor(R6, K7);
        String R7 = exor(f7, L6);
        String L7 = R6;
        System.out.println("L7 :" + L7);
        System.out.println("R7  :" + R7);

        System.out.println("Round 7");
        String f8 = exor(R7, K8);
        String R8= exor(f8, L7);
        String L8 = R7;
        System.out.println("L8 :" + L8);
        System.out.println("R8 :" + R8);


        System.out.println("Round 8");
        String f9 = exor(R8, K9);
        String R9 = exor(f9, L8);
        String L9  = R8;
        System.out.println("L9 :" + L9);
        System.out.println("R9 :" + R9);

        System.out.println("Round 9");
        String f10 = exor(R9, K10);
        String R10 = exor(f10, L9);
        String L10 = R9;
        System.out.println("L10 :" + L10);
        System.out.println("R10 :" + R10);

        System.out.println("Round 10");
        String f11 = exor(R10, K11);
        String R11 = exor(f11, L10);
        String L11= R10;
        System.out.println("L11:" + L11);
        System.out.println("R11 :" + R11);


        System.out.println("Round 11");
        String f12 = exor(R11, K12);
        String R12 = exor(f12, L11);
        String L12 = R11;
        System.out.println("L12 :" + L12);
        System.out.println("R12 :" + R12);

        System.out.println("Round 12");
        String f13 = exor(R12, K13);
        String R13 = exor(f13, L12);
        String L13 = R12;
        System.out.println("L13 :" + L13);
        System.out.println("R6 :" + R13);

        System.out.println("Round 13");
        String f14 = exor(R13, K14);
        String R14 = exor(f14, L13);
        String L14 = R13;
        System.out.println("L14 :" + L14);
        System.out.println("R14  :" + R14);

        System.out.println("Round 14");
        String f15 = exor(R14, K15);
        String R15 = exor(f15, L14);
        String L15 = R14;
        System.out.println("L15 :" + L15);
        System.out.println("R15 :" + R15);

        System.out.println("Round 15");
        String f16 = exor(R15, K16);
        String R16 = exor(f16, L15);
        String L16 = R15;
        System.out.println("L16 :" + L16);
        System.out.println("R16  :" + R16);


        // Concate the L16 and R16
        String cipherBinary = L16+ R16;

        // Convert to cipher text (7-bit chunks)
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < cipherBinary.length(); i += 7) {
            int end = Math.min(i + 7, cipherBinary.length());
            String chunk = cipherBinary.substring(i, end);
            cipherText.append((char) binaryToDecimal(chunk));
        }

        System.out.println("Cipher Text: " + cipherText);

        // 🔁 Decryption using same keys
        System.out.println("------------------Decryption Process-----------------");
        System.out.println("Decryption Round 15");
        String df1 = exor(L16, K16);
        String dL15 = exor(df1, R16);
        String dR15 = L16;
        System.out.println("dL15: " + dL15);
        System.out.println("dR15: " + dR15);
        
        System.out.println("Decryption Round 14");
        String df2 = exor(dL15, K15);
        String dL14 = exor(df2, dR15);
        String dR14 = dL15;
        System.out.println("dL14: " + dL14);
        System.out.println("dR14: " + dR14);
        
        System.out.println("Decryption Round 13");
        String df3 = exor(dL14, K14);
        String dL13 = exor(df3, dR14);
        String dR13 = dL14;
        System.out.println("dL13: " + dL13);
        System.out.println("dR13: " + dR13);
        
        System.out.println("Decryption Round 12");
        String df4 = exor(dL13, K13);
        String dL12 = exor(df4, dR13);
        String dR12 = dL13;
        System.out.println("dL12: " + dL12);
        System.out.println("dR12: " + dR12);
        
        System.out.println("Decryption Round 11");
        String df5 = exor(dL12, K12);
        String dL11 = exor(df5, dR12);
        String dR11 = dL12;
        System.out.println("dL11: " + dL11);
        System.out.println("dR11: " + dR11);
        
        System.out.println("Decryption Round 10");
        String df6 = exor(dL11, K11);
        String dL10 = exor(df6, dR11);
        String dR10 = dL11;
        System.out.println("dL10: " + dL10);
        System.out.println("dR10: " + dR10);
        
        System.out.println("Decryption Round 9");
        String df7 = exor(dL10, K10);
        String dL9 = exor(df7, dR10);
        String dR9 = dL10;
        System.out.println("dL9: " + dL9);
        System.out.println("dR9: " + dR9);
        
        System.out.println("Decryption Round 8");
        String df8 = exor(dL9, K9);
        String dL8 = exor(df8, dR9);
        String dR8 = dL9;
        System.out.println("dL8: " + dL8);
        System.out.println("dR8: " + dR8);
        
        System.out.println("Decryption Round 7");
        String df9 = exor(dL8, K8);
        String dL7 = exor(df9, dR8);
        String dR7 = dL8;
        System.out.println("dL7: " + dL7);
        System.out.println("dR7: " + dR7);
        
        System.out.println("Decryption Round 6");
        String df10 = exor(dL7, K7);
        String dL6 = exor(df10, dR7);
        String dR6 = dL7;
        System.out.println("dL6: " + dL6);
        System.out.println("dR6: " + dR6);
        
        System.out.println("Decryption Round 5");
        String df11 = exor(dL6, K6);
        String dL5 = exor(df11, dR6);
        String dR5 = dL6;
        System.out.println("dL5: " + dL5);
        System.out.println("dR5: " + dR5);
        
        System.out.println("Decryption Round 4");
        String df12 = exor(dL5, K5);
        String dL4 = exor(df12, dR5);
        String dR4 = dL5;
        System.out.println("dL4: " + dL4);
        System.out.println("dR4: " + dR4);
        
        System.out.println("Decryption Round 3");
        String df13 = exor(dL4, K4);
        String dL3 = exor(df13, dR4);
        String dR3 = dL4;
        System.out.println("dL3: " + dL3);
        System.out.println("dR3: " + dR3);
        
        System.out.println("Decryption Round 2");
        String df14 = exor(dL3, K3);
        String dL2 = exor(df14, dR3);
        String dR2 = dL3;
        System.out.println("dL2: " + dL2);
        System.out.println("dR2: " + dR2);
        
        System.out.println("Decryption Round 1");
        String df15 = exor(dL2, K2);
        String dL1 = exor(df15, dR2);
        String dR1 = dL2;
        System.out.println("dL1: " + dL1);
        System.out.println("dR1: " + dR1);
        
        System.out.println("Decryption Round 0");
        String df16 = exor(dL1, K1);
        String dL0 = exor(df16, dR1);
        String dR0 = dL1;
        System.out.println("dL0: " + dL0);
        System.out.println("dR0: " + dR0);
        
        
        // Combine final L0 and R0 to get decrypted plaintext
        String decryptedBinary = dL0 + dR0;
        System.out.println("Decrypted Binary: " + decryptedBinary);
        
        
        // Convert binary to characters
        StringBuilder recoveredText = new StringBuilder();
        for (int i = 0; i < decryptedBinary.length(); i += 8) {
            int end = Math.min(i + 8, decryptedBinary.length());
            String byteStr = decryptedBinary.substring(i, end);
            recoveredText.append((char) binaryToDecimal(byteStr));
        }
        
        System.out.println("Retrieved Plain Text is: " + recoveredText);
        sc.close();
    }
}
