public class StakingThread implements Runnable{

    Blockchain blockhain;

    public StakingThread(Blockchain blockchain){
        this.blockhain = blockchain;
    }

    @Override
    public void run() {
        while(blockhain != null){
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blockhain.proofOfStake();
        }
    }
}
