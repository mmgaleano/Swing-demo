package model;

import java.io.Serializable;


/** Represents a Person.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class Person implements Serializable {
	
	private static final long serialVersionUID = -7275639526502411856L;
	private static int count = 1;
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory empCat;
	private String taxID;
	private boolean usCitizen;
	private Gender gender;
	
	
	/** Constructor.
	 * @param name String representing the name of the Person.
	 * @param occupation String representing the occupation of the Person.
	 * @param ageCategory AgeCategory representing the Age Category of the Person.
	 * @param empCat EmploymentCategory representing the Employment Category of the Person.
	 * @param taxID String representing the taxID of the person.
	 * @param usCitizen boolean True if US citizen, False if not.
	 * @param gender Gender reference for representing the gender of the person.
	*/
	
	public Person(String name, String occupation, AgeCategory ageCategory, EmploymentCategory empCat, String taxID,
			boolean usCitizen, Gender gender) {
	
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empCat = empCat;
		this.taxID = taxID;
		this.usCitizen = usCitizen;
		this.gender = gender;
		
		this.id = count;
		count++;
	}
	
	
	/** Constructor.
	 * @param id integer for the Person ID.
	 * @param name String representing the name of the Person.
	 * @param occupation String representing the occupation of the Person.
	 * @param ageCategory AgeCategory representing the Age Category of the Person.
	 * @param empCat EmploymentCategory representing the Employment Category of the Person.
	 * @param taxID String representing the taxID of the person.
	 * @param usCitizen boolean True if US citizen, False if not.
	 * @param gender Gender reference for representing the gender of the person.
	*/
	
	public Person(int id, String name, String occupation, AgeCategory ageCategory, EmploymentCategory empCat, String taxID,
			boolean usCitizen, Gender gender) {
		
		this(name, occupation, ageCategory, empCat, taxID, usCitizen, gender);
		
		this.id = id;
	
	}

	
	/** Method for getting the ID of the Person.
	 * @return id integer for the Person ID.
	*/
	
	public int getId() {
		return id;
	}
	
	
	/** Method for setting the ID of the Person.
	 * @param id integer for the Person ID.
	*/
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	/** Method for getting the name of the Person.
	 * @return name String for the Person name.
	*/
	
	public String getName() {
		return name;
	}
	
	
	/** Method for setting the name of the Person.
	 * @param name String for the name of the person.
	*/
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	/** Method for getting the occupation of the Person.
	 * @return occupation String for the occupation of the person.
	*/
	
	public String getOccupation() {
		return occupation;
	}
	
	
	/** Method for setting the occupation of the Person.
	 * @param occupation String for the occupation of the person.
	*/
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
	/** Method for getting the AgeCategory of the Person.
	 * @return ageCategory AgeCategory for the AgeCategory of the person.
	*/
	
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	
	
	/** Method for setting the AgeCategory of the Person.
	 * @param ageCategory AgeCategory for the AgeCategory of the person.
	*/
	
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	
	
	/** Method for getting the EmploymentCategory of the Person.
	 * @return empCat EmploymentCategory for the AgeCategory of the person.
	*/
	
	public EmploymentCategory getEmpCat() {
		return empCat;
	}
	
	
	/** Method for setting the EmploymentCategory of the Person.
	 * @param empCat EmploymentCategory for the AgeCategory of the person.
	*/
	
	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}
	
	
	/** Method for getting the taxID of the Person.
	 * @return taxID String for the taxID of the person.
	*/
	
	public String getTaxID() {
		return taxID;
	}
	
	
	/** Method for setting the taxID of the Person.
	 * @param taxID String for the taxID of the person.
	*/
	
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}
	
	
	/** Method for getting the usCitizen boolean value of the Person.
	 * @return usCitizen boolean true is YES, FALSE if not.
	*/
	
	public boolean isUsCitizen() {
		return usCitizen;
	}
	
	
	/** Method for setting the usCitizen boolean value of the Person.
	 * @param usCitizen boolean true is YES, FALSE if not.
	*/
	
	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}
	
	
	/** Method for getting the gender of the Person.
	 * @return gender Gender of the person.
	*/
	
	public Gender getGender() {
		return gender;
	}
	
	
	/** Method for setting the gender of the Person.
	 * @param gender Gender of the person.
	*/
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	
	/** Overriding the toString function.
	 * @return String person information.
	*/
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", occupation=" + occupation + ", ageCategory=" + ageCategory
				+ ", empCat=" + empCat + ", taxID=" + taxID + ", usCitizen=" + usCitizen + ", gender=" + gender + "]";
	}

	
	
	
}
