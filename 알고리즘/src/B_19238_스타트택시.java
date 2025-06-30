import java.util.*;
import java.io.*;

public class B_19238_스타트택시 {
	static int N; 			      // 지도의 크기 
	static int M; 				  // 승객의 수 
	static int fuel; 			  // 연료 
	static int[][] arr; 		  // 지도 
	static int[][] passenger;     // 승객의 정보  
	static boolean[] complete;    // 승객을 태웠는지 확인 
	static int[][] visited;  
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		passenger = new int[M][6];
		complete = new boolean[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int taxiR = Integer.parseInt(st.nextToken()) -1; 
		int taxiC = Integer.parseInt(st.nextToken()) -1;
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) -1;
			
			passenger[i][5] = i; 	// 손님 번호 
			passenger[i][0] = a;
			passenger[i][1] = b;
			passenger[i][2] = c;
			passenger[i][3] = d;
			passenger[i][4] = 0;  	// 거리는 일단 0
		}
		boolean flag = true;
		int cnt = M;
		while (cnt > 0) {
			// 택시 -> 승객 거리 구하기 
			bfs(taxiR, taxiC);
			
			for (int i = 0; i < M; i++) {
				if (!complete[passenger[i][5]]) {
					passenger[i][4] = visited[passenger[i][0]][passenger[i][1]] - 1;
					
				}
				if (passenger[i][4] == -1) {
					flag = false;
					break;
				}
			}
			if (!flag) break;
			// 정렬
			Arrays.sort(passenger, (a,b) -> {
				// 거리 순 오르차순 
				if (a[4] != b[4]) {
					return a[4] - b[4];
				// 행 번호 순 오르참순 
				} else if (a[0] != b[0]) {
					return a[0] - b[0];
				} 
				// 열 번호 오름차순 
				return a[1] - b[1];
			});
			
			fuel -= passenger[0][4];
			
			// 택시 위치 변경 
			taxiR = passenger[0][0];
			taxiC = passenger[0][1];
			// 태운 승객 표시 
			complete[passenger[0][5]] = true;
			
			// 연료 사용 후 음수이면 
			if (fuel <= 0) {
				flag = false;
				break;
			}
			
			// 태운 손님은 거리를 큰 수로 바꾸자
			passenger[0][4] = 40000;
			
		
			// 손님 도착지까지 이동
			int temp = bfs2(taxiR, taxiC, passenger[0][2], passenger[0][3]);
			fuel -= temp;
			
			// 택시 위치 변경 
			taxiR = passenger[0][2];
			taxiC = passenger[0][3];
					
			// 연료가 부족하면 종료 
			if (fuel < 0) {
				flag = false;
				break;
			}
			
			
			// 연료충전
			fuel += temp*2;
			cnt--;
		}
		
		if (!flag) {
			System.out.println(-1);
		} else {
			System.out.println(fuel);
		}
	}
	
	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		visited = new int[N][N];
		visited[r][c] = 1;
		q.add(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] v = q.poll();
			for (int k = 0; k < 4; k++) {
				int nr = v[0] + dr[k];
				int nc = v[1] + dc[k];
				
				if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] != 1 && visited[nr][nc] == 0) {
					visited[nr][nc] = visited[v[0]][v[1]] + 1;
					q.add(new int[] {nr,nc});
				}
			}
		}
		
	}
	
	static int bfs2(int r, int c, int endR, int endC) {
		Queue<int[]> q = new LinkedList<>();
		visited = new int[N][N];
		visited[r][c] = 1;
		q.add(new int[] {r,c});
		
		
		while (!q.isEmpty()) {
			int[] v = q.poll();
			if (v[0] == endR && v[1] == endC) {
				return visited[v[0]][v[1]] -1;
				
			}
			
			for (int k = 0; k < 4; k++) {
				int nr = v[0] + dr[k];
				int nc = v[1] + dc[k];
				
				if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] != 1 && visited[nr][nc] == 0) {
					visited[nr][nc] = visited[v[0]][v[1]] + 1;
					q.add(new int[] {nr,nc});
				}
			}
		}
		return -1;
	}
}
