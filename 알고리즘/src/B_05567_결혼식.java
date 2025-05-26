import java.util.*;
import java.io.*;
public class B_05567_결혼식 {
	static ArrayList<Integer>[] G; // 인접리스트 
	static int[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		G = new ArrayList[n+1];
		visited = new int[n+1];
		
		for (int i = 0; i < n+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			G[u].add(v);
			G[v].add(u);
		}
		int answer = 0;
		BFS(1);
		for (int i = 1; i < n+1; i++) {
			if(visited[i] == 2 || visited[i] == 3) {
				answer += 1;
			}
		}
		System.out.println(answer);
	}
	static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = 1;
		
		while (!q.isEmpty()) {
			int w = q.poll();
			
			for (int v: G[w]) {
				if (visited[v] == 0) {
					visited[v] = visited[w] +1;
					q.add(v);
				}
			}
		}
	}
}
