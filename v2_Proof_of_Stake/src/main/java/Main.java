import java.security.KeyPair;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
//        Block block = new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()));
//        block.mineBlock(5);
        

        // NO OUTPUT EVEN FOR STDOUT..
        System.out.println("hello!");

        // Set up server, start to accept threads
        Server server = new Server(9000);

        // simulate new clients trying to connect
        for(int i=0; i<10; i++) {
            Client th = new Client(Integer.toString(i), 9000);
            server.clients.add(th);
        }
        for(Client th: server.clients) {
            th.start();
        }  

    //   blockchain = new Blockchain();
//        blockchain.addTransaction(new Transaction("abc123", "def456", 10));
//        blockchain.addTransaction(new Transaction("abc123", "def456", 20));
//        blockchain.addTransaction(new Transaction("abc123", "def456", 30));
//        blockchain.minePendingTransactions("abc123");
//        System.out.print(blockchain.chain);

//        KeyPair keyPair = SignatureUtil.generateKeyPair();
//        String priv = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
//        System.out.println(priv);
//        byte[] output = SignatureUtil.sign(priv, "test");
//        System.out.println(Base64.getEncoder().encodeToString(output));
    }
}
