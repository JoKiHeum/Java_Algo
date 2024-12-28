import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;
public class B_01697_숨바꼭질 {
	static int N;
	static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		
		int answer = BFS();
		System.out.println(answer);
	}
	static int BFS() {
		int[] visited = new int[100001];
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visited[N] = 1;
		
		
		while (!q.isEmpty()) {
			int x = q.poll();
		
			if (x == K) {
				return visited[x] - 1;
			}
			for(int nx: new int[] {x-1, x+1, x*2}) {
				
				if (0 <= nx && nx <= 100000 && visited[nx] == 0) {
					q.add(nx);
					visited[nx] = visited[x] + 1;
				}
			}
		}
		return -1;
		
	}
}
