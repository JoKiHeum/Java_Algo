import java.util.*;
import java.io.*;

public class B_02157_여행 {
    static int N;
    static int M;
    static int K;
    static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a > b) continue;    //   서쪽으로 가는 건 필요 없음
            arr[a][b] = Math.max(arr[a][b], c);
        }


        // dp[i][j]: j번 비행으로 i 도시에 도착했을 때 최대 기내식 점수
        int[][] dp = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) Arrays.fill(dp[i], -1);
        dp[1][1] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = i+1 ; j <= N; j++) {
                if (arr[i][j] == 0) continue;       // 갈 수없음

                for (int k = 1; k < M; k++) {
                    if (dp[i][k] != -1) {
                        dp[j][k+1] = Math.max(dp[j][k+1], dp[i][k] + arr[i][j]);
                    }
                }
            }
        }
//        for (int i = 0; i < N+1; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        int answer = 0;
        for (int i = 1; i < M+1; i++) {
            if (answer < dp[N][i]) {
                answer = dp[N][i];
            }
        }
        System.out.println(answer);


    }
}
