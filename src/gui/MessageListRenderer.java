package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.Message;


/** Interface for detecting an Event related to the FormListener a FormPanel.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class MessageListRenderer implements ListCellRenderer<Object> {

	private JPanel panel;
	private JLabel label;
	private Color selectedColor, normalColor;

	
	/** Constructor.
	*/
	
	public MessageListRenderer(){
		
		initGUI();
	}
	
	
	/** Method that initializes the GUI elements.
	*/
	
	private void initGUI() {
		
		panel = new JPanel();
		label = new JLabel();
		selectedColor = new Color(210, 210, 255);
		normalColor = Color.WHITE;
		
		label.setFont(Utils.createFont("/fonts/baarsophia.ttf").deriveFont(Font.BOLD, 22));
		
		label.setIcon(Utils.createIcon("/images/info.png"));
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
		
	}
	

	/**Overriding the getListCellRendererComponent for returning a customized panel.
	 * @param list JList<?> reference.
	 * @param value Object to render.
	 * @param index integer value for the index.
	 * @param isSelected boolean for checking if the object is selected.
	 * @param cellHasFocus boolean for checking if the object has focus.
	*/
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		Message message = (Message)value;
		label.setText(message.getTitle());
		panel.setBackground(cellHasFocus? selectedColor : normalColor);
		
		return panel;
	}

}
