package gui;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import model.EmploymentCategory;


/** Represents the Editor for EmploymentCategory in the table cell, allowing to select the value.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class EmploymentCategoryEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;
	private JComboBox<EmploymentCategory> combo;
	
	
	/** Constructor.
	*/
	
	public EmploymentCategoryEditor(){
		combo = new JComboBox<EmploymentCategory>(EmploymentCategory.values());
	}
	
	
	/** Overriding the getCellEditorValue.
	 * @param object Object representing the value.
	 * 
	*/
	
	@Override
	public Object getCellEditorValue() {
		
		Object object = combo.getSelectedItem();
		
		return object;
	}

	
	/** Overriding the getTableCellEditorComponent.
	 * @param table JTable reference
	 * @param value Object(value) selected
	 * @param isSelected Boolean to check if the cell is selected.
	 * @param hasFocus Boolean to check is the cell is focus
	 * @param int Row index
	 * @param int Column index
	*/
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		return combo;
	}

	
	@Override
	public boolean isCellEditable(EventObject arg0) {
		
		return true;
	}

	

}
