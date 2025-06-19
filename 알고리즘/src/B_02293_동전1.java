import java.util.*;
import java.io.*;

public class B_02293_동전1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[N];
		int[] dp = new int[K+1];
		dp[0] = 1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coins[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 0; i < N; i++) {
			int coin = coins[i];
			for (int j = 0; j < K+1; j++) {
				if (j < coin) continue;
				dp[j] = dp[j] + dp[j-coin];  
			}
		}
		System.out.println(dp[K]);
	}
}
