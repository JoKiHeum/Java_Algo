import java.util.*;
import java.io.*;

public class B_16562_친구비 {
	static int N; 	// 사람 수 
	static int M; 	// 친구 관계 수 
	static int K; 	// 가지고 있는 돈
	static int[] parents; 
	static int[] cost;
	// make set 함수 
	static void makeSet(int a) {
		parents[a] = a;
	}
	// 부모 찾는 함수 
	static int findSet(int a) {
		if (a == parents[a]) return a;
		return findSet(parents[a]);
	}
	
	// 합치기 
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) {
			return;
		}
		// 더 싼 쪽으로 합치기 
		else if (cost[aRoot] < cost[bRoot]) {
			parents[bRoot] = aRoot;
		} else {
			parents[aRoot] = bRoot;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		cost = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N+1; i++) {
			makeSet(i);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
		
		int totalCost = 0;
		for (int i = 1; i < N+1; i++) {
			if (findSet(i) == i) {
				totalCost += cost[i];
			}
		}
		if (totalCost > K) {
			System.out.println("Oh no");
		} else {
			System.out.println(totalCost);
		}
		
	}
}
