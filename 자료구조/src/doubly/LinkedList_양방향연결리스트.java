package doubly;


class Node {
	String data;
	Node prev;
	Node next;
	
}

class DoublyLinkedList {
	Node head;
	Node tail;
	int size;
	
	public DoublyLinkedList() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}
	
	// 삽입
	void addData(int i, String data) {
		if (i < 0 || i > size) {
			System.out.println("삽입할 수 없습니다");
			return;
		}
		size++;
		
		Node curr = head;
		for (int k = 0; k < i; k++) {
			curr = curr.next;
		}
		
		// 새노드 만들고 연결하기
		Node newNode = new Node();
		newNode.data = data;
		newNode.next = curr.next;
		newNode.prev = curr;
		
		// 기존 노드 연결 재구성
		curr.next.prev = newNode;
		curr.next = newNode;	
	}
	
	// 삭제 
	void removeData(int i) {
		if (i < 0 || i >= size) {
			System.out.println("삭제할 수 없습니다.");
			return;
		}
		size--;
		
		// 삭제할 위치 찾기
		Node rmNode = head.next;
		for (int k = 0; k < i; k++) {
			rmNode = rmNode.next;
		}
		
		rmNode.prev.next = rmNode.next;
		rmNode.next.prev = rmNode.prev;
	}
	
	void printReverseAll() {
		Node curr = tail.prev;
		String str = "";
		while (curr != head) {
			str = "<-" + curr.data + str;
			curr = curr.prev;
		}
		System.out.println(str);
	}
}

public class LinkedList_양방향연결리스트 {
	public static void main(String[] args) {
		DoublyLinkedList list = new DoublyLinkedList();
		
		list.addData(0,"조기흠1");
		list.addData(0,"조기흠2");
		list.addData(0,"조기흠3");
		list.addData(0,"조기흠4");
		list.addData(0,"조기흠5");
		
		list.printReverseAll();
		
		list.removeData(1);
		list.printReverseAll();
		
	}
}
