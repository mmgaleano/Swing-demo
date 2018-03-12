
import javax.swing.SwingUtilities;

import gui.MainFrame;

/** Represents a wrapper for the MainFrame.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/
public class App {
	
	/** Main method.
	*/
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				
				new MainFrame();
			}
			
		});

	}

}
