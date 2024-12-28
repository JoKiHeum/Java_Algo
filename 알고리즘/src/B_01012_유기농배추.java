import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class B_01012_유기농배추 {
	static int[][] arr;
	static boolean[][] visited; 
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int M;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++ ) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int answer = 0;
			arr = new int[N][M];
			visited = new boolean[N][M];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				arr[Y][X] = 1;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) {
						BFS(i,j);
						answer++;
					}
				}
			}
			System.out.println(answer);
			
		}
	}
	static void BFS(int si, int sj) {
		visited[si][sj] = true;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {si,sj});
		
		while (!q.isEmpty()) {
			int now[] = q.poll();
			int r = now[0];
			int c = now[1];
			
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				
				if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 1 && !visited[nr][nc]) {
					q.add(new int[] {nr,nc});
					visited[nr][nc] = true;
				}
				
			}
		}
	}
}
