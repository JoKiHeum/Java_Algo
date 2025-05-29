import java.util.*;
import java.io.*;

public class B_02283_구간자르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int max = -1;
		int min = 1000001;
	
		int[] arr = new int[1000001];  // 누적합 리스트 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[s]++;
			arr[e]--;
			max = Math.max(max, e);
			min = Math.min(min, s);
		}
		
		for (int i = min+1; i <= max; i++) {
			arr[i] += arr[i-1];
		}
		
		int left = min;
		int right = min;
		int A = 0;
		int B = 0;
		int sum = 0;
		while (right <= max ) {
			if (sum == K) {
				A = left;
				B = right;
				break;
			} else if (sum < K) {
				sum += arr[right];
				right++;
			} else {
				sum -= arr[left];
				left++;
				
			}
		}
		if(A == min) {
			 A = 0;
		}
		System.out.println(A + " " + B);

	}

}
