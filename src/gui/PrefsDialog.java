package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


/** Represents a Dialog for setting db settings.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class PrefsDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JFrame parent;
	private JPanel controlsPanel, buttonsPanel;
	private JButton okButton, cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JLabel portLabel, userLabel, passwordLabel;
	private JTextField userField;
	private JPasswordField passField;
	private PrefsListener prefsListener;
	
	
	/** Constructor.
	*/
	
	public PrefsDialog(JFrame parent){
		
		super(parent, "Preferences", false);
		
		this.parent = parent;
		
		initGUIComponents();
		setListeners();
		
		SwingUtilities.getRootPane(okButton).setDefaultButton(okButton); //Set OK button for Enter key
	}


	/** Method for initialize the GUI components.
	*/
	
	private void initGUIComponents() {
		
		controlsPanel = new JPanel();
		buttonsPanel = new JPanel();
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerModel);
		
		portLabel = new JLabel("Port: ");
		userLabel = new JLabel("User: ");
		passwordLabel = new JLabel("Password: ");
		
		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		initControlsPanel();
		initButtonsPanel();
		addSubPanels();
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		setSize(340, 250);
		
		
	}

	
	/** Method for initialize Control Panel.
	*/
	
	private void initControlsPanel() {
		
		Border spaceBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		
		//First row
		
		gc.gridy = 0;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(userLabel, gc);
		
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(userField, gc);
		
		
		//Next row
		
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(passwordLabel, gc);
		
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(passField, gc);
		
		
		//Next row
		
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(portLabel, gc);
		
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(portSpinner, gc);
		
	}

	
	/** Method for initialize the buttons panel.
	*/
	
	private void initButtonsPanel() {
		
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
	}
	
	
	/** Method for adding sub-panels.
	*/
	
	private void addSubPanels() {
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
				
		
	}
	
	
	/** Method for listeners to the GUI elements.
	*/
	
	public void setListeners(){
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		 addWindowListener(new WindowAdapter() {

			 
			 	/** Overriding the windowClosing method for handling the closing event.
			 	 * @param e WindowEvent reference.
				*/
			 
	            @Override
	            public void windowClosing(WindowEvent e) {
	            	parent.setEnabled(true);
	    			setVisible(false);
	            }
	        });
	}
	
	
	/** Override the actionPerformed method.
	 * @param e ActionEvent reference.
	*/

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == okButton){ //Action for okButton
			
			if(prefsListener != null){
				
				prefsListener.preferencesSet(userField.getText(), new String(passField.getPassword()), (Integer)portSpinner.getValue());
				parent.setEnabled(true);
			}
			
			setVisible(false);
			
		}
		else if(obj == cancelButton){ //Action for cancelButton
			parent.setEnabled(true);
			setVisible(false);
		}
		
	}

		
	/** Method for setting PrefsListener interface
	 * @param prefsListener PrefsListener reference.
	*/
	
	public void setPrefsListener(PrefsListener prefsListener){
		
		this.prefsListener = prefsListener;
	}
	
	
	/** Method for setting default values.
	 * @param user String for the user name.
	 * @param password String for the password.
	 * @param port integer for the port number.
	*/
	
	public void setDefaults(String user, String password, int port){
		
		userField.setText(user);
		passField.setText(password);
		portSpinner.setValue(port);
		
	}
}
