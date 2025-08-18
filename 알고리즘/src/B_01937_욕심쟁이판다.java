import java.util.*;
import java.io.*;
public class B_01937_욕심쟁이판다 {
    static int N;
    static int[][] arr;
    static int[][] dp;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int answer = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(i,j));
            }
        }
        System.out.println(answer);
    }

    static int dfs(int r, int c) {    //   현재 행,열,이동횟수
        // 만약 이미 방문했던 곳이면
        if (dp[r][c] != 0) return dp[r][c];

        int best = 1;
        // 4방향 탐색
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            // 만약 인덱스를 벗어나면
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            // 만약 현재 칸이 다음 칸보다 크다면
            if (arr[r][c] >= arr[nr][nc]) continue;

            best = Math.max(best, dfs(nr,nc) +1);
        }
        dp[r][c] = best;
        return best;
    }
}
