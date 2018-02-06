/*
 * Singly Linked List
 * @author JDP Teach
 * 1/23/2018
 * This is an implementation done without reference
 * and therefore may not be the most optimized solution
 */
public class SinglyLinkedList {
  
	private int elements;
	private int setSize;
	private Node head;
	private Node tail;
	
	/*
	 * Constructor method for singly linked list.
	 * Creates linked list of size 0 with 0 elements
	 *   with head and tail reference nodes of null values
	 */
	public SinglyLinkedList(){
		elements = 0;
		setSize = 0;
		head = null;
		tail = null;
	}
	
	/*
	 * Constructor method for singly linked list of specific size.
	 * Creates linked list of specific size with 0 elements
	 *   with head and tail reference nodes of null values
	 */
	public SinglyLinkedList(int initSize){
		elements = 0;
		setSize = initSize;
		head = null;
		tail = null;
	}
	
	/*
	 * Private method to increase the size capacity of the linked list
	 */
	private void increaseSize(){
		System.out.println("Size was " + setSize + ".");
		setSize = (setSize + 2) * 2;
		System.out.println("Size is now " + setSize + ".");
	}
	
	/*
	 * Private method to decrease the size capacity of the linked list
	 */
	private void decreaseSize(){
		System.out.println("Size was " + setSize + ".");
		setSize = (setSize - 2) / 2;
		if(setSize % 2 == 1){
			setSize = setSize + 1;
		}
		System.out.println("Size is now " + setSize + ".");
	}
	
	/*
	 * Method to add a data element to the front of an list
	 * @param data   Data element to be added
	 * Steps:
	 * 1. Check size - increase if elements will go over max size
	 * 2. Create a new node with passed in data
	 * 3. If list is empty, make tail reference node refer to new node
	 * 4. Make head reference node refer to the new node
	 * 5. Up element count
	 */
	public void addFront(int data){
		if(elements == setSize){
			increaseSize();
		}
		Node newNode = new Node(data);
		newNode.nextNode = head;
		if(head == null){
			tail = newNode;
		}
		head = newNode;
		elements++;
	}
	
	/*
	 * Method to add a data element to the end of an list
	 * @param data   Data element to be added
	 * Steps:
	 * 1. Check size - increase if elements will go over max size
	 * 2. Create a new node with passed in data
	 * 3. If list is empty, make head reference node refer to new node
	 * 4. Make the current tails next node the new node
	 * 4. Make tail reference node refer to the new node
	 * 5. Up element count
	 */
	public void addEnd(int data){
		if(elements == setSize){
			increaseSize();
		}
		Node newNode = new Node(data);
		if(head == null){
			head = newNode;
		}
		tail.nextNode = newNode;
		tail = newNode;
		elements++;
	}
	
	/*
	 * Method to add a data element to a specified location
	 * @param data   Data element to be added
	 * @param index   Index in which to insert the passed in data
	 * Steps:
	 * 1. Check size - increase if elements will go over max size
	 * 2. Check if index is valid
	 *       If valid 
	 * 3. If index refers to the front, call addFront method with data
	 * 4. If index refers to the end, call addEnd method with data
	 * 5. Otherwise, create a new node with passed in data
	 * 6. Initialize a previousNode and indexNode
	 * 7. Loop through the linkedList till index is found
	 * 8. Once found make previousNode's next node the new node
	 * 9. Make the new nodes next node the index node
	 * 10. Up element count
	 */
	public void add(int data, int index){
		if(elements == setSize){
			increaseSize();
		}
		if(index < 0 || index > elements){
			System.out.println("Out of bounds.");
		}
		else if(index == 0){
			addFront(data);
		}
		else if(index == elements){
			addEnd(data);
		}
		else{
			Node currentNode = new Node(data);
			Node previousNode = head;
			Node indexNode = head;
			for(int i = 1; i <= index; i++){
				previousNode = indexNode;
				indexNode = indexNode.nextNode;
			}
			previousNode.nextNode = currentNode;
			currentNode.nextNode = indexNode;
			elements++;
		}	
	}
	
	/*
	 * Method to remove a data element from the front of a list
	 * @param data   Data element to be removed
	 * Steps:
	 * 1. Check size - decrease if elements make up 1/4 of the list size
	 * 2. Make current head node the next node in the array
	 * 3. Reduce element count by 1
	 * This implementation relies on a garbage collector.
	 */
	public void removeFirst(){
		if(setSize/4 == elements){
			decreaseSize();
		}
		head = head.nextNode;
		elements--;
	}
	
	/*
	 * Method to print data in linked list
	 * Steps:
	 * 1. If head is null, then the linked list is empty
	 * 2. Otherwise, initiate the head node into a reference
	 * 3. Loop through next nodes till end of the list, printing
	 *    the element in each iteration
	 */
	public void print(){
		if(head == null){
			System.out.println("The linked list is empty.");
		}
			else{
			Node reference = head;
			System.out.println(reference.dataInt);
			while(reference.nextNode != null){
				reference = reference.nextNode;
				System.out.println(reference.dataInt);
			}
			}
	}
	
	/*
	 * Simple method to print the size of the linked list
	 */
	public void printSize(){
		System.out.println("Linked list Size: " + setSize);
	}
}
