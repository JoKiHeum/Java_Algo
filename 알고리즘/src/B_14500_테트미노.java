import java.util.*;
import java.io.*;

// DFS
public class B_14500_테트미노 {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                DFS(0,i,j,arr[i][j]);
                extraShape(i,j);
                visited[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    // 'ㅗ'를 제외한 모양
    static void DFS(int level, int r, int c, int sum) {     // 깊이, 행, 열, 합계
        if (level == 3) {
            answer = Math.max(answer,sum);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {
                visited[nr][nc] = true;
                DFS(level+1, nr, nc, sum+arr[nr][nc]);
                visited[nr][nc] = false;
            }
        }
    }

    // ㅗ 모양
    static void extraShape(int r, int c) {
        int center = arr[r][c];     // ㅗ 모양의 중심
        for (int k = 0; k < 4; k++) {
            int temp = center;
            boolean flag = false;
            for (int i = 0; i < 3; i++) {
                int nr = r + dr[(k + i) % 4];
                int nc = c + dc[(k + i) % 4];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    flag = true;
                    break;
                }
                temp += arr[nr][nc];
            }
            if (flag) continue;
            answer = Math.max(temp, answer);
        }
    }
}
