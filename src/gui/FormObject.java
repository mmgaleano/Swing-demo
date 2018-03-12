package gui;

/** Represents the application's controller.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class FormObject {

	private String name;
	private String occupation;
	private int ageCategory;
	private String empCat;
	private String taxID;
	private boolean usCitizen;
	private String gender;
	
	
	/** Form object constructor.
	 * @param name A String representing the person's name.
	 * @param occupation A String representing the person's occupation.
	 * @param ageCategory An int representing the person's ageCategory.
	 * @param empCat A String representing the person's employment category
	 * @param taxID A String representing the person's ID.
	 * @param usCitizen A String representing if the person is US citizen or not.
	 * @param gender A String representing the person's gender.
	*/
	
	public FormObject(String name, String occupation, int ageCategory, String empCat, String taxID, boolean usCitizen, String gender) {
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empCat = empCat;
		this.taxID = taxID;
		this.usCitizen = usCitizen;
		this.gender = gender;
	}
	
	
	/** Method for getting the name from the FormObject.
	 * @return name A String representing the FormObject's name.
	*/

	public String getName() {
		return name;
	}

	
	/** Method for setting the name in the FormObject.
	 * @param name A String representing the new name for the FormObject .
	*/
	
	public void setName(String name) {
		this.name = name;
	}

	
	/** Method for getting the Occupation from the FormObject.
	 * @return occupation A String representing the FormObject's occupation.
	*/
	
	public String getOccupation() {
		return occupation;
	}

	
	/** Method for setting the Occupation in the FormObject.
	 * @param name A String representing the new occupation for the FormObject .
	*/
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
	/** Method for getting the AgeCategory from the FormObject.
	 * @return ageCategory A String representing the FormObject's AgeCategory.
	*/
	
	public int getAgeCategory(){
		return ageCategory;
	}
	
	
	/** Method for getting the EmploymentCategory from the FormObject.
	 * @return empCat A String representing the FormObject's EmploymenCategory.
	*/
	
	public String getEmploymentCategory(){
		return empCat;
	}
	
	
	/** Method for getting the TaxID from the FormObject.
	 * @return taxID A String representing the FormObject's TaxID.
	*/
	
	public String getTaxID(){
		return taxID;
	}
	
	
	/** Method for checking is the FormObject is a US citizen or not.
	 * @return usCitizen A Boolean true for US citizen and false if not.
	*/
	
	public boolean isUsCitizen(){
		return usCitizen;
	}
	
	
	/** Method for getting the Gender from the FormObject.
	 * @return gender A String representing the FormObject's Gender.
	*/
	
	public String getGender(){
		return gender;
	}

}
