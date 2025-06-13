import java.util.*;
import java.io.*;

public class B_01043_거짓말 {
	static int N;
	static int M;
	static int[] parents;
	
	static void makeSet(int a) {
		parents[a] = a;
	}
	
	static int findSet(int a) {
		if (a == parents[a]) return a;
		return findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		// 	집합 만들기 
		for (int i = 1; i < N+1; i++) {
			makeSet(i);
		}
		
		// 진실을 아는 사람들 리스트 
		List<Integer> truthPeople = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int truthCnt = Integer.parseInt(st.nextToken());
		
		if (truthCnt == 0) {
			System.out.println(M);
			return;
		}
		for (int i = 0; i < truthCnt; i++) {
			int p = Integer.parseInt(st.nextToken());
			truthPeople.add(p);
		}
		
		// 파티 정보 리스트 
		List<Integer>[] parties = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			parties[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int participantCnt = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			parties[i].add(a);
			for (int j = 1; j < participantCnt; j++) {
				int b = Integer.parseInt(st.nextToken());
				parties[i].add(b);
				union(a,b);
			}
		}
		
		// 진실을 아는 사람들의 root 번호 set에 저
		HashSet<Integer> set = new HashSet<>();
		
		for (int p : truthPeople) {
			set.add(findSet(p));
		}
		
		
		int answer = 0;
		for (int i = 0; i < M; i++) {
			boolean flag = true;
			for (int person : parties[i]) {
				if (set.contains(findSet(person))) {
					flag = false;
					break;
				}
			}
			if (flag) answer++;
		}
		System.out.println(answer);
		
		
	}
}
