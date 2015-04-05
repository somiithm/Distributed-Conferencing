/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conferencing;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DC_UI extends javax.swing.JFrame {

	/*Global Variables*/
	public static Font ui_font;
	public String nick_error1;
	public String nick_error2;
	public String nick_error3;
	public String nick_error4;
	/**
	 * Creates new form DC_UI
	 */
	String ServerIP;
	ArrayList<Conference_Manager> conferences;
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

		String nick = this.nickname_text_field.getText();
		
		// testing
		this.start_work();
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
		try {
			Socket soc = new Socket("10.141.212.64",8080);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
			bw.write("N" + nick + "\n" );
			bw.flush();
			String line = stdIn.readLine();
			if(line.equals("NAK"))
			{
				// Reject
				this.nick_error_label.setText(this.nick_error4);
				return;
			}
			else
			{
				// Accepted
				this.nick_error_label.setText("");
				// move to next UI page
			}
		} catch (IOException ex) {
			Logger.getLogger(DC_UI.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("Registered!");
	}//GEN-LAST:event_submit_btnActionPerformed

	private void refresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_btnActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_refresh_btnActionPerformed

	private void create_group_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_group_btnActionPerformed
		// add a new conference panel to the tabs
		conferences.add(new Conference_Manager(this));
		//this.tabbed_pane.add(new Conference_Panel());
	}//GEN-LAST:event_create_group_btnActionPerformed
	
	public void remove_conference(Conference_Manager c)
	{
		this.conferences.remove(c);
	}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
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
				new DC_UI().setVisible(true);
			}
		});
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
