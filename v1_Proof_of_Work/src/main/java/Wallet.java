import java.security.KeyPair;

public class Wallet {
    int balance;
    KeyPair keyPair;

    public Wallet(int initialBalance){
        this.balance = initialBalance;
        this.keyPair = SignatureUtil.generateKeyPair();
    }

    public String sign(String dataHash){
        byte[] signature = SignatureUtil.sign(SignatureUtil.toBase64String(keyPair.getPrivate().getEncoded()), dataHash);
        return SignatureUtil.toBase64String(signature);
    }

    public String getPublicKey(){
        return SignatureUtil.toBase64String(keyPair.getPublic().getEncoded());
    }
}
