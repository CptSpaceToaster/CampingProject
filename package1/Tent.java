package package1;

public class Tent extends Site {
	
	/** default serial version UID */
	private static final long serialVersionUID = 1L;
	
	/** Type for Tent **/
	public static final int TYPE = 1;
	
	/** Represents the number of tenters on this site */
	private int numOfTenters;

	/******************************************************************
	 * default constructor for tent
	 *****************************************************************/
	public Tent(){
		super();
		numOfTenters = 1;
	}
	
	/**************************************************************
	 * Constructor for tents
	 * @param tenters
	 *************************************************************/
	public Tent(Object[] vars) {
		this((String) vars[0], (String)vars[2], (Integer) vars[4], (Integer) vars[1], (Integer) vars[3]);
	}
	
	/**************************************************************
	 * Constructor for tents
	 * @param tenters
	 *************************************************************/
	public Tent(String name, String checkIn, int daysStaying, int siteNumber, int tenters) {
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
	
	/******************************************************************
	 * Calculate the costs for Tent
	 * @param days the number of days staying
	 * @return double the cost of the stay
	 *****************************************************************/
	@Override
	public double calcCost(int days) {
		return 3*days*numOfTenters;
	}
	
	
}
