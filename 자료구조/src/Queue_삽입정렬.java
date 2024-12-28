

import java.util.Arrays;

public class Queue_삽입정렬 {
	
	static int[] queue = new int[100];
	static int front = -1;
	static int rear = -1;
	
	
	public static void main(String[] args) {
		
		
		euQueue(10);
		euQueue(12);
		euQueue(3);
		euQueue(9);
		euQueue(8);
		
		System.out.println(deQueue());
		System.out.println(deQueue());
		System.out.println(deQueue());
		System.out.println(deQueue());
		System.out.println(deQueue());
		
	}
	
	static void euQueue(int data) {
		queue[++rear] = data;
		int i = rear;
		
		int j; 
		// 정렬된 집합의 뒤에서부터 비교하여 위치 찾아주기
		for (j = i - 1; j >= 0 && queue[j] > data; j--) {
			queue[j+1] = queue[j];
		}
		queue[j+1] = data;
	}
	
	static int deQueue() {
		return queue[++front];
	}
}
