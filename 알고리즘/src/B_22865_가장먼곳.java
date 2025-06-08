	import java.util.*;
	import java.io.*;
	
	public class B_22865_가장먼곳 {
		static class Edge implements Comparable<Edge>{
			int v;
			int dist;
			
			public Edge(int v, int dist) {
				this.v = v;
				this.dist = dist;
			}
			
			@Override
			public int compareTo(Edge o) {
				return this.dist - o.dist;
			}
			
		}
		static List<Edge>[] G; // 인접리스트
		static int[] dist; 
		static int[] friends = new int[3];
		public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			G = new ArrayList[N+1];
			for (int i = 0; i < N+1; i++) {
				G[i] = new ArrayList<>();
			}
			// 친구들이 사는 곳 
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				friends[i] = Integer.parseInt(st.nextToken());
			}
			
			
			// 간선의 수 
			int M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				G[u].add(new Edge(v,d));
				G[v].add(new Edge(u,d));
			}
			
			int[][] arr = new int[3][N+1];
			
			for (int i = 0; i < 3; i++) {
				int index = 0;
				int maxDist = Integer.MIN_VALUE;
				int start = friends[i];
				dist = new int[N+1];
				for (int j = 1; j < N+1; j++) {
					dist[j] = Integer.MAX_VALUE;
				}
				dijkstra(start, N);
				arr[i] = dist;
			}
			
			int[] minDist = new int[N+1];
			
			for (int i = 1; i < N+1; i++) {
				minDist[i] = Math.min(Math.min(arr[0][i], arr[1][i]), arr[2][i]);
			}
			
			int answerIndex = 0;
			int answerMax = Integer.MIN_VALUE;
			
			for (int i = 1; i < N+1; i++) {
				if (answerMax < minDist[i]) {
					answerMax = minDist[i];
					answerIndex = i;
				}
			}
			System.out.println(answerIndex);
		}
		
		static void dijkstra(int node, int N) {
			
			boolean[] visited = new boolean[N+1];
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(node,0));
			dist[node] = 0;
			int cnt = N;
			
			while(!pq.isEmpty() && cnt !=0) {
				Edge e = pq.poll();
				if(visited[e.v]) {
					continue;
				}
				visited[e.v] = true;
				
				for (Edge edge : G[e.v]) {
					if (!visited[edge.v] && dist[edge.v] > dist[e.v] + edge.dist) {
						dist[edge.v] = dist[e.v] + edge.dist;
						pq.add(new Edge(edge.v, dist[edge.v]));
					}
				}
			}
		}
	}
