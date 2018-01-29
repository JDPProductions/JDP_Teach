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
	
	public SinglyLinkedList(){
		elements = 0;
		setSize = 0;
		head = null;
		tail = null;
	}
	
	public SinglyLinkedList(int initSize){
		elements = 0;
		setSize = initSize;
		head = null;
		tail = null;
	}
	
	
	private void increaseSize(){
		System.out.println("Size was " + setSize + ".");
		setSize = (setSize + 2) * 2;
		System.out.println("Size is now " + setSize + ".");
	}
	
	private void decreaseSize(){
		System.out.println("Size was " + setSize + ".");
		setSize = (setSize - 2) / 2;
		if(setSize % 2 == 1){
			setSize = setSize + 1;
		}
		System.out.println("Size is now " + setSize + ".");
	}
	
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
	
	public void removeFirst(){
		if(setSize/4 == elements){
			decreaseSize();
		}
		head = head.nextNode;
		elements--;
	}
	
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
	
	public void printSize(){
		System.out.println("Array Size: " + setSize);
	}
}
