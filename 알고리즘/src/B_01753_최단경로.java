import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



import java.io.IOException;
public class B_01753_최단경로 {
	static class Edge implements Comparable<Edge> {
		int V;
		int W;
		int weight;
		
		public Edge(int v, int w, int weight) {
			super();
			this.V = v;
			this.W = w;
			this.weight= weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
			
		}
	}
	static int V;				// 정점의 수
	static int E;				// 간서의 수
	static List<Edge>[] G;		// 인접리스트 
	static int[] dist;			// 거리리스트  
	static boolean[] visited;		// 방문리스트  
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[V+1];
		visited = new boolean[V+1];
		G = new ArrayList[V+1];
		
		int start = Integer.parseInt(br.readLine());
		
		// 인접리스트 초기화 및 거리리스트 큰수로 초기화   
		for (int i = 0; i < V + 1; i++) {
			G[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			G[u].add(new Edge(u,v,weight));
		}
//		
////		 인접리스트 확인용 
//		for (int i = 0; i < V + 1; i++) {
//		    System.out.print(i + "번 노드: ");
//		    for (Edge edge : G[i]) {
//		        System.out.print("(to: " + edge.W + ", weight: " + edge.weight + ") ");
//		    }
//		    System.out.println();
//		}
		dijkstra(start);
		for (int i = 1; i < V+1; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
	}
	
	
	static void dijkstra(int s) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(s, s, 0));
		dist[s] = 0;
		int cnt = V;
		
		while (!q.isEmpty() && cnt != 0) {
			Edge e = q.poll();
			
			if (visited[e.W]) {
				continue;
			}
			
			visited[e.W] = true;
			
			for (Edge edge : G[e.W]) {
				if (!visited[edge.W] && dist[edge.W] > dist[e.W] + edge.weight) {
					dist[edge.W] = dist[e.W] + edge.weight;
					q.add(new Edge(edge.V, edge.W, dist[edge.W]));
				}
			}
			cnt--;
		}
	}
	
	
	
	
}
