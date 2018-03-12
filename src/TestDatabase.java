import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class TestDatabase {


	/*public static void main(String[] args) {
	
		System.out.println("Testing Database... ");
		
		Database db = new Database();
		
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.addPerson(new Person("Joe", "Lion tamer", AgeCategory.adult, EmploymentCategory.employed, "777", true, Gender.male));
		db.addPerson(new Person("Sara", "Artist", AgeCategory.senior, EmploymentCategory.selfemployed, null, false, Gender.female));
		db.addPerson(new Person("Vanessa", "Convicta", AgeCategory.senior, EmploymentCategory.selfemployed, "82824", true, Gender.female));
		db.addPerson(new Person("Mary", "Politician", AgeCategory.senior, EmploymentCategory.selfemployed, null, true, Gender.female));
		
		try {
			db.save();
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		db.disconnect();
		
	}*/
}
