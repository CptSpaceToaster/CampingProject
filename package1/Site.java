package package1;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JOptionPane;

public abstract class Site implements Serializable, Comparable<Site>{
	
	/**Default serial UID */
	private static final long serialVersionUID = 1L;

	/** The name of the person who is occupying the Site */
	protected String nameReserving;
	
	/** The date the Site was checked-in (occupied) */
	protected BetterGregorianCalendar checkIn;

	/** The estimated number of days the person is reserving */
	/** This is just an estimate when the camper is  */
	/** is checking in  */
	protected int daysStaying; 

	/** The date the Site was checked out */
	/** This is the exact day they checked-out */
	protected BetterGregorianCalendar checkOutOn;

	/** The Site number */
	protected int siteNumber;
	
	/** costs per site */
	private double account;

	/**************************************************************
	 * Default Constructor
	 *************************************************************/
	public Site(){
		nameReserving = "";
		checkIn = new BetterGregorianCalendar();	
		checkOutOn = new BetterGregorianCalendar();
		daysStaying = 0;
		siteNumber = 1;
		account = 0;
	}
	
	public Site(String name, String checkIn, int daysStaying, int siteNumber){
		this(name, checkIn, daysStaying, siteNumber, 0);
	}
	/**************************************************************
	 * Constructor for Site
	 * @param checkIn takes in the date of check in
	 * @param name takes in the name of the person
	 * @param daysStaying takes in the days staying
	 * @param out takes in the day of check out
	 * @param siteNumber takes in the site number
	 *************************************************************/
	public Site(String name, String checkIn, int daysStaying, int siteNumber, 
													double account){
		this.nameReserving = name;

		Date date;
		BetterGregorianCalendar checkInDate = new BetterGregorianCalendar();
		try {
			// parses the date into mm/dd/yyyy
			date = GUICampingReg.SIMPLE_FORMAT.parse(checkIn);
			// sets the time for the BetterGregorianCalendar
			checkInDate.setTime(date);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Could not parse date", 
						"ERROR", JOptionPane.ERROR_MESSAGE);
		}

		this.checkIn = checkInDate;
		this.daysStaying = daysStaying;
		this.siteNumber = siteNumber;
		this.account = account;
	}

	/**************************************************************
	 * @return the nameReserving
	 *************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}

	/**************************************************************
	 * @param nameReserving the nameReserving to set
	 *************************************************************/
	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}

	/**************************************************************
	 * @return the checkIn
	 *************************************************************/
	public BetterGregorianCalendar getCheckIn() {
		return checkIn;
	}

	/**************************************************************
	 * @param checkIn the checkIn to set
	 *************************************************************/
	public void setCheckIn(BetterGregorianCalendar checkIn) {
		this.checkIn = checkIn;
	}

	/**************************************************************
	 * @return the daysStaying
	 *************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}

	/**************************************************************
	 * @param daysStaying the daysStaying to set
	 *************************************************************/
	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}

	/**************************************************************
	 * @return the checkOutOn
	 *************************************************************/
	public BetterGregorianCalendar getCheckOutOn() {
		return checkOutOn;
	}

	/**************************************************************
	 * @param checkOutOn the checkOutOn to set
	 *************************************************************/
	public void setCheckOutOn(BetterGregorianCalendar checkOutOn) {
		this.checkOutOn = checkOutOn;
	}

	/**************************************************************
	 * @return the siteNumber
	 *************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}

	/**************************************************************
	 * @param siteNumber the siteNumber to set
	 *************************************************************/
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	
	
	/**************************************************************
	 * @return the value of the account for the Site
	 *************************************************************/
	public double getAccount(){
		return account;	
	}
	
	public void setAccount(double account){
		this.account = account;
	}
	
	/******************************************************************
	 * Compares two sites
	 * @param site takes in a site to be compared
	 * @return int value of the comparison
	 *****************************************************************/
	@Override
	public int compareTo(Site site) {
		final int EQUAL = 0;
		
		int comparison = this.nameReserving.compareTo(site.nameReserving);
		if (comparison != EQUAL) 
			return comparison;
		
		return EQUAL;
	}  
	/******************************************************************
	 * Compares two sites, by looking at the First letter of the Name
	 * 
	 * @param str
	 * @return 0 if they are equal, 1 if str is alphabetically above
	 * the name -1 if str is alphabetically below.
	 *****************************************************************/
	public int compareTo(String str){
		final int EQUAL = 0;
		
		int comparison = this.compareTo(str);
		if (comparison != EQUAL) 
			return comparison;
		
		return EQUAL;
	}
	
	/******************************************************************
	 * Abstract method for calculating the costs
	 * @param days takes in the number of days
	 * @return returns the double cost
	 *****************************************************************/
	public abstract double calcCost(int days);
	
	/**
	 * The numerous Comparators we used to sort the Database in a number of
	 * different ways.
	 * 
	 * ASC_FIRSTNAME - Alphabetical By First Name
	 * DES_FIRSTNAME - Reverse Alphabetical By First Name
	 * ASC_LASTNAME - Alphabetical By Last Name
	 * DES_LASTNAME - Reverse Alphabetical By Last Name
	 * 
	 * ASC_CHECKIN - Ascending Check in date (by total number of days)
	 * DES_CHECKIN - Descending Check in date
	 * 
	 * ASC_DAYS - Ascending Days being Stayed
	 * DES_DAYS - Descending Days being Stayed
	 * 
	 * ASC_SITENUMBER - Ascending Site Number
	 * DES_SITENUMBER - Descending Site Number
	 *
	 * Tent and RV also contain their own comparators for their specific Data
	 */
	public static class Comparators{
		//0
		public static Comparator<Site> ASC_FIRSTNAME = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s1.compareTo(s2);
			}
		};
		
		public static Comparator<Site> DES_FIRSTNAME = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s2.compareTo(s1);
			}
		};
		
		public static Comparator<Site> ASC_LASTNAME = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				String[] name;
				String lastName1;
				String lastName2;
				
				try{
				name = s1.getNameReserving().split(" ");
				lastName1 = name[1];
				}catch(Exception e){
					lastName1 = s1.getNameReserving();
				}
				
				try{
				name = s2.getNameReserving().split(" ");
				lastName2 = name[1];
				}catch(Exception e){
					lastName2 = s2.getNameReserving();
				}
				
				return lastName1.compareTo(lastName2);
			}
		};
		
		public static Comparator<Site> DES_LASTNAME = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				String[] name;
				String lastName1;
				String lastName2;
				
				try{
				name = s1.getNameReserving().split(" ");
				lastName1 = name[name.length - 1];
				}catch(Exception e){
					lastName1 = s1.getNameReserving();
				}
				
				try{
				name = s2.getNameReserving().split(" ");
				lastName2 = name[name.length - 1];
				}catch(Exception e){
					lastName2 = s2.getNameReserving();
				}
				
				return lastName2.compareTo(lastName1);
			}
		};
		
		//1
		public static Comparator<Site> ASC_CHECKIN = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s1.getCheckIn().daysSince(s2.getCheckIn());
			}
		};
		
		public static Comparator<Site> DES_CHECKIN = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s2.getCheckIn().daysSince(s1.getCheckIn());
			}
		};
		
		//2
		public static Comparator<Site> ASC_DAYS = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s1.daysStaying - s2.daysStaying;
			}
		};
		
		public static Comparator<Site> DES_DAYS = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s2.daysStaying - s1.daysStaying;
			}
		};
		
		//3
		public static Comparator<Site> ASC_SITENUMBER = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s1.siteNumber - s2.siteNumber;
			}
		};
		
		public static Comparator<Site> DES_SITENUMBER = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return s2.siteNumber - s1.siteNumber;
			}
		};
	}
}
