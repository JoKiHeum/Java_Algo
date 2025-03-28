import java.util.*;
import java.io.*;

public class B_01915_가장큰정사각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][M+1];
		int[][] dp = new int[N+1][M+1];
		
		for (int i = 1; i < N+1; i++) {
			String sr = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j+1] = sr.charAt(j) - '0';
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				if (arr[i][j] == 0) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) +1;
				}
				
			}
		}
		
		int answer = 0;
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				if (answer < dp[i][j]) {
					answer = dp[i][j];
				}
			}
		}
		System.out.println(answer*answer);
		
		
	}
}
