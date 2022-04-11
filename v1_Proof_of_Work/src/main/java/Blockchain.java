import java.sql.Timestamp;
import java.util.ArrayList;

public class Blockchain {
    ArrayList<Block> chain;
    ArrayList<Transaction> pendingTransactions;
    int difficulty;
    int miningReward;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.chain.add(this.createGenesisBlock());
        this.pendingTransactions = new ArrayList<>();
        this.difficulty = 1;
        this.miningReward = 100;
    }

    public Block createGenesisBlock() {
        return new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()), "0");
    }

    public Block getLatestBlock() {
        // Blockchain guaranteed to have a minimum size of 1 containing the genesis block
        return chain.get(this.chain.size() - 1);
    }

    public void minePendingTransactions(String rewardAddress) {
        Transaction reward = new Transaction(null, rewardAddress, this.miningReward);
        this.pendingTransactions.add(reward);

        Block block = new Block(this.pendingTransactions, new Timestamp(System.currentTimeMillis()), this.getLatestBlock().hash);
        block.mineBlock(this.difficulty);

        this.chain.add(block);
        this.pendingTransactions.clear();
    }

    public void addTransaction(Transaction transaction) {
        if(transaction.isValid()){
            this.pendingTransactions.add(transaction);
            System.out.println("Added transaction to pending transactions.");
        }
        else{
            System.out.println("Failed to add transaction (invalid).");
        }
    }

    public boolean isChainValid() {
        for(int i = 1; i < chain.size(); i++) {
            Block currentBlock = this.chain.get(i);
            Block previousBlock = this.chain.get(i-1);
            String cHash = currentBlock.calculateHash();
            if(!currentBlock.hash.equals(currentBlock.calculateHash()))
                return false;
            if(!previousBlock.hash.equals(previousBlock.calculateHash()))
                return false;
        }
        return true;
    }
}
