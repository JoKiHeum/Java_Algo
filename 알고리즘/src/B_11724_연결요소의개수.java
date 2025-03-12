import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_11724_연결요소의개수 {
	
	static int N; // 정점의 개수
	static int M; // 간선의 개수
	static List<Integer>[] G; // 인접리스트 생성 
	static boolean[] visited; // 방문 배열 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		G = new ArrayList[N+1]; 
		
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		// 인접 리스트 만들기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			G[u].add(v);
			G[v].add(u);
		}
		
		int cnt = 0;
		for (int i = 1; i < N+1; i++) {
			if (!visited[i]) {
				BFS(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static void BFS(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : G[v]) {
				if (visited[w]) {
					continue;
				}
				q.add(w);
				visited[w] = true;
			}
		}
	}
}
