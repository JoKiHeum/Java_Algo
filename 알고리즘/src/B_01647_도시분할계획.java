import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.IOException;
public class B_01647_도시분할계획 {
	static class Edge implements Comparable<Edge>{
		int V;
		int weight;
		
		public Edge(int v, int weight) {
			super();
			this.V = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int N;
	static List<Edge>[] G;
	static boolean[] visited;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		G = new ArrayList[N+1];
		dist = new int[N+1];
		visited = new boolean[N+1];
		// 인접리스트 및 거리 리스트 
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			G[u].add(new Edge(v,weight));
			G[v].add(new Edge(u,weight));
		}
		Prim();
		int max = 0;
		int sum = 0;
		for (int i = 1; i < N+1; i++) {
			if (max < dist[i]) {
				max = dist[i];
			}
			sum += dist[i];
		}
		System.out.println(sum-max);
		
	}
	
	static void Prim() {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(1,0));
		dist[1] = 0;
		
		int cnt = N-1;
		while (!q.isEmpty() && cnt !=0) {
			Edge e = q.poll();
			if (visited[e.V]) {
				continue;
			}
			
			visited[e.V] = true;
			
			for (Edge edge: G[e.V]) {
				if (!visited[edge.V] && dist[edge.V] > edge.weight) {
					dist[edge.V] =  edge.weight;
					q.add(edge);
				}
			}
			cnt--;
			
		}
		
	}
}
