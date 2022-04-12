import java.util.ArrayList;


public class BeaconChain {
    public static int counter = 0;
    ArrayList<Shard> shards;
    Shard one = new Shard();
    Shard two = new Shard();
    Shard three = new Shard();
    Blockchain blockchain = new Blockchain();

    public BeaconChain(){
        this.shards = new ArrayList<>();

        for (int t=0; t<9; t++){
            Block current = blockchain.createGenesisBlock();
            separateInShard(current);
        }

        one.start();
        two.start();
        three.start();
    }

    public void separateInShard(Block block){

            if (counter<3) one.createChain(block);
            else if(counter<6) two.createChain(block);
            else three.createChain(block);

    }
}
