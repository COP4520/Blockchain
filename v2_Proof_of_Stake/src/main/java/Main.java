import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
//        Block block = new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()));
//        block.mineBlock(5);

        Server server = new Server(9000);

        Blockchain blockchain = new Blockchain();
        blockchain.addTransaction(new Transaction("abc123", "def456", 10));
        blockchain.addTransaction(new Transaction("abc123", "def456", 20));
        blockchain.addTransaction(new Transaction("abc123", "def456", 30));
        blockchain.minePendingTransactions("abc123");

    }
}
