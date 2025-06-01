import java.io.*;
import java.util.*;

public class B_14719_빗물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] arr= new int[W];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		for (int i = 1; i < W-1; i++) {
			int left = 0;
			int right = 0;
			
			int now = arr[i];
			
			// 현재 index를 기준으로 왼쪽에서 현재 블록보다 높으면서 가장 높은 값 
			for (int l = i-1; l >= 0; l--) {
				left = Math.max(left,arr[l]);
			}
			
			// 현재 index를 기준으로 오른쪽에서 현재 블록보다 높으면서 가장 높은 값
			for (int r = i+1; r < W; r++) {
				right = Math.max(right, arr[r]);
			}
			
			if (left > arr[i] && right > arr[i]) {
				answer += Math.min(left,right) - arr[i];
			}
		}
		System.out.println(answer);
	}
}
