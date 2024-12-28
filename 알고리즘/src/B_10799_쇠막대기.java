import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;

public class B_10799_쇠막대기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		int cnt = 0;
		int index = 0;
		while(index < s.length()) {
			if(s.charAt(index) == '(') {			
				// '('이면 먼저 다음 문자가 ')'인지 확인 후 레이저 라면
				if(s.charAt(index+1) == ')') {
					cnt += stack.size();
					index += 1;
				} else {		// 만약 레이저가 아니라면 스택에 추가
					stack.push(s.charAt(index));
				}
			} else {
				cnt += 1;
				stack.pop();
			}
			index++;
		}
		System.out.println(cnt);
	}
}
