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
        this.signature = generateSignature();
    }

    public boolean isValid(int balance) {
        if(balance == amount && verifiySignature())
            return true;
        else
            return false;
    }

    public void generateSignature() {
        String data = StringUtil.getStringFromKey(fromAddress) + StringUtil.getStringFromKey(toAddress) + Float.toString(amount)	;
        signature = StringUtil.applyECDSASig(timestamp, data);
    }

    // to verify the data we signed hasnt been tampered with
    public boolean verifiySignature() {
        String data = StringUtil.getStringFromKey(fromAddress) + StringUtil.getStringFromKey(toAddress) + Float.toString(amount)	;

        return StringUtil.verifyECDSASig(fromAddress, data, signature);
    }

    // TODO: Done (but needs to be looked over): Add transaction signing with public/private keys
}
