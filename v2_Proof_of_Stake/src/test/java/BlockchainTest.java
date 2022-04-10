import org.junit.Assert;
import org.junit.Test;

public class BlockchainTest {

    @Test
    public void testSignatureValid(){
        Wallet w = new Wallet(100);

        Blockchain blockchain = new Blockchain();
        Transaction t = new Transaction(w.getPublicKey(), "def456", 10);
        Transaction.signTransaction(t, w);
        Assert.assertTrue(t.isValid());
    }

    @Test
    public void testSignatureInvalid(){
        Wallet w = new Wallet(100);

        Blockchain blockchain = new Blockchain();
        Transaction t = new Transaction(w.getPublicKey(), "def456", 10);
        Transaction.signTransaction(t, w);
        t.amount = 100; // malicious change
        Assert.assertFalse(t.isValid());
    }

    @Test
    public void testMain() {

        int PORT = 28;
        System.out.println("hello!");

        // simulate new clients trying to connect
        for(int i=0; i<3; i++) {
            Client th = new Client(Integer.toString(i), PORT);
            th.start();
        
        }
       
        // Set up server, start to accept threads
        Server server = new Server(PORT);

        
    }

}