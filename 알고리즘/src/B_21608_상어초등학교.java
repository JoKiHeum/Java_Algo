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
			if (this.likecnt != o.likecnt) {
				return o.likecnt - this.likecnt; 
			}
			else if (this.emptycnt != o.emptycnt) {
				return o.emptycnt - this.emptycnt;
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
	static HashMap<Integer, int[]> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		likes = new int[N*N][5];
		
				
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				likes[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
		arr[1][1] = likes[0][0];   // 초기화 
		map.put(likes[0][0], Arrays.copyOfRange(likes[0], 1,5));
		solution();
		
		
		int result = getScore();
		System.out.println(result);
		
	}
	
	static void solution() {
		for (int i = 1; i < N*N; i++) {
			PriorityQueue<Seat> pq = new PriorityQueue<>();
			int likeperson1 = likes[i][1];
			int likeperson2 = likes[i][2];
			int likeperson3 = likes[i][3];
			int likeperson4 = likes[i][4];

			map.put(likes[i][0], new int[]{likeperson1,likeperson2,likeperson3,likeperson4});
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 이미 자리에 앉아있다면 
					if (arr[r][c] != 0) {
						continue;
					}
					
					int cnt = 0;   // 좋아하는 사람 수
					int empty = 0; // 빈자리 수 
					// 주변 살펴보기 
					for (int k = 0; k < 4; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k]; 
						
						if (0<= nr && nr < N && 0 <= nc && nc < N) { 
							// 만약 주변 중 좋아하는 사람이 있다면 
							if (arr[nr][nc] == likeperson1 || arr[nr][nc] == likeperson2 || arr[nr][nc] == likeperson3 || arr[nr][nc] == likeperson4) {
								cnt += 1;
							}
							// 만약 주변이 빈자리라면 
							if (arr[nr][nc] == 0) {
								empty += 1;
							}
						}
					}
					pq.add(new Seat(r, c, cnt, empty));
				}
			}
			Seat seat = pq.poll();
			arr[seat.r][seat.c] = likes[i][0];
		}
	}
	
	static int getScore() {
		int score = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				int[] candidates = map.get(arr[i][j]);
				int candidate1 = candidates[0];
				int candidate2 = candidates[1];
				int candidate3 = candidates[2];
				int candidate4 = candidates[3];
				for (int k = 0; k < 4; k ++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					
					if (0 <= nr && nr < N && 0 <= nc && nc < N) {
						if (arr[nr][nc] == candidate1 || arr[nr][nc] == candidate2 || arr[nr][nc] == candidate3 || arr[nr][nc] == candidate4) {
							cnt += 1;
						}
					}
				}
				if (cnt == 0) {
					score += 0;
				} else if (cnt == 1) {
					score += 1;
				} else if (cnt == 2) {
					score += 10;
				} else if (cnt == 3) {
					score += 100;
				} else if (cnt == 4) {
					score += 1000;
				}
			}
		}
		return score;
	}
	
	
	
	
}
