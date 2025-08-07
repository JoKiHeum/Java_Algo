import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// L[i]: 해당 행의 가장 왼쪽 생산 기계 열, R[i]: 가장 오른쪽 생산 기계 열
		int[] L = new int[N + 1];
		int[] R = new int[N + 1];
		final int INF = Integer.MAX_VALUE / 2;
		// 초기화
		for (int i = 1; i <= N; i++) {
			L[i] = N + 1;
			R[i] = 0;
		}
		// 입력 읽으면서 L, R 계산
		for (int i = 1; i <= N; i++) {
			String row = br.readLine();
			for (int j = 1; j <= N; j++) {
				if (row.charAt(j - 1) == '1') {
					L[i] = Math.min(L[i], j);
					R[i] = Math.max(R[i], j);
				}
			}
		}
		// 시작점 (1,1)
		L[1] = Math.min(L[1], 1);
		R[1] = Math.max(R[1], 1);
		// 반드시 (N,N)
		L[N] = Math.min(L[N], N);
		R[N] = Math.max(R[N], N);

		// 처리할 행 목록(rows): R[i] != 0 인 행들
		List<Integer> rows = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (R[i] != 0) {
				rows.add(i);
			}
		}
		int m = rows.size();
		// dp[i][0]: rows.get(i)행을 L 위치에서 끝낼 때 최소 벨트 개수
		// dp[i][1]: rows.get(i)행을 R 위치에서 끝낼 때
		int[][] dp = new int[m][2];
		for (int i = 0; i < m; i++) {
			dp[i][0] = dp[i][1] = INF;
		}
		// 첫 행: rows.get(0) == 1, L=R=1 에서 출발
		dp[0][0] = dp[0][1] = 0;

		// DP 전이
		for (int i = 0; i < m - 1; i++) {
			int prevRow = rows.get(i);
			int curRow = rows.get(i + 1);
			int ddown = curRow - prevRow;
			int Li = L[curRow];
			int Ri = R[curRow];
			int width = Ri - Li;

			for (int k = 0; k < 2; k++) {
				int prevCol = (k == 0 ? L[prevRow] : R[prevRow]);
				int base = dp[i][k] + ddown;
				if (base >= INF) continue;

				// 이번 행을 R 위치에서 끝내는 경우
				int cost1 = base + Math.abs(prevCol - Li) + width;
				int cost2 = base + Math.abs(prevCol - Ri) + 2 * width;
				dp[i + 1][1] = Math.min(dp[i + 1][1], Math.min(cost1, cost2));

				// 이번 행을 L 위치에서 끝내는 경우
				int cost3 = base + Math.abs(prevCol - Ri) + width;
				int cost4 = base + Math.abs(prevCol - Li) + 2 * width;
				dp[i + 1][0] = Math.min(dp[i + 1][0], Math.min(cost3, cost4));
			}
		}

		// 결과 계산
		int best = Math.min(dp[m - 1][0], dp[m - 1][1]);
		if (best >= INF) {
			System.out.println(-1);
		} else {
			// 마지막 (N,N) -> 격자 밖 한 칸 이동
			System.out.println(best + 1);
		}
	}
}
