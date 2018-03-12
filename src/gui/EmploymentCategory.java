package gui;

/** EmploymentCategory enum.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class EmploymentCategory {
	
	private int id;
	private String text;
	
	
	/** EmploymentCategory constructor.
	 * @param id integer for the EmploymentCategory id.
	 * @param text String for the EmployementCategory text value.
	*/
	
	public EmploymentCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	
	/** toString method.
	 * @return text String for the text value in EmploymentCategory.
	*/
	
	public String toString(){
		return text;
	}
	
	
	/** Method for getting the ID of the EmploymentCategory.
	 * @return id int for the id value in EmploymentCategory.
	*/
	
	public int getId(){
		return id;
	}

}
