package singly;


class Node {
	String data;
	Node link;
}



class SinglyLinkedList {
	Node head;
	int size;
	// 생성자
	SinglyLinkedList() {
		head = new Node();
	}
	
	// 삽입
	void addData(int i, String data) {
		// i 인덱스에 데이터 삽입
		// 0이면 제일 앞에 추가
		// size와 같으면 제일 뒤에 추가
		if (0 > i || i > size) {
			System.out.println("삽입할 위치가 잘못되었습니다.");
			return;
		}
		// 새 노드 생
		Node newNode = new Node();
		newNode.data = data;
		
		// 삽입할 위치 앞에 있는 노드 찾기;
		Node curr = head;
		for (int k = 0 ; k < i; k++) {
			curr = curr.link;
		}
		
		// 새 노드부터 연결
		newNode.link = curr.link;
		// 기존 노드 연결 변경
		curr.link = newNode;
		size++;
	}
	
	// 삭제
	void removeData(int i) {
		// 0번 : 제일 앞에 있는 데이터 삭제
		// size-1: 마지막 삭제
		if (0 > i || i >= size) {
			System.out.println("삭제할 수 없습니다.");
			return;
		}
		
		size--;
		
		// 삭제할 노드의 앞 노드로 이동
		Node curr = head;
		
		for (int k = 0; k < i; k++) {
			curr = curr.link;
		}
		
		//
		curr.link = curr.link.link;
	}
	
	
	// 조회
	void printALL() {
		Node curr = head.link;
		
		while (curr != null) {
			System.out.print(curr.data + " -> ");
			curr = curr.link;
		}
		System.out.println();
	}
	
}




public class LinkedList_단순연결리스트 {
	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		
		list.addData(0, "조기흠1");
		list.addData(0, "조기흠2");
		list.addData(0, "조기흠3");
		list.addData(0, "조기흠4");
		list.addData(0, "조기흠5");
		list.addData(0, "조기흠6");
		list.printALL();
		
		
	}
}
