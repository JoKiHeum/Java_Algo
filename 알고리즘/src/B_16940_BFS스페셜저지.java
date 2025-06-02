import java.util.*;
import java.io.*;

public class B_16940_BFS스페셜저지 {
	static ArrayList<Integer>[] G; // 인접리스트 
	static int[] answer; // 방문 순서 배열
	static boolean[] visited; // 방문배열 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		answer = new int[N];
		visited = new boolean[N+1];
		G = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			G[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			G[u].add(v);
			G[v].add(u);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean result = check();
		if (answer[0] != 1) result = false;
		
		if(result) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		
	}
	
	static boolean check() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		int index = 1;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			int cnt = 0; // 자식의 갯수 
			for (int u: G[v]) {
				if(!visited[u]) {
					visited[u] = true;
					cnt++;
				}
			}
			
			for (int i = index; i < index+cnt; i++) {
				// 해당 인덱스 안에 아직 자식들이 방문을 안했으면 
				if(!visited[answer[i]]) {
					return false;
				} else {
					q.add(answer[i]);
				}
			}
			index += cnt;
		}
		return true;
		
	}
}
