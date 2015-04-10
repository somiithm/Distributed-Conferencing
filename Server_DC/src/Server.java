import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    public Map<String,Integer> conf_list;
    public int Conf_ID = 10000;
    public static int portNumber = 8888;
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
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        //BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String input = ois.readUTF();
        System.out.println(input + ";");
        switch(input.charAt(0))
        {
            case 'N':           // new nickname
                String nick = input.substring(1);
                if(this.mapping.containsKey(nick))
                {
                    // username already used
                    oos.writeUTF("N");
                    oos.flush();                    
                }
                else
                {
                    // add the username
                    this.mapping.put(nick, clientSocket.getInetAddress());
                    oos.writeUTF("A");
                    oos.flush();
                    oos.writeObject(this.mapping);
                    oos.flush();
                    oos.writeObject(this.conf_list);
                    oos.flush();
                    //sending global list of users
                }
                break;
            case 'C':       //Create Conference
                
                String ConfName = input.substring(1);
                conf_list.put(ConfName, this.Conf_ID);                
                String num = Integer.toString(Conf_ID);               
                oos.writeInt(this.Conf_ID);
                System.out.println(num);
                oos.flush();
                this.Conf_ID++;
                break;
            case 'J':
                int id = (int)conf_list.get(input.substring(1));
                oos.writeUTF(Integer.toString(id));
                oos.flush();
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