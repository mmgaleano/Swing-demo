package gui;


/** Represents fake info from a fake server.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class ServerInfo {

	private String name;
	private int id;
	private boolean checked;

	
	/** Constructor.
	 * @param name String representing a name
	 * @param id Integer representing an id
	 * @param checked Boolean representing a check condition
	*/
	
	public ServerInfo(String name, int id, boolean checked) {

		this.name = name;
		this.id = id;
		this.checked = checked;

	}

	
	/** Method for getting the Id value from ServerInfo.
	 * @return id Integer for the Id.
	*/
	
	public int getId() {
		return id;
	}

	
	/** Overriding the toString method.
	 * @return name String representing the name.
	*/
	
	@Override
	public String toString() {
		return name;
	}
	
	
	/** Method for checking the checked condition from ServerInfo.
	 * @return checked Boolean true if the element is checked, false if not.
	*/
	
	public boolean isChecked(){
		return checked;
	}

	
	/** Method for setting the check condition in ServerInfo.
	*/
	public void setChecked(boolean checked){
		this.checked = checked;
	}
}
