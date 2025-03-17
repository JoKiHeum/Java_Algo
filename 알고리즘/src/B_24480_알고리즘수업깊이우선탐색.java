import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class B_24480_알고리즘수업깊이우선탐색 {
	static int N;
	static int M;
	static int R;
	static List<Integer>[] G;
	static int[] visited;
	static int cnt = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		G = new ArrayList[N+1];
		visited = new int[N+1];
		
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			G[u].add(v);
			G[v].add(u);
			
		}
		
		// 내림 차순 정렬
		for (int i = 1; i < N+1; i++) {
			G[i].sort(Comparator.reverseOrder());
		}
		
//		for (int i = 1; i < N+1; i++) {
//			System.out.println(G[i].toString());
//		}
		
		DFS(R);
		for (int i = 1; i < N+1; i++) {
			System.out.println(visited[i]);
		}
	}
	
	static void DFS(int v) {
		
		visited[v] = cnt++;
		
		for (int u: G[v]) {
			if (visited[u] != 0) {
				continue;
			}
			DFS(u);
		}
	}
}
