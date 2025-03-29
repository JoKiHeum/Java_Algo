import java.util.*;
import java.io.*;


public class B_02665_미로만들기 {
	
	static class Room implements Comparable<Room> {
		int r;
		int c;
		int count;
		
		public Room(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
		
		@Override
		public int compareTo(Room o) {
			return Integer.compare(this.count, o.count);
		}
	}
	
	static int N;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	static int[][] arr;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String sr = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = sr.charAt(j) - '0';
			}
		}
		int result = Dijkstra();
		
		System.out.println(result);
	}
	
	static int Dijkstra() {
		PriorityQueue<Room> pq = new PriorityQueue<>();
		pq.add(new Room(0,0,0));
		
		while (!pq.isEmpty()) {
			Room room = pq.poll();
			
			// 만약 방문햇으면 패스 ( 꼭 필요하지는 않음)
			if (visited[room.r][room.c]) {
				continue;
			}
			
			visited[room.r][room.c] = true;
			
			//도착 했을 경우
			if (room.r == N-1 && room.c == N-1) {
				return room.count;
			}
			
			
			for (int i = 0; i < 4; i++) {
				int nr = room.r + dr[i];
				int nc = room.c + dc[i];
				
				// 인덱스 안벗어나고 방문하지 않았다면  
				if (0<= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
					// 흰 색 방이면 count , 검은색 방이면 count +1 
					if (arr[nr][nc] == 1) {
						pq.add(new Room(nr, nc, room.count));
					} else {
						pq.add(new Room(nr, nc, room.count+1));
					}
					
					
				}
			}
		}
		return 0;
	}
}
