import java.util.*;
import java.io.*;

public class B_02240_자두나무 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[T+1][W+1]; // dp 배열 생성 
		
		for (int i = 1; i < T+1; i++) {
			int tree = Integer.parseInt(br.readLine());
			
			// 한번도 움직이지 않는 경우 1번 나무에서 떨어지면 
			if (tree == 1) {
				dp[i][0] = dp[i-1][0] + 1; 
			} else {
				dp[i][0] = dp[i-1][0];
			}
			
			// 움직이는 경우에 대해서 
			for (int j = 1; j < W+1; j++) {
				// 1번 나무에서 떨어지고 1번 나무 위치에 있을 경우  
				if (tree == 1 && j % 2 == 0) {
					// 그대로 있다가 받는 경우 혹은 움직이고 받는 경우 중 최대 
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) +1;
				} else if (tree == 2 && j % 2 == 1) { // 2번 나무에서 떨어지고 2번 나무 
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) +1;
				// 위치가 다를 경우 
				} else {
					// 움직여서 못먹냐 안움직여서 못먹냐 
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]);
				}
					
			}
		}
		int answer = 0;
		for (int i = 0; i < W+1; i++) {
			answer = Math.max(dp[T][i], answer);
		}
		System.out.println(answer);
	}
}
