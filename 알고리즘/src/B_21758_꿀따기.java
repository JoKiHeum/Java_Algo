import java.util.*;
import java.io.*;

public class B_21758_꿀따기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			arr[i] = a;
			total += a;
		}
		
		// case1 : 벌 벌 꿀
		// 첫번째 벌 왼쪽 끝 고정 꿀통 오른쪽 끝 고정 
		int case1 = 0;
		int temp2 = total - arr[0];
		for (int i = 1; i < N-1; i++) {
			int temp1 = total - arr[0]-arr[i];
			temp2 -= arr[i];
			case1 = Math.max(case1, temp1 + temp2);
		}
		
		// case2: 벌 꿀 벌
		// 첫번째 벌 왼쪽 끝 고정 2번째 벌 오른쪽 끝 고정
		int case2 = 0;
		temp2 = total - arr[0] - arr[N-1];
		for (int i = 1; i < N-1; i++) {
			int temp1 = temp2 + arr[i];
			case2 = Math.max(case2, temp1);
		}
		
		// case3: 꿀 벌 벌 
		// 벌통 왼쪽 끝 고정 첫번째 벌 오른쪽 끝 고정 
		int case3 = 0;
		temp2 = total - arr[N-1];
		for (int i = N-2; i > 0; i--) {
			int temp1 = total - arr[N-1] -arr[i];
			temp2 -= arr[i];
			case3 = Math.max(case3, temp1 + temp2);
		}
		
		int answer = Math.max(case1, Math.max(case2, case3));
		System.out.println(answer);
	}
}
