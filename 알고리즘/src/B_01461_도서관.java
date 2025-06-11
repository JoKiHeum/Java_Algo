import java.util.*;
import java.io.*;

public class B_01461_도서관 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int max = Integer.MIN_VALUE; // 절댓값으로 가장 먼곳의 위치 
		
		// 양수 담을 곳 
		PriorityQueue<Integer> pq1 = new PriorityQueue<>((a,b) -> 
			{
				return b-a;
			}
		);
		
		// 음수 담을 곳
		PriorityQueue<Integer> pq2 = new PriorityQueue<>((a,b) -> 
			{
				return b-a;
			}
		);
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int dist = Integer.parseInt(st.nextToken());
			// 만약 dist 가 음수라면 
			if (dist < 0) {
				pq2.add(Math.abs(dist)); // 절대값으로 넣어주기 
			} else {
				pq1.add(dist);
			}
			max = Math.max(max, Math.abs(dist));
		}
		
		int answer = 0;
		
		// 음수 빼기 
		while(!pq2.isEmpty()) {
			int temp = pq2.poll(); // M개 씩 묶었을 때 가장 큰값 
			for (int i = 0; i < M-1; i++) {
				// 만약 pq2가 비었다면 
				if(pq2.isEmpty()) {
					break;
				} else {
					pq2.poll();
				}
			}
			answer += temp*2;
		}
		
		// 양수 빼기 
		while(!pq1.isEmpty()) {
			int temp = pq1.poll(); // M개 씩 묶었을 때 가장 큰값 
			for (int i = 0; i < M-1; i++) {
				// 만약 pq2가 비었다면 
				if(pq1.isEmpty()) {
					break;
				} else {
					pq1.poll();
				}
			}
			answer += temp*2;
		}
		answer -= max;
		System.out.println(answer);
	}
}
