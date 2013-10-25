package package1;

import java.util.Comparator;

public class RV extends Site {
	
	/** default serial version ID */
	private static final long serialVersionUID = 1L;

	/** Type for RV **/
	public static final int TYPE = 2;
	
	/** Represents the power supplied to the site */
	private int power; // 30, 40, 50 amps of service.
	/******************************************************************
	 * default constructor
	 *****************************************************************/
	public RV(){
		super();
		power = 30;
	}
	
	/**************************************************************
	 * Constructor for RV
	 * @param array of objects... to constuct a RV with
	 *************************************************************/
	public RV(Object[] vars) {
		this((String) vars[0], (String)vars[2], (Integer) vars[4], (Integer) vars[1], (Integer) vars[3]);
	}
	
	/**************************************************************
	 * Constructor for RV
	 * @param power
	 *************************************************************/
	public RV(String name, String checkIn, int daysStaying, int siteNumber, int power) {
		super(name, checkIn, daysStaying,siteNumber);
		this.power = power;
	}

	/**************************************************************
	 * @return the power
	 *************************************************************/
	public int getPower() {
		return power;
	}

	/**************************************************************
	 * @param power the power to set
	 *************************************************************/
	public void setPower(int power) {
		this.power = power;
	}
	
	/******************************************************************
	 * Calculates the cost for RV
	 * @param days takes in the number of days
	 *****************************************************************/
	@Override
	public double calcCost(int days){
		return 30*days;
	}
	
	public static class Comparators{
		//4
		public static Comparator<Site> ASC_POWER = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return ((RV)s1).power - ((RV)s2).power;
			}
		};
		public static Comparator<Site> DES_POWER = new Comparator<Site>(){
			@Override
			public int compare(Site s1, Site s2) {
				return ((RV)s2).power - ((RV)s1).power;
			}
		};
		
	}
}
