


import java.util.Arrays;

public class Queue_원형큐구현 {
	static String[] queue = new String[4];
	static int front, rear;
	static int N = queue.length;
	static int size;
	
	public static void main(String[] args) {
		
		enQueue("루나");
		enQueue("데이");
		enQueue("맥스");
		enQueue("기흠");
		
		System.out.println(size());
		System.out.println(Arrays.toString(queue));
		deQueue();
		System.out.println(size());
		enQueue("보형");
		System.out.println(size());
		System.out.println(Arrays.toString(queue));

		

		
	}
	
	// 포화상태 확인
	static boolean isFull() {
		return front == (rear + 1) % N;
	}
	
	// 공백상태 확인
	static boolean isEmpty() {
		return front == rear;
	}
	
	// 삽입
	static void enQueue(String data) {
		if (isFull()) {
			System.out.println("큐가 가득찼습니다.");
			return;
		}
		size++;
		rear = (rear + 1) % N;
		queue[rear] = data;
	}
	
	// 삭제 
	static String deQueue() {
		if (isEmpty()) {
			System.out.println("큐가 비었습니다.");
			return null;
		}
		size--;
		front = (front + 1) % N;
		String item = queue[front];
		queue[front] = null;
		return item;
	}
	
	// 조회
	static String Qpeek() {
		if (isEmpty()) {
			System.out.println("큐가 비었습니다.");
			return null;
		}
		return queue[(front + 1) % N];
	}
	
	// 데이터 개수
	static int size() {
		return (N + rear - front) % N;
	}
}
