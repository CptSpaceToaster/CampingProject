package package1;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUICampingReg extends JFrame implements ActionListener {	

	/** JButton for ok */
	private JButton okButton;

	/** JButton for cancel */
	private JButton cancelButton;
	
	private JMenu fileMenu;
	private JMenu checkInMenu;
	private JMenu checkOutMenu;
	private JMenuItem saveS;
	private JMenuItem openS;
	private JMenuItem saveT;
	private JMenuItem openT;
	private JMenuItem quit;
	private JMenuItem checkInTent;
	private JMenuItem checkInRV;
	private JMenuItem checkOut;
	private JMenuBar menus;
	private JTable table;
	private SiteModel siteTableModel; 
	private JScrollPane scrollPane;
	
	/**
	 * 
	 * @param args
	 */
	public GUICampingReg(){
		JPanel panel;
		
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
		panel = new JPanel();
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
		
		quit.addActionListener(this);
		openT.addActionListener(this);
		saveT.addActionListener(this);
		openS.addActionListener(this);
		saveS.addActionListener(this);
		checkInTent.addActionListener(this);
		checkInRV.addActionListener(this);
		checkOut.addActionListener(this);
		
		setJMenuBar(menus);
		
		siteTableModel = new SiteModel();
		table = new JTable(siteTableModel);
		scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		setSize(700,300);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new GUICampingReg();
	}
	
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
