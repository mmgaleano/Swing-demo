package controller;

import model.AgeCategory;
import model.EmploymentCategory;
import model.Gender;


/** Utility class for the controller package.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class Utils {

	
	/** Method for defining the AgeCategory based on numeric index.
	 * @param index integer for the numeric index.
	 * @return AgeCategory AgeCategory value.
	*/
	
	public static AgeCategory defineAgeCategory(int index){
		
		switch(index){
		
		case 0:
			return AgeCategory.child;
			
		case 1:
			return AgeCategory.adult;
			
		case 2:
			return AgeCategory.senior;
			
		default:
			return null;
		}
		
	}
	
	
	/** Method for defining the EmploymentCategory based on a String.
	 * @param cat String value.
	 * @return EmploymentCategory EmploymentCategory value.
	*/
	
	public static EmploymentCategory defineEmploymentCategory(String cat){
		
		if(cat.equals("employed")){
			return EmploymentCategory.employed;
		}
		
		else if(cat.equals("self-employed")){
			return EmploymentCategory.selfemployed;
		}
		
		else if(cat.equals("unemployed")){
			return EmploymentCategory.unemployed;
		}
		else{
			return EmploymentCategory.other;
		}
		
	}
	
	
	/** Method for defining the Gender Category based on a String.
	 * @param cat String value.
	 * @return EmploymentCategory EmploymentCategory value.
	*/
	
	public static Gender defineGenderCategory(String cat){
		
		if(cat.equals("male")){
			return Gender.male;
		}
		else{
			return Gender.female;
		}
		
	}
	
}
