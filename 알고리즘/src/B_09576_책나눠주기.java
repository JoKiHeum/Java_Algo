import java.util.*;
import java.io.*;

public class B_09576_책나눠주기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[N+1]; // 나눠준 책에 대한 여부 
			int[][] needs = new int[M][2]; // 학생들이 원하는 책의 범위
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				needs[i] = new int[] {a,b};
			}
			
			Arrays.sort(needs, (a,b) -> {
				if(a[1] != b[1]) return	a[1] - b[1];
				return a[0] - b[0];
			});
			
			int answer = 0;
			for (int i = 0; i < M; i++) {
				int s = needs[i][0];
				int e = needs[i][1];
				
				//해당 범위 내에서 탐색
				for (int j = s; j <= e; j++) {
					// 만약 방문하지 않았다면 
					if(!visited[j]) {
						answer++;
						visited[j] = true;
						break;
					}
				}
			}
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);
		
		
	}
}
