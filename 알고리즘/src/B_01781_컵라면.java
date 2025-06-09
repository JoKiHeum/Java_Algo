import java.util.*;
import java.io.*;
public class B_01781_컵라면 {
	static class problem implements Comparable<problem> {
		int time; // 데드라인 
		int cnt; // 컵라면 수
		
		public problem(int time, int cnt) {
			this.time = time;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(problem o) {
			if (this.time !=  o.time) {
				return o.time - this.time;
			} else {
				return o.cnt - this.cnt;
			}
		}
		
	}

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int maxTime = Integer.MIN_VALUE;
		PriorityQueue<problem> pq = new PriorityQueue<>(); // 후보군 

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			maxTime = Math.max(maxTime, t);	
			pq.add(new problem(t,c));
		}

		
		// 데드라인 기준으로 내림차순
		
		int answer = 0;
		PriorityQueue<problem> pq2 = new PriorityQueue<>(new Comparator<problem>() {
		    @Override
		    public int compare(problem o1, problem o2) {
		        return o2.cnt - o1.cnt;
		    }
		    }); 
		for (int i = maxTime; i > 0; i--) {
			while(!pq.isEmpty()) {
				problem p = pq.poll(); // 후보군 꺼내기
				
				// 만약 꺼내 후보군이 maxTime 이상이라면 
				if (p.time >= i) {
					pq2.add(p);
				} else {
					pq.add(p);
					break;
				}
			}
			if (!pq2.isEmpty()) {
				problem choice = pq2.poll();
//				System.out.println(choice.time + " " + choice.cnt);
				answer += choice.cnt;
			}
			
		}
		System.out.println(answer);
	}
}
