package package1;

import java.io.Serializable;
import java.text.ParseException;
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

	/**************************************************************
	 * Default Constructor
	 *************************************************************/
	public Site(){
		nameReserving = "";
		checkIn = new BetterGregorianCalendar();	
		checkOutOn = new BetterGregorianCalendar();
		daysStaying = 0;
		siteNumber = 1;
	}

	/**************************************************************
	 * Constructor for Site
	 * @param checkIn takes in the date of check in
	 * @param name takes in the name of the person
	 * @param daysStaying takes in the days staying
	 * @param out takes in the day of check out
	 * @param siteNumber takes in the site number
	 *************************************************************/
	public Site(String name, String checkIn, int daysStaying, int siteNumber){
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
	/******************************************************************
	 * Compares two sites
	 * @param site takes in a site to be compared
	 * @return int value of the comparison
	 *****************************************************************/
	@Override
	public int compareTo(Site site) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		if(this.siteNumber < site.siteNumber)
			return BEFORE;
		if(this.siteNumber > site.siteNumber)
			return AFTER;
		
		int comparison = this.nameReserving.compareTo(site.nameReserving);
		if (comparison != EQUAL) 
			return comparison;
		
		if(this.daysStaying < site.daysStaying)
			return BEFORE;
		if(this.daysStaying > site.daysStaying)
			return AFTER;
	
		return EQUAL;
	}  
	
	/******************************************************************
	 * Abstract method for calculating the costs
	 * @param days takes in the number of days
	 * @return returns the double cost
	 *****************************************************************/
	public abstract double calcCost(int days);

}
