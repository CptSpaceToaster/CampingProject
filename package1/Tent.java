package package1;

public class Tent extends Site {
	/** Represents the number of tenters on this site */
	private int numOfTenters;

	public Tent(){
	}
	/**************************************************************
	 * Constructor for tents
	 * @param tenters
	 *************************************************************/
	public Tent(int tenters) {
		super();
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
