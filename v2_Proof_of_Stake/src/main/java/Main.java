import java.security.KeyPair;
import java.util.Base64;
import java.util.concurrent.*;
import java.io.*;

public class Main {

    public static BlockingQueue<Blockchain> bcServer;

    public static void main(String[] args) {
//        Block block = new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()));
//        block.mineBlock(5);
        
//        bcServer = new LinkedBlockingQueue<Blockchain>();

        // NOTE: might be better to move server code to Main.java
      //  Server server = new Server(9000);

      //  handleConn(server);

//        Blockchain blockchain = new Blockchain();
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
//
//        boolean isValid = SignatureUtil.verify(SignatureUtil.toBase64String(keyPair.getPublic().getEncoded()), "test", SignatureUtil.toBase64String(output));
//        System.out.println(isValid);

//        Wallet w = new Wallet(100);
//
//        Blockchain blockchain = new Blockchain();
//        Transaction t = new Transaction(w.getPublicKey(), "def456", 10);
//        Transaction.signTransaction(t, w);
//        t.amount = 100; // malicious change
//        System.out.println(t.isValid());


    }

    private static void handleConn(Server s) {

        String line = "";

        while(true) {
            try{

                s.socket = s.server.accept();
                System.out.print("Enter detail about transaction: ");
                // takes input from the client socket
                s.in = new DataInputStream(
                    new BufferedInputStream(s.socket.getInputStream()));
 
                try
                {
                    line = s.in.readUTF();

                    if(line.equals("Over")){
                        break;
                    }

                    String [] data = line.split("\\s+");
                    if(data.length < 3){
                        System.out.print("Wrong input");
                        continue;
                    }
                    String from = data[0];
                    String to = data[1];
                    String amount = data[2];

                    // TODO: Create a new block, etc.
                    System.out.println(line);

                    // TODO: Add new blockchain to BlockingQueue
                    // bcServer.add(newBlockchain)
                    
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }

            } catch(IOException i) {
                
                System.out.println(i);
            }
            
        }

        // close connection
        try{
            s.socket.close();
            s.in.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
}
