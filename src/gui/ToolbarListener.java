package gui;

/** Represents a Toolbar for the Main frame.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public interface ToolbarListener {
	
	
	/** Method to be executed when saving file.
	*/
	
	public void saveEventOccured();
	
	
	/** Method to be executed when refreshing the table.
	*/
	
	public void refreshEventOccured();

}
