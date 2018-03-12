package gui;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;


/** A customized CellTreeEditor.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {
	
	private static final long serialVersionUID = 1L;
	private ServerTreeCellRenderer renderer;
	private JCheckBox checkBox;
	private ServerInfo serverInfo;
	
	
	/** Constructor.
	*/
	
	public ServerTreeCellEditor() {
		
		renderer = new ServerTreeCellRenderer();
	}
	
	
	/** Overriding the isCellEditable method for checking if the cell is editable or not.
	 * @param Boolean True if YES, False for NO.
	*/
	
	@Override
	public boolean isCellEditable(EventObject event) {
		
		if(!(event instanceof MouseEvent)) return false;
		
		MouseEvent mouseEvent = (MouseEvent)event;
		JTree tree = (JTree)event.getSource(); //I know is a JTree reference
		
		TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		
		
		if(path == null) return false;
		
		
		Object lastComponent = path.getLastPathComponent();
		
		
		if(lastComponent == null) return false;
		
		
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)lastComponent;
		
		
		return treeNode.isLeaf();
	}

	
	/** Overriding the getTreeCellEditor method for changing the check box state.
	 * @param tree JTable reference
	 * @param value Object(value) selected
	 * @param isSelected Boolean to check if the cell is selected.
	 * @param expanded Boolean to check if the Object is expanded or not.
	 * @param int Row index
	*/
	
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		
		Component component = renderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
		
		if(leaf) {
			
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)value;
			serverInfo = (ServerInfo)treeNode.getUserObject();
			
			checkBox = (JCheckBox)component;
			
			ItemListener itemListener = new ItemListener() {
				
				/** Overriding the itemStateChanged method for changing the check box state.
				*/
				
				public void itemStateChanged(ItemEvent arg0) {
					fireEditingStopped(); //Calls getCellEditorValue below
					checkBox.removeItemListener(this); //Removing the listener when editing. (It avoid a ConcurrentModification exception)
				}
			};
			
			checkBox.addItemListener(itemListener); //Re-adding the listener.
		}
		return component;
	}

	
	/** Overriding the getCellEditorValue for changing the value in the CheckBox.
	 * @return Object with its value in CheckBox changed.
	*/
	
	@Override
	public Object getCellEditorValue() {
		
		serverInfo.setChecked(checkBox.isSelected());
		
		return serverInfo;
	}
}
