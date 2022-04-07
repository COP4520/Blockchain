import java.sql.Timestamp;
import java.security.*;

public class Transaction {
    public String transactionID;
    String fromAddress;
    String toAddress;
    int amount;
    Timestamp timestamp;
    byte[] signature;

    public Transaction(String fromAddress, String toAddress, int amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        //generateSignature();
    }
/* //TODO: these need to be fixed. Look into Private and PublicKey
    public boolean isValid(int balance) {
        return balance == amount && verifySignature();
    }*/

/*
    public void generateSignature() {
        String data = StringUtil.getStringFromKey(fromAddress) + StringUtil.getStringFromKey(toAddress) + Float.toString(amount)	;
        signature = StringUtil.applyECDSASig(toAddress, data);
    }

    // to verify the data we signed hasnt been tampered with
    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(fromAddress) + StringUtil.getStringFromKey(toAddress) + Float.toString(amount)	;

        return StringUtil.verifyECDSASig(fromAddress, data, signature);
    } */

    // TODO: finished adding transaction signing with public/private keys, just needs to be reviewed
}
