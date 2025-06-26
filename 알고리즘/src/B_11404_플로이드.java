import java.util.*;
import java.io.*;

public class B_11404_플로이드 {
	static int N;
	static int M;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][N+1];
		
		// dp 배열 초기화 
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j ++) {
				if(i==j) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = 10000000;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (dp[u][v] > cost) {
				dp[u][v] = cost;
			}
		}
		
		// 플로이드 워샬
		for (int k = 1; k < N+1; k++) {
			for (int i = 1; i < N+1; i++) {
				for (int j = 1; j < N+1; j++) {
					if (dp[i][k] + dp[k][j] < dp[i][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if (dp[i][j] == 10000000) {
					dp[i][j] = 0;
				}
				sb.append(dp[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}
