import java.util.*;
import java.io.*;

public class B_02169_로봇조종하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dp 배열 생성 및 초기화
        int[][] dp = new int[N][M];
        dp[0][0] = arr[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j-1] + arr[0][j];
        }
        for (int i = 1; i < N; i++) {
            Arrays.fill(dp[i], -1000000000);
        }

        int[] L;
        int[] R;

        for (int i = 1; i < N; i++) {
            // 왼쪽에서 오는거와 위에서 오는거 비교
            L = new int[M];
            L[0] = dp[i-1][0] + arr[i][0];
            for (int j = 1; j < M; j++) {
                L[j] = Math.max(dp[i-1][j], L[j-1]) + arr[i][j];
            }


            // 오른쪽에서 오는거와 위에서 오는 거 비교
            R = new int[M];
            R[M-1] = dp[i-1][M-1] + arr[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                R[j] = Math.max(dp[i-1][j], R[j+1]) + arr[i][j];
            }


            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(L[j], R[j]);
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}

