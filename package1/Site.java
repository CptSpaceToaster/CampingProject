package package1;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Site implements Serializable {
		private static final long serialVersionUID = 1L;

		/** The name of the person who is occupying the Site */
		protected String nameReserving;

		/** The date the Site was checked-in (occupied) */
		protected GregorianCalendar checkIn;

		/** The estimated number of days the person is reserving */
		/** This is just an estimate when the camper is  */
		/** is checking in  */
		protected int daysStaying; 

		/** The date the Site was checked out */
		/** This is the exact day they checked-out */
		protected GregorianCalendar checkOutOn;

		/** The Site number */
		protected int siteNumber;
		
		/**************************************************************
		 * Default Constructor
		 *************************************************************/
		public Site(){
			nameReserving = "";
			checkIn = new GregorianCalendar();	
			checkOutOn = new GregorianCalendar();
			daysStaying = 0;
			siteNumber = 0;
		}
		
		/**************************************************************
		 * Constructor for Site
		 * @param in takes in the date of check in
		 * @param name takes in the name of the person
		 * @param days takes in the days staying
		 * @param out takes in the day of check out
		 * @param site takes in the site number
		 *************************************************************/
		public Site(GregorianCalendar in, String name, int days, 
								GregorianCalendar out, int site){
			nameReserving = name;
			checkIn = in;
			checkOutOn = out;
			daysStaying = days;
			siteNumber = site;
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
		public GregorianCalendar getCheckIn() {
			return checkIn;
		}

		/**************************************************************
		 * @param checkIn the checkIn to set
		 *************************************************************/
		public void setCheckIn(GregorianCalendar checkIn) {
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
		public GregorianCalendar getCheckOutOn() {
			return checkOutOn;
		}

		/**************************************************************
		 * @param checkOutOn the checkOutOn to set
		 *************************************************************/
		public void setCheckOutOn(GregorianCalendar checkOutOn) {
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

}
