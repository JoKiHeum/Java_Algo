import java.io.*;
import java.util.*;

public class B_1253_좋다 {
	public static void main(String[] args) throws IOException {
		// 입력 받
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬 오름차순
		Arrays.sort(arr);
		
		int answer = 0;
		// 투포인터 
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N-1;
			while(true) {
				// 자기 자신과 달라야 하므로
				if (left == i) {
					left++;
				} else if (right == i) {
					right--;
				}
				
				if (left >= right) {
					break;
				}
				
				// 만약 같다면 
				if (arr[left] + arr[right] == arr[i]) {
					answer += 1;
					break;
				} else if (arr[left] + arr[right] > arr[i]) {
					right -= 1;
				} else {
					left += 1;
				}
			}
		}
		System.out.println(answer);
		
	}
}
