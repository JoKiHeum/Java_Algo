import java.util.*;
import java.io.*;

public class B_01103_게임 {
    static int N, M;
    static int[][] arr;
    static int[][] dp;
    static boolean[][] visited;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    static int answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char num = input.charAt(j);
                if (num == 'H') {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = num - '0';
                }
            }
        }

        visited[0][0] = true;
        dfs(0, 0, 1);

        System.out.println(answer);
    }

    static void dfs(int r, int c, int cnt) {
        if (answer == -1) {
            return;
        }
        if (cnt > answer) {
            answer = cnt;
        }
        dp[r][c] = cnt;

        for (int i = 0; i < 4; i++) {
            int step = arr[r][c];       // 움직이는 칸 수
            int nr = r + (dr[i] * step);
            int nc = c + (dc[i] * step);

            // 범위 벗어나거나 구멍이면
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || arr[nr][nc] == 0) continue;

            // 방문 했다면
            if (visited[nr][nc]) {
                answer = -1;
                return;
            };

            // 다른 루트에서 방문했는데 더 크다면
            if (dp[nr][nc] > cnt) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, cnt+1);
            visited[nr][nc] = false;
        }
    }
}
