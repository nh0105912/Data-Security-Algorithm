import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES_Encryption {
    public static void main(String[] args){

        try{

            KeyGenerator kg = KeyGenerator.getInstance("DES");
            SecretKey myDESkey = kg.generateKey();

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, myDESkey);
            System.out.println();
            System.out.print("Enter the PT  :  ");
            Scanner sc = new Scanner(System.in);
            String PT =sc.nextLine();
            byte[] text = PT.getBytes();
            System.out.println("Text in bytes form : " +text);
            System.out.println("Text form : " + new String(text));

            byte[] textEnc =cipher.doFinal(text);
            System.out.println("Encrypt Text in bytes form : " +textEnc);
            System.out.println("Encrypt Text  form : " + new String(textEnc));

            System.out.println("-----------Decryption Mode-----------");

            cipher.init(Cipher.DECRYPT_MODE, myDESkey);
            byte[] textDec =cipher.doFinal(textEnc);
            System.out.println("Decrypt Text in bytes form : " + textDec);
            System.out.println("Decrypt Text  form : " + new String(textDec));

            sc.close();

        }catch(Exception e ){

        }

    }
}