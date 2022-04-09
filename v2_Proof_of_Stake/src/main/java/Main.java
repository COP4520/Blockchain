import java.security.KeyPair;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class Main {

    public List<Client> threads;
    public Blockchain blockchain;

    public static void main(String[] args) {
//        Block block = new Block(new ArrayList<>(), new Timestamp(System.currentTimeMillis()));
//        block.mineBlock(5);
        
//        bcServer = new LinkedBlockingQueue<Blockchain>();

        // NOTE: might be better to move server code to Main.java
      //  Server server = new Server(9000);

      //  handleConn(server);

       Main TCPServer = new Main();
       TCPServer.threads = new ArrayList<>();
       TCPServer.blockchain = new Blockchain();
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

        for(int i=0; i<10; i++) {
            Client th = new Client(TCPServer, Integer.toString(i));
            TCPServer.threads.add(th);
        }

        for(Client th: TCPServer.threads) {
            th.start();
        }

    }

    // FIXME: To be changed/removed
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

                    // TODO: Start a new thread: create a new block, etc.
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
