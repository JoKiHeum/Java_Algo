import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_18258_큐2 {
	public static int[] queue;
	public static int size = 0;
	public static int front = 0;
	public static int back = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(br.readLine());
		queue = new int[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String commend = st.nextToken();
			
			if(commend.equals("push")) {
				push(Integer.parseInt(st.nextToken()));
			} else if(commend.equals("pop")) {
				int result = pop();
				sb.append(result).append('\n');
			} else if(commend.equals("size")) {
				int result = size();
				sb.append(result).append('\n');
			} else if(commend.equals("empty")) {
				int result = empty();
				sb.append(result).append('\n');
			} else if(commend.equals("front")) {
				int result = front();
				sb.append(result).append('\n');
			} else if(commend.equals("back")) {
				int result = back();
				sb.append(result).append('\n');
			}
		}
		System.out.println(sb);
	}
	public static void push(int item) {
		queue[back] = item;
		back++;
		size++;
	}
	
	public static int pop() {
		if(size == 0) {
			return -1;
		} else {
			int result = queue[front];
			queue[front] = 0;
			front++;
			size--;
			return result;
		}
	}
	public static int size() {
		return size;
	}
	public static int empty() {
		if(size==0) {
			return 1;
		} else {
			return 0;
		}
	}
	public static int front() {
		if(size == 0) {
			return -1;
		} else {
			return queue[front];
		}
	}
	public static int back() {
		if(size==0) {
			return -1;
		} else {
			return queue[back-1];
		}
	}
}
