import java.io.*;
import java.util.*;

public class B_10216_CountCircleGroups {
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			parents = new int[N];
			int[][] location = new int[N][3]; // 적 위치 
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				parents[i] = i;
				location[i][0] = x;
				location[i][1] = y;
				location[i][2] = r;
			}
			
			int answer = N;
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					int diffX = location[i][0] - location[j][0];
					int diffY = location[i][1] - location[j][1];
					int r = location[i][2] + location[j][2];
					
					if (diffX * diffX + diffY * diffY <= r * r) {
						if (findSet(i) != findSet(j)) {
							unionSet(i,j);
							answer--;
						}
					}
				}
			}
			sb.append(answer + "\n");
 		}
		System.out.println(sb);
	}
	
	static int findSet(int a) {
		if (parents[a] != a) {
			return findSet(parents[a]);
		}
		return a;
	}
	
	static void unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot > bRoot) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
	}
}
