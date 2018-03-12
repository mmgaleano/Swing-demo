package gui;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/** Represents a TextPanel.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class TextPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	
	/** Constructor.
	*/
	
	public TextPanel(){
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		
		textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		textArea.setFont(new Font("Serif", Font.PLAIN, 20));
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	
	/** Method for appending text into the TextPanel.
	 * @param textp String to append into the TextPanel.
	*/
	
	public void addText(String textp){
		textArea.append(textp);
	}
	
	
	/** Method for setting text into the TextPanel.
	 * @param textp String to text into the TextPanel.
	*/
	
	public void setText(String text){
		textArea.setText(text);
	}
}
