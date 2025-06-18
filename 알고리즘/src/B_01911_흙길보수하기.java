import java.util.*;
import java.io.*;

public class B_01911_흙길보수하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 시작 점을 기준으로 오름차순 정렬 
		Arrays.sort(arr, (a,b) -> {
			return a[0] - b[0];
		});
		
		int index = 0;
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			int start = arr[i][0];
			int end = arr[i][1];
			int needs = 0;
			// 마지막 널판지의 위치가 끝지점보다 크다면 다음 웅덩이로 
			if (index >= end) {
				continue;
			}
			// 마지막 널판지의 위치가 start 보다 작다면 
			if (index < start) {
				if ((end-start) % L != 0) {
					needs = (end - start) / L + 1;
				} else {
					needs = (end - start) / L;
				}
				index = start + needs * L;
			} else {
			
				if ((end-index) % L != 0) {
					needs = (end - index) / L + 1;
				} else {
					needs = (end - index) / L;
				}
				index = index + needs * L;
			}
			cnt += needs; 
		}
		System.out.println(cnt);
	}
}
