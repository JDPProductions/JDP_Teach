public class Test {
	public static void main(String[] args){
		System.out.println("TEST");
		
		SinglyLinkedList list1 = new SinglyLinkedList();
		list1.print();
		list1.addFront(5);
		list1.addFront(4);
		list1.addFront(3);
		list1.addFront(2);
		list1.addEnd(6);
		list1.addEnd(1);
		list1.add(52, 5);
		list1.printSize();
		list1.print();
		System.out.println("    ");
		list1.removeFirst();
		list1.print();
		list1.removeFirst();
		list1.removeFirst();
		list1.removeFirst();
		list1.removeFirst();
		list1.removeFirst();
		list1.removeFirst();
		list1.print();
	}
}
