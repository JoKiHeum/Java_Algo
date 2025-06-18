import java.util.*;
import java.io.*;

public class B_01194_달이차오른다가자 {
	static int N;
	static int M;
	static char[][] arr;
	static int[][][] visited; // 방문 배열
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visited = new int[64][N][M]; // 열쇠에 대한 상태관리가 필요 
		
		int startR = 0;
		int startC = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = input.charAt(j);
				arr[i][j] = c;
				if (c == '0') {
					startR = i;
					startC = j;
				}
			}
		}
		int result = bfs(startR, startC, 0);
		System.out.println(result);
		
		
	}
	
	static int bfs(int startR, int startC, int startStatus) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startR, startC, startStatus});
		visited[startStatus][startR][startC] = 1;
		
		while(!q.isEmpty()) {
			int[] v = q.poll();
			int r = v[0];
			int c = v[1];
			int status = v[2];
//			System.out.println(status + " "+r + " " + c + " ");
			
			if (arr[r][c] == '1') {
				return visited[status][r][c]-1;
			}
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				int nstatus = 0;
				if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[status][nr][nc] == 0) {
					// 벽을 만났을 때 
					if (arr[nr][nc] == '#') continue;
					// 길을 만났을 때 
					else if (arr[nr][nc] == '.' || arr[nr][nc] == '0' || arr[nr][nc] == '1') {
						q.add(new int[] {nr, nc, status});
						visited[status][nr][nc] = visited[status][r][c] +1;
					}
					// 열쇠를 만났을 때
					else if (97 <= arr[nr][nc] && arr[nr][nc] <= 102) {
						if (arr[nr][nc] == 'a') {
							nstatus = status | (1 << 5);	
						} else if (arr[nr][nc] == 'b') {
							nstatus = status | (1 << 4);
						} else if (arr[nr][nc] == 'c') {
							nstatus = status | (1 << 3 );
						} else if (arr[nr][nc] == 'd') {
							nstatus = status | (1 << 2);
						} else if (arr[nr][nc] == 'e') {
							nstatus = status | (1 << 1);
						} else {
							nstatus = status | (1 << 0);
						}
						q.add(new int[] {nr, nc, nstatus});
						visited[nstatus][nr][nc] = visited[status][r][c] + 1;
					}
					// 문을 만났을 때 
					else if (65 <= arr[nr][nc] && arr[nr][nc] <= 100) {
						int check = 0;
						if (arr[nr][nc] == 'A') check = status & (1 << 5);
						else if (arr[nr][nc] == 'B') check = status & (1 << 4);
						else if (arr[nr][nc] == 'C') check = status & (1 << 3);
						else if (arr[nr][nc] == 'D') check = status & (1 << 2);
						else if (arr[nr][nc] == 'E') check = status & (1 << 1);
						else check = status & (1 << 0);
						// 만약 열쇠가 없다면 
						if (check == 0) {
							continue;
						// 열쇠가 있다면 
						} else {
							q.add(new int[] {nr, nc, status});
							visited[status][nr][nc] = visited[status][r][c] +1;
						}
					}
					
				}
			}
		}
		return -1;
	}
	

}
