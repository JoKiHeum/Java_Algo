import java.util.*;
import java.io.*;

public class B_10159_저울_다른풀이 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			arr[i][i] = 1;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr[u][v] = 1;
			arr[v][u] = -1;
		}
		
		// 플로이드 와샬 
		for (int k = 1; k < N+1; k++) {
			for (int i = 1; i < N+1; i++) {
				for (int j = 1; j < N+1; j++) {
					if (arr[i][k] == 1 && arr[k][j] == 1) {
						arr[i][j] = 1;
					}
					if (arr[i][k] == -1 && arr[k][j] == -1) {
						arr[i][j] = - 1;
					}
				}
			}
		}
		for (int i = 1; i < N+1; i++) {
			int cnt = 0;
			for (int j = 1; j < N+1; j++) {
				if (arr[i][j] !=0) {
					cnt++;
				}
			}
			System.out.println(N-cnt);
		}
		
	}
}
