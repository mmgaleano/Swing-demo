package controller;

import model.AgeCategory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import gui.FormObject;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;


/** Represents the application's controller.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class Controller {

	Database db = new Database();
	
	/** Method for adding a person into the database.
	 * @param formObject FormObject object
	*/
	
	public void addPerson(FormObject formObject){
		
		String name = formObject.getName();
		String occupation = formObject.getOccupation();
		int ageCat = formObject.getAgeCategory();
		String empCat = formObject.getEmploymentCategory();
		boolean isUS = formObject.isUsCitizen();
		String taxID = formObject.getTaxID();
		String gender = formObject.getGender();
		
		AgeCategory ageCategory = Utils.defineAgeCategory(ageCat); //Defines AgeCategory based on int
		EmploymentCategory employmentCategory = Utils.defineEmploymentCategory(empCat);  //Define EmploymentCategory based on String
		Gender genderCategory = Utils.defineGenderCategory(gender); //Define Gender based on String
		
		Person person = new Person(name, occupation, ageCategory, employmentCategory, taxID, isUS, genderCategory);
		
		db.addPerson(person);
	}
	
	
	/** Method for getting a List<Person> object for the People contained in the database.
	 * @return List<Person> object for the People contained in the database
	*/
	
	public List<Person> getPeople(){
		
		return db.getPeople();
		
	}
	
	
	/** Method for saving to File the content from Database.
	 * @param file File which will contain the Data from Database
	*/
	
	public void saveToFile(File file) throws IOException{
	
		db.saveToFile(file);
		
	}
	
	
	/** Method for loading File to Database.
	 * @param file File which will contain the Data to store in Database
	 * @throws IOException
	 * @throws ClassNotFoundException
	*/
	
	public void loadFromFile(File file) throws IOException, ClassNotFoundException{
		
		db.loadFromFile(file);
		
	}

	
	/** Method for removing a Person contained in the Database.
	 * @param index integer that represents the index of List<Person> inside the Database Class.
	 * @throws SQLException 
	*/
	
	public void removePerson(int index) throws SQLException {
		
		db.removePerson(index);
		
	}
	
	
	/** Method for connecting to the database.
	 * @throws SQLException 
	*/
	
	public void connect() throws Exception{
		
		db.connect();
		
	}
	
	
	/** Method for saving the database.
	 * @throws SQLException 
	*/
	
	public void save() throws SQLException{
		
		db.save();
		
	}
	
	
	/** Method for loading the database.
	 * @throws SQLException 
	*/
	
	public void load() throws SQLException{
		
		db.load();
		
	}
	
	
	/** Method for configuring the database.
	 * @param port integer that represents the Database port.
	 * @param user String that represents the user name for login into the Database.
	 * @param password String that represents the password for login into the Database.  
	 * @throws SQLException 
	*/
	
	public void configure(int port, String user, String password) throws Exception {
		db.configure(port, user, password);
	}
	
	
	/** Method for loading the database.
	 * @throws SQLException 
	*/
	
	public void disconnect(){
		
		db.disconnect();
	}
	
}
