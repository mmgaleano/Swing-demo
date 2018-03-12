package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/** Utility class for the gui package.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class Utils {

	
	/** Method for getting the file extension of a file name.
	 * @param name String for the filename.
	 * @return String String representing the file extension.
	*/
	
	public static String getFileExtension(String name) {

		int pointIndex = name.lastIndexOf(".");

		if (pointIndex == -1) {
			return null;
		}

		if (pointIndex == name.length() - 1) {
			return null;
		}

		return name.substring(pointIndex + 1, name.length());
	}
	
	
	/** Method for setting the alignment in the table cells.
	 * @param table JTable reference.
	 * @param aligment Integer for the alignment value.
	*/

	public static void setCellsAlignment(JTable table, int alignment) {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(alignment);

		PersonTableModel tableModel = (PersonTableModel) table.getModel();

		for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
			table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
		}
	}
	

	/** Method for creating an icon based on its path.
	 * @param path String representing the file path of the icon.
	 * @return icon ImageIcon representation of the icon.
	*/
	
	public static ImageIcon createIcon(String path) {

		ImageIcon icon = null;

		URL url = System.class.getResource(path);

		if (url == null) {

			System.err.println("Cannot load the icon: " + path);
		}

		Image img;

		try {
			img = ImageIO.read(url).getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(img);
		} catch (IOException e) {
			System.err.println("Cannot load the icon URL: " + path);
		}

		return icon;

	}
	
	
	/** Method for creating a customized Font.
	 * @param path String representing the file path of the Font.
	 * @return font Font reference.
	*/
	
	public static Font createFont(String path) {
		URL url = System.class.getResource(path);

		if (url == null) {
			System.err.println("Unable to load font: " + path);
		}

		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException e) {
			System.err.println("Bad format in font file: " + path);
		} catch (IOException e) {
			System.out.println("Unable to read font file: " + path);
		}

		return font;
	}

}
