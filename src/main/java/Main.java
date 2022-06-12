import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        //für die verschlüsselung mit aes als erstes Argument "aes" angeben.
        //zweites argument: der dateipfad der zu verschlüsselnde Datei. als Beispiel kann man "plain.txt" in diesem projekt verwenden.
        //drittes argument: "1" für verschlüsseln
        //viertes argument: dateipfad für die verschlüsselte datei
        //bsp: aes plain.txt 1 encrypted.txt
        //zum entschlüsseln die dateipfade anpassen und als drittes argument "0" übergeben
        //bsp: aes encrypted.txt 0 decrypted.txt
        if(args[0].equals("aes")){
            AES.aes(args);
        //zum erstellen der drei hashdateien als erstes argument "hash" eingeben und als zweites argument die zu hashende datei.
        //hash plain.txt
        }else if(args[0].equals("hash")){
            Hash.hash(args);
        //zum überprüfen eines hashes an folgende Konvention halten:
        //checkhash plain.txt md5 sha3-256
        //wobei das vierte argument die zu verwendende hashfunktion und das letzte argument die zu überprüfende datei ist.
        } else if(args[0].equals("checkhash")){
            Hash.checkHash(args);
        }
    }
}
