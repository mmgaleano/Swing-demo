package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;


/** Customized TreeCellRenderer.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class ServerTreeCellRenderer implements TreeCellRenderer {

	private JCheckBox leafRenderer;
	private DefaultTreeCellRenderer nonLeafRenderer;
	private Color textForeground;
	private Color textBackground;
	private Color selectionForeground;
	private Color selectionBackground;
	
	
	/** Constructor.
	*/
	
	public ServerTreeCellRenderer(){
		
		leafRenderer = new JCheckBox();
		nonLeafRenderer = new DefaultTreeCellRenderer();
		
		//nonLeafRenderer.setLeafIcon(Utils.createIcon("/images/server_1b.png"));  //Not being used by the moment
		nonLeafRenderer.setOpenIcon(Utils.createIcon("/images/world_1.png"));
		nonLeafRenderer.setClosedIcon(Utils.createIcon("/images/world_2.png"));
		
		textForeground  = UIManager.getColor("Tree.textForeground");
		textBackground  = UIManager.getColor("Tree.textBackground");
		selectionForeground  = UIManager.getColor("Tree.selectionForeground");
		selectionBackground  = UIManager.getColor("Tree.selectionBackground");
	}
	
	
	/** Overriding the getTreeCellRendererComponent method for setting the leaf and non-leaf.
	 * @param tree JTree reference
	 * @param value Object reference
	 * @param selected Boolean for checking if the object is selected
	 * @param expanded Boolean for checking if the branch is expanded
	 * @param leaf Boolean for checking if the Object is a leaf
	 * @param row Integer reference for the row index
	 * @param hasFocus Boolean for checking if the Object has focus.
	 * @return Component leafRenderer for leaf Object and nonLeafRenderer for Non Leaf Object. 
	*/
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		
		if(leaf){
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			ServerInfo nodeInfo = (ServerInfo)node.getUserObject();
			
			if(selected){
				
				leafRenderer.setForeground(selectionForeground);
				leafRenderer.setBackground(selectionBackground);
			}
			else{
				
				leafRenderer.setForeground(textForeground);
				leafRenderer.setBackground(textBackground);
			}
			
			leafRenderer.setText(nodeInfo.toString());
			leafRenderer.setSelected(nodeInfo.isChecked());
			
			return leafRenderer;
		}
		else{
			return nonLeafRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
	}
}
