public class StakingThread implements Runnable{

    Server server;

    public StakingThread(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        while(server.blockchain != null){

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(server.blockchain.validators.size() > 0)
                server.blockchain.proofOfStake();
        }
    }
}
