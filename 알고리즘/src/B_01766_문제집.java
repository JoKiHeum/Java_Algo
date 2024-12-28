import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class B_01766_문제집 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 진입차수
		int[] indegree = new int[N+1];
		
		// 인접리스트  
		ArrayList<Integer>[] G = new ArrayList[N+1];
		
		for (int i = 0; i < N + 1; i++) {
			G[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			G[u].add(v);
			indegree[v] += 1;
		}
		
		// priorityq 생성
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		
		// 진입차수가 0인 것들 우선순위 큐에 넣기  
		for (int i = 1; i < N + 1; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append(" ");
			
			for (int w : G[v]) {
				indegree[w] -= 1;
				
				if (indegree[w] == 0) {
					q.add(w);
				}
			}
			
		}
		System.out.println(sb);
	}
}
