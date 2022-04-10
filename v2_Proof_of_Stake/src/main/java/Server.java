import java.net.*;
import java.util.*;
import java.io.*;

// Accepts client, create separate working threads for each client
public class Server {

    //initialize socket and input stream
    public Socket          socket   = null;
    public ServerSocket    server   = null;
    

    public List<Client> clients;
    public List<WorkingThread> workingThreads;
    public Blockchain blockchain;
 
    // constructor with port
    public Server(int port)
    {
        this.clients = new ArrayList<>();
        this.workingThreads = new ArrayList<>();
        this.blockchain = new Blockchain();
        // starts server and waits for a connection
        try{
            server = new ServerSocket(port);
            
            System.out.println("Server started");
            System.out.println("Waiting for a client...");

        }catch(IOException i){

            System.out.println(i);
        }

        while(true) {

            try {
                socket = server.accept();
                System.out.println("Client accepted");
            }
            catch(IOException i) 
            {
                System.out.println(i);
            }

            handleConn();

        }

        // close connection
        // try{
        //     socket.close();
        //     in.close();
        // } catch(IOException i) {
        //     System.out.println(i);
        // }

    }

    // FIXME: Create a working thread for each client
    public void handleConn() {   
        
        
        WorkingThread th = new WorkingThread(this, socket);

        workingThreads.add(th);

    }

}
