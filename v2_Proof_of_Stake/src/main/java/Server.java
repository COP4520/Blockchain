import org.checkerframework.checker.units.qual.A;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    //initialize socket and input stream
    public Socket          socket   = null;
    public ServerSocket    server   = null;
    public DataInputStream in       =  null;
 
    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try{
            server = new ServerSocket(port);
            
            System.out.println("Server started");
            System.out.println("Waiting for a client...");

        }catch(IOException i){

            System.out.println(i);
        }
        
    }

}
