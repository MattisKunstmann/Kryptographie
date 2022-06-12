import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Hash {
    private static String methods[] = {"MD5", "SHA3-256", "RIPEMD160"};

    public static void hash(String[] args) throws NoSuchAlgorithmException, IOException {
        Security.addProvider(new BouncyCastleProvider());
        String text = Utils.readFile(args[1]);
        for (String i : methods) {
            final MessageDigest digest = MessageDigest.getInstance(i);
            final byte[] encodedhash = digest.digest(
                    text.getBytes(StandardCharsets.UTF_8));
            String hashed = bytesToHex(encodedhash);
            Utils.createFile(i, hashed);
        }
    }

    public static void checkHash(String args[]) throws IOException, NoSuchAlgorithmException {
        String text = Utils.readFile(args[1]);
        String hashingMethod = args[2];
        String oldHash = Utils.readFile(args[3]);
        if(hash(text, hashingMethod).equals(oldHash)){
         System.out.println("Der eingelesene Wert stimmt mit dem berechnetem Wert überein.");
        } else {
         System.out.println("Der eingelesene Wert stimmt mit dem berechnetem Wert nicht überein.");
        }

    }

    private static String hash(String text, String hashMethod) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        final MessageDigest digest = MessageDigest.getInstance(hashMethod);
        final byte[] encodedhash = digest.digest(
                text.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);

    }


    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
