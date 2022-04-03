import java.net.*;
import java.io.*;

public class Server {
    
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
 
    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try{
            server = new ServerSocket(port);
            
            System.out.println("Server started");
            System.out.println("Waiting for a client...");

            socket = server.accept();

        }catch(IOException i){

            System.out.println(i);
        }
        
        String line = "";

        while(true) {
            try{

                System.out.print("Enter detail about transaction: ");
                // takes input from the client socket
                in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
 
                try
                {
                    line = in.readUTF();
                    if(line.equals("Over")){
                        break;
                    }
                    // TODO: Create a new block, etc.
                    System.out.println(line);
                    
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }

            } catch(IOException i) {
                
                System.out.println(i);
            }
            
        }

        // close connection
        try{
            socket.close();
            in.close();
        } catch(IOException i) {
            System.out.println(i);
        }
        
    }

}
