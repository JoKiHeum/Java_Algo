import java.io.*;
import java.util.*;

public class B_14864_줄세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] index = new int[N+1];
		
		for (int i = 1; i < N+1; i++) {
			index[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			index[u]++;
			index[v]--;
		}
		
		boolean flag = false;
		boolean[] visited = new boolean[N+1]; // 중복확인용 
		
		// 만약 index를 벗어나는 숫자가 있거나 중복된 숫자가 있다면 
		for (int i = 1; i < N+1; i++) {
			if(index[i] < 0 || index[i] > N) {
				flag = true;
				break;
			}
			if(visited[index[i]]) {
				flag = true;
				break;
			} else {
				visited[index[i]] = true;
			}
		}
		
		
		if(flag) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < N+1; i++) {
				sb.append(index[i] + " ");
			}
			System.out.println(sb);
		}
	}
}
