import java.io.*;
import java.util.*;

public class B_05212_지구온난화 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] arr = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = input.charAt(j);
			}
		}
		
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		List<int[]> temp = new ArrayList<>();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int cnt = 0; // 바다 갯수 
				if (arr[i][j] == 'X') {
					
					for (int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						// 인덱스를 벗어나거나 . 이면 cnt + 1
						if (0 > nr || nr >= R || 0 > nc || nc >= C) {
							cnt++;
							continue;
						}
						if (arr[nr][nc] == '.') {
							cnt++;
						}

					}
					// cnt 가 3 이상이면 바다로 바꾸기 위해 임시 리스트에 저장 
					if (cnt >= 3) {
						int[] location = {i,j};
						temp.add(location);
					}
					
				}
			}
		}
		
		for (int[] l : temp) {
			arr[l[0]][l[1]] = '.';
		}
		
		int minR = Integer.MAX_VALUE;
		int minC = Integer.MAX_VALUE;
		int maxR = Integer.MIN_VALUE;
		int maxC = Integer.MIN_VALUE;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 'X') {
					minR = Math.min(minR, i);
					minC = Math.min(minC, j);
					maxR = Math.max(maxR, i);
					maxC = Math.max(maxC, j);
				}
			}
		}
		for (int i = minR; i <= maxR; i++) {
			for (int j = minC; j <= maxC; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		
	}
}
