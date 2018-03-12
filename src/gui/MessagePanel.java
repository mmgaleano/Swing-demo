package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import controller.MessageServer;
import model.Message;


/** MainFrame class.
 * @author Michael Mora
 * @version 1.0
 * @since 1.0
*/

public class MessagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ServerTreeCellRenderer treeCellRenderer;
	private JTree serverTree;
	private ServerTreeCellEditor treeCellEditor;
	
	private Set<Integer> selectedServers;
	private MessageServer messageServer;
	private ProgressDialog progressDialog;
	SwingWorker<List<Message>, Integer> worker; //Like AsyncTask in Android
	
	private TextPanel textPanel;
	private JList<Message> messageList;
	private JSplitPane upperPane,lowerPane;
	private DefaultListModel<Message> messageListModel;
	
	
	/** Constructor.
	 * @param parent JFrame reference
	*/
	
	public MessagePanel(JFrame parent){
		
		initGUI(parent);
		setListeners();
		
	}
	
	
	/** Method for initializing the GUI elements.
	 * @param parent JFrame reference.
	*/
	
	private void initGUI(JFrame parent) {
		
		messageListModel = new DefaultListModel<Message>();
		
		treeCellRenderer = new ServerTreeCellRenderer();
		treeCellEditor = new ServerTreeCellEditor();
		selectedServers = new TreeSet<Integer>();
		messageServer = new MessageServer();
		progressDialog = new ProgressDialog(parent, "Retrieving messages..");
		textPanel = new TextPanel();
		
		messageList = new JList<Message>(messageListModel);
		messageList.setCellRenderer(new MessageListRenderer());
		
		selectedServers.add(0);
		selectedServers.add(1);
		selectedServers.add(4);
		
		serverTree = new JTree(createTree());
		serverTree.setCellRenderer(treeCellRenderer);
		serverTree.setCellEditor(treeCellEditor);
		serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		serverTree.setEditable(true);
		
		setLayout(new BorderLayout());
		
		textPanel.setMinimumSize(new Dimension(0, 120));
		messageList.setMinimumSize(new Dimension(0, 120));
		
		lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(messageList), textPanel);
		upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(serverTree), lowerPane);
		
		upperPane.setResizeWeight(0.5);
		lowerPane.setResizeWeight(0.5);
		
		add(upperPane, BorderLayout.CENTER);
		
		messageServer.setSelectedServers(selectedServers);
	}
	
	
	/** Method for setting the listeners for the GUI elements.
	*/
	
	private void setListeners(){
		
		treeCellEditor.addCellEditorListener(new CellEditorListener(){

			
			/** Overriding the editingCanceledMethod. Currently it does nothing.
			*/
			
			@Override
			public void editingCanceled(ChangeEvent arg0) {
				
			}

			
			/** Overriding the editingStopped. It adds or remove a serverID.
			 * @param arg0 ChangeEvent reference.
			*/
			
			@Override
			public void editingStopped(ChangeEvent arg0) {
				
				ServerInfo info = (ServerInfo)treeCellEditor.getCellEditorValue();
				
				System.out.println("ID: " + info.getId() + " Checked: " + info.isChecked());
				
				int serverID = info.getId();
				
				if(info.isChecked()){
					selectedServers.add(serverID);
				}
				else{
					selectedServers.remove(serverID);
				}
				
				messageServer.setSelectedServers(selectedServers);
				
				retrieveMessages();
				
			}
			
		});
		
		progressDialog.setListener(new ProgressDialogListener(){ //Implementing the interface.

			@Override
			public void progressDialogCancelled() {
				if(worker != null){
					worker.cancel(true);
				}
			}
			
		});
		
		messageList.addListSelectionListener(new ListSelectionListener(){

			
			/** Overriding the valueChanged method for setting the content into the textPanel when clicking different items in List
			 * @param arg0 ChangeEvent reference.
			*/
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(messageList.getModel().getSize() > 0){
				
					Message message = (Message)messageList.getSelectedValue();  
					
					if(e.getValueIsAdjusting()){ //For avoiding an exception when unchecking the server
						textPanel.setText(message.getContent());
					}
				}
				else{
					textPanel.setText(" ");
				}
				
			}
			
		});
	}


	/** Method for retrieving messages from server.
	*/
	
	private void retrieveMessages(){
		
		progressDialog.setMax(messageServer.getSelectedCount());
		progressDialog.setVisible(true);
				
		worker = new SwingWorker<List<Message>, Integer>(){  //SwingWorker

			
			/** Overriding the doInBackground method for the SwingWorker.
			 * @return retrievedMessages List<Message> that contained the retrieved messages.
			 * @throw Exception 
			*/
			
			@Override
			protected List<Message> doInBackground() throws Exception {
				
				List<Message> retrievedMessages = new ArrayList<Message>();
				
				int count = 0;
				
				for(Message message: messageServer){
					
					if(isCancelled()) break;
					
					System.out.println(message.getTitle());
					
					retrievedMessages.add(message);
					count++;
					publish(count);
				}
				
				return retrievedMessages;
			}
			
			
			/** Overriding the done method for the SwingWorker.
			*/

			@Override
			protected void done() {
				
				progressDialog.setVisible(false);
				
				if(isCancelled()) return;
				
				try {
					List<Message> retrievedMessages = get(); //Will get what doInBackGround returns
					
					System.out.println("Total Retrieved Messages: " + retrievedMessages.size() + " messages");
					
					messageListModel.removeAllElements();
					
					for(Message message: retrievedMessages){
						messageListModel.addElement(message);
					}
					
					if(messageList.getModel().getSize() > 0){   //Updates the textPanel in the bottom after updates
					
						messageList.setSelectedIndex(0);
						Message firstMsg = (Message)messageList.getSelectedValue();
						textPanel.setText(firstMsg.getContent());
					}
					
				} catch (InterruptedException | ExecutionException e) {
					
					e.printStackTrace();
				}
			}
			

			/** Overriding the process method for the SwingWorker. 
			 * It will update the Progress Bar based on what "publish" throws.
			 * @return counts List<Integer> for the number to use for the ProgressBar update.
			*/
			
			@Override
			protected void process(List<Integer> counts) {
				
				int retrieved = counts.get(counts.size() - 1);
				
				progressDialog.setValue(retrieved);
			}
		};
		
		worker.execute();
	}
	
	
	/** Method for creating the JTree.
	 * @return rootNode JTree object.
	*/
	
	private DefaultMutableTreeNode createTree(){
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Severs");
		DefaultMutableTreeNode leftNode = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode rightNode = new DefaultMutableTreeNode("Taiwan");
		rootNode.add(leftNode);
		rootNode.add(rightNode);
		
		DefaultMutableTreeNode serverNode_1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0, selectedServers.contains(0)));
		DefaultMutableTreeNode serverNode_2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1, selectedServers.contains(1)));
		DefaultMutableTreeNode serverNode_3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2, selectedServers.contains(2)));
		leftNode.add(serverNode_1);
		leftNode.add(serverNode_2);
		leftNode.add(serverNode_3);
		
		
		DefaultMutableTreeNode serverNode_4 = new DefaultMutableTreeNode(new ServerInfo("Taipei", 3, selectedServers.contains(3)));
		DefaultMutableTreeNode serverNode_5 = new DefaultMutableTreeNode(new ServerInfo("Taichung", 4, selectedServers.contains(4)));
		DefaultMutableTreeNode serverNode_6 = new DefaultMutableTreeNode(new ServerInfo("Kaoshiung", 5, selectedServers.contains(5)));
		rightNode.add(serverNode_4);
		rightNode.add(serverNode_5);
		rightNode.add(serverNode_6);
		
		
		return rootNode;
	}
	
	
	/** Method for refreshing the Tree.
	*/
	
	public void refresh(){
		retrieveMessages();
	}
	
}
