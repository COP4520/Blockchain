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
        this.difficulty = 3;
        this.miningReward = 100;
    }

    public Block createGenesisBlock() {
        return new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()), "0");
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
        // TODO ensure transaction is valid and the balance of wallet before accepting transaction

        this.pendingTransactions.add(transaction);
        System.out.println("Added transaction to pending transactions.");
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
