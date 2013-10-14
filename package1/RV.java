package package1;

public class RV extends Site {
	
	/** Represents the power supplied to the site */
	private int power; // 30, 40, 50 amps of service.

	/**************************************************************
	 * Constructor for RV
	 * @param power
	 *************************************************************/
	public RV(int power) {
		super();
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
	
	
}
