package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Person;
import model.EmploymentCategory;


/** Represents a Customized TableModel.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class PersonTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Person> db;
	private String[] colNames = {"ID", "Name", "Occupation", "Age Category", "Employment Status", "US Citizen", "Tax ID"};
	
	
	/** Class constructor.
	*/
	
	public PersonTableModel(){
		
	}
	
	
	/** Method for setting the db attribute.
	 * @param db List<Person> reference.
	*/
	
	public void setData(List<Person> db){
		this.db = db;
	}
	
	
	/** Overriding the getColumnCount method.
	 * @return colCount integer that represents the Column Count of this model.
	*/
	
	@Override
	public int getColumnCount() {
		
		int colCount = 7;
		return colCount;
	}
	
	
	/** Overriding the getColumnCount method.
	 * @return rowCount that represents the Row Count for this model.
	*/

	@Override
	public int getRowCount() {
		
		int rowCount = db.size();
		return rowCount;
		
	}

	
	/** Overriding the getValueAt method.
	 * @return Object An Object from the selected.
	*/
	
	@Override
	public Object getValueAt(int row, int col) {
		
		Person person = db.get(row);
		
		switch(col){
			case 0:
				return person.getId();
				
			case 1:
				return person.getName();
				
			case 2:
				return person.getOccupation();
				
			case 3:
				return person.getAgeCategory();
				
			case 4:
				return person.getEmpCat();
				
			case 5:
				return person.isUsCitizen();
				
			case 6:
				return person.getTaxID();
		}
		
		return null;
	}

	
	/** Overriding the getColumnName method.
	 * @return columnName a String representing the Name of the Column.
	*/
	
	@Override
	public String getColumnName(int column) {
		
		String columnName = colNames[column];
		return columnName;
	}

	
	/** Overriding the isCellEditable method.
	 * @return Boolean true if the cell is editable or false if not.
	*/
	
	@Override
	public boolean isCellEditable(int row, int col) {
		
		switch(col){
			
		case 1:
			return true;
			
		case 4:
			return true;
			
		case 5:
			return true;
		
		default: 
			return false;
		}
	}

	
	/** Overriding the setValueAt method for setting the selected value content.
	*/
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		if(db == null) return;
		
		Person person = db.get(row);
		
		switch(col) {
		case 1:
			person.setName((String)value);
			break;
		
		case 4:
			person.setEmpCat((EmploymentCategory)value);
			break;
		case 5:
			person.setUsCitizen((Boolean)value);
		default:
			return;
		}
	}

	
	/** Overriding the getColumnClass method for getting the Class used in the column.
	 * Note: after some testing, noted that this method is not working well on booleans 
	 * So, a Customized Renderer was created: CitizenRenderer.
	*/
	
	@Override
	public Class<?> getColumnClass(int col) {
		switch(col) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return EmploymentCategory.class;
		case 5:
			return Boolean.class;
		case 6:
			return String.class;
		default:
			return null;
		}
	}

}
