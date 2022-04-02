import java.net.*;
import java.io.*;
import java.sql.Timestamp;

public class Server {
    
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private int balance = 0;
 
    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
 
            System.out.println("Waiting for a client...");
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    //handle server connection
    public void handleConn(){

       
        /**
         * TODO:
         * Receive a broadcast of the latest blockchain
         * Receive a broadcast of which validator in the network won the latest block
         * Add itself to the overall list of validators
         * Propose a new block
         * 
         */

        // FIXME: Need an infinite loop
        socket = server.accept();

         // takes input from the client socket
         in = new DataInputStream(
            new BufferedInputStream(socket.getInputStream()));

        String line = "";
        
        try
        {
            line = in.readUTF();

        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        
        
        System.out.println("Closing connection");

        // close connection
        socket.close();
        in.close();
    }

}
