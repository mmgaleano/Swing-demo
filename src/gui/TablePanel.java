package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import model.Person;


/** Represents a Table Panel.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class TablePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private PersonTableModel tableModel;
	private JTable table;
	private JPopupMenu popUp;
	private JMenuItem removeItem;
	private PersonTableListener personTableListener;
	
	
	/** Constructor.
	*/
	
	public TablePanel(){
		
		initGUIComponents();
		setListeners();
		
	}
	

	/** Method for initializing the GUI elements.
	*/

	private void initGUIComponents(){
		
		Dimension dim = getPreferredSize();
		dim.width = 700;
		setMinimumSize(dim);
		
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		Utils.setCellsAlignment(table, SwingConstants.CENTER);
		
		EmploymentCategoryRenderer comboRenderer = new EmploymentCategoryRenderer(); //Rendering the Employment Categories
		table.getColumnModel().getColumn(4).setCellRenderer(comboRenderer);
		table.getColumnModel().getColumn(4).setCellEditor(new EmploymentCategoryEditor()); //Employmen Categories Editor
		
		CitizenRenderer checkBoxRenderer = new CitizenRenderer();  //Citizen Renderer
	    table.getColumnModel().getColumn(5).setCellRenderer(checkBoxRenderer);
	    table.setRowHeight(22);
	    
		popUp = new JPopupMenu();
		removeItem = new JMenuItem("Hide row");
		popUp.add(removeItem);
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	
	}
	
	
	/** Method for setting data into the TableModel.
	 * @param db List<Person> data to be set into the TableModel.
	*/
	
	public void setData(List<Person> db){
		
		tableModel.setData(db);
	}
	
	
	/** Method for refreshing the data contained in the TableModel.
	*/
	
	public void refresh(){
		
		tableModel.fireTableDataChanged();
	}
	
	
	/** Method for setting the Listeners in the GUI element.
	*/
	
	private void setListeners() {
	
		table.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent e) { //For showing the popup
				
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);
				
				if(e.getButton() == MouseEvent.BUTTON3){
					
					popUp.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener(new ActionListener(){ //Action for the popup

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int row = table.getSelectedRow();
				
				if(personTableListener != null){
					personTableListener.rowDeleted(row);
					//tableModel.fireTableRowsDeleted(row, row);  //it is also OK
				}
				
			}
			
		});
		
	}


	/** Method for setting thePersonTableListener.
	*/

	public void setPersonTableListener(PersonTableListener personTableListener) {
		
		this.personTableListener = personTableListener;
		
	}

}
