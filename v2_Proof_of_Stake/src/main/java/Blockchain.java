import com.google.common.hash.Hashing;
import org.json.JSONArray;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Blockchain {
    static final int BLOCK_GENERATION_INTERVAL = 10;
    static final int DIFFICULTY_ADJUSTMENT_INTERVAL = 10;

    ArrayList<Block> chain;
    ArrayList<Transaction> pendingTransactions;
    int difficulty;
    int miningReward;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(this.createGenesisBlock());
        this.pendingTransactions = new ArrayList<>();
        this.difficulty = 3;
        this.miningReward = 100;
    }

    public Block createGenesisBlock() {
        return new Block(new ArrayList<>(), 0, new Timestamp(System.currentTimeMillis()), "0", 10, 100, "1");
    }

    public Block generateNextBlock(String info)  {
        Block prevBlock = getLatestBlock();
        java.util.Date date = new java.util.Date();
        Timestamp nextTime = new Timestamp(date.getTime());

        return new Block(new ArrayList<>(), prevBlock.index+1, nextTime, prevBlock.hash, info, 100, 10, "Addy"+1);
    }

    public Block getLatestBlock() {
        // Blockchain guaranteed to have a minimum size of 1 containing the genesis block
        return chain.get(this.chain.size() - 1);
    }

    public Block addAndValidateBlock(Block curr) {
        // check prev hash, add if it is valid
        Block temp = curr;
        boolean found = false;

        for(int i = chain.size()-1; i >= 0; i--) {
            Block b = chain.get(i);

            if(b.getHash().equals(temp.getPrevHash())) {
                temp = b;
                found = true;
            }
        }

        if(found)
            this.chain.add(curr);
        else
            throw new RuntimeException("Block is invalid.");

        return temp;
    }

    public String calculateHash(ArrayList<Transaction> transactions, String prevHash, Timestamp ts, int diff){
        String json = new JSONArray(transactions).toString();
        return Hashing.sha256()
                .hashString(prevHash + ts.getTime() + diff + json, StandardCharsets.UTF_8)
                .toString();
    }

  /*  public Block findBlock(ArrayList<Transaction> info, String prevHash, int diff) {
        int pastTime = 0;

        while (true) {
            long timestamp = new java.util.Date().getTime();
            if(pastTime == timestamp) {
                String hash = calculateHash(info, prevHash, new Timestamp(timestamp), diff);
                // TODO: add validation for stake
                return new Block(info, timestamp, )
            }
        }
    } */

    public int getDifficulty() {
        Block curr = getLatestBlock();

        if(curr.index % DIFFICULTY_ADJUSTMENT_INTERVAL == 0 && curr.index != 0) {
            return getAdjustedDifficulty(); }
        else
            return curr.difficulty;
    }

    public int getAdjustedDifficulty() {
        // TODO: need to finish this

        return 0;
    }

    public void minePendingTransactions(String rewardAddress) {
        Transaction reward = new Transaction(null, rewardAddress, this.miningReward);
        this.pendingTransactions.add(reward);
// TODO: UPDATE FOR POS
        //Block block = new Block(this.pendingTransactions, new Timestamp(System.currentTimeMillis()), this.getLatestBlock().hash);
      //  block.mineBlock(this.difficulty);

       // this.chain.add(block);
        this.pendingTransactions.clear();
    }

    public void addTransaction(Transaction transaction) {
        // TODO ensure transaction is valid and the balance of wallet before accepting transaction
     //   if(transaction.verifySignature() && transaction.isValid(transaction.amount)) {
            this.pendingTransactions.add(transaction);
           // System.out.println("Added transaction to pending transactions.");
      //  }
       // else
       //     System.out.println("Failed to add transaction.");
    }

    public boolean isChainValid() {
        for(int i = 1; i < chain.size(); i++) {
            Block currentBlock = this.chain.get(i);
            Block previousBlock = this.chain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash()))
                return false;
            if(!previousBlock.hash.equals(previousBlock.calculateHash()))
                return false;
        }

        return true;
    }
}