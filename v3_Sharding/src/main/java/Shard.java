import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Shard extends Thread {
    Random header;
    Random signature;
    ArrayList<Block> blockchains;
    Queue<Staker> validators;
    Blockchain shardChain;
    int y=0;

    public Shard(){
        this.header = new Random(System.currentTimeMillis());
        this.signature = new Random(System.currentTimeMillis());
        this.blockchains = new ArrayList<Block>();
        this.validators = new ConcurrentLinkedQueue<>();
        this.shardChain = new Blockchain();
    }

    public void run(){
        shardChain.createGenesisBlock();
        while (y<blockchains.size()){
            shardChain.addAndValidateBlock(blockchains.get(y));
            y++;
        }
        if (y==blockchains.size()) System.out.println("Validation done(:");
    }

    public void createChain(Block block){
        blockchains.add(block);

    }
}
