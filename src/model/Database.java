package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;

public class Database {

	private List<Person> people;
	private Connection conn;
	private int port;
	private String user, password;
	
	
	/** Creates the Database.
	*/
	public Database(){
		
		people = new LinkedList<Person>();
		
	}
	
	/** Connects to the DataBase.
	 * @throws Exception Driver not found
	*/
	
	public void connect() throws Exception{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		
		String url = "jdbc:mysql://localhost:" + port + "/" + "mikedb";
		conn = DriverManager.getConnection(url, user, password);
		
	}
	
	
	/** Configures the DataBase connection with provided parameters.
	 * @param port integer for the port listening the Database.
	 * @param user String for the user name.
	 * @param String for the password
	 * @throws Exception
	*/
	
	public void configure(int port, String user, String password) throws Exception {
		
		this.port = port;
		this.user = user;
		this.password = password;
		
		if(conn != null) {
			disconnect();
			connect();
		}
		else{
			connect();
		}
	}
	
	
	/** Adds a person Object to the internal List<Person> of the class.
	 * @param person Person object to add.
	*/
	
	public void addPerson(Person person){
		
		people.add(person);
	}
	
	
	/** Returns an unmodifiableList reference from the Internal List<Person> of the class .
	 * @returns unmodifiableList List<Person>.
	*/
	
	public List<Person> getPeople(){
		
		return Collections.unmodifiableList(people);  //Needed a List for the unmodifiableList

	}
	
	
	/** Saves the Internal List<Person> of the class to a File.
	 * @param file File that will contain the data of the List<Person>.
	 * @throws IOException
	*/
	
	public void saveToFile(File file) throws IOException{
		
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] persons = people.toArray(new Person[people.size()]);
		
		oos.writeObject(persons);
		
		oos.close();
		
	}
	
	
	/** Loads from File to the Internal List<Person> of the class.
	 * @param file File that contains the data to be placed in List<Person>.
	 * @throws IOException
	 * @throws ClassNotFoundException
	*/
	
	public void loadFromFile(File file) throws IOException, ClassNotFoundException{
		
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Person[] persons = (Person[])ois.readObject();
		
		people.clear();
		
		people.addAll(Arrays.asList(persons));
		
		ois.close();
		
	}

	
	/** Removes the person from the Internal List<Person> of the class and call
	 * the method remove(integer) for removing it from Database. (optional)
	 * @param int index of the element to be removed.
	 * @throws SQLException 
	*/
	
	public void removePerson(int index) throws SQLException {
		
		//this.remove(index);
		people.remove(index);
		
	}
	
	
	/** Saves to List<Person> content of the class to the real Database.
	 * @throws SQLException
	*/
	
	public void save() throws SQLException{
		
		String checkSql = "select count(*) as count from people where id=?";
		String insertSql = "insert into people (id, name, age, employment_status, tax_id, us_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String updateSql = "update people set name=?, age=?, employment_status=?, tax_id=?, us_citizen=?, gender=?, occupation=? where id=?";
		
		PreparedStatement checkStm = conn.prepareStatement(checkSql);
		PreparedStatement insertStm = conn.prepareStatement(insertSql);
		PreparedStatement updateStm = conn.prepareStatement(updateSql);
		
		for(Person person:people){
			
			int id = person.getId();
			String name = person.getName();
			AgeCategory ageCategory = person.getAgeCategory();
			EmploymentCategory employmentCategory = person.getEmpCat();
			boolean isUsCitizen = person.isUsCitizen();
			String taxID = person.getTaxID();
			Gender gender = person.getGender();
			String occupation = person.getOccupation();
			
			checkStm.setInt(1, id);  //Starts from 1, not zero
			ResultSet checkResult = checkStm.executeQuery();
			
			checkResult.next();
			
			int count = checkResult.getInt(1);
			
			if(count == 0){
			
				System.out.println("Inserting person with ID: " + id );
				
				int col = 1;
				
				insertStm.setInt(col++, id);
				insertStm.setString(col++, name);
				insertStm.setString(col++, ageCategory.name());
				insertStm.setString(col++, employmentCategory.name());
				insertStm.setString(col++, taxID);
				insertStm.setBoolean(col++, isUsCitizen);
				insertStm.setString(col++, gender.name());
				insertStm.setString(col++, occupation);
				
				insertStm.executeUpdate();
				
			}
			else{
				
				System.out.println("Updating person with ID: " + id );
				
				int col = 1;
			
				updateStm.setString(col++, name);
				updateStm.setString(col++, ageCategory.name());
				updateStm.setString(col++, employmentCategory.name());
				updateStm.setString(col++, taxID);
				updateStm.setBoolean(col++, isUsCitizen);
				updateStm.setString(col++, gender.name());
				updateStm.setString(col++, occupation);
				updateStm.setInt(col++, id);
				
				updateStm.executeUpdate();
				
			}
			
			checkResult.close();
			
		}
		
		updateStm.close();
		checkStm.close();
		insertStm.close();
		
	}
	
	
	/** Removes a Person from the Database.
	 * @param index integer that represents the index inside List<Person> in the Class
	 * @throws SQLException
	*/
	
	public void remove(int index) throws SQLException{
		
		String deleteSql = "delete from people where id=?";
		
		PreparedStatement deleteStm = conn.prepareStatement(deleteSql);
		
		Person person = people.get(index);
		
		if(person != null){
		
			deleteStm.setInt(1, person.getId());  //Starts from 1, not zero
			deleteStm.executeUpdate();
		}
		
		deleteStm.close();
		
	}
	
	
	/** Loads to real Database content to List<Person> of the class.
	 * @throws SQLException
	*/
	
	public void load() throws SQLException{
		
		people.clear();
		
		Statement selecStm = conn.createStatement();
		String sql = "select id, name, age, employment_status, tax_id, us_citizen, gender, occupation from people order by id";  
		ResultSet results = selecStm.executeQuery(sql);
		
		while(results.next()){
			
			int id = results.getInt("id");
			String name = results.getString("name");
			String age = results.getString("age");
			String employment_status = results.getString("employment_status");
			String tax_id = results.getString("tax_id");
			boolean us_citizen = results.getBoolean("us_citizen");
			String gender = results.getString("gender");
			String occupation = results.getString("occupation");
			
			//TODO: Check if user is not entering the correct data
			
			Person person = new Person(id, name, occupation, AgeCategory.valueOf(age), EmploymentCategory.valueOf(employment_status), 
					tax_id, us_citizen, Gender.valueOf(gender));
			
			people.add(person);
			
		}
		
		results.close();
		selecStm.close();
		
	}
	
	
	/** Disconnects from the Database.
	 * @throws SQLException
	*/
	
	public void disconnect(){
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
}
