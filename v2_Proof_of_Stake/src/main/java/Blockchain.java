import com.google.common.hash.Hashing;
import org.json.JSONArray;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Blockchain {
    private static final Object mutex = new Object();

    private Random rand = new Random();
    ArrayList<Block> chain;
    ArrayList<Transaction> pendingTransactions;
    ArrayList<Block> tempBlocks;
    public BlockingQueue<Staker> validators;
    int difficulty;
    int miningReward;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(this.createGenesisBlock());
        this.pendingTransactions = new ArrayList<>();
        this.tempBlocks = new ArrayList<>();
        this.validators = new LinkedBlockingQueue<>();
        this.difficulty = 3;
        this.miningReward = 100;
    }

    public Block createGenesisBlock() {
        return new Block(0, new ArrayList<>(), new Timestamp(System.currentTimeMillis()));
    }

    public Block generateNextBlock(Staker validator)  {
        if(!this.isChainValid()){
            validator.stake -= 10; // tampering detected, staker loses stake
            return null;
        }

        Block prevBlock = getLatestBlock();

        Block block = new Block(prevBlock.index + 1, new ArrayList<>(),new Timestamp(System.currentTimeMillis()), prevBlock.hash);
        block.setValidatorAddress(validator.getAddress());

        if(!this.isChainValid()){
            validator.stake -= 10; // tampering detected, staker loses stake
            return null;
        }

        this.chain.add(block);
        return block;
    }

    public Block getLatestBlock() {
        // Blockchain guaranteed to have a minimum size of 1 containing the genesis block
        return chain.get(this.chain.size() - 1);
    }

    public void addValidator(String address, int stake){
        this.validators.add(new Staker(address, stake));
    }

    public void proofOfStake(){
        for(Block b : tempBlocks) {
            pickWinner(b);
        }
        tempBlocks.clear();
    }

    private Staker pickWinner(Block block){
        ArrayList<Staker> lotteryPool = new ArrayList<>();
        int totalStake = 0;

        for(Staker v : this.validators){
            if(v.stake >= 10){
                lotteryPool.add(v);
                totalStake += v.getStake();
            }
        }

        if(lotteryPool.isEmpty()){
            System.out.println("No validators in the lottery pool!");
            return null;
        }

        int lotteryRoll = rand.nextInt(totalStake);
        int selector = 0;

        for(Staker v : this.validators){
            if(v.stake >= 10){
                selector += v.getStake();
                if(lotteryRoll < selector){
                    return v;
                }
            }
        }

        System.out.println("Failed to select a winner for validation lottery!");
        return null;

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
//      if(transaction.verifySignature() && transaction.isValid(transaction.amount)) {
            this.pendingTransactions.add(transaction);
            System.out.println("Added transaction to pending transactions.");
//      }
//       else
           System.out.println("Failed to add transaction.");
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
