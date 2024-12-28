import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class B_02606_바이러스 {
	static ArrayList<Integer>[] G;	// 인접리스트
	static boolean[] visited;		// 방문리스트 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		
		G = new ArrayList[N+1];
		visited = new boolean[N+1];
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
		}
		StringTokenizer st;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			G[v].add(w);
			G[w].add(v);
		}
		
		BFS();
		int cnt = 0;
		
		for (int i = 1; i < N+1; i++) {
			if (visited[i]) {
				cnt++;
			}
		}
		
		System.out.println(cnt-1);
		
		
		
	}
	static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : G[v]) {
				if (!visited[w]) {
					q.add(w);
					visited[w] = true;
				}
			}
		}
		
	}
}
