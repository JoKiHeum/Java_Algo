import java.util.*;
import java.io.*;

public class B_02138_전구와스위치 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr1 = new int[N+1];
		int[] arr2 = new int[N+1];
		int[] answer = new int[N];
		int cnt1 = 0;
		int cnt2 = 1;
		
		String input1 = br.readLine();
		String input2 = br.readLine();
		for (int i = 0; i < N; i++) {
			int temp = input1.charAt(i) - '0';
			int temp2 = input2.charAt(i) - '0';
			arr1[i] = temp;
			arr2[i] = temp;
			answer[i] = temp2;
		}
		// 0번째 스위치를 누르는 경우 
		arr2[0] = arr2[0] == 1 ? 0 : 1;
    	arr2[1] = arr2[1] == 1 ? 0 : 1;
    	
		for (int i = 1; i < N; i++) {
			// 만약 첫번째 경우인 스위치를 누르지 않았을 때 정답 값과 다르다면 
			if (arr1[i-1] != answer[i-1]) {
				arr1[i-1] = arr1[i-1] == 1? 0:1; 
				arr1[i] = arr1[i] == 1? 0:1;
				arr1[i+1] = arr1[i+1] == 1? 0:1;
				cnt1++;
			}
			
			// 만약 첫번째 경우인 스위치를 누르지 않았을 때 정답 값과 다르다면 
			if (arr2[i-1] != answer[i-1]) {
				arr2[i-1] = arr2[i-1] == 1? 0:1; 
				arr2[i] = arr2[i] == 1? 0:1;
				arr2[i+1] = arr2[i+1] == 1? 0:1;
				cnt2++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (arr1[i] != answer[i]) cnt1 = Integer.MAX_VALUE;
			if (arr2[i] != answer[i]) cnt2 = Integer.MAX_VALUE;
		}
	
		int cnt = Math.min(cnt1, cnt2);
		if(cnt== Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(cnt);
		}
		
		
	}
}
