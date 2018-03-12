package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;


/** Represents a FormPanel.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class FormPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JLabel ageLabel;
	private JLabel employmentLabel;
	private JLabel taxLabel;
	private JLabel usCitizenLabel;
	private JLabel genderLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JTextField taxField;
	private JButton okButton;
	private FormListener formListener;
	private JList<AgeCategory> ageList;
	private JComboBox<EmploymentCategory> empCombo;
	private JCheckBox citizenCheck;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	

	/** FormPanel constructor.
	*/
	
	public FormPanel(){
		
		Dimension dimMin = getPreferredSize();
		dimMin.width = 260;
		setMinimumSize(dimMin);
		
		Border innerBorder = BorderFactory.createTitledBorder("  Add Person  ");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		initMainPanel();
		defineListBox();
		defineComboBox();
		placeInGridLayout();
		addListeners();
		setMNemonics();
	}


	/** Method that initializes the Main Elements of the panel.
	*/
	
	private void initMainPanel(){
		
		nameLabel = new JLabel("Name : ");
		occupationLabel = new JLabel("Occupation : ");
		ageLabel = new JLabel("Age : ");
		employmentLabel = new JLabel("Employment : ");
		taxLabel = new JLabel("Tax ID : ");
		usCitizenLabel = new JLabel("US citizen");
		genderLabel = new JLabel("Gender : ");
		
		taxLabel.setEnabled(false);
		
		
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		taxField = new JTextField(10);
		
		taxField.setEnabled(false);
		
		okButton = new JButton("OK");
		ageList = new JList<AgeCategory>();
		empCombo = new JComboBox<EmploymentCategory>();
		citizenCheck = new JCheckBox();
		
		maleRadio = new JRadioButton(" Male");
		femaleRadio = new JRadioButton(" Female");
		genderGroup = new ButtonGroup();
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);
		
		setLayout(new GridBagLayout());
	}
	
	
	/** Method that initializes the list for AgeCategory items.
	*/
	
	public void defineListBox(){
		
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)ageList.getCellRenderer();  //For aligning
		renderer.setHorizontalAlignment(JLabel.CENTER); 
		
		DefaultListModel<AgeCategory> ageModel = new DefaultListModel<AgeCategory>();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1,"18 to 65"));
		ageModel.addElement(new AgeCategory(2,"65 or over"));
		ageList.setModel(ageModel);
		ageList.setPreferredSize(new Dimension(110, 68));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);
		
	}
	
	
	/** Method that initializes the ComboBox for the EmplymentCategory items.
	*/
	
	public void defineComboBox(){

		DefaultListCellRenderer renderer = new DefaultListCellRenderer();  //For aligning
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
		empCombo.setRenderer(renderer); 
		
		DefaultComboBoxModel<EmploymentCategory> empModel = new DefaultComboBoxModel<EmploymentCategory>(); 
		empModel.addElement(new EmploymentCategory(0,"employed"));
		empModel.addElement(new EmploymentCategory(1,"self-employed"));
		empModel.addElement(new EmploymentCategory(2,"unemployed"));
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
	}
	
	
	/** Method that places GUI elements in the Grid.
	*/
	
	private void placeInGridLayout(){
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);  //For leaving some space
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(ageLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(employmentLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empCombo, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(usCitizenLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.06;
		
		gc.gridx = 0;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(genderLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 1;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gc);
		
		//row
		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridx = 1;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okButton, gc);
		
	}
	
	
	/** Method that adds listeners to GUI elements.
	*/
	
	public void addListeners(){
		
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		
		okButton.addActionListener(new ActionListener(){  //Action for the OK Button

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = ageList.getSelectedValue();
				EmploymentCategory empCat = (EmploymentCategory)empCombo.getSelectedItem();
				String taxID = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();
				
				String genderCommand = genderGroup.getSelection().getActionCommand();
				
				FormObject ev = new FormObject(name, occupation, ageCat.getId(), empCat.toString(), taxID, usCitizen, genderCommand); //Forming object
				//AgeCategory uses int just for db purposes, but it could something else, just as empCat
				
				if(formListener != null){
					formListener.formEventOccurred(ev);
				}
			}
			
		});
		
		citizenCheck.addActionListener(new ActionListener(){ //Listener for the citizen check box

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = citizenCheck.isSelected();
				
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
				
				taxField.requestFocus();
			}
			
		});
		
	}
	
	
	/** Method for setting the FormListener to the FormPanel.
	*/
	
	public void setFormListener(FormListener formListener) { 
		this.formListener = formListener;
	}

	
	/** Method for setting the MNemonics needed.
	*/
	
	private void setMNemonics(){
		
		okButton.setMnemonic(KeyEvent.VK_O);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		occupationLabel.setDisplayedMnemonic(KeyEvent.VK_C);
		occupationLabel.setLabelFor(occupationField);
		
		ageLabel.setDisplayedMnemonic(KeyEvent.VK_A);
		ageLabel.setLabelFor(ageList);
		
		employmentLabel.setDisplayedMnemonic(KeyEvent.VK_E);
		employmentLabel.setLabelFor(empCombo);
		
		usCitizenLabel.setDisplayedMnemonic(KeyEvent.VK_U);
		usCitizenLabel.setLabelFor(citizenCheck);
		
		taxLabel.setDisplayedMnemonic(KeyEvent.VK_T);
		taxLabel.setLabelFor(taxField);
		
		maleRadio.setMnemonic(KeyEvent.VK_M);
		femaleRadio.setMnemonic(KeyEvent.VK_L);
		
	}
	
	
}
