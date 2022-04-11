import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignatureUtil {

    public static KeyPair generateKeyPair(){
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
            gen.initialize(ecSpec, new SecureRandom());
            return gen.generateKeyPair();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    //Applies ECDSA Signature and returns the result ( as bytes ).
    public static byte[] sign(String privateKey, String input) {
        Signature dsa;
        byte[] output = new byte[0];
        try {
            dsa = Signature.getInstance("SHA256withECDSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PrivateKey realPrivateKey = keyFactory.generatePrivate(privateKeySpec);
            dsa.initSign(realPrivateKey);
            byte[] strByte = input.getBytes(StandardCharsets.UTF_8);
            dsa.update(strByte);
            output = dsa.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    //Verifies a String signature
    public static boolean verify(String publicKey, String data, String signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("EC");;
            PublicKey realPublicKey = keyFactory.generatePublic(publicKeySpec);
            ecdsaVerify.initVerify(realPublicKey);
            ecdsaVerify.update(data.getBytes());
            byte[] byteSignature = Base64.getDecoder().decode(signature);
            return ecdsaVerify.verify(byteSignature);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toBase64String(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
