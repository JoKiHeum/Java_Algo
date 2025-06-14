import java.util.*;
import java.io.*;

public class B_21610_마법사상어와비바라기 {
	static int N; // 격자판 크기 
	static int M; // 명령 갯수
	static int[][] arr; // 격자판
	// 방향설정 
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static List<int[]> cloud; // 구름 좌표를 저장할 리스트
	static int[] ddr = {-1, -1, 1, 1};
    static int[] ddc = {-1, 1, -1, 1};	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 초기 구름 설정 
		cloud = new ArrayList<>();
		cloud.add(new int[] {N-1, 0});
		cloud.add(new int[] {N-1,1});
		cloud.add(new int[] {N-2,0});
		cloud.add(new int[] {N-2,1});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rain(d,s);
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer+= arr[i][j];
			}
		}
		System.out.println(answer);

	}
	
	static void rain(int d, int s) {
		List<int[]> temp = new ArrayList<>(); // 이동시킨 구름의 위치를 저장할 임시 리스트 
		// 1단계 구름 d방향을 s만큼 이동  
		for (int i = 0; i < cloud.size(); i++) {
			// 현재 구름 위치 
			int r = cloud.get(i)[0];
			int c = cloud.get(i)[1];
			 
			int nr = (N*s + r + (dr[d-1] * s)) % N;
			int nc = (N*s + c + (dc[d-1] * s)) % N;
			
			temp.add(new int[] {nr,nc});
		}
		
		// 2단계 구름 있는 지역 +1
		for (int i = 0; i < temp.size(); i++) {
			arr[temp.get(i)[0]][temp.get(i)[1]]++;
		}
		
		
		// 물복사버그 
		for (int i = 0; i < temp.size(); i++) {
			int cnt = 0;
			int r = temp.get(i)[0];
			int c = temp.get(i)[1];
			for (int k = 0; k < 4; k++) {
				int nr = r + ddr[k];
				int nc = c + ddc[k];
				
				if (0 <= nr && nr < N && 0 <= nc && nc <N && arr[nr][nc] > 0) {
					cnt++;
				}
			}
			arr[r][c] += cnt;
		}
		
		// 5단계 
		cloud = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean flag = false;
				for (int k = 0; k < temp.size(); k++) {
					// 사라진 구름에 포함되면서
					if (i == temp.get(k)[0] && j == temp.get(k)[1]) {
						flag = true;
						break;
					}
				}
				
				if (!flag && arr[i][j] >= 2) {
					cloud.add(new int[] {i,j});
					arr[i][j] -= 2;
				}
			}
		}
	}
	
	
}
