package package1;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;


import javax.swing.*;


public class DialogCheckInRV extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField nameTxt;
	private JTextField siteNumTxt;
	private JTextField checkInTxt;
	private JTextField daysTxt;
	private JTextField powerTxt;

	private JButton okButton;
	private JButton cancelButton;
	private int closeStatus;

	public static final int OK = 0;
	public static final int CANCEL = 1;
	
	private RV rv;
	public DialogCheckInRV(JFrame parent, RV r){

	super(parent, true);

	setTitle("Reserve A RV");
	closeStatus = CANCEL;
	setSize(400,200);

	rv = r;

	setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(6,2));

	panel.add(new JLabel("Name Reserving:"));
	nameTxt = new JTextField("John Doe", 30);
	panel.add(nameTxt);

	panel.add(new JLabel("Site Number:"));
	siteNumTxt = new JTextField("1", 30);
	panel.add(siteNumTxt);

	panel.add(new JLabel("Occupied On:"));
	checkInTxt = new JTextField("10/15/2013", 30);
	panel.add(checkInTxt);

	panel.add(new JLabel("Power needed:"));
	powerTxt = new JTextField("30");
	panel.add(powerTxt);
	
	panel.add(new JLabel("Days Staying:"));
	daysTxt = new JTextField("1");
	panel.add(daysTxt);

	getContentPane().add(panel, BorderLayout.CENTER);

	okButton = new JButton("OK");
	cancelButton = new JButton("Cancel");
	JPanel panel2 = new JPanel();
	panel2.add(okButton);
	panel2.add(cancelButton);
	getContentPane().add(panel2, BorderLayout.SOUTH);
	okButton.addActionListener(this);
	cancelButton.addActionListener(this);

	setSize(300,300);
	setVisible(true);

}

public void actionPerformed(ActionEvent e){
	JButton button = (JButton) e.getSource();
	if(button == okButton){
		closeStatus = OK;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date;
		try{
			date = dateFormat.parse(checkInTxt.getText());

			BetterGregorianCalendar calender = new BetterGregorianCalendar();
			calender.setTime(date);

			int siteNumber = Integer.parseInt(siteNumTxt.getText());
			int daysStaying = Integer.parseInt(daysTxt.getText());
			int power = Integer.parseInt(powerTxt.getText());

			rv.setPower(power);
			rv.setDaysStaying(daysStaying);
			rv.setSiteNumber(siteNumber);
			rv.setCheckIn(calender);
			rv.setNameReserving(nameTxt.getText());

		} catch (Exception error) {
			System.out.println ("Error");
			error.printStackTrace();
		}
	}
	
	dispose();
}

public int getCloseStatus(){
	return closeStatus;
}
}
