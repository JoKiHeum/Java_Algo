import java.util.*;
import java.io.*;
public class B_01766_문제집_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 인접 차수 
		int[] indegree = new int[N+1];
		// 인접리스트 
		ArrayList<Integer>[] G = new ArrayList[N+1];
		
		
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			G[u].add(v);
			indegree[v] += 1;
		}
		
		// 진입 차수가 0인 문제 우선순위 큐에 넣기
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i < N+1; i++) {
			if (indegree[i] == 0) {
				pq.add(i);
			}
		}
		
		
		while (!pq.isEmpty()) {
			int v = pq.poll();
			sb.append(v).append(" ");
			for (int u : G[v]) {
				indegree[u] -= 1;
				
				if (indegree[u] == 0) {
					pq.add(u);
				}
			}
		}
		System.out.println(sb);
	}
}
