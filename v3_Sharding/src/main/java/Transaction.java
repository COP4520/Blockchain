import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

public class Transaction {
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
}
