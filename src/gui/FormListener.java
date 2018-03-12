package gui;

/** Interface for detecting an Event related to the FormListener a FormPanel.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public interface FormListener {

	/** Method for Creating Event Objects.
	 * @param e FormObject reference.
	*/
	
	public void formEventOccurred(FormObject e);
}
