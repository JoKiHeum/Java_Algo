import java.util.*;
import java.io.*;

public class B_01520_내리막길 {
	static int N;
	static int M;
	static int[][] arr;
	static int[][] dp;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;  // 아직 방문하지 않은 곳은 -1 
			}
		}
		int answer = DFS(0,0);
		System.out.println(answer);
	}
	
	static int DFS(int r, int c) {
		// 도착 지점을 만나면 1을 리턴해서 더해주기 
		if (r == N-1 && c == M-1) {
			return 1;
		}
		
		// 이미 도착한 곳이라면 더이상 방문할 필요없음 -> 이미 도착할 수 있는 경로를 방문했으니까 
		if (dp[r][c] != -1) {
			return dp[r][c];
		}
		
		// 방문 안했다면 -> 찾아야지
		dp[r][c] = 0;
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			
			if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[r][c] > arr[nr][nc]) {
				dp[r][c] += DFS(nr,nc);
			}
		}
		return dp[r][c];
	}
}
