import java.io.*;
import java.util.*;
public class B_12865_평범한배낭 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게 
		
		int[][] dp = new int[N+1][K+1]; // dp 배열
		
		int[][] arr = new int[N+1][2];
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < N+1; i++) {
			int weight = arr[i][0];
			int value = arr[i][1];
			for (int j = 1; j < K+1; j++) {
				if (weight <= j) {
					// dp[i-1][j] => 현재 바라보고 있는 물건이 없을 때 까치의 가치 즉, 안넣었을 때
					// dp[i-1][j-value] + value => 현재 바라보고 있는 물건을 넣었을 때의 가치
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight] + value);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N][K]);
		
	}
}
