/*
 * Simple node class that holds data of type integer
 * @author JDP Teach
 * 1/23/2018
 */

public class Node {
	int dataInt;
	Node nextNode;
	
	public Node(int data){
		dataInt = data;
	}
	
	public Node(int data, Node next){
		dataInt = data;
		nextNode = next;
	}
	
	public void print(){
		System.out.println("NODE DATA: " + dataInt + ". NEXT NODE: " + nextNode);
	}
}