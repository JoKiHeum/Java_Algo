import java.util.*;
import java.io.*;

public class B_15805_트리나라관광가이드 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] P = new int[N]; // 부모 배열
		boolean[] visited = new boolean[N]; // 방문 배열
		
		st = new StringTokenizer(br.readLine());
		
		
		// 첫번째 방문 도시 설정
		int v = Integer.parseInt(st.nextToken()); 
		P[v] = -1;
		visited[v] = true;
		int max = v;         // 가장 마지막 번호의 숫자 

		
		for (int i = 1; i < N; i++) {
			int parent = v;
			v = Integer.parseInt(st.nextToken());
			max = Math.max(v, max);
			// 만약 방문하지 않은 도시라면 
			if (!visited[v]) {
				P[v] = parent;
				visited[v] = true;
			} else {
				continue;
			}
		}
		for (int i = 0; i <= max; i++) {
			sb.append(P[i] + " ");
		}
		int count = max + 1; // 총도시의 갯수 
		System.out.println(count);
		System.out.println(sb);
		
		
		
		
	}
}
