package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;


/** Progress Dialog.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class ProgressDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	private JProgressBar progressBar;
	private ProgressDialogListener progressDialogListener;
	
	
	/** Constructor
	 * @param parent Window parent.
	 * @param title String representing the title.
	*/
	
	public ProgressDialog(Window parent, String title){
		super(parent, title, ModalityType.APPLICATION_MODAL); //modal
		
		initGUI(parent, title);
		setListeners();
		
	}
	
	
	/** Method for initializing the GUI elements
	 * @param parent Window parent.
	 * @param title String representing the title.
	*/

	private void initGUI(Window parent, String title){
		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setString(title);
		
		progressBar.setMaximum(10);
		
		setLayout(new FlowLayout());
		add(progressBar);
		add(cancelButton);
		
		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		pack();
		
		this.setLocationRelativeTo(parent);
	}
	
	
	/** Method for setting the GUI listeners
	 * @param parent Window parent.
	 * @param title String representing the title.
	*/
	
	private void setListeners(){
		cancelButton.addActionListener(new ActionListener(){  //Action for cancel button

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(progressDialogListener != null){
					progressDialogListener.progressDialogCancelled(); //Executing the interface
				}
			}
			
		});
		
		addWindowListener(new WindowAdapter(){ //Executing the interface

			@Override
			public void windowClosing(WindowEvent e) {
				if(progressDialogListener != null){
					progressDialogListener.progressDialogCancelled();
				}
			}
			
		});
	}
	
	
	/** Method for setting the ProgressDialogListener
	 * @param progressDialogListener ProgramDialogListener.
	*/
	
	public void setListener(ProgressDialogListener progressDialogListener){
		this.progressDialogListener = progressDialogListener;
	}

	
	/** Method for setting the max value to the ProgressBar
	 * @param value Integer for the number to set.
	*/
	
	public void setMax(int value){
		progressBar.setMaximum(value);
		
	}
	
	
	/** Method for setting the value to the ProgressBar
	 * @param value Integer for the number to set.
	*/
	
	public void setValue(int value){
		
		int progress = 100*value / progressBar.getMaximum();
		progressBar.setString(String.format("%d%% complete", progress));
		
		progressBar.setValue(value);
	}

	
	/** Method for setting the ProgressBar visibility.
	 * @param visible final boolean for the visibility option.
	*/
	
	@Override
	public void setVisible(final boolean visible) {
		
		SwingUtilities.invokeLater(new Runnable(){ //Like Asynctask in Android

			@Override
			public void run() {
				
				if(visible == false){  
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				else{
					progressBar.setValue(0);
				}
				
				if(visible){
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				}
				else{
					setCursor(Cursor.getDefaultCursor());
				}
				
				ProgressDialog.super.setVisible(visible);
				
			}
			
		});
	}	
}
