
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Server {
    HashMap mapping;
    
    public static void main(String args[]) throws IOException{
        Server serv = new Server();
        serv.mapping = new HashMap();
        int portNumber = 8888;
        boolean run = true;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        
        /*Printing the Server IP addresses*/
        System.out.println("Printing the IP addresses :");
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                System.out.println(i.getHostAddress());
            }
        }
        
        while(run){
            System.out.println("waiting for client");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Koi aaya!");
            serv.readCommand(clientSocket);
            System.out.println(clientSocket.getInetAddress().toString());           
        }
    }

    private void readCommand(Socket clientSocket) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        
        String input = br.readLine();
        System.out.println(input + ";");
        switch(input.charAt(0))
        {
            case 'N':           // new nickname
                String nick = input.substring(1);
                if(this.mapping.containsKey(nick))
                {
                    // username already used
                    bw.write("NAK\n");
                    bw.flush();                    
                }
                else
                {
                    // add the username
                    this.mapping.put(nick, clientSocket.getInetAddress());
                    bw.write("ACK\n");
                    bw.flush();
                    oos.writeObject(this.mapping);
                    //sending global list of users
                }
                break;
            case 'C':           // change nickname
                
                break;
            case 'R':           // refresh the list
                // Server has to send the whole mapping of users along with the IPs
                
                break;
        }
        
        
        
    }    
}
