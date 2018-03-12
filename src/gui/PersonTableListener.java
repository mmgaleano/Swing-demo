package gui;


/** Represents an Interface with a method when the row is deleted.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public interface PersonTableListener {
	
	
	/** Method for the deletion of a row.
	 * @param row integer for the row index.
	*/
	
	public void rowDeleted(int row);

}
