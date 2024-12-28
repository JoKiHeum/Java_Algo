import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class 미로탐색 {
	static int[][] arr;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				arr[i][j] = s.charAt(j) -'0';
			}
		}
		
		int result = BFS(N, M);
		System.out.println(result);
	}
	public static int BFS(int n, int m) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0});
		visited[0][0] = 1;
		
		while (!q.isEmpty()) {
			int now[] = q.poll();
			int r = now[0];
			int c = now[1];
			if (r == n-1 && c == m-1) {
				return visited[r][c];
			}
			for (int k=0; k<4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				
				if (0 <= nr && nr < n && 0 <= nc && nc < m && arr[nr][nc] == 1 && visited[nr][nc] == 0) {
					q.add(new int[] {nr,nc});
					visited[nr][nc] = visited[r][c] + 1;
				}
			}
		}
		return 0;
	}
}
