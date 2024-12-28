package 알고리즘연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Prim {
	static class Edge implements Comparable<Edge> {
		int V;
		int W;
		int weight;
		
		public Edge(int v, int w, int weight) {
			super();
			this.V = v;
			this.W = w;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) { 
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static List<Edge>[] G;				// 인접리스트
	static boolean visited[];			// 방문리스트
	static int dist[];				// 거리리스트 
	static int V;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());	// 정점 
		int E = Integer.parseInt(st.nextToken());	// 간선
		visited = new boolean[V+1];
		dist = new int[V+1];
		G = new ArrayList[V+1];
		
		// 인접리스트 만들기
		for (int i = 0; i < V+1; i++) {
			G[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			G[v].add(new Edge(v,w,weight));
			G[w].add(new Edge(w,v,weight));
		}
//		 인접리스트 확인용 
		for (int i = 0; i < V + 1; i++) {
		    System.out.print(i + "번 노드: ");
		    for (Edge edge : G[i]) {
		        System.out.print("(to: " + edge.W + ", weight: " + edge.weight + ") ");
		    }
		    System.out.println();
		}
		prim(0);
		int total_dist = 0;
		for (int i = 0; i < V+1; i++) {
			total_dist += dist[i];
		}
		System.out.println(total_dist);
		System.out.println(Arrays.toString(dist));

	}
	
	static void prim(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0, 0));
		dist[start] = 0;
		
		int cnt = V;
		while (!pq.isEmpty() && cnt != 0) {
			Edge e = pq.poll();
			
			if (visited[e.W]) {
				continue;
			}
			visited[e.W] = true;
			for (Edge edge : G[e.W]) {
				if (!visited[edge.W] && dist[edge.W] > edge.weight) {
					dist[edge.W] = edge.weight;
					pq.add(new Edge(edge.V, edge.W, edge.weight));
				}
			}
			cnt--;
			

		}
	}
			
}
