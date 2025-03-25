import java.util.*;
import java.io.*;

public class B_31871_영일랜드 {
	static int N;
	static int M;
	static int[][] arr;
	static boolean visited[];
	static int max = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (r == c) {
				continue;
			}
			if (arr[r][c] < weight) {				
				arr[r][c] = weight;
			}
		}
		
//		for (int i = 0; i < N+1; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		DFS(0, 0, 0);
		System.out.println(max);
		
	}
	static void DFS(int level, int dist, int v) {
		if (level == N) {
			// 마지막 방문함 놀이기구에서 0으로 가는 길이 없다면 그냥 return 
			if (arr[v][0] == 0) {
				return;
			}
			max = Math.max(dist+ arr[v][0],max);
			return;
		}
		
		for (int i = 1; i < N+1; i++) {
			if (visited[i] || arr[v][i] <= 0 || i == v) {
				continue;
			}
			visited[i] = true;
			DFS(level + 1, dist + arr[v][i], i);
			visited[i] = false;
		}
	}
}
