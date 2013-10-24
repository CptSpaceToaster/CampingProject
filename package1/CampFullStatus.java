package package1;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;
public class CampFullStatus extends JDialog implements ActionListener{

	/**Default serial UID */
	private static final long serialVersionUID = 1L;
		
	/** table for data */
	private JTable outputTable;
	
	/** scroll panel for table */
	private JScrollPane scrollPane;
	
	/** label to display date given */
	private JLabel displayDate;
	
	/** table model */
	private StatusModel tableModel;
	
	/** OK Button */
	private JButton OKButton;
	
	public void checkStatus(BetterGregorianCalendar d) {
		// Instantiates the table model with the passed in date
		tableModel = new StatusModel(d);
		// loads the text file that was saved off in the StatusModel
		tableModel.loadFromText("CampStatus");
		// sets the JTable to the StatusModel
		outputTable = new JTable(tableModel);
		// instantiates a new JScollPane
		scrollPane = new JScrollPane(outputTable);
		// Displays the date of checked status
		displayDate = new JLabel("Camp status on: " + d.toString());
		// JButton for OK
		OKButton = new JButton("OK");
		// adds action listener to OK
		OKButton.addActionListener(this);
		
		// Creates a new panel
		JPanel panel = new JPanel();
		// Creates a panel for the JButton
		JPanel buttonPanel = new JPanel();
		// sets the layout for the button panel
		buttonPanel.setLayout(new FlowLayout());
		// sets the layout for the panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		// adds the display date
		panel.add(displayDate);
		// adds the scroll pane and table
		panel.add(scrollPane);
		// adds the ok button
		buttonPanel.add(OKButton);
		// adds button panel to the panel
		panel.add(buttonPanel);
		// add the panel to the dialog
		add(panel);
		
		// sets the size
		setSize(700,300);
		// sets visible to be true
		setVisible(true);
		
	}
	
	/******************************************************************
	 * This listens for buttons to be pressed in the GUI
	 * @param event used for the listener
	 *****************************************************************/
	public void actionPerformed(ActionEvent event) {
		// checks to see if the ok button was pushed
		if(event.getSource() == OKButton){
			dispose();
		}
	}
	
	

}
