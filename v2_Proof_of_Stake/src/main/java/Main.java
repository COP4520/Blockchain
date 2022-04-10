import java.security.KeyPair;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
//        Block block = new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()));
//        block.mineBlock(5);
        

        int PORT = 33;

        if(args.length == 0 || !args[0].equals("--server")) {
            System.out.println("client!");

            // simulate new clients trying to connect
            Client th = new Client("localhost", PORT);
            th.start();


        }
        else {
            // Set up server, start to accept clients
            System.out.println("server!");

            Server server = new Server(PORT);
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
