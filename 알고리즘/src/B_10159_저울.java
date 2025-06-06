import java.util.*;
import java.io.*;

public class B_10159_저울 {
	static int[] count;  // 비교 가능한 물건 count 배열
	static List<Integer>[] G;  // 그래프
	static boolean[] visited;  // 방문 배열 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		count = new int[N+1];
		G = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		// 인접리스트 만들기 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			G[u].add(v);
		}
		
		// 모든 정점에 대해서 DFS 돌리기
		for (int i = 1; i < N+1; i++) {
			visited = new boolean[N+1];
			visited[i] = true;
			DFS(i,i);
		}
		
		for (int i = 1; i < N+1; i++) {
			int result = N - count[i];
			System.out.println(result);
		}
		
	}
	
	static void DFS(int node, int start) {
		count[node]++;
		for(int v: G[node]) {
			if(visited[v]) {
				continue;
			}
			visited[v] = true;
			count[start]++;
			DFS(v, start);
		}
	}
	
}
