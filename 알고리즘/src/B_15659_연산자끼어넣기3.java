import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15659_연산자끼어넣기3 {
	static int N;
	static int[] number;
	static int[] operator = new int[4];
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;
	static int[] cal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		number = new int[N];
		cal = new int[N-1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i< 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void dfs(int level) {
		if (level == N-1) {
			
		}
		
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				continue;
			}
			cal[level] = i; 
					
			operator[i] -= 1;
			dfs(level++);
			operator[i] += 1;
		}
	}
	
	public static int calculater(int[] cal) {
		
	}
}
