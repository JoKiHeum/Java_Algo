import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

public class B_02667_단지번호붙이기 {
	static int[][] arr;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] visited;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		
		
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0 && arr[i][j] == 1) {
					BFS(i,j,cnt);
					cnt++;
				}
			}
		}
		
		
		int[] count = new int[cnt];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] != 0) {
					count[visited[i][j]] += 1;
				}
			}
		}
		
		Arrays.sort(count);
		System.out.println(cnt-1);
		for (int i = 1; i < cnt; i++) {
			System.out.println(count[i]);
		}
		
	}
	static void BFS(int si, int sj, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {si, sj});
		visited[si][sj] = cnt;
		
		while (!q.isEmpty()) {
			int now[] = q.poll();
			int r = now[0];
			int c = now[1];
			
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] == 0 && arr[nr][nc] == 1) {
					visited[nr][nc] = cnt;
					q.add(new int[] {nr, nc});
				}
	
				
			}
			
		}
		
		
	}
}
