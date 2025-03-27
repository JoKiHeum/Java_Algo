import java.util.*;
import java.io.*;

public class B_01027_고층건물 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			double temp = 0;
			
			for (int j = i-1; j >=0 ; j--) {
				double slope = (double)(arr[i] - arr[j])/(i-j);
				if (j==i-1 || temp > slope) {
					temp = slope;
					cnt++;
				}
			}
			
			for (int j = i+1; j < N; j++) {
				double slope = (double)(arr[i] - arr[j])/(i-j);
				if(j == i+1 || temp < slope) {
					temp = slope;
					cnt++;
				}
			}
			
			max = Math.max(max, cnt);
		}
		
		System.out.println(max);
		
		
	}
}
