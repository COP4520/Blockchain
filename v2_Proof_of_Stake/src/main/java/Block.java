import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Block {
    int index;
    ArrayList<Transaction> transactions;
    Timestamp timestamp;
    String previousHash;
    String hash;

    String validatorAddress;
    int bpm;
    int nonce;

    /*//how a winning validator is chosen
    // WORK IN PROGRESS
    public static class pickWinner implements Runnable {

        @Override
        public synchronized void run() {
            public void lock() {
                Thread current = Thread.currentThread();
                while (!cas.compareAndSet(null, current)) {
                }
            }
            public void unlock() {
                Thread current = Thread.currentThread();
                cas.compareAndSet(current, null);
            }
        }
    }*/

    // checking validity of the block
    public Boolean isBlockValid() {
        return this.calculateHash().equals(this.hash);
    }

    public Block(int index, ArrayList<Transaction> transactions, Timestamp timestamp){
        this.index = index;
        this.transactions = transactions;
        this.timestamp = timestamp;
        this.nonce = 0;
        this.hash = calculateHash();
    }

    public Block(int index, ArrayList<Transaction> transactions,Timestamp timestamp, String previousHash){
        this.index = index;
        this.transactions = transactions;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        nonce = 0;
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String json = new JSONArray(this.transactions).toString();
        return Hashing.sha256()
                .hashString(this.previousHash + this.timestamp + json, StandardCharsets.UTF_8)
                .toString();
    }

    public void mineBlock(int difficulty) {
        long start = System.nanoTime();

        while (!this.hash.substring(0, difficulty).equals(StringUtils.leftPad("", difficulty, "0"))) {
            this.nonce++;
            this.hash = this.calculateHash();
        }

        long end = System.nanoTime();
        double ms = ((double)end-start) / 1000000;
        System.out.printf("Block Mined in %.2fms with mining hash %s%n", ms, this.hash);
    }

    public String getPrevHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public int getIndex() { return index; }

    public String getValidatorAddress() {
        return validatorAddress;
    }

    public void setValidatorAddress(String validatorAddress) {
        this.validatorAddress = validatorAddress;
    }
}
