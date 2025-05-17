import java.io.*;
import java.util.*;

public class B_06236_용돈관리 {
	static int N; // 사용 날짜 
	static int M; // 인출 횟수 
	static int[] arr; // 하루에 사용할 금액 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		int start = 1;
		int end = 1;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			start = Math.max(start, arr[i]);
			end += arr[i];
		}

		int answer = 0;
		while(start <= end) {
			
			int mid = (start + end) / 2;
			
			// 만약 M 보다 작으면
			if (M >= check(mid)) {
				answer = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(answer);
		
	}
	static int check(int mid) {
		int count = 1;
		int money = mid;
		for (int m:arr) {
			if (money < m) {
				count++;
				money = mid;
			}
			money -= m;
		}
		return count;
	}
}
