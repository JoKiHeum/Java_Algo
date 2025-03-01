import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_14888_연산자끼어넣기 {
	static int N; // 숫자 갯수 
	static int[] number; // 숫자들
	static int[] operator = new int[4]; // 각 연산자 갯수 
	static int MAX = Integer.MIN_VALUE; // 최댓값 
	static int MIN = Integer.MAX_VALUE; // 최솟값  

	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		number = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(number));
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(operator));
	
		
		dfs(number[0], 1);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	
	public static void dfs(int num, int level) {
		if (level == N) {
			// 최댓값 갱신 
			if (MAX < num) {
				MAX  = num;
			}
			// 최솟값 갱신 
			if (MIN > num) {
				MIN = num;
			}
			
			return;
		}
	
		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				
				operator[i] -= 1;
				
				if (i == 0) {
					dfs(num + number[level], level+1);
				} else if (i == 1) {
					dfs(num - number[level], level+1);
				} else if (i == 2) {
					dfs(num * number[level], level+1);
				} else {
					dfs(num / number[level], level+1);
				}
				operator[i] += 1;
			}
		}
	}
	
}
