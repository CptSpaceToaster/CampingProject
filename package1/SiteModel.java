package package1;

import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.io.*;
import java.text.*;
public class SiteModel extends AbstractTableModel {
	private ArrayList<Site> listSite;
	private String[] columnNames = {"Name Reserving", "Checked-In", 
			"Days Staying", "Site #", "Tent/RV Info"};

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return listSite.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return null;
	}

	public SiteModel(){
		super();
		listSite = new ArrayList<Site>();
	}

	public void checkOut(int i){
		listSite.remove(i);
		fireTableRowsInserted(0, listSite.size());
	}

	public void addSite(Site s){
		listSite.add(s);
		fireTableRowsInserted(0, listSite.size());
	}

	public Site getSite(int i){
		return listSite.get(i);
	}

	public int getSize(){
		return listSite.size();
	}

	public void saveDatabase(String filename){
		try{
			FileOutputStream fileOutput = new FileOutputStream(filename);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(listSite);
			objectOutput.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void loadDatabase(String filename){
		try{
			FileInputStream input = new FileInputStream(filename);
			ObjectInputStream objectInput = new ObjectInputStream(input);
			listSite = (ArrayList<Site>)objectInput.readObject();
			fireTableRowsInserted(0,listSite.size() - 1);
			objectInput.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean saveAsText(String filename){
		if(filename.equals(""))
			return false;
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			out.println(listSite.size());
			for (int i = 0; i < listSite.size(); i++) {
				Site s = listSite.get(i);
				out.println(s.getClass().getName());
				out.println(DateFormat.getDateInstance(DateFormat.SHORT)
						.format(s.getCheckIn()));
				out.println(s.getDaysStaying());
				out.println(s.getSiteNumber());

				if (s instanceof Tent)
					out.println(((Tent) s).getNumOfTenters());
				else
					out.println(((RV) s).getPower());
			}
			out.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
	public void loadFromText(String filename) {
		listAuto.clear();
		fireTableRowsDeleted( 0, listAuto.size());

		try {
			Scanner scanner = new Scanner(new File(filename));
			// should clear the arrayList and screen.... 
			int count = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i < count; i++) {
				String type = scanner.nextLine().trim();
				GregorianCalendar dateBought = null;
				try {
					DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					Date date = formatter.parse(scanner.nextLine().trim());
					dateBought = new GregorianCalendar();
					dateBought.setTime(date);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				String owner = scanner.nextLine().trim();
				double cost = Double.parseDouble(scanner.nextLine().trim());

				if (type.contains("SportsCar")) {
					String s = scanner.nextLine();
					SportsCar car;
					if (s.equals ("true"))
						car = new SportsCar(dateBought, cost, owner, true);
					else
						car = new SportsCar(dateBought, cost, owner, false);

					listAuto.add(car);
					fireTableRowsInserted(listAuto.size() - 1, listAuto.size() - 1);

				} else {
					ElectricCar car;
					int volts = Integer.parseInt(scanner.nextLine().trim());
					car = new ElectricCar(dateBought, cost, owner, volts);

					listAuto.add(car);
					fireTableRowsInserted(listAuto.size() - 1, listAuto.size() - 1);
				}
			}
			scanner.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
