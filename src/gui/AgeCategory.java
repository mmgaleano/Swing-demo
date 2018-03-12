package gui;

/** Represents a AgeCategory.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class AgeCategory {

	private int id;
	private String text;
	
	
	/** AgeCategory constructor.
	 * @param id integer for category id.
	 * @param text String for the content.
	*/
	
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	
	/** toString method.
	 * @return text String to be displayed.
	*/
	
	public String toString(){
		return text;
	}
	
	
	/** Method for getting the ID.
	 * @return id integer of the AgeCategory.
	*/
	
	public int getId(){
		return id;
	}
	
}
