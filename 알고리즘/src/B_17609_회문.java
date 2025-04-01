import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class B_17609_회문 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < T; i++) {
			String word = br.readLine();
			int result = check(0,word.length()-1, 0, word);
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
	
	static int check(int start, int end, int cnt, String word) {
		// 2번 부터는 회문 or 유사회문 아님 
		if (cnt > 1) {
			return 2;
		}
		
		while (start < end) {
			if (word.charAt(start) == word.charAt(end)) {
				start++;
				end--;
				continue;
			}
			// 왼쪽 즉, start point가 가르키는 문자를 없애본다.
			int resultLeft = check(start+1, end, cnt+1, word);
			// 오른 쪽 즉, end point가 가르키는 문자를 없애본다.
			int resultRight = check(start, end-1, cnt+1, word);
			return Math.min(resultLeft, resultRight);
		}
		return cnt;
	}
}
