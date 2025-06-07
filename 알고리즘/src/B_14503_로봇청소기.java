import java.io.*;
import java.util.*;

public class B_14503_로봇청소기 {
	static int N;
	static int M;
	static int[][] arr;
	static int answer=0;
	static int r; // 현재 행 위치
	static int c; // 현재 열 위치
	static int d; // 현재 방향
	// 방향설정: 0: 상 1: 우 2: 남 3:서  
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
	
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean();
		System.out.println(answer);
	}
	static void clean() {
		if (arr[r][c] == 0) {
			answer++;
			arr[r][c] = 2;
		}
		while (true) {
			boolean flag = false;
			for (int k =0; k < 4; k++) {		
				int nr = r + dr[k];
				int nc = c + dc[k];
				// 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 있는 지 확인   
				if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 0) {
					flag = true;
				}
			}
			// flag = false -> 빈칸이 없는 경우
			if (!flag) {
				int tempD = (4+d-2) % 4;
				// 후진할 수 있는 지 확인
				int nr = r + dr[tempD];
				int nc = c + dc[tempD];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] != 1) {
					r = nr;
					c = nc;
				} else {
					break;
				}
			} else {   // 빈칸이 있는 경우 
				while (true) {
					d = (4+d-1) % 4;
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == 0) {
						r = nr;
						c = nc;
						answer++;
						arr[nr][nc] = 2; // 청소한 구역 체크 
						break;
					}
				}
			}
			
		}
		
	}
	
}
