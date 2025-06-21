import java.util.*;
import java.io.*;

public class B_02573_빙산 {
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	static Queue<int[]> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		q = new LinkedList<>(); // 빙산의 높이가 0 초과인 것을 담을 배열 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int t = Integer.parseInt(st.nextToken());
				arr[i][j] = t;
				if (t > 0) {
					q.add(new int[] {i, j, t}); // 행 열 높이 
				}
			}
		}
		
		boolean flag = false;
		int answer = 0;
		
		while(!flag) {
			answer++;
			Queue<int[]> temp = new LinkedList<>(); // 해당 좌표에 몇 높이를 줄여야되는지 저장 
			while(!q.isEmpty()) {
				int[] v = q.poll();
				int cnt = 0; // 주변 바다의 갯수 
				for (int k = 0; k < 4; k++) {
					int nr = v[0] + dr[k];
					int nc = v[1] + dc[k];
					// 범위안에 들면서 바다라면 
					if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 0) {
						cnt++;
					}
				}
				temp.add(new int[] {v[0],v[1],cnt});
			}
			while (!temp.isEmpty()) {
				int[] t = temp.poll();
				if (arr[t[0]][t[1]] > t[2]) {
					q.add(new int[] {t[0], t[1], arr[t[0]][t[1]] - t[2]});
					arr[t[0]][t[1]] -= t[2];
				} else {
					arr[t[0]][t[1]] = 0;
				}
			}
			
			Queue<int[]> copy = new LinkedList<>(q);
			int result = check(copy);
			if (result == 0) {
				answer = 0; 
				break;
			} else if (result == 2) {
				break;
			}
			while (!temp.isEmpty()) {
				int[] t = temp.poll();
				int r = t[0];
				int c = t[1];
				int h = t[2];
				q.add(new int[] {r,c,h});
			}
			
			
		}
		System.out.println(answer);
		
	}
	static int check(Queue<int[]> lst) {
		int cnt = 0; 
		visited = new boolean[N][M];
		while (!lst.isEmpty()) {
			int[] v = lst.poll();
			// 방문하지 않았다면 
			if (!visited[v[0]][v[1]]) {
				cnt++;
				bfs(v[0], v[1], cnt);
			}
		}
		// cnt가 0이라면 한번에 다 녹아버린 것 
		if (cnt == 0) return 0;
		// cnt가 1라면 섬이 한개 인 것 즉, 분리되지 않음 
		if (cnt == 1) return 1;
		// 둘다 아니라면 섬이 분리된 것  
		return 2;
	}
	
	// 섬에 숫자 매기기 
	static void bfs(int startR, int startC, int cnt) {
		
		Queue<int[]> temp2 = new LinkedList<>();
		temp2.add(new int[] {startR, startC});
		visited[startR][startC] = true;
		
		while(!temp2.isEmpty()) {
			int[] v = temp2.poll();
			for (int k = 0; k < 4; k++) {
				int nr = v[0] + dr[k];
				int nc = v[1] + dc[k];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]  && arr[nr][nc] > 0) {
					visited[nr][nc] = true;
					temp2.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	
	
	
}
