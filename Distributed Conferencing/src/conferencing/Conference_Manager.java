package conferencing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	Conference_Manager(DC_UI Main)
	{
		this.Main = Main;
		ui = new Conference_Panel();
		this.Main.tabbed_pane.add(ui);
		
		ui.exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				// event handling function
				delete_conference();
			}
		});
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
