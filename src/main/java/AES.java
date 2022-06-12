import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class AES {

    public static void aes(String args[]) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        String keyFile = "key";
        SecretKey key;
        try {
            byte[] keyb = Files.readAllBytes(Paths.get(keyFile));
            key = new SecretKeySpec(keyb, "AES");
        } catch (NoSuchFileException e) {
            try (FileOutputStream out = new FileOutputStream(keyFile)) {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                key = kgen.generateKey();
                byte[] keyb = key.getEncoded();
                out.write(keyb);
            }
        }
        String text = Utils.readFile(args[1]);

        String solution = "";
        if (args[2].equals("1")) {
            solution = encrypt(text, key);
        } else if (args[2].equals("0")) {
            solution = decrypt(text, key);
        }
        System.out.println(solution);
        Utils.createFile(args[3],solution);
    }

    public static String encrypt(String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, SecretKey key
    ) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
