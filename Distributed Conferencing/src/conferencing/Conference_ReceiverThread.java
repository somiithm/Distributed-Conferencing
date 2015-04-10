/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conferencing;

import java.io.IOException;
import java.io.ObjectInputStream;
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
        ObjectInputStream ois;
        ObjectOutputStream oos;
	public Conference_ReceiverThread(Conference_Manager conf)
	{
		this.conf = conf;
		this.counter = 0;                
	}
	
	private void handleMessage(Socket sock) throws IOException
	{
//		DataOutputStream dw = new DataOutputStream(sock.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		//read the message
		String message = ois.readUTF();
		System.out.println("Received :"+message);
		String name;
		switch(message.charAt(0))
		{
			case 'A':
				this.counter++;
				// acknowledgement received join
				// send the list of peers to the new guy
//				oos.writeInt(conf.port);
//				oos.flush();
				oos.writeObject(conf.peers);
				oos.flush();
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
				conf.update_peers_list();
				break;
                        case 'M':
                            //display message
                            String msg = message.substring(1);
                            conf.ui.conf_text_area.append(msg+"\n");
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
