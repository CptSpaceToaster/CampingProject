package package1;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BetterGregorianCalendar extends GregorianCalendar{
	/**
	 * Default Serialization ID......
	 */
	private static final long serialVersionUID = 1L;

	int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	private int[] monthNum = {0,1,2,3,4,5,6,7,8,9,10,11,12};
	
	/** this array is for the names of the months */
	private String[] monthName = {"","January", "Febuary", "March", "April",
			"May", "June", "July", "August", "September", "October", 
			"November", "December"};
	
	/******************************************************************
	 * This helper method takes a given year and month and returns the
	 * number of days in the given month
	 * 
	 * @param pmonth The target month
	 * @param pyear The target year
	 * @return The number of days in the target month, returns 0 
	 * 			if an invalid month is used
	 *****************************************************************/
	private int daysInMonth(int month, int year) {
		return monthDays[month]+(isLeapYear(year)?1:0);
	}
	
	/******************************************************************
	 * Used to count how many days have passed from January 1st of 
	 * this year.
	 * 
	 * @return The number of days passed from January 1st of this year
	 *****************************************************************/
	public int ordinalDate() {
		int result = this.get(Calendar.DAY_OF_MONTH);
		for(int i=this.get(Calendar.MONTH)-1; i>0; i--) {
			result += daysInMonth(i,this.get(Calendar.YEAR));
		}
		return result;
	}
	
	
	/******************************************************************
	 * A method that counts the number of days between this BetterGregorianCalendar
	 * and a given BetterGregorianCalendar.  If this BetterGregorianCalendar is ahead of the 
	 * given BetterGregorianCalendar, then the result will be positive.  If this 
	 * BetterGregorianCalendar chronologically behind the given BetterGregorianCalendar, then the
	 * result will be negative
	 * 
	 * E.g. BetterGregorianCalendar(1,1,2000).daysFrom(BetterGregorianCalendar(12,31,1999)) will 
	 * return 1
	 * 
	 * @param other The given BetterGregorianCalendar to compare this BetterGregorianCalendar with
	 * @return The number of days between the two dates, can be 
	 * 			positive or negative depending on which BetterGregorianCalendar
	 * 			comes first chronologically
	 *****************************************************************/
	public int daysSince(BetterGregorianCalendar other) {
		int j = compareTo(other);
		
		if (j != 0) {
			int  daysSince = j*(this.ordinalDate() - other.ordinalDate());
			for (int i = 0 ; i < Math.abs(this.get(Calendar.YEAR) - other.get(Calendar.YEAR)); i++) {
				daysSince += (this.isLeapYear(i)?366:365);
			}
			return j*daysSince;
		} else {
			return 0;
		}
	}
	
	/******************************************************************
	 * Takes a GregorianCalendar in the format of a string 
	 * and changes it to DD Month Year
	 * @return returns a String formated MM/DD/YYYY
	 *****************************************************************/
	public String toString(){
		String date = "";
		for(int i = 1; i <= this.get(Calendar.MONTH); i++){
			// find the specific month
			if(monthNum[i] == this.get(Calendar.MONTH)){
				// adds the month to the date String
				date += monthNum[i+1] + "/"; 
			}
		}
		String zeroDay;
		// if the day is less than 10, add a 0
		if(this.get(Calendar.DAY_OF_MONTH) < 10)
			zeroDay = "0" + this.get(Calendar.DAY_OF_MONTH);
		else
			zeroDay = "" + this.get(Calendar.DAY_OF_MONTH);
		date += zeroDay;
		
		date += "/" +  this.get(Calendar.YEAR);
		return date;
	}

}
