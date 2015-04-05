/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast_t;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Ashutosh
 */
public class multicast_r
{
	public static void main(String args[]) throws IOException
	{
		MulticastSocket socket = new MulticastSocket(4446);
		InetAddress group = InetAddress.getByName("230.0.0.1");
		socket.joinGroup(group);

		DatagramPacket packet;
		System.out.println("Receiver Started!!");
		for (int i = 0; i < 5; i++)
		{
			byte[] buff = new byte[256];
			packet = new DatagramPacket(buff,buff.length);
			socket.receive(packet);
			String received = new String(packet.getData());
			System.out.println("Quote of the Moment: " + received);
			
			String buf = "Got your message transmitter";
			packet = new DatagramPacket(buf.getBytes(), buf.getBytes().length);
			socket.send(packet);
			

		}
		socket.leaveGroup(group);
		socket.close();
	}
}
