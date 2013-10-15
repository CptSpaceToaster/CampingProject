package package1;

import java.util.GregorianCalendar;

public class Tent extends Site {
	/** Represents the number of tenters on this site */
	private int numOfTenters;

	public Tent(){
		super();
	}
	/**************************************************************
	 * Constructor for tents
	 * @param tenters
	 *************************************************************/
	public Tent(String name, GregorianCalendar checkIn, int daysStaying, int siteNumber, int tenters) {
		super(name, checkIn, daysStaying, siteNumber);
		this.numOfTenters = tenters;
	}

	/**************************************************************
	 * @return the numOfTenters
	 *************************************************************/
	public int getNumOfTenters() {
		return numOfTenters;
	}

	/**************************************************************
	 * @param numOfTenters the numOfTenters to set
	 *************************************************************/
	public void setNumOfTenters(int numOfTenters) {
		this.numOfTenters = numOfTenters;
	}
	
	
}
