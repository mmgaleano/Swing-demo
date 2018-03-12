package gui;


/** Represents a listener for the PrefsDialog.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public interface PrefsListener {

	
	/** Method for setting default values.
	 * @param user String for the user name.
	 * @param password String for the password.
	 * @param port integer for the port number.
	*/
	
	public void preferencesSet(String user, String password, int port);
	
}
