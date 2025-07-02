//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Stack;
//import java.util.StringTokenizer;
//
//
//public class B_15659_연산자끼어넣기3 {
//
//	static int N;
//	static int[] numbers;
//	static int[] operators = new int[4];
//	static int max = Integer.MIN_VALUE; // 최대값
//	static int min = Integer.MAX_VALUE; // 최소값
//	static int[] arr;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());
//
//		numbers = new int[n];
//
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		for (int i = 0; i < N; i++ ) {
//			numbers[i] = Integer.parseInt(st.nextToken());
//		}
//
//		st = new StringTokenizer(br.readLine());
//		for (int i = 0; i < 4; i++) {
//			operators[i] = Integer.parseInt(st.nextToken());
//		}
//	}
//
//	static void DFS(int level) {
//		if (level == N) {
//			int result = cal();
//			min = Math.min(min, result);
//			max = Math.max(max, result);
//			return;
//		}
//		for (int i = 0; i < 4; i++) {
//			if (operators[i] > 0) {
//				operators[i] -= 1;
//
//				if (i == 0) {
//
//				}
//			}
//		}
//
//
//	}
//
////	static int cal() {
////
////	}
//}
