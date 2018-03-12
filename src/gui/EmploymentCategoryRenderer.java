package gui;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import model.EmploymentCategory;


/** Represents the Employment Category Renderer for the table.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class EmploymentCategoryRenderer implements TableCellRenderer {

	private JComboBox<EmploymentCategory> combo;
	
	
	/** Constructor of the class.
	*/
	
	public EmploymentCategoryRenderer(){
		combo = new JComboBox<EmploymentCategory>(EmploymentCategory.values());
	}
	
	
	/** Overriding the getTableCellRenderer Method for returning the JComboBox to be renderer.
	 * @param table JTable reference
	 * @param value Object(value) selected
	 * @param isSelected Boolean to check if the cell is selected.
	 * @param hasFocus Boolean to check is the cell is focus
	 * @param int Row index
	 * @param int Column index
	*/
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		combo.setSelectedItem(value);
		
		return combo;
	}
}
