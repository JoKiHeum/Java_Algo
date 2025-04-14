import java.util.*;
import java.io.*;

public class B_01759_암호만들기 {
	static int L;
	static int C;
	static char[] arr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		visited = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		// 정렬 
		Arrays.sort(arr);
		dfs(0,0);
		System.out.println(sb);
		
	}
	
	static void dfs(int start, int level) {
		if (level == L) {
			String key = "";
			int vowel  = 0; // 모음수;
			int consonant = 0; // 자음 수;
			
			for (int i = 0; i < C; i++) {
				if (visited[i]) {
					key += arr[i];
					if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
						vowel += 1;
					} else {
						consonant += 1;
					}
				}
				
			}
			if (vowel >= 1 && consonant >=2) {
				sb.append(key + '\n');
			}
		}
		
		for (int i = start; i < C; i++) {
			visited[i] = true;
			dfs(i+1, level+1);
			visited[i] = false;
		}
	}
}
