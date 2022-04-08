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

}