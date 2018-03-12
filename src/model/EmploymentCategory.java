package model;

/** EmploymentCategory enum.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public enum EmploymentCategory {

	employed("Employed"),
	selfemployed("Self employed"),
	unemployed("Unemployed"),
	other("Other");
	
	
	private String text;
	
	
	/** EmploymentCategory constructor.
	 * @param text String that represents enum value
	*/
	
	private EmploymentCategory(String text){
		
		this.text = text;
		
	}
	
	
	/** toString method.
	 * @return text String text to display.
	*/
	
	public String toString(){
		return text;
	}
}
