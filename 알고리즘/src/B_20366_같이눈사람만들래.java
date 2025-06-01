import java.io.*;
import java.util.*;

public class B_20366_같이눈사람만들래 {
	static int[] arr;
	static int N;
	static int[][] snows;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int len = N*(N-1)/2;
		snows = new int[len][3];  // 가능한 조합 
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int index = 0;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				snows[index][0] = i; // 사용한 첫번째 눈덩이 
				snows[index][1] = j; // 사용한 두번째 눈덩이 
				snows[index][2] = arr[i] + arr[j]; // 지름의 합
				index++;
			}
		}
		
		
		Arrays.sort(snows, Comparator.comparing(a -> a[2]));
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < len-1; i++) {
			for (int j = i+1; j < len; j++) {
				boolean result = check(i, j);
				if (result) {
					continue;
				}
				answer = Math.min(Math.abs(snows[i][2]-snows[j][2]),answer);
				break;
			}
		}
		System.out.println(answer);
	}
	
	static boolean check(int first, int second) {
		boolean flag = false;
		if (snows[first][0] == snows[second][0] || snows[first][0] == snows[second][1] || 
				snows[first][1] == snows[second][0] || snows[first][1] == snows[second][1]) {
			flag = true;
		}
		return flag;
	}
}
