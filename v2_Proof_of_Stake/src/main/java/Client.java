import java.net.*;
import java.util.*;
import java.io.*;

// Each client is represented by a thread
public class Client extends Thread{

    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    public String address;
    public int port;
    
    
    public Client(String address, int port) {
        
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {

        boolean connected = false;
        while(!connected) {

            try{

                Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println(e);
            }


            // establish a connection
            try
            {
                socket = new Socket(address, port);
                System.out.println("Client " + address + " connected");

                connected = true;
    
                // takes input from terminal
                // input  = new DataInputStream(System.in);

                // FIXME: Stake range to be changed
                int stake = (int)(Math.random()*10);
    
                // sends output to the socket
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(address + " " + Integer.toString(stake));
                out.writeUTF("Over");

            }
            catch(UnknownHostException u)
            {
                System.out.println("client " + address + " connecting..");
                // System.out.println(u);
            }
            catch(IOException i)
            {
                System.out.println("client " + address + " connecting..");
                // System.out.println(i);
            }

        }
        

    }
}
