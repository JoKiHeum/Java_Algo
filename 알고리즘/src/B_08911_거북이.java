import java.io.*;
import java.util.*;

public class B_08911_거북이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		// 상우하좌 
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		for (int t = 0; t < T; t++) {
			String command = br.readLine();
			int len = command.length();  // 명령의 수 
			
			int maxX = 0;  // x좌표의 최댓값 
			int minX = 0;  // x좌표의 최솟값
			int maxY = 0; // y좌표의 최대값
			int minY = 0; // y좌표의 최솟값 
			
			
			int direction = 0; // 상 : 0, 우 : 1, 하 : 2, 좌 : 3 
			int nx = 0; // 현재 x 좌표 
			int ny = 0; // 현재 y 좌표 
			
			for (int i = 0; i < len; i++) {
				char c = command.charAt(i);
				if (c == 'F') {
					nx += dx[direction];
					ny += dy[direction];
					maxX = Math.max(nx, maxX);
					maxY = Math.max(ny, maxY);
					minX = Math.min(nx, minX);
					minY = Math.min(ny, minY);
				} else if (c == 'B') {
					nx -= dx[direction];
					ny -= dy[direction];
					maxX = Math.max(nx, maxX);
					maxY = Math.max(ny, maxY);
					minX = Math.min(nx, minX);
					minY = Math.min(ny, minY);
				} else if (c == 'R') {
					direction = (direction + 1) % 4;
				} else if (c == 'L') {
					if (direction == 0) {
						direction = (4 + direction - 1) % 4;
					} else {
						direction = (direction - 1) % 4;
					}
				}
			}
			int answer = (maxX - minX) * (maxY - minY);
			System.out.println(answer);
		}
		
	}
}
