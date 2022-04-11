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
                System.out.println("Client " + " connected");

                connected = true;
    
                // sends output to the socket
                out = new DataOutputStream(socket.getOutputStream());
                input  = new DataInputStream(socket.getInputStream());

                while(true) {
                    // Generate random stake ranging from 0 to 29
                    int stake = (int)(Math.random()*30);

                    out.writeUTF(address + " " + Integer.toString(stake));
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String line = input.readUTF();
                    if(line.length()>0) {
                        System.out.println(line);
                    }
                }

//                out.writeUTF("Over");

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
