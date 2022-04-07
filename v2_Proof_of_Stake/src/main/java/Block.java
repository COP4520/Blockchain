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
    String data;
    int difficulty;
    int nonce;
    int mintBalance;
    String mintAddy;

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
        Block currentBlock = this;

        if (!previousHash.equals(currentBlock.previousHash)) {
            return false;
    }

        return currentBlock.calculateHash().equals(currentBlock.hash);
    }

    public Block(ArrayList<Transaction> transactions, int ind, Timestamp timestamp, String info, int bal, int diff, String addy){
        transactions = transactions;
        timestamp = timestamp;
        index = ind;
        data = info;
        nonce = 0;
        this.hash = calculateHash();
        mintBalance = bal;
        difficulty = diff;
        mintAddy = addy;
    }

    public Block(ArrayList<Transaction> transactions, int ind, Timestamp timestamp, String previousHash, String info, int bal, int diff, String addy){
        this.transactions = transactions;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        nonce = 0;
        this.hash = calculateHash();
        data = info;
        mintBalance = bal;
        difficulty = diff;
        mintAddy = addy;
    }

    public String calculateHash(){
        String json = new JSONArray(this.transactions).toString();
        return Hashing.sha256()
                .hashString(this.previousHash + this.timestamp + this.difficulty + json, StandardCharsets.UTF_8)
                .toString();
    }

    public Block generateBlock(Block prevBlock, String info, String addy, int diff, int mintBal) {
        java.util.Date date = new java.util.Date();

        return new Block(new ArrayList<>(), prevBlock.index+1, new Timestamp(date.getTime()), prevBlock.previousHash, info, mintBal, diff, addy);
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
}
