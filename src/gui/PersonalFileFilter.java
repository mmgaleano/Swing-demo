package gui;
import java.io.File;

import javax.swing.filechooser.FileFilter;


/** Customized file filter.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class PersonalFileFilter extends FileFilter {

	
	/** Overriding the accept method for defining what is going to be displayed in the File Chooser.
	 * @param file File reference.
	 * @return Boolean Boolean True for displaying, otherwise it will return false.
	*/
	
	@Override
	public boolean accept(File file) {
		
		if(file.isDirectory()){
			return true;
		}
		
		String name = file.getName();
		String extension = Utils.getFileExtension(name);
		
		if(extension == null){
			return false;
		}
		if(extension.equals("dbf")){
			return true;
		}
		
		return false;
	}

	
	/** Overriding the getDescription method for defining the description in the File Chooser.
	 * @return String String representing the description.
	*/
	
	@Override
	public String getDescription() {
		
		return "Person database files ( *.dbf )";
	}

}
