import java.util.*;
import java.io.*;

public class B_23288_주사위굴리기2 {
	static int[][] dice = {{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}}; // 주사위 전개도 
	static int N;
	static int M;
	static int K;
	static int[][] arr; 
	static int[] dr = {0, 1, 0, -1}; // 0: 동 1: 남 2: 서 3: 북  
	static int[] dc = {1, 0, -1, 0};
	static int direction = 0; // 초기 진행 방향 동
	static int r = 0; // 현재 행 위치
	static int c = 0; // 현재 열 위치 
	static int score = 0; // 점수 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// K만큼 반복 
		for (int i = 0; i < K; i++) {
			moveDice();
			getScore();
			changeDirection();
//			System.out.println(direction);
		}
		
		System.out.println(score);
		
	}
	
	// 주사위 굴렸을 때 위치변경 함수
	static void moveDice () {
		int nr = r + dr[direction];
		int nc = c + dc[direction];
		
		// 만약 범위를 범어나면 반대 방향으로 전환 
		if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
			direction = (direction + 2) % 4;
		}
		
		// 주사위 전개도 변경 
		switch(direction) {
			case 0: // 동 
				int temp = dice[1][2];
				dice[1][2] = dice[1][1];
				dice[1][1] = dice[1][0];
				dice[1][0] = dice[3][1];
				dice[3][1] = temp;
				break;
			case 1: // 남
				temp = dice[3][1];
				dice[3][1] = dice[2][1];
				dice[2][1] = dice[1][1];
				dice[1][1] = dice[0][1];
				dice[0][1] = temp;
				break;
			case 2: // 서
				temp = dice[1][0];
				dice[1][0] = dice[1][1];
				dice[1][1] = dice[1][2];
				dice[1][2] = dice[3][1];
				dice[3][1] = temp;
				break;
			case 3: // 북 
				temp = dice[0][1];
				dice[0][1] = dice[1][1];
				dice[1][1] = dice[2][1];
				dice[2][1] = dice[3][1];
				dice[3][1] = temp;
				break;
		}
		r += dr[direction];
		c += dc[direction];
	}
	
	// 점수 계산 
	static void getScore() {
		Queue<int[]> q = new LinkedList<>();
		int[] start = {r,c}; // 출발 점 
		q.add(start);
		boolean[][] visited = new boolean[N][M];
		visited[r][c] = true; // 방문 체크 
		int num = arr[r][c]; // 현재 주사위가 놓인 위치의 정수 
		int cnt = 1; // 갈 수 있는 갯수 
		while(!q.isEmpty()) {
			int[] v = q.poll();
			for (int k = 0; k < 4; k++) {
				int nr = v[0] + dr[k];
				int nc = v[1] + dc[k];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && arr[nr][nc] == num && visited[nr][nc] == false) {
					q.add(new int[] {nr,nc});
					visited[nr][nc] =true;
					cnt++;
				}
			}
		}
		score += cnt * num;
		
	}
	
	// 주사위 이동 방향 변경 
	static void changeDirection() {
		int num = arr[r][c]; // 주사위가 놓인 위치의 정수
		int under = dice[3][1]; // 주사위 밑 바닥 수 
		
		if (under > num) {
			direction = (direction + 1) % 4;
		} else if (under < num) {
			direction = (4 + direction -1) % 4;
		} 
		
	}
}
