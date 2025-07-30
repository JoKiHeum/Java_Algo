import java.util.*;
import java.io.*;
public class B_20058_마법사상어파이어스톰 {
    static double n;
    static int N;
    static int Q;
    static int[] L;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1 ,0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = (double)Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        N = (int)Math.pow(2, n);
        arr = new int[N][N];
        L = new int[Q];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            double s = Double.parseDouble(st.nextToken());
            L[i] = (int)Math.pow(2,s);
        }

        for (int i = 0; i < Q; i++) {
            rotate(L[i]);
            reduceIce();
        }
        int sum = 0;
        int answer = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && arr[i][j] !=0) {
                    int result = bfs(i, j);
                    answer = Math.max(answer,result);
                }
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
        System.out.println(answer);

    }

    // 회전시키기
    static void rotate(int l) {
        int[][] temp = new int[N][N];
        for (int si = 0; si < N; si+=l) {
            for (int sj = 0; sj < N; sj+=l) {
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l; j++) {
                        temp[si+i][sj+j] = arr[si+l-1-j][sj+i];
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    // 얼음줄이기
    static void reduceIce() {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 만약 얼음이 없으면 패스
                if (arr[i][j] == 0) continue;

                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (0 <= nr && nr < N && 0 <= nc && nc <N) {
                        if (arr[nr][nc] > 0) cnt++;
                    }
                }
                if (cnt < 3) q.add(new int[] {i,j});
            }
        }
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int[] v = q.poll();
            arr[v[0]][v[1]] -= 1;
        }
    }





    // 덩어리 구하기
    static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r,c});
        visited[r][c] = true;
        int cnt = 1;

        while(!q.isEmpty()) {
            int[] v = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && arr[nr][nc] != 0) {
                    q.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

}
