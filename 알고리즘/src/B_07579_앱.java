import java.util.*;
import java.io.*;

public class B_07579_앱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[N+1];  		// 앱
		int[] cost = new int[N+1];	// 비용 
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][10001];
		int answer = 100000;
		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < 10001; j++) {
				// 만약 app i의 비용이 더 크다면 
				if (cost[i] > j) {
					dp[i][j] = dp[i-1][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]] + A[i]);
				}
				
				if (dp[i][j] >= M) {
					answer = Math.min(answer, j);
				}
			}
		}
		
		System.out.println(answer);
		
	}
}
