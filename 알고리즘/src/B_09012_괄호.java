import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
public class B_09012_괄호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		int N = Integer.parseInt(br.readLine());
		
		
		for(int i=0; i<N; i++) {
			Stack<Character> stack = new Stack<>();
			String s = br.readLine();
			
			for(int j=0; j<s.length(); j++) {
				if(s.charAt(j) == '(') {
					stack.push(s.charAt(j));
				} else {
					// 비어있는지 확인
					if(stack.isEmpty()) {
						stack.push(s.charAt(j));
						break;
					} else {
						stack.pop();
					}
				}
			}
			if(stack.isEmpty()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
		}
	}
}
