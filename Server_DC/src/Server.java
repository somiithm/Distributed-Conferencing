import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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
    public HashMap mapping;
    public HashMap conf_list;
    public int Conf_ID = 1025;
    public static int portNumber = 8000;
    public Server()
    {
        this.conf_list = new HashMap();
        this.mapping = new HashMap();
    }
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        Server serv = new Server();        
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

    private void readCommand(Socket clientSocket) throws IOException, ClassNotFoundException
    {
        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        //BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        
        String input = dis.readUTF();
        System.out.println(input + ";");
        switch(input.charAt(0))
        {
            case 'N':           // new nickname
                String nick = input.substring(1);
                if(this.mapping.containsKey(nick))
                {
                    // username already used
                    dos.writeUTF("N");
                    dos.flush();                    
                }
                else
                {
                    // add the username
                    this.mapping.put(nick, clientSocket.getInetAddress());
                    dos.writeUTF("A");
                    dos.flush();
                    oos.writeObject(this.mapping);
                    oos.flush();
//                    oos.writeObject(this.conf_list);
//                    oos.flush();
                    //sending global list of users
                }
                break;
            case 'C':       //Create Conference
                
                String ConfName = input.substring(1);
                conf_list.put(ConfName, this.Conf_ID);                
                String num = Integer.toString(Conf_ID);               
                dos.writeInt(this.Conf_ID);
                System.out.println(num);
                dos.flush();
                this.Conf_ID++;
                break;
            case 'J':
                int id = (int)conf_list.get(input.substring(1));
                dos.writeUTF(Integer.toString(id));
                dos.flush();
            case 'R':           // refresh the list
                // Server has to send the whole mapping of users along with the IPs                
                oos.writeObject(this.mapping);                
                oos.flush();
//                oos.writeObject(this.conf_list);
//                oos.flush();
                break;             
        }                
        
    }    
}