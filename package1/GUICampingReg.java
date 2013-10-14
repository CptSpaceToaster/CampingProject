package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUICampingReg extends JFrame implements ActionListener {	

	/** JButton for ok */
	private JButton okButton;

	/** JButton for cancel */
	private JButton cancelButton;
	
	private static JMenu fileMenu;
	private static JMenu checkInMenu;
	private static JMenu checkOutMenu;
	private static JMenuItem saveS;
	private static JMenuItem openS;
	private static JMenuItem saveT;
	private static JMenuItem openT;
	private static JMenuItem quit;
	private static JMenuItem checkInTent;
	private static JMenuItem checkInRV;
	private static JMenuItem checkOutTent;
	private static JMenuItem checkOutRV;
	private static JMenuBar menues;
	private static JTable table;
	private static JPanel panel;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		JTable table;
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
		checkOutTent = new JMenuItem("Check Out Tent");
		checkOutRV = new JMenuItem("Check Out RV");
		menues = new JMenuBar();
		panel = new JPanel();
		menues.add(fileMenu);
		fileMenu.add(saveS);
		fileMenu.add(openS);
		fileMenu.add(saveT);
		fileMenu.add(openT);
		fileMenu.add(quit);
		menues.add(checkInMenu);
		checkInMenu.add(checkInTent);
		checkInMenu.add(checkInRV);
		menues.add(checkOutMenu);
		checkOutMenu.add(checkOutTent);
		checkOutMenu.add(checkOutRV);
		
		GUICampingReg listener = new GUICampingReg();
		quit.addActionListener(listener);
		
		JFrame frame = new JFrame ("Camping");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.add(menues);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JComponent comp = (JComponent) event.getSource();
		if(comp == quit){
			System.exit(1);
		}

	}

}
