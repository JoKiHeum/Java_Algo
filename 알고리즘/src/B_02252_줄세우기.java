import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_02252_줄세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] indegrees = new int[N+1];
		ArrayList<Integer>[] G = new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			G[u].add(v);
			indegrees[v] += 1;
		}
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i < N+1; i++) {
			if (indegrees[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append(" ");
			for (int w: G[v]) {
				indegrees[w] -= 1;
				
				if (indegrees[w] == 0) {
					q.add(w);
				}
			}
		}
		
		System.out.println(sb);
		
	}
}
