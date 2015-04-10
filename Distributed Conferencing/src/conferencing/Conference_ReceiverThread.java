/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conferencing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashutosh
 */
public class Conference_ReceiverThread extends Thread
{
	Conference_Manager conf;
	int counter;
	public Conference_ReceiverThread(Conference_Manager conf)
	{
		this.conf = conf;
		this.counter = 0;
	}
	
	private void handleMessage(Socket sock) throws IOException
	{
		DataOutputStream dw = new DataOutputStream(sock.getOutputStream());
		DataInputStream dr = new DataInputStream(sock.getInputStream());
		ObjectOutputStream ow = new ObjectOutputStream(sock.getOutputStream());
		//read the message
		String message = dr.readUTF();
		System.out.println("Received :"+message);
		String name;
		switch(message.charAt(0))
		{
			case 'A':
				this.counter++;
				// acknowledgement received join
				// send the list of peers to the new guy
				dw.writeInt(conf.port);
				dw.flush();
				ow.writeObject(conf.peers);
				ow.flush();
				break;
			case 'R':
				this.counter++;
				// remove this from the map
				name = message.substring(1);
//				conf.map.remove(name);
				break;
			case 'P':
				// Add peer request
				name = message.substring(1);
				conf.peers.put(name, (Inet4Address) sock.getInetAddress());
				break;
		}
	}
	public void run()
	{
		try 
		{
			// Create a server socket that listens on the port
			ServerSocket serverSocket = new ServerSocket(conf.port);
			// read messages in while 1
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				this.handleMessage(clientSocket);
			}
		}
		catch (IOException ex) {
			Logger.getLogger(Conference_ReceiverThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
