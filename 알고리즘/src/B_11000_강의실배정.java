import java.util.*;
import java.io.*;

public class B_11000_강의실배정 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			
			arr[i] = new int[] {startTime,endTime};
		}
		// 정렬(시작시간을 기준으로 오르차순 만약 같다면 끝나는 시간을 기준으로 오름차순)
		Arrays.sort(arr, (a,b) -> {
			if (a[0] != b[0]) return a[0] - b[0];
			return a[1]-b[1];
		});
		
		// pq 정렬 기준 일찍 끝나는 순으로 정렬 
		PriorityQueue<int[]> pq = new PriorityQueue<>(
			(a,b) -> {
				return a[1] - b[1];
			}
		);
		pq.add(arr[0]);
		
		for (int i = 1; i < N; i++) {
			int start = arr[i][0];
			int end = arr[i][1];
			
			int[] room = pq.poll();
			// 만약 현재 제일 일찍 끝나는 수업이 새로운 수업의 시작보다 빨리 끝난다면 
			if (room[1] <= start) {
				pq.add(arr[i]);
			} else {
				pq.add(room);
				pq.add(arr[i]);
			}
		}
		
		System.out.println(pq.size());
		
		
	}
}
