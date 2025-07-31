import java.util.*;
import java.io.*;
public class B_02228_구간나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] sum = new int[N+1];
        int[][] dp = new int[N+1][M+1];

        for (int i = 0; i < N; i++) {
            sum[i+1] = sum[i] + Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = -32768 * 101;
            }
        }

        dp[1][1] = sum[1];

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // i번째 수를 포함 안했을 경우
                dp[i][j] = dp[i-1][j];

                // i번째 수를 포함하는 경우들
                // 만약 j=1 이면
                if (j == 1) {
                    dp[i][j] = Math.max(dp[i][j], sum[i]);
                }
                for (int k = 0; k <= i-2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j-1] + sum[i] - sum[k+1]);
                }
            }
        }
        System.out.println(dp[N][M]);
    }
}
