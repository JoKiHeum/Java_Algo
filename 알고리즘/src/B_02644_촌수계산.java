import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_02644_촌수계산 {
	
	static int N;
	static int M;
	static int S;
	static int E;
	static List<Integer>[] G;
	static boolean flag;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		
		G = new ArrayList[N+1];
		visited = new boolean[N+1];
		
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
			
		
		DFS(S, 0);
		if (!flag) {
			System.out.println(-1);
		}
		
	}
	static void DFS(int v, int level) {
		visited[v] = true; // 방문 표시 

		if (v == E) {
			flag = true;
			System.out.println(level);
		}
		
		for (int u : G[v]) {
			if (!visited[u]) {
				DFS(u, level+1);
			}
		}
	}
}
