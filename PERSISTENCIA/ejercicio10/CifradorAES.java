import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.Key;

public class CifradorAES {

    private static final String CLAVE_SECRETA = "EstaEsMiClave16A"; // 16 caracteres = 128 bits

    private static Key getKey() throws Exception {
        return new SecretKeySpec(CLAVE_SECRETA.getBytes("UTF-8"), "AES");
    }

    /**
     * Cifra un texto utilizando AES.
     */
    public static String cifrar(String texto) throws Exception {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] textoCifrado = cipher.doFinal(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(textoCifrado);
    }

    /**
     * Descifra un texto utilizando AES.
     */
    public static String descifrar(String textoCifrado) throws Exception {
        Key key = getKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytesDescifrados = Base64.getDecoder().decode(textoCifrado);
        byte[] textoDescifrado = cipher.doFinal(bytesDescifrados);
        return new String(textoDescifrado, "UTF-8");
    }
}