package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUICampingReg extends JFrame implements ActionListener {	
	private static final long serialVersionUID = 1L;

	/** JButton for ok */
	private JButton okButton;

	/** JButton for cancel */
	private JButton cancelButton;
	
	/** JMenu for file */
	private JMenu fileMenu;
	
	/** JMenu for check in menu */
	private JMenu checkInMenu;
	
	/** JMenu for check out menu */
	private JMenu checkOutMenu;
	
	/** JMenu Item for saving serializable file */
	private JMenuItem saveS;
	
	/** JMenu Item for opening serializable file */
	private JMenuItem openS;
	
	/** JMenu Item for saving text file */
	private JMenuItem saveT;
	
	/** JMenu Item for opening text file */
	private JMenuItem openT;
	
	/** JMenu Item for exiting */
	private JMenuItem quit;
	
	/** JMenu Item for checking in a tent */
	private JMenuItem checkInTent;
	
	/** JMenu Item for checking in a RV */
	private JMenuItem checkInRV;
	
	/** JMenu Item for checking out */
	private JMenuItem checkOut;
	
	/** JMenu bar to hold the menus */
	private JMenuBar menus;
	
	/** JTable for holding the information */
	private JTable table;
	
	/** instance of SiteModel */
	private SiteModel siteTableModel;
	
	/** JScrollPanel to allow for scrolling */
	private JScrollPane scrollPane;
	
	/******************************************************************
	 * Sets up the GUI
	 *****************************************************************/
	public GUICampingReg(){
		
		// Instantiate the menus and menu items
		fileMenu = new JMenu("File:");
		checkInMenu = new JMenu("Check In:");
		checkOutMenu = new JMenu("Check Out:");
		saveS = new JMenuItem("Save Serializable");
		openS = new JMenuItem("Open Serializable");
		openT = new JMenuItem("Open Text");		
		saveT = new JMenuItem("Save Text");
		quit = new JMenuItem("Quit");
		checkInTent = new JMenuItem("Check In Tent");
		checkInRV = new JMenuItem("Check In RV");
		checkOut = new JMenuItem("Check Out");
		menus = new JMenuBar();
		
		// add the menus and menu items to the frame
		menus.add(fileMenu);
		fileMenu.add(saveS);
		fileMenu.add(openS);
		fileMenu.add(saveT);
		fileMenu.add(openT);
		fileMenu.add(quit);
		menus.add(checkInMenu);
		checkInMenu.add(checkInTent);
		checkInMenu.add(checkInRV);
		menus.add(checkOutMenu);
		checkOutMenu.add(checkOut);
		
		// add ActionListeners
		quit.addActionListener(this);
		openT.addActionListener(this);
		saveT.addActionListener(this);
		openS.addActionListener(this);
		saveS.addActionListener(this);
		checkInTent.addActionListener(this);
		checkInRV.addActionListener(this);
		checkOut.addActionListener(this);
		
		// set the menu bar
		setJMenuBar(menus);
		
		// instantiate the SiteModel and add it to the frame
		siteTableModel = new SiteModel();
		table = new JTable(siteTableModel);
		scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		// set the default close operation
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		setSize(700,300);
		setVisible(true);
	}
	
	/******************************************************************
	 * Main method to run the GUI
	 * @param args
	 *****************************************************************/
	public static void main(String[] args){
		new GUICampingReg();
	}
	
	/******************************************************************
	 * actionPerformed method for the buttons
	 * @param event listens for buttons to be clicked
	 *****************************************************************/
	@Override
	public void actionPerformed(ActionEvent event) {
		JComponent comp = (JComponent) event.getSource();
		
		if(comp == quit){
			System.exit(1);
		}
		
		if(comp == openS){
			siteTableModel.loadDatabase("siteDB");
		}
		
		if(comp == saveS){
			siteTableModel.saveDatabase("siteDB");
		}
		
		if(comp == openT){
			siteTableModel.loadFromText("siteDBText");
		}
		
		if(comp == saveT){
			siteTableModel.saveAsText("siteDBText");
		}
		
		if(comp == checkInTent){
			Tent t = new Tent();
			DialogCheckInTent x = new DialogCheckInTent(this, t);
			if(x.getCloseStatus() == x.OK)
				siteTableModel.addSite(t);
		}
		
		if(comp == checkInRV){
			RV r = new RV();
			DialogCheckInRV x = new DialogCheckInRV(this, r);
			if(x.getCloseStatus() == x.OK)
				siteTableModel.addSite(r);
		}
		
		if(comp == checkOut){
			int index = table.getSelectedRow();
			if (index == -1)
				JOptionPane.showMessageDialog(this,"NO");
			else
				siteTableModel.checkOut(index);
		}
		
	}

}
