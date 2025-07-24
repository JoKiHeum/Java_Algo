import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class B_01238_파티 {
	static class Edge implements Comparable<Edge> {
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
	static int M;
	static int X; 
	static List<Edge>[] G;	//	인접리스트 
	static List<Edge>[] reverse_G; // 인접리스트 거꾸로 
	static final int INF = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		G = new ArrayList[N+1];
		reverse_G = new ArrayList[N+1];

		
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
			reverse_G[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			G[v].add(new Edge(w, weight));
			reverse_G[w].add(new Edge(v, weight));
		}

		int[] dist1 = dijkstra(X, G);
		System.out.println(Arrays.toString(dist1));
		int[] dist2 = dijkstra(X, reverse_G);
		System.out.println(Arrays.toString(dist2));

		

		
		int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }
        System.out.println(ans);
	}
	
	// x번에서 집으로 가는 함수 
	static int[] dijkstra(int start, List<Edge>[] graph){
		int[] dist = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			dist[i] = INF;
		}
		boolean[] visited = new boolean[N+1];
		
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(start, 0));
		dist[start] = 0;
		
		int cnt = N;
		while (!q.isEmpty() && cnt !=0) {
			Edge e = q.poll();
			
			if (visited[e.V]) {
				continue;
			}
			visited[e.V] = true;
			
			for (Edge edge : graph[e.V]) {
				if (!visited[edge.V] && dist[edge.V] > dist[e.V] + edge.weight) {
					dist[edge.V] = dist[e.V] + edge.weight;
					q.add(new Edge(edge.V, dist[edge.V]));
				}
				
			}
			cnt--;
		}
		return dist;
		
	}
	
	
	
	
}
