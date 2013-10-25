package package1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class StatusModel extends AbstractTableModel{
	
	/** default serial version UID */
	private static final long serialVersionUID = 1L;
	
	/** ArrayList of site */
	private ArrayList<Site> listSite;
	
	/** Names of the columns */
	private String[] columnNames = {"Name Reserving", "Checked-In", 
			"Site #", "Estimated Days", "Days Remaining"};
	
	/** BetterGregorianCalendar to store a date */
	private BetterGregorianCalendar date;
	
	/******************************************************************
	 * gets the column names
	 * @param col the column number
	 * @return String the name of the column
	 *****************************************************************/
	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
	
	/******************************************************************
	 * gets the length of the columns
	 * @return int the length of the columns
	 *****************************************************************/
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/******************************************************************
	 * gets the number of rows
	 * @return int the number of rows
	 *****************************************************************/
	@Override
	public int getRowCount() {
		return listSite.size();
	}

	/******************************************************************
	 * sets the values of the table according the names and rows
	 * @param int row the row number
	 * @param int col the column number
	 * @return Object returns the table values
	 *****************************************************************/
	@Override
	public Object getValueAt(int row, int col) {
		switch(col){
		case 0:
			// return the name reserving
			return(listSite.get(row).getNameReserving());
		case 1:
			// return the date in the format mm/dd/yyyy
			return GUICampingReg.SIMPLE_FORMAT
					.format(listSite.get(row).getCheckIn().getTime());
		case 2:
			// return the site number
			return (listSite.get(row).getSiteNumber());
		case 3: 
			// return the days staying
			return (listSite.get(row).getDaysStaying());
		case 4:
			// return the days remaining
			return ((listSite.get(row).getCheckIn().daysSince(date))) + listSite.get(row).getDaysStaying();
			
		default:
			return null;
		}
	}
	
	/******************************************************************
	 * Constructor for StatusModel, accepts a date
	 * @param d date from the GUICampingReg
	 *****************************************************************/
	public StatusModel(BetterGregorianCalendar d){
		super();
		date = d;
		listSite = new ArrayList<Site>();
	}
	
	/******************************************************************
	 * loads the table from a text file
	 * @param filename the name of the file
	 *****************************************************************/
	public void loadFromText(String filename) {
		listSite.clear();
		fireTableRowsDeleted( 0, listSite.size());

		try {
			Scanner scanner = new Scanner(new File(filename));
			
			int size = Integer.parseInt(scanner.nextLine().trim());

			for (int i = 0; i < size; i++) {
				// gets the type of the site
				String siteType = scanner.nextLine().trim();
				// gets the name
				String name = scanner.nextLine().trim();

				String checkInDate = null;
				// gets the check in date
				checkInDate = scanner.nextLine().trim();
				// gets the days staying
				int daysStaying = Integer.parseInt(scanner.nextLine().trim());
				// gets the site number
				int siteNumber = Integer.parseInt(scanner.nextLine().trim());
				// gets the tenters or the power
				int lastParam = Integer.parseInt(scanner.nextLine().trim());
				
				// if it is a tent, add a tent site
				if (siteType.equals("t")) {
					Tent t;
					t = new Tent(name, checkInDate, daysStaying,siteNumber, lastParam);
					listSite.add(t);
					fireTableRowsInserted(listSite.size() - 1, listSite.size() - 1);

				}
				// if it is an RV, add an RV site
				else if (siteType.equals("r")) {
					RV r;
					r = new RV(name, checkInDate, daysStaying, siteNumber, lastParam);
					listSite.add(r);
					fireTableRowsInserted(listSite.size() - 1, listSite.size() - 1);

				}

			}

			scanner.close();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "File not Recognized", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
