import java.io.*;
import java.util.*;

public class B_11066_파일합치기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			int[] arr = new int[K+1];
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 누적합 만들기
			int[] prefix = new int[K+1];
			for (int i = 1; i < K+1; i++) {
				prefix[i] = prefix[i-1] + arr[i-1];
			}
			
			// dp 배열 
			int[][] dp = new int[K+1][K+1];
			
			for (int l = 2; l < K+1; l++) {    // 구간의 길이  
				for (int i = 1; i < K-l+2; i++) {   // 시작점 
					int j = i + l - 1;	// 끝점  
					
					dp[i][j] = Integer.MAX_VALUE;
					
					for (int k = i; k < j; k++) {
						int cost = dp[i][k] + dp[k+1][j] + prefix[j] - prefix[i-1];
						dp[i][j] = Math.min(dp[i][j], cost);
					}
				}
			}
			
			
			System.out.println(dp[1][K]);
			
		}
	}
}
