import java.io.*;
import java.util.*;
public class B_11559_PuyoPuyo {
    static int[][] arr;
    static int answer = 0;
    static boolean[][] visited;
    static int N = 12;
    static int M = 6;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[N][M];

        // 빈칸: 0, Red: 1, Green: 2, Blue: 3, Pupple: 4, Yellow: 5
        for (int i = 0; i < 12; i++) {
            String st = br.readLine();
            for (int j = 0; j < 6; j++) {
                if (st.charAt(j) == '.') {
                    arr[i][j] = 0;
                } else if (st.charAt(j) == 'R') {
                    arr[i][j] = 1;
                } else if (st.charAt(j) == 'G') {
                    arr[i][j] = 2;
                } else if (st.charAt(j) == 'B') {
                    arr[i][j] = 3;
                } else if (st.charAt(j) == 'P') {
                    arr[i][j] = 4;
                } else {
                    arr[i][j] = 5;
                }
            }
        }

        while (true) {
            Queue<int[]> q = findGroup();
            // 만약 빈 q면 더이상 그룹이 없다는 뜻
            if (q.size() == 0) {
                break;
            }
            while (!q.isEmpty()) {
                int[] v = q.poll();
//                System.out.println(v[0] + " " + v[1]);
                removeGroup(v[0], v[1], arr[v[0]][v[1]]);
            }
//            for (int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(arr[i]));
//            }
            gravity();
            answer++;
        }

        System.out.println(answer);

    }

    static Queue<int[]> findGroup() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 만약 빈칸이 아니고 방문하지 않았다면
                if (arr[i][j] != 0 && !visited[i][j]) {
                    boolean result = bfs(i, j, arr[i][j]);
                    // 만약 그룹이 맞다면 Q에 넣기
                    if (result) q.add(new int[] {i, j});
                }
            }
        }
        return q;
    }

    static boolean bfs(int r, int c, int color) {
        int cnt = 1;        // 그룹 요소 갯수
        Queue<int[]> q= new LinkedList<>();
        visited[r][c] = true;
        q.add(new int[] {r,c});

        while (!q.isEmpty()) {
            int[] v = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                    if (arr[nr][nc] == color) {
                        cnt++;
                        visited[nr][nc] = true;
                        q.add(new int[] {nr,nc});
                    }
                }
            }
        }
        if (cnt >= 4) {
            return true;
        } else {
            return false;
        }
    }

    // 그룹 제거
    static void removeGroup(int r, int c, int color) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];
        visited[r][c] = true;
        q.add(new int[] {r,c});
        arr[r][c] = 0;

        while(!q.isEmpty()) {
            int[] v = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                    if (arr[nr][nc] == color) {
                        arr[nr][nc] = 0;
                        q.add(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }

    // 중력
    static void gravity() {
        for (int col = 0; col < M; col++) {
            for (int row = N-2; row >= 0; row--) {
                // 빈칸이면 패스
                if (arr[row][col] == 0) continue;

                int nextR = row+1;
                int temp = arr[row][col];
                while (true) {
                    // 인덱스를 벗어나거나 다음 칸이 빈칸이 아니면
                    if (nextR >= N || arr[nextR][col] != 0) break;
                    nextR++;
                }
                nextR--;
                if (nextR == row) {
                    continue;
                }
                arr[row][col] = 0;
                arr[nextR][col] = temp;
            }
        }
    }
}
