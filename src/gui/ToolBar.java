package gui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;


/** Represents a ToolBar for the Main frame.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/
public class ToolBar extends JToolBar implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8294154099106005191L;
	private JButton saveButton;
	private JButton refreshButton;
	
	private ToolbarListener toolbarListener;
	
	/** Creates the ToolBar.
	*/
	public ToolBar(){
		
		initGUIComponents();
		setListeners();
		setMNemonics();
		
	}
	
	
	/** Initializes GUI for the ToolBar.
	*/
	private void initGUIComponents(){
		
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false); //For not letting the ToolBar to be floatable
		
		saveButton = new JButton();
		refreshButton = new JButton();
		
		Dimension btnSize = new Dimension(100, 40);  //26 is default height
		saveButton.setPreferredSize(btnSize);
		refreshButton.setPreferredSize(btnSize);
		
		ImageIcon saveIcon = Utils.createIcon("/images/save.png");
		ImageIcon refreshIcon = Utils.createIcon("/images/refresh.png");
		
		saveButton.setIcon(saveIcon);
		refreshButton.setIcon(refreshIcon);
		
		saveButton.setToolTipText("Save Table to DB");
		refreshButton.setToolTipText("Refresh Table from DB");
		
		addSeparator();
		add(saveButton);
		add(refreshButton);
		
	}
	

	/** Sets the Toolbar’s listener.
	 * @param listener a ToolbarListener object.
	*/
	public void setToolbarListener(ToolbarListener listener){
		this.toolbarListener = listener;
	}

	
	/** Overrides actionPerformed method.
	 * @param event An ActionEvent for occurred event.
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
		
		JButton bClicked = (JButton)event.getSource();
		
		if(bClicked == saveButton){
			
			if(toolbarListener != null){
				toolbarListener.saveEventOccured(); //Executing the interface
				
			}
		}
		
		else if(bClicked == refreshButton){
			
			if(toolbarListener != null){
				toolbarListener.refreshEventOccured(); //Executing the interface
				
			}
		}
		
	}
	
	
	/** Set Listeners for the GUI elements.
	*/
	private void setListeners(){
		
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
	}
	
	/** Set MNemonics for the GUI elements.
	*/
	private void setMNemonics(){
		
		saveButton.setMnemonic(KeyEvent.VK_H);
		refreshButton.setMnemonic(KeyEvent.VK_B);
		
	}

}
