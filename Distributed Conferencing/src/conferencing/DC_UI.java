/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conferencing;

import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.net.*;

/**
 *
 * @author Administrator
 */
public class DC_UI extends javax.swing.JFrame {

	/*Global Variables*/
	public static Font ui_font;
	public static int req_port = 8000;
//	public String server_ip = "10.138.52.215";
	public String server_ip = "10.132.141.216";
	public int server_port = 8888;
	public String nick_error1;
	public String nick_error2;
	public String nick_error3;
	public String nick_error4;
	static public String nick;
    static public Map<String,Inet4Address> map;
	static public Map<String,Integer> conf_list;
	static public Inet4Address ip;
	public static DC_UI ui;
	/**
	 * Creates new form DC_UI
	 */
	static ArrayList<Conference_Manager> conferences;
	public DC_UI() {
		this.nick_error1 = "* No Nickname given";
		this.nick_error2 = "* Nickname should be within 25 characters";
		this.nick_error3 = "* Nickname should be alphanumeric";
		this.nick_error4 = "* Nickname already taken. Please choose a different username";
		/*Initialize global font here*/
		ui_font = new Font("Tahoma",Font.PLAIN,12);
		initComponents();
		nick_error_label.setText("");
		start_home();
		
		conferences = new ArrayList<Conference_Manager>();
	}
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        head_panel = new javax.swing.JPanel();
        heading_label = new javax.swing.JLabel();
        close_btn = new javax.swing.JButton();
        work_panel = new javax.swing.JPanel();
        tabbed_pane = new javax.swing.JTabbedPane();
        selector_panel = new javax.swing.JPanel();
        refresh_btn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        user_list = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        conference_list = new javax.swing.JList();
        conference_label = new javax.swing.JLabel();
        user_label = new javax.swing.JLabel();
        user_error_label = new javax.swing.JLabel();
        create_group_btn = new javax.swing.JButton();
        conf_error_label = new javax.swing.JLabel();
        join_group_btn = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();
        home_panel = new javax.swing.JPanel();
        nickname_text_field = new javax.swing.JTextField();
        nickname_text_label = new javax.swing.JLabel();
        submit_btn = new javax.swing.JButton();
        nick_error_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Distributed Conferencing Project");
        setUndecorated(true);

        heading_label.setFont(ui_font);
        heading_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        heading_label.setText("Distributed System Project");

        close_btn.setText("X");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout head_panelLayout = new javax.swing.GroupLayout(head_panel);
        head_panel.setLayout(head_panelLayout);
        head_panelLayout.setHorizontalGroup(
            head_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(head_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(heading_label, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(close_btn)
                .addContainerGap())
        );
        head_panelLayout.setVerticalGroup(
            head_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(head_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(head_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(close_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(heading_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        work_panel.setPreferredSize(new java.awt.Dimension(495, 500));

        tabbed_pane.setToolTipText("");

        refresh_btn.setFont(DC_UI.ui_font);
        refresh_btn.setText("Refresh");
        refresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_btnActionPerformed(evt);
            }
        });

        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        user_list.setFont(DC_UI.ui_font);
        user_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(user_list);

        conference_list.setFont(DC_UI.ui_font);
        conference_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        conference_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(conference_list);

        conference_label.setFont(DC_UI.ui_font);
        conference_label.setText("Conferences:");

        user_label.setFont(DC_UI.ui_font);
        user_label.setText("Users:");

        user_error_label.setFont(DC_UI.ui_font);
        user_error_label.setForeground(new java.awt.Color(255, 0, 0));
        user_error_label.setText("*");

        create_group_btn.setFont(DC_UI.ui_font);
        create_group_btn.setText("Create Conference");
        create_group_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_group_btnActionPerformed(evt);
            }
        });

        conf_error_label.setFont(DC_UI.ui_font);
        conf_error_label.setForeground(new java.awt.Color(255, 0, 0));
        conf_error_label.setText("*");

        join_group_btn.setFont(DC_UI.ui_font);
        join_group_btn.setText("Join Conference");

        javax.swing.GroupLayout selector_panelLayout = new javax.swing.GroupLayout(selector_panel);
        selector_panel.setLayout(selector_panelLayout);
        selector_panelLayout.setHorizontalGroup(
            selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selector_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selector_panelLayout.createSequentialGroup()
                        .addComponent(refresh_btn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(selector_panelLayout.createSequentialGroup()
                        .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(conference_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(conf_error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(selector_panelLayout.createSequentialGroup()
                                .addGap(0, 97, Short.MAX_VALUE)
                                .addComponent(join_group_btn))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(selector_panelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(user_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selector_panelLayout.createSequentialGroup()
                                        .addGap(0, 81, Short.MAX_VALUE)
                                        .addComponent(create_group_btn))))
                            .addGroup(selector_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(user_error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        selector_panelLayout.setVerticalGroup(
            selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selector_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refresh_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separator, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(selector_panelLayout.createSequentialGroup()
                        .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(conference_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(user_label, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(selector_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(selector_panelLayout.createSequentialGroup()
                                .addComponent(user_error_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(create_group_btn))
                            .addGroup(selector_panelLayout.createSequentialGroup()
                                .addComponent(conf_error_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(join_group_btn)))
                        .addContainerGap())))
        );

        tabbed_pane.addTab("Select", selector_panel);

        back_btn.setFont(DC_UI.ui_font);
        back_btn.setText("Back");

        javax.swing.GroupLayout work_panelLayout = new javax.swing.GroupLayout(work_panel);
        work_panel.setLayout(work_panelLayout);
        work_panelLayout.setHorizontalGroup(
            work_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(work_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(work_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(back_btn)
                    .addComponent(tabbed_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        work_panelLayout.setVerticalGroup(
            work_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, work_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbed_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
        );

        nickname_text_label.setFont(ui_font);
        nickname_text_label.setText("Nickname");

        submit_btn.setFont(ui_font);
        submit_btn.setText("Submit");
        submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_btnActionPerformed(evt);
            }
        });

        nick_error_label.setFont(ui_font);
        nick_error_label.setForeground(new java.awt.Color(255, 0, 0));
        nick_error_label.setText("* Error");

        javax.swing.GroupLayout home_panelLayout = new javax.swing.GroupLayout(home_panel);
        home_panel.setLayout(home_panelLayout);
        home_panelLayout.setHorizontalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, home_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nickname_text_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nick_error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nickname_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(home_panelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(138, 138, 138))
        );
        home_panelLayout.setVerticalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nickname_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nickname_text_label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nick_error_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submit_btn)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(head_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(work_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
            .addComponent(home_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(head_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(work_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	
	private void start_home()
	{
		this.home_panel.setVisible(true);
		this.work_panel.setVisible(false);
		this.pack();
	}
	private void start_work()
	{
		this.home_panel.setVisible(false);                
		this.work_panel.setVisible(true);
		this.pack();
	}
	private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
		System.exit(0);
	}//GEN-LAST:event_close_btnActionPerformed

	private void submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_btnActionPerformed

		nick = this.nickname_text_field.getText();
		Socket soc;
		// testing
		
		//System.out.println("Nick : " + nick + ";");
		if(nick.isEmpty())
		{
			//System.out.println("No NickName given!");
			this.nick_error_label.setText(this.nick_error1);
			return;
		}
		else if(nick.length() > 25)
		{
			//nickname should be less than 25 characters
			this.nick_error_label.setText(this.nick_error2);
			return;
		}
		else if(!nick.matches("[A-Za-z0-9]+"))
		{
			//nickname should be alpha numeric
			this.nick_error_label.setText(this.nick_error3);
			return;
		}
		try 
		{
			soc = new Socket(server_ip,server_port);
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			oos.writeUTF("N" + nick);
			oos.flush();
			String line = ois.readUTF();
			System.out.println("Line is :"+line);
			if(line.equals("N"))
			{
				// Reject
//				this.nick_error_label.setText(this.nick_error4);
				JOptionPane.showMessageDialog(this, "Choose a different username", "Username already taken", JOptionPane.ERROR_MESSAGE);
				this.nickname_text_field.setText("");
				return;
			}
			else
			{
				// Accepted
				this.nick_error_label.setText("");

//				map = new HashMap<String,Inet4Address>();
				map = (Map<String, Inet4Address>) ois.readObject();
				conf_list = (Map<String,Integer>) ois.readObject();
				DefaultListModel model = new DefaultListModel();                             


				int i = 0;
				for (Map.Entry<String, Inet4Address> entry : map.entrySet()) {
					if(!nick.equals(entry.getKey()))
						model.addElement(entry.getKey());
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				}
				this.user_list.setModel(model);			
				this.start_work();
			}
			soc.close();
		} catch (IOException ex) {
			Logger.getLogger(DC_UI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DC_UI.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("Registered!");
	}//GEN-LAST:event_submit_btnActionPerformed

	private void refresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_btnActionPerformed
		// TODO add your handling code here:
		String send = "R"+ this.nick;

		try {
			Socket soc = new Socket(server_ip,server_port);
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeUTF(send);
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			map = new HashMap<String,Inet4Address>();            
			map = (Map<String, Inet4Address>) ois.readObject();
			DefaultListModel model = new DefaultListModel();                             
			int i = 0;
			for (Map.Entry<String, Inet4Address> entry : map.entrySet()) {
				if(!nick.equals(entry.getKey()))
					model.addElement(entry.getKey());
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}
			this.user_list.setModel(model);                
		} catch (IOException ex) {
			Logger.getLogger(DC_UI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DC_UI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}//GEN-LAST:event_refresh_btnActionPerformed
	
	private int get_port_from_server(String name) throws IOException
	{
		Socket soc = new Socket(server_ip,server_port);
		ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
//		BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
		oos.writeUTF("C" + name);
		oos.flush();
		System.out.println("Just Before read!!");
		System.out.println("kuch :"+ois.readInt());
		return ois.readInt();
	}
	private void create_group_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_group_btnActionPerformed
		// get list of selected users
		// Get the index of all the selected items
		Map<String,Inet4Address> mp = new HashMap<String,Inet4Address>();
		int[] selectedIx = this.user_list.getSelectedIndices();
		if(selectedIx.length == 0)
		{
			// no user selected
			JOptionPane.showMessageDialog(this, "No user selected", "Conference Creation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Get all the selected items using the indices
		for (int i = 0; i < selectedIx.length; i++)
		{
			String sel = (String) this.user_list.getModel().getElementAt(selectedIx[i]);
			mp.put(sel, map.get(sel));
		}
		// ask for conference name
		String name;
		while(true)
		{
			name = JOptionPane.showInputDialog(this, "Give a conference name","New Conference", JOptionPane.QUESTION_MESSAGE);
			System.out.println("Name inputted :" + name);
			if(name != null && !name.isEmpty())
				break;
		}
		
		// ask server for a new conference port
		int port = 10000;
		try 
		{
			port = get_port_from_server(name);
			System.out.println("Server Gave port : " + port);
			// add a new conference panel to the tabs
			conferences.add(new Conference_Manager(this,port,nick,name,mp));
		}
		catch (IOException ex)
		{
			Logger.getLogger(DC_UI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}//GEN-LAST:event_create_group_btnActionPerformed
	
	public void remove_conference(Conference_Manager c)
	{
		this.conferences.remove(c);
	}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				DC_UI.ui = new DC_UI();
				DC_UI.ui.setVisible(true);
			}
		});
		
		// start the server that listens for new conference request
		ServerSocket serverSocket = new ServerSocket(DC_UI.req_port);
		while(true){
            System.out.println("waiting for conference");
            Socket clientSocket = serverSocket.accept();
            handleRequest(clientSocket);
        }
	}

public static void handleRequest(Socket sock) throws IOException, ClassNotFoundException
{
	ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
	ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
	//read request form socket
	String request = ois.readUTF();
	String conference = request.substring(1);
	if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to join conference "+conference,"Conference Join Request",JOptionPane.YES_NO_OPTION))
	{
		// send accept
		oos.writeUTF("A");
		int port = ois.readInt();
		Map<String,Inet4Address> peers = (Map<String,Inet4Address>) ois.readObject();
		// iterate on map and send peer request to everyone
		for (Map.Entry<String, Inet4Address> entry : peers.entrySet())
		{
			Socket new_sock = new Socket(entry.getValue(),port);
			ObjectOutputStream oos1 = new ObjectOutputStream(new_sock.getOutputStream());
			oos1.writeUTF("P"+nick);
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		// create a new conference manager
		Conference_Manager temp = new Conference_Manager(ui,port,nick,conference);
		temp.peers = peers;
		conferences.add(temp);
	}
	else
	{
		// send reject
		oos.writeUTF("R");
	}
}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton back_btn;
    public javax.swing.JButton close_btn;
    public javax.swing.JLabel conf_error_label;
    public javax.swing.JLabel conference_label;
    public javax.swing.JList conference_list;
    public javax.swing.JButton create_group_btn;
    public javax.swing.JPanel head_panel;
    public javax.swing.JLabel heading_label;
    public javax.swing.JPanel home_panel;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JButton join_group_btn;
    public javax.swing.JLabel nick_error_label;
    public javax.swing.JTextField nickname_text_field;
    public javax.swing.JLabel nickname_text_label;
    public javax.swing.JButton refresh_btn;
    public javax.swing.JPanel selector_panel;
    public javax.swing.JSeparator separator;
    public javax.swing.JButton submit_btn;
    public javax.swing.JTabbedPane tabbed_pane;
    public javax.swing.JLabel user_error_label;
    public javax.swing.JLabel user_label;
    public javax.swing.JList user_list;
    public javax.swing.JPanel work_panel;
    // End of variables declaration//GEN-END:variables
}
