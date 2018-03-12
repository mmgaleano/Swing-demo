package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import model.Message;


/** A fixed server for testing purposes.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class MessageServer implements Iterable<Message> {
	
	private List<Message> selected;
	private Map<Integer, List<Message>> messages;
	
	
	/** Constructor and creation of fakes messages.
	*/
	
	public MessageServer() {
		
		selected = new ArrayList<Message>();
		messages = new TreeMap<Integer, List<Message>>();
		
		List<Message> list = new ArrayList<Message>();
		list.add(new Message("I am hungry", "Do you have a hamburger?"));
		list.add(new Message("Are you there?", "I have been looking for you"));
		list.add(new Message("Do you think you can help me?", "I have been looking for a dancing partner"));
		
		messages.put(0, list);
		
		
		list = new ArrayList<Message>();
		list.add(new Message("I am thirsty", "I need a Sprite"));
		list.add(new Message("We would like to watch the game", "The game starts at 3:00pm"));
		list.add(new Message("Need your help", "I cannot find my sunglasses"));
		
		messages.put(1, list);
		
		list = new ArrayList<Message>();
		list.add(new Message("Hello", "are you there"));
		list.add(new Message("Pics of the week", "They are all very funny"));
		list.add(new Message("My fault", "I was drunk last night"));
		
		messages.put(4, list);
		
		list = new ArrayList<Message>();
		list.add(new Message("Error message", "can you please help to check this message?"));
		list.add(new Message("program error", "Cannot execute the command"));
		list.add(new Message("Lesson", "Good programming lesson"));
		
		messages.put(5, list);
		
	}

	
	/** Method for setting the selected servers.
	 * @param serverIDS Set<Integer> with the IDs to set.
	*/
	
	public void setSelectedServers(Set<Integer> serverIDS){
		
		selected.clear();
		
		for(Integer id: serverIDS ){
			
			if(messages.containsKey(id)) {
				selected.addAll(messages.get(id));
			}
		}
	}
	
	
	/** Method for getting the amount of selected servers.
	 * @return selectedSize Integer representing the amount of selected servers.
	*/
	
	public int getSelectedCount() {
		
		int selectedSize = selected.size();
		return selectedSize;
	}
	
	
	/** Method for adding a iterator on which MessagePanel class can iterate on MessageSever in for loop.
	 * @return messageIterator Iterator<Message>.
	*/
	
	@Override
	public Iterator<Message> iterator() {
		
		MessageIterator messageIterator = new MessageIterator(selected);
		
		return messageIterator;
		
		//return selected.iterator();  // A more direct way maybe.
	}
	
}


/** Represents a customized Iterator<Message> on which MessagePanel class can iterate on MessageSever in for loop.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

class MessageIterator implements Iterator<Message> {
	
	private Iterator<Message> iterator;
	
	
	/** Constructor.
	*/
	
	public MessageIterator(List<Message> messages) {
		iterator = messages.iterator();
	}

	
	/** Overriding the hasNext method for determine if there is another element.
	 * @return hasNext boolean True is YES, False if not.
	*/
	
	@Override
	public boolean hasNext() {
		boolean hasNext = iterator.hasNext();
		return hasNext;
	}
	
	
	/** Overriding the next method for iteration purposes.
	 * @return Message Message object.
	*/

	@Override
	public Message next() {  //Object for generic
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
		}
		
		return iterator.next();
	}

	
	/** Overriding the remove method.
	*/
	
	@Override
	public void remove() {
		iterator.remove();
	}
	
}
