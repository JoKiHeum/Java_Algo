import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;


public class B_07576_토마토 {	
	static int M;
	static int N;
	static int[][] arr;
	static int[][] visited;
	static int tomato;
	static Queue<int[]> q = new LinkedList<int[]>();
	static int date = 1;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 0) {
					tomato++;
				} else if (tmp == 1) {
					q.add(new int[] {i,j});
					visited[i][j] = 1;
				}
				arr[i][j] = tmp;
			}
		}
		BFS();
		
		if (tomato == 0) {
			System.out.println(date-1);
		} else {
			System.out.println(-1);
		}
	}
	
	
	static void BFS() {
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				
				if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 0 && visited[nr][nc] == 0) {
					q.add(new int[] {nr,nc});
					visited[nr][nc] = visited[r][c] + 1;
					tomato--;
					date = visited[r][c]+1;
				}
			}
			
		}
	}
}
