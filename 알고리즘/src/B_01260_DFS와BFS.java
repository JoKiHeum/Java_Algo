import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_01260_DFS와BFS {
	static boolean[] visited;	   // 방문리스트
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] G; // 인접리스
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		G = new ArrayList[N+1];
		
		for (int i=1; i<N+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		// 인접리스트 만들기 
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			G[v].add(w);
			G[w].add(v);
		}
		// 정렬
		for (int i=1; i<N+1; i++) {
			Collections.sort(G[i]);
//			System.out.println(G[i]);
		}
		
		DFS(V);
		System.out.println(sb);
		visited = new boolean[N+1];
		sb = new StringBuilder();
		BFS(V);
		System.out.println(sb);
		
	}
	static void DFS(int V) {
		sb.append(V).append(' ');
		visited[V] = true;
		for(int w : G[V]) {
			if (!visited[w]) {
				DFS(w);
			}
		}
	}
	
	static void BFS(int V) {
		Queue<Integer> q = new LinkedList<>();
		q.add(V);
		visited[V] = true;
		while (!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append(' ');
			for (int w: G[v]) {
				if (!visited[w]) {
					visited[w] = true;
					q.add(w);
				}
			}
		}
	}
}
