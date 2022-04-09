public class Client extends Thread{

    public Main TCPServer;
    public String address;
    
    public Client(Main TCPServer, String address) {
        this.TCPServer = TCPServer;
        this.address = address;
    }

    @Override
    public void run() {

        // FIXME: Proper terminate condition
        while(true) {

            try{

                Thread.sleep(1);
            }
            catch(Exception e){
                // System.out.println(e);
                break;
            }

            // FIXME: Stake range to be changed
            int stake = (int)(Math.random()*10);

            TCPServer.blockchain.addValidator(address, stake);

        }
    }
}
