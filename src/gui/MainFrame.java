package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.Box;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import controller.Controller;

/** MainFrame class.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class MainFrame extends JFrame implements ActionListener {
	
	private Controller controller;
	private ToolBar toolBar;
	private FormPanel formPanel;
	private JMenuBar menuBar;
	private JMenu fileMenu, windowMenu, showMenu;
	private JMenuItem exportDataItem, importDataItem, exitItem, prefsItem;
	private JCheckBoxMenuItem showFormItem;
	private JFileChooser fileChooser;
	private TablePanel tablePanel;
	private MessagePanel messagePanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitPane;
	private JTabbedPane tabPanel;
	
	
	/** MainFrame constructor.
	*/
	
	public MainFrame () {
		super("Hello World");
		
		prefs = Preferences.userRoot().node("db");
		controller = new Controller();
		
		initGUIelements();
		setListeners();
		setMNemonics();
		setAccelerators();
		fixDisplay();
		
	}

	
	/** Method that initializes the GUI .
	*/
	
	public void initGUIelements() {
		
		this.setTitle("Mike Swing DEMO");
		
		setLayout(new BorderLayout());
		
		toolBar = new ToolBar();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		tabPanel = new JTabbedPane();
		messagePanel = new MessagePanel(this);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabPanel);
		tabPanel.add("Person Data Base", tablePanel);
		tabPanel.add("Messages", messagePanel);
		
		setMinimumSize(new Dimension(1100, 500));
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
		add(toolBar, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);
		
		tablePanel.setData(controller.getPeople());
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new PersonalFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		setJMenuBar(createMenuBar());
		
		prefsDialog = new PrefsDialog(this); 
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		int port = prefs.getInt("port",3306);
		prefsDialog.setDefaults(user, password, port);
		
		try {
			controller.configure(port, user, password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	
	/** Method setting listeners to the GUI.
	*/
	
	public void setListeners(){

		toolBar.setToolbarListener(new ToolbarListener(){

			
			/** Overriding the saveEventOccurred.
			*/
			
			@Override
			public void saveEventOccured() { //Save event
				
				connect();
				
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Can't save to Database", "Database connection problem", JOptionPane.ERROR_MESSAGE);
				}
			}

			
			/** Overriding the refreshEventOccured.
			*/
			
			@Override
			public void refreshEventOccured() {  //Refresh event
				
				connect();
				
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Can't load Database", "Database connection problem", JOptionPane.ERROR_MESSAGE);
				}
				
				tablePanel.refresh();
				
			}
			
		});
		
		formPanel.setFormListener(new FormListener(){
			
			
			/** Implementation of FormListener.
			 * @param e FormObject reference.
			*/
			
			public void formEventOccurred(FormObject e){
				
				controller.addPerson(e);
				tablePanel.refresh();
				
			}
		});
		
		tablePanel.setPersonTableListener(new PersonTableListener(){
			
			
			/** Implementation of PersonTableListener.
			 * @param row integer for row index.
			*/
			
			public void rowDeleted(int row){
				
				try {
					controller.removePerson(row);
					tablePanel.refresh();
				} catch (SQLException e) {
					
					JOptionPane.showMessageDialog(MainFrame.this, "Error deleting the entry from database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		prefsDialog.setPrefsListener(new PrefsListener() {
			
			/** Implementing the PrefsListener.
			 * @param userm String for the user name
			 * @param password String for the password
			 * @param port integer for the port number
			*/
			
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
				
				try {
					controller.configure(port, user, password);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to re-connect to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		tabPanel.addChangeListener(new ChangeListener(){

			
			/** Overriding the stateChanged method. It will refresh the Tree when the user selects the Message Tab.
			 * @param arg0 ChangeEvent reference.
			*/
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				int tabIndex = tabPanel.getSelectedIndex();
				
				if(tabIndex == 1){
					messagePanel.refresh();
				}
			}
		});
		
		addWindowListener(new WindowAdapter(){

			
			/** Overriding the windowClosing method for defining the actions to do on closing.
			 * @param arg0 ChangeEvent reference.
			*/
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				controller.disconnect();
				dispose();
				System.gc();
			}
		});
		
		showFormItem.addActionListener(this);
		exitItem.addActionListener(this);
		importDataItem.addActionListener(this);
		exportDataItem.addActionListener(this);
		prefsItem.addActionListener(this);

	}
	
	
	/** Method for creating the MenuBar.
	 * @param arg0 ChangeEvent reference.
	*/
	
	private JMenuBar createMenuBar(){
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		exportDataItem = new JMenuItem("Export Data   ");
		importDataItem = new JMenuItem("Import Data   ");
		prefsItem = new JMenuItem("Preferences. . .   ");
		exitItem = new JMenuItem("Exit   ");
		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		windowMenu = new JMenu("Window");
		
		showMenu = new JMenu("Show");
		showFormItem = new JCheckBoxMenuItem(" Person Form ");
		showFormItem.setSelected(true);
		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		
		menuBar.add( Box.createHorizontalStrut( 10 ) );
		menuBar.add(fileMenu);
		menuBar.add( Box.createHorizontalStrut( 10 ) ); 
		menuBar.add(windowMenu);

		return menuBar;
	}
	
	
	/** Method for setting Mnemonics.
	*/
	
	private void setMNemonics(){
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exportDataItem.setMnemonic(KeyEvent.VK_E);
		importDataItem.setMnemonic(KeyEvent.VK_I);
		exitItem.setMnemonic(KeyEvent.VK_X);
		showFormItem.setMnemonic(KeyEvent.VK_S);
		
		windowMenu.setMnemonic(KeyEvent.VK_W);
		showMenu.setMnemonic(KeyEvent.VK_S);
		prefsItem.setMnemonic(KeyEvent.VK_P);
		
		tabPanel.setMnemonicAt(0, KeyEvent.VK_D);
		tabPanel.setMnemonicAt(1, KeyEvent.VK_M);
		
	}
	
	
	/** Method for setting Accelerators.
	*/
	
	private void setAccelerators(){
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		showFormItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		
	}
	   

	/** Overriding the actionPerformed method.
	*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == showFormItem){  //Action when show action is selected.
	
			if(showFormItem.isSelected()){
				splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
			}
			formPanel.setVisible(showFormItem.isSelected());
		
		}
		else if(obj == exitItem){  //Action for exit
			
			int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
			
			if(action == JOptionPane.OK_OPTION){
				
				WindowListener[] listeners = getWindowListeners();
				
				for(WindowListener listener : listeners){
					
					listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					
				}
				
			}
			
		}
		else if(obj == importDataItem){ //Action for importing data.
			
			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				
				try {
					controller.loadFromFile(fileChooser.getSelectedFile());
					tablePanel.refresh();
				} catch (IOException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(MainFrame.this, "Cannot import the file", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(obj == exportDataItem){  //Action for exporting data.
			
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				
				try {
					controller.saveToFile(fileChooser.getSelectedFile());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(MainFrame.this, "Cannot export the file", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(obj == prefsItem){  //Action for preferences.
			
			prefsDialog.setVisible(true);
			this.setEnabled(false);
		}
		
	}
	
	
	/** Method for connecting to the database.
	*/
	
	public void connect(){
		
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Can't connecto to Database", "Database connection problem", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	/** Method for fixing the relative location of the GUI.
	*/
	
	private void fixDisplay() {

		prefsDialog.setLocationRelativeTo(null);
		
	}
}
