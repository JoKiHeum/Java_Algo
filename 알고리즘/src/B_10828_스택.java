import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B_10828_스택 {
	public static int[] stack;
	public static int size = 0;
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		stack = new int[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String commend = st.nextToken();
//			System.out.println(commend);
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
			} else if(commend.equals("top")) {
				int result = top();
				sb.append(result).append('\n');
			}
		}
		System.out.println(sb);
	}
	public static void push(int item) {
		stack[size] = item;
		size++;
	}
	
	public static int pop() {
		if(size == 0) {
			return -1;
		} else {
			int result = stack[size-1];
			stack[size-1] =0;
			size--;
			return result;
		}
	}
	
	public static int size() {
		return size;
	}
	
	public static int empty() {
		if(size == 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static int top() {
		if(size == 0) {
			return -1;
		} else {
			return stack[size-1];
		}
	}


}
