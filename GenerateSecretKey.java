
import java.security.SecureRandom;
import java.util.Base64;

public class GenerateSecretKey {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[64]; // 64 bytes = 512 bits
        random.nextBytes(key);

        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Generated 64-byte secret key:");
        System.out.println(base64Key);
    }
}
