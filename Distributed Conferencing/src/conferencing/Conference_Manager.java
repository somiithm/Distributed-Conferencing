package conferencing;

import static conferencing.DC_UI.map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashutosh
 */
public class Conference_Manager 
{
    Conference_Panel ui;
    DC_UI Main;
	Map<String,Inet4Address> map;
	Map<String,Inet4Address> peers;
	String name;
	String user;
	int port;
	Conference_ReceiverThread receiver;
	
	
	private void init_components()
	{
		ui = new Conference_Panel();
		System.out.println("Adding a new conference named :"+name);
		this.Main.tabbed_pane.add(name,ui);
		ui.exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				// event handling function
				delete_conference();
			}
		});
                ui.send_btn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Map.Entry<String, Inet4Address> entry : peers.entrySet()) {				
                            String msg = ui.chat_text_area.getText();
                            try {
                                Socket soc = new Socket(entry.getValue(),port);
                                ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
                                Date date = new Date();
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                int hours = cal.get(Calendar.HOUR_OF_DAY);
                                int min = cal.get(Calendar.MINUTE);                                
                                oos.writeUTF("M"+hours+":"+min+":>"+user+": "+msg);
                                oos.flush();                                
                            } catch (IOException ex) {
                                Logger.getLogger(Conference_Manager.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        ui.chat_text_area.setText("");
                    }                    
                });
	

	}
	
	private void send_requests() throws IOException
	{
		//traverse on map and send conf request to all users in the map
		for (Map.Entry<String, Inet4Address> entry : map.entrySet()) {
			// create a socke and send message
			if(entry.getKey() == user)
				continue;
			Socket soc = new Socket((InetAddress) entry.getValue(),DC_UI.req_port);
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeUTF("I"+name+":"+port);
			oos.flush();
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}
	Conference_Manager(DC_UI Main,int port,String user,String name)
	{
		this.Main = Main;
		this.port = port;
		this.name = name;
		this.user = user;
		this.map = new HashMap<String,Inet4Address>();
		this.peers = new HashMap<String,Inet4Address>();
		init_components();
		this.receiver = new Conference_ReceiverThread(this);
		this.receiver.start();
		// Initialize the list of sockets
	}
	
	Conference_Manager(DC_UI Main,int port,String user,String name,Map<String,Inet4Address> map) throws IOException
	{
		this.Main = Main;
		this.port = port;
		this.map = map;
		this.peers = new HashMap<String,Inet4Address>();
		this.name = name;
		this.user = user;
		init_components();
		// add self to the peers list as initialization
		peers.put(user,map.get(user));
		this.receiver = new Conference_ReceiverThread(this);
		this.receiver.start();
		send_requests();
	}
	
	// function that updates the UI
	public void update_peers_list()
	{
		DefaultListModel model = new DefaultListModel();
		int i = 0;
		for (Map.Entry<String, Inet4Address> entry : this.peers.entrySet()) {
			model.addElement(entry.getKey());
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		this.ui.peer_list.setModel(model);
	}
	public void leave()
	{
		
	}
	public void delete_conference()
	{
		// ask wanna really exit
		if (JOptionPane.showConfirmDialog(null, "Do you really wanna quit this Conference?", "Exit Conference",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
		{
			// yes option
			Main.tabbed_pane.remove(ui);
			Main.remove_conference(this);
		}
		else 
		{
			// no option
		}
	}
	

}
