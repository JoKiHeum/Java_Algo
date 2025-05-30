import java.util.*;
import java.io.*;

public class B_01477_휴게소세우기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		// 휴게소 배열  
		int[] arr = new int[N+2];
		arr[N+1] = L;
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		// distance 배열 
		int[] distance = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			distance[i] = arr[i+1] - arr[i];
		}
//		System.out.println(Arrays.toString(distance));
		
		// 이분 탐색 
		int left = 1;
		int right = L;
		int answer = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = 0;  // 필요한 정류장 갯수
			
			for (int i = 0; i < N+1; i++) {
				if(distance[i] > mid) {
					cnt += (distance[i] -1) / mid;
				}
			}
			if (cnt > M) {
				left = mid+1;
			} else {
				answer = mid;
				right = mid-1;
			}
		}
		System.out.println(answer);
	}
}
