import java.util.*;
import java.io.*;
public class B_16938_캠프준비 {
	static int N;
	static int L;
	static int R;
	static int X;
	static int cnt = 0; // 정답 
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		boolean[] visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 2; i <= N; i++) {
			comb(0, i, 0, visited);
		}
		
		System.out.println(cnt);
	}
	
	static void comb(int level, int count, int sum, boolean[] visited) {
		// 기저 조건 1: 난이도의 합이 R 초과 
		if (sum > R) {
			return;
		}
		// 종료 조건 
		if (count == 0) {
			// 만역 난이도의 합이 L보다 작으면 끝내기
//			System.out.println(sum);
			if (sum < L) {
				return;
			}
			
			int max = 1; // 뽑힌 것중 최대 닌이도 
			int min = 1000001; // 뽑힌 것중 최소 난이도 
			
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					max = Math.max(max, arr[i]);
					min = Math.min(min, arr[i]);
				}
			}
			if (max-min < X) {
				return;
			}
			cnt++;
			return;
		}

		if (level == N) {
			return;
		}
		// 포함 
		visited[level] = true;
		comb(level+1, count-1, sum+arr[level], visited);
		visited[level] = false; // 포함 해제 
		// 안포함
		comb(level+1, count, sum, visited);
		
	}
}
