import java.util.*;
import java.io.*;

public class B_02143_두배열의함 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long T = Long.parseLong(br.readLine());
		
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int aSize = (n*(n+1))/2; // 수열 A의 부배열의 합의 갯수
		long[] aSum = new long[aSize]; 
		
		int idx = 0;
		for (int i = 0; i < n; i++) {
			long sumA = 0;
			for (int j = i; j < n; j++) {
				sumA += A[j];
				aSum[idx] = sumA;
				idx += 1;
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		int bSize = (m*(m+1))/2; // 수열 B의 부배열의 합의 갯수
		long[] bSum = new long[bSize];
		
		idx = 0;
		for (int i = 0; i < m; i++) {
			long sumB = 0;
			for (int j = i; j < m; j++) {
				sumB += B[j];
				bSum[idx] = sumB;
				idx += 1;
			}
		}
		
		Arrays.sort(aSum);
		Arrays.sort(bSum);
//		System.out.println(Arrays.toString(aSum));
//		System.out.println(Arrays.toString(bSum));
		
		int left = 0;
		int right = bSize -1;
		
		long count = 0;
		while (left < aSize && right > -1) {
			// 만약 두 부 배열의 합이 T보다 크다면 
			if (aSum[left] + bSum[right] > T) {
				right -= 1;
			} else if(aSum[left] + bSum[right] < T) {
				left += 1;
			} else {
				// 같을 경우 중복된 수가 있는 지 한번에 세주어야 한다. 안그럼 모든 값을 탐색할 수 없다.
				long cntA = 0;
				long a = aSum[left];
				while (left < aSize && a == aSum[left]) {
					left += 1;
					cntA += 1;
				}
				
				long cntB = 0;
				long b = bSum[right];
				while (right > -1 && b == bSum[right]) {
					right -= 1;
					cntB += 1;
				}
				count += cntA * cntB;
				
			}
		}
		System.out.println(count);
	}
}
