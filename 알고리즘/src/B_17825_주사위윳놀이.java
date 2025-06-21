import java.util.*;
import java.io.*;
public class B_17825_주사위윳놀이 {
	static int[][] G = {
		//  0     1    2    3    4      5     6     7    8     9      10
			{1}, {2}, {3}, {4}, {5}, {6, 21}, {7}, {8}, {9}, {10}, {11, 24},
		//  11    12    13     14    15       16    17     18    19    20    21
			{12}, {13}, {14}, {15}, {16, 26}, {17}, {18}, {19}, {20}, {32}, {22},
		//  22     23    24    25    26   27     28    29   30    31   32
			{23}, {29}, {25}, {29}, {27}, {28}, {29}, {30}, {31}, {20}, {32}
	};
	
	static int[] score = {
	//      0  1  2  3  4  5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
			0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40,
	//      21  22  23  24  25  26  27  28  29  30  31  32
			13, 16, 19, 22, 24, 28, 27, 26, 25, 30, 35, 0  
	};
	
	static int[] arr = new int[10];
	static int answer;
	static int[] location = {0,0,0,0}; // 말의 현재 위치
	
	static void dfs(int level, int sum) {
		// 기저 조건 level == 10
		if (level == 10) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int now = location[i];  // i번 말의 현재 위치 
			if (now == 32) continue; // i번 말이 도착지점에 있는 말이면 넘어감 
			int next = G[now][0];
			if (now == 5 || now == 10 || now == 15) next = G[now][1]; // 만약 분기점이면 뒤에 노드로 이동 
			int cnt = arr[level];   // 이동 횟수 
			// 한번 이동 했으니까 이동횟수-1 만큼 이동 
			for (int j = 1; j < cnt; j++) {
				next = G[next][0];
			}
			
			if (next == 32 || check(next)) {  // 도착지점이 아니거나 다른 말이 없을 경우 
				location[i] = next;
				dfs(level + 1, sum + score[next]);
				location[i] = now;
			}
			
		}
	}
	
	static boolean check(int horse) {
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if (location[i] == horse) flag = false; // 만약 도착지점에 다른 말이 있을 경우 
		}
		return flag;
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		answer = 0;
		dfs(0, 0); // 
		System.out.println(answer);
	}
	
	
	
}
