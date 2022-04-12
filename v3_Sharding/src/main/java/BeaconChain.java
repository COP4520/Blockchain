import java.util.ArrayList;


public class BeaconChain {
    public static int counter = 0;
    ArrayList<Shard> shards;
    Shard one = new Shard();
    Shard two = new Shard();
    Shard three = new Shard();
    Blockchain blockchain = new Blockchain();
    //Server server = new Server(33);

    public BeaconChain(){
        this.shards = new ArrayList<>();

        for (counter=0; counter<9; counter++){
            Block current = blockchain.createGenesisBlock();
            separateInShard(current);
        }

        one.start();
        two.start();
        three.start();
    }

    public void separateInShard(Block block){
            if (counter<3) {
                one.createChain(block);
                System.out.printf("separating block %d into shard one\n", counter+1);
            }
            else if(counter<6) {
                two.createChain(block);
                System.out.printf("separating block %d into shard two\n", counter+1);
            }
            else {
                three.createChain(block);
                System.out.printf("separating block %d into shard three\n", counter+1);
            }

    }
}
