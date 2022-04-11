import java.net.*;
import java.util.*;
import java.io.*;

// Thread created by server to handle each client
public class WorkingThread extends Thread {
    
    public Server server;
    public Socket socket;
    public DataInputStream in = null;
    public DataOutputStream out = null;

    WorkingThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {

//        System.out.println("Working thread started");
        try {
            // takes input from the client socket
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

            out  = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }

        String line = "";
        while(!line.equals("Over")) {
            try
            {
                line = in.readUTF();

                if(line.equals("Over")){
                    break;
                }

                String [] data = line.split("\\s+");
                if(data.length < 2){
                    System.out.print("Wrong input");
                    continue;
                }
                String address = data[0];
                int stake = Integer.parseInt(data[1]);

                System.out.println("Working thread " + Thread.currentThread().getId() + " with stake = " + stake);

                if(stake<10) {
                    out.writeUTF("Request ignored: Stake must be at least 10");
                }
                else {
                    out.writeUTF("Stake " + stake + " added to validator list");
                    server.blockchain.addValidator(address, stake);
                }
                
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
    }
}
