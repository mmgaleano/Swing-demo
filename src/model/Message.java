package model;


/** Represents a Message.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class Message {
	
	private String title;
	private String content;
	
	
	/** Constructor.
	 * @param title String representing the title of the Message.
	 * @param content String representing the content of the Message. 
	*/
	
	public Message(String title, String content) {
		
		this.title = title;
		this.content = content;
	}

	
	/** Method for getting the title of the Message.
	 * @return title String representation of the title.
	*/
	
	public String getTitle() {
		return title;
	}


	/** Method for setting the title of the Message.
	 * @param title String representation of the title to set.
	*/
	
	public void setTitle(String title) {
		this.title = title;
	}


	/** Method for getting the content of the Message.
	 * @return title String representation of the content.
	*/
	
	public String getContent() {
		return content;
	}


	/** Method for setting the content of the Message.
	 * @param title String representation of the content to set.
	*/
	
	public void setContent(String content) {
		this.content = content;
	}
}
