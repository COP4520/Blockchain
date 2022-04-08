import com.google.common.hash.Hashing;
import org.json.JSONArray;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.security.*;

public class Transaction {
    public String transactionID;
    String fromAddress;
    String toAddress;
    int amount;
    Timestamp timestamp;
    String signature;

    public Transaction(String fromAddress, String toAddress, int amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        //generateSignature();
    }

    public String calculateHash(){
        return Hashing.sha256()
                .hashString(this.toAddress + this.fromAddress + this.amount + this.timestamp, StandardCharsets.UTF_8)
                .toString();
    }

    public static void signTransaction(Transaction transaction, Wallet senderWaller){
        String transactionHash = transaction.calculateHash();
        transaction.signature = senderWaller.sign(transactionHash);
    }

    public boolean isValid(){
        // When fromAddress is null, we assume it is the mining reward.
        if(this.fromAddress == null) return true;

        if(this.signature == null || this.signature.length() == 0) return false;

        return SignatureUtil.verify(this.fromAddress, this.calculateHash(), this.signature);
    }

/* //TODO: these need to be fixed. Look into Private and PublicKey
    public boolean isValid(int balance) {
        return balance == amount && verifySignature();
    }*/

/*
    public void generateSignature() {
        String data = SignatureUtil.getStringFromKey(fromAddress) + SignatureUtil.getStringFromKey(toAddress) + Float.toString(amount)	;
        signature = SignatureUtil.applyECDSASig(toAddress, data);
    }

    // to verify the data we signed hasnt been tampered with
    public boolean verifySignature() {
        String data = SignatureUtil.getStringFromKey(fromAddress) + SignatureUtil.getStringFromKey(toAddress) + Float.toString(amount)	;

        return SignatureUtil.verifyECDSASig(fromAddress, data, signature);
    } */

    // TODO: finished adding transaction signing with public/private keys, just needs to be reviewed
}
