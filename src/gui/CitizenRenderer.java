package gui;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/** Represent a Renderer for the Citizen cell in the table.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class CitizenRenderer extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	
	/** Constructor.
	*/
	
	CitizenRenderer() {
      setHorizontalAlignment(JLabel.CENTER);
    }

	
	/** Overriding the getTableCellRenderer Method for returning the JCheckBox to be renderer.
	 * @param table JTable reference
	 * @param value Object(value) selected
	 * @param isSelected Boolean to check if the cell is selected.
	 * @param hasFocus Boolean to check is the cell is focus
	 * @param int Row index
	 * @param int Column index
	*/
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      
    	if (isSelected) {
	        setForeground(table.getSelectionForeground());
	        setBackground(table.getSelectionBackground());
      } else {
	        setForeground(table.getForeground());
	        setBackground(table.getBackground());
      }
	      setSelected((value != null && ((Boolean) value).booleanValue()));
	      return this;
    }
}
