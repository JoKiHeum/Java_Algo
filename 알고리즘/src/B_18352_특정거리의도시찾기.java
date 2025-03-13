import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_18352_특정거리의도시찾기 {
	
	static int N; // 도시의 개수(노드)  
	static int M; // 도로의 개수(간선) 
	static int K; // 거리 정보 
	static int X; // 출발 도시 번호 
	static List<Integer>[] G; 
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
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
		}
		BFS(X);
		for (int i = 1; i < N+1; i++) {
			if (visited[i]-1 == K) {
				sb.append(i).append("\n");
			}
		}
		if (sb.length() == 0) {
			System.out.println(-1);
		} else {			
			System.out.println(sb);
		}
	}
	
	static void BFS(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = 1;
		while (!q.isEmpty()) {
			int v = q.poll();
		
			for (int u : G[v]) {
				// 방문한적이 있다면 패스 
				if (visited[u] != 0) {
					continue;
				}
				q.add(u);
				visited[u] = visited[v] + 1;
			}
		}
	}
	
}
