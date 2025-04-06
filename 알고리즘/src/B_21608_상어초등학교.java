import java.util.*;
import java.io.*;


public class B_21608_상어초등학교 {
	static class Seat implements Comparable<Seat> {
		int r;
		int c;
		int likecnt; 
		int emptycnt;
		
		public Seat(int r, int c, int likecnt, int emptycnt) {
			this.r = r;
			this.c = c;
			this.likecnt = likecnt;
			this.emptycnt = emptycnt;
			
		}
		
		@Override
		public int compareTo(Seat o) {
			if (this.emptycnt != o.emptycnt) {
				return o.emptycnt - this.emptycnt; 
			}
			else if (this.likecnt != o.likecnt) {
				return o.likecnt - this.likecnt;
			}
			else if  (this.r != o.r) {
				return this.r - o.r;
			} else {
				return this.c - o.c;
			}
		}
	}
	
	static int N;
	static int[][] arr; //자리배치 
	static int[][] likes; // 선호도 
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		likes = new int[N*N][5];
				
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				likes[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		arr[1][1] = likes[0][0];   // 초기화 
		visited[1][1] = true; 
	}
	
	static void solution() {
		for (int i = 1; i < N*N; i++) {
			PriorityQueue<Seat> pq = new PriorityQueue<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c]) {
						continue;
					}
					for (int k = 0; k < 4; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						
						if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
							for (int l = 1; l < 5; l++) {
								if(arr[nr][nc] == likes[i][l]) {
									s
								}
							}
						}
					}
					
				}
			}
		}
	}
}
