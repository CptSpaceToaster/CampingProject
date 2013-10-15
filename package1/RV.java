package package1;
import java.util.*;
public class RV extends Site {
	
	/** Represents the power supplied to the site */
	private int power; // 30, 40, 50 amps of service.

	public RV(){
		super();
	}
	
	/**************************************************************
	 * Constructor for RV
	 * @param power
	 *************************************************************/
	public RV(String name, GregorianCalendar checkIn, int daysStaying, int siteNumber, int power) {
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
	
	
}
