package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.*;

import VariableInputApi.*;

public class GUICampingReg extends JFrame implements ActionListener {

	/** the serial version UID */
	private static final long serialVersionUID = 1L;

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

	/** Default Name */
	private final String DEFAULT_NAME;

	/** Default Site Number */
	private final int DEFAULT_SITE_NUMBER;

	/** Default Date */
	private final String DEFAULT_DATE;

	/** Default number of days staying */
	private final int DEFAULT_DAYS_STAYING;

	/** Default power use  */
	private final int DEFAULT_POWER_USED;

	/** Default number of days staying */
	private final int DEFAULT_TENTERS;

	/** Maximum number of sites **/
	private final int MAX_NUMBER_OF_SITES;

	/** **/
	private Boolean[] sitesTaken;

	/** Cost for the stay */
	private double[] costs;

	/******************************************************************
	 * Sets up the GUI
	 *****************************************************************/
	public GUICampingReg(){
		DEFAULT_NAME = "John Doe";
		DEFAULT_SITE_NUMBER = 1;
		DEFAULT_DATE = "10/15/2013";
		DEFAULT_DAYS_STAYING = 1;
		DEFAULT_POWER_USED = 30;
		DEFAULT_TENTERS = 1;

		MAX_NUMBER_OF_SITES = 5;
		sitesTaken = new Boolean[MAX_NUMBER_OF_SITES];
		costs = new double[MAX_NUMBER_OF_SITES];
		clearAllSites();

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

	private void clearAllSites() {
		for (int i = 0; i<MAX_NUMBER_OF_SITES; i++) {
			sitesTaken[i]= false;
		}
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
			JFileChooser fc = new JFileChooser();
			File file;
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try{
					file = fc.getSelectedFile();
					siteTableModel.loadDatabase(file.getName());
				}catch(Throwable e){
					JOptionPane.showMessageDialog(null, "Choose a serializable file");

				}
			}

		}

		if(comp == saveS){
			JFileChooser fc = new JFileChooser();
			File file;
			int returnVal = fc.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try{
					file = fc.getSelectedFile();
					siteTableModel.saveDatabase(file.getName());

				}catch(Throwable e){
					JOptionPane.showMessageDialog(null, "Choose a text file");
				}
			}
		}

		if(comp == openT){
			JFileChooser fc = new JFileChooser();
			File file;
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try{
					file = fc.getSelectedFile();
					siteTableModel.loadFromText(file.getName());	  

				}catch(Throwable e){
					JOptionPane.showMessageDialog(null, "Choose a text file");
				}
			}
		}

		if(comp == saveT){
			JFileChooser fc = new JFileChooser();
			File file;
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try{
					file = fc.getSelectedFile();
					siteTableModel.saveAsText(file.getName());	  

				}catch(Throwable e){
					JOptionPane.showMessageDialog(null, "Choose a text file");
				}
			}

		}

		if(comp == checkInTent){
			String[] labelsTent = {"Name Reserving:", "Site Number:", "Occupied On:", "Number of Tenters:", "Days Staying:"};

			VarInputPanel vT = new VarInputPanel(labelsTent, DEFAULT_NAME, DEFAULT_SITE_NUMBER, DEFAULT_DATE, DEFAULT_TENTERS, DEFAULT_DAYS_STAYING);
			int resultTent;

			do {
				resultTent = JOptionPane.showConfirmDialog(null, vT, "Reserve a Tent", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			} while(!checkInputForError(vT, resultTent, Tent.TYPE));
			if(resultTent == JOptionPane.OK_OPTION){
				// got this to work
				Object[] varResult = vT.getUpdatedVars();

				Tent t = new Tent(varResult);
				sitesTaken[t.getSiteNumber() - 1] = true;

				costs[t.getSiteNumber() -1] = t.getDaysStaying() * t.getNumOfTenters() * 3;
				DecimalFormat df = new DecimalFormat("#.00");
				JOptionPane.showMessageDialog(null, "You owe $" + df.format(costs[0]));

				siteTableModel.addSite(t);
			}
		}

		if(comp == checkInRV){

			//DialogCheckInRV x = new DialogCheckInRV(this, r);
			String[] labelsRV = {"Name Reserving:", "Site Number:", "Occupied On:", "Power needed:", "Days Staying:"};

			VarInputPanel vR = new VarInputPanel(labelsRV, DEFAULT_NAME, DEFAULT_SITE_NUMBER, DEFAULT_DATE, DEFAULT_POWER_USED, DEFAULT_DAYS_STAYING);
			int resultRV;

			do {
				resultRV = JOptionPane.showConfirmDialog(null, vR, "Reserve an RV", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			} while(!checkInputForError(vR, resultRV, RV.TYPE));

			if(resultRV == JOptionPane.OK_OPTION){	
				// got this to work
				Object[] varResult = vR.getUpdatedVars();

				RV r = new RV(varResult);
				sitesTaken[r.getSiteNumber() - 1] = true;

				costs[r.getSiteNumber() - 1] = r.getDaysStaying() * 30;
				DecimalFormat df = new DecimalFormat("#.00");
				JOptionPane.showMessageDialog(null, "You owe $" + df.format(costs[1]));

				siteTableModel.addSite(r);
			}

		}

		if(comp == checkOut){
			String[] labelsCheckOut = {"Check Out On"};
			VarInputPanel vR = new VarInputPanel(labelsCheckOut, DEFAULT_DATE);
			int resultCheckOut;
			// need to check for error on this and implement it
			do{
			resultCheckOut = JOptionPane.showConfirmDialog(null, vR, "Check Out", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			}while(!checkInputForError(vR, resultCheckOut, 0));
			int index = table.getSelectedRow();
			try {
				siteTableModel.checkOut(index);
			}
			catch(IndexOutOfBoundsException e) {
				//e.printStackTrace();
				JOptionPane.showMessageDialog(this,"You have not selected an Entry to Check Out");
			}
		}
	}

	private boolean checkInputForError(VarInputPanel p, int i, int type) {
		if (i==JOptionPane.OK_OPTION){	
			if(type == RV.TYPE || type == Tent.TYPE){
				if(p.doUpdatedVarsMatchInput()) {
					Object[] varResult = p.getUpdatedVars(); 				
					return checkInputVariableBounds(varResult, type);
				}

				JOptionPane.showMessageDialog(null, "Numbers out of range. " +
						" Please check your inputs.");
				return false;
			}
			else if(type == 0){
				if(p.doUpdatedVarsMatchInput()){
					Object[] varResult = p.getUpdatedVars();
					return checkOutVariableBounds(varResult, type);
				}
			}
		}
	

		return true;
	}

	private boolean checkInputVariableBounds(Object[] varResult, int type) {
		//Check the Site number

		if ((Integer)varResult[1] < 1) {
			JOptionPane.showMessageDialog(null, "The Site Number must be 1 or larger.");
			return false;
		}
		if ((Integer)varResult[1] > MAX_NUMBER_OF_SITES) {
			JOptionPane.showMessageDialog(null, "The Site Number must be " + MAX_NUMBER_OF_SITES + " or less.");
			return false;
		}
		if (sitesTaken[(Integer)varResult[1] - 1]) {
			JOptionPane.showMessageDialog(null, "The Site has already been taken!");
			return false;
		}

		//Check the Date
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date;
		try {
			date = sdf.parse((String)varResult[2]);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Enter a correct date (MM/DD/YYYY)");
			return false;
		}


		//Check the Number of Tenters, or the Power used!
		if (type == Tent.TYPE)
		{
			if ((Integer)varResult[3] < 1) {
				JOptionPane.showMessageDialog(null, "There must be at least one tenter!");
				return false;
			}
		} else if (type == RV.TYPE) {
			if ((Integer)varResult[3] < 0) {
				JOptionPane.showMessageDialog(null, "We will not accept your RV's Power as payment");
				return false;
			}
			if (((Integer)varResult[3] / 10 < 3) || 
					((Integer)varResult[3] / 10 > 5) ||
					(Integer)varResult[3]%10 != 0) {
				JOptionPane.showMessageDialog(null, "Power must be either 30, 40, or 50 Amps");
				return false;
			}
		}

		//Check the Number of Days Stayed.
		if ((Integer)varResult[4] < 1) {
			JOptionPane.showMessageDialog(null, "You can't stay a negative number of Days!");
			return false;
		}

		return true;

	}
	
	private boolean checkOutVariableBounds(Object[] varResult, int type){
		Site s = new Site();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = (String)varResult[0];
		GregorianCalendar cal = new GregorianCalendar();
		try {
			date = sdf.parse((String)varResult[0]);
			cal.setTime(date);
			System.out.print(((cal.getTimeInMillis() - (s.getCheckIn()).getTimeInMillis()))/(1000*60*60*24));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Enter a correct date (MM/DD/YYYY)");
			return false;
		}
		return true;
	}

}
