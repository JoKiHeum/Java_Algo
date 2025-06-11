import java.util.*;
import java.io.*;

public class B_02194_유닛이동시키기 {
	static int N;
	static int M;
	static int[][] arr; // 맵 
	static int A;
	static int B;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int startR;
	static int startC;
	static int endR;
	static int endC;
	static int answer = 0;
	static int[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[r-1][c-1] = 1; // 장애물이 있는 지역은 1로 표시 
		}
		
		st = new StringTokenizer(br.readLine());
		startR = Integer.parseInt(st.nextToken())-1;
		startC = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		endR = Integer.parseInt(st.nextToken())-1;
		endC = Integer.parseInt(st.nextToken())-1;
		bfs();
		System.out.println(visited[endR][endC]-1);
		
	}
	
	// BFS 함수 돌리기 
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startR,startC,0});
		visited[startR][startC] = 1; // 방문처리 
		
		while(!q.isEmpty()) {
			int[] v = q.poll();
			if (v[0] == endR && v[1] == endC) {
				return;
			}
			int top = v[0]; // 유닛의 가장 위 좌표;
			int bottom = v[0]+ A-1;
			int left = v[1];
			int right = v[1] + B-1;
			
			for (int k = 0; k < 4; k++) {
				int ntop = top + dr[k];
				int nbottom = bottom + dr[k];
				int nleft = left + dc[k];
				int nright = right + dc[k];
				if (0 <= ntop && nbottom < N && 0 <= nleft && nright < M && visited[ntop][nleft] == 0 && check(ntop,nleft)) {
					q.add(new int[] {ntop, nleft});
					visited[ntop][nleft] = visited[top][left] + 1;
				}
			}
		}
		return;
	}
	
	// 유닛이 장애물에 걸리는지 확인 (걸리면flase 안걸리면 true)
	static boolean check(int r, int c) {
		boolean flag = true;
		
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < B; j++) {
				if (arr[r + i][c + j] == 1) {
					flag = false;
				}
			}
		}
		return flag;
	}
	
	
}
