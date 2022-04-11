import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        Block block = new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()));
//        block.mineBlock(5);

        Wallet w = new Wallet(1000);
        Blockchain blockchain = new Blockchain();

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 3; j++){
                Transaction t = new Transaction(w.getPublicKey(), "def456", 10);
                Transaction.signTransaction(t, w);
                blockchain.addTransaction(t);
            }
            blockchain.minePendingTransactions(w.getPublicKey());
            System.out.println("Blockchain " + (blockchain.isChainValid() ? "is valid." : "is not valid."));
        }
    }
}
