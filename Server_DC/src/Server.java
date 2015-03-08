
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
    
    public static void main(String args[]) throws IOException{
        int portNumber = 8080;
        boolean run = true;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        while(run){
            System.out.println("wait for client");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getInetAddress().toString());           
        }
    }        

    private static void HandShake(Socket clientSocket) throws IOException
    {
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//        bw.write("welcome");
//        bw.flush();
//        bw.close();
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        out.flush();
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        out.writeObject("welcome");
//        try {
//            String message = (String)in.readObject();
//            System.out.println("Nick: " + message);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }    
}
