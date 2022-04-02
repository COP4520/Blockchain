import java.sql.Timestamp;

public class Transaction {

    String fromAddress;
    String toAddress;
    int amount;
    Timestamp timestamp;

    public Transaction(String fromAddress, String toAddress, int amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    // TODO Add transaction signing with public/private keys
}
