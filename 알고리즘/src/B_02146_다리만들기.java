import java.util.*;
import java.io.*;

public class B_02146_다리만들기 {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int answer = Integer.MAX_VALUE;
    static Queue<int[]> edges = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 라벨링 함수 실행
        int label = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    bfs(i, j, label);
                    label++;
                }
            }
        }

        bfs2();
        System.out.println(answer);


//        // 디버깅
//        for (int[] e : edges) {
//            System.out.println(Arrays.toString(e));
//        }
    }

    // 섬 라벨
    static void bfs(int r, int c, int label) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        arr[r][c] = label;

        while (!q.isEmpty()) {
            int[] v = q.poll();
            boolean isEdge = false;
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];
                if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                    if (arr[nr][nc] != 0 && !visited[nr][nc]) {
                        q.add(new int[]{nr, nc});
                        arr[nr][nc] = label;
                        visited[nr][nc] = true;
                    } else if (arr[nr][nc] == 0) {
                        isEdge = true;
                    }
                }
            }
            // 만약 섬의 가장자리라면
            if (isEdge) {
                edges.add(new int[]{v[0], v[1], label});
            }
        }
    }

    static void bfs2() {
        int[][] dist = new int[N][N];
        int[][] labelMap = new int[N][N];
        Queue<int[]> q = new LinkedList<>();

        // 모든 가장자리 좌표를 큐에 넣고 초기화
        for (int[] e : edges) {
            int r = e[0];
            int c = e[1];
            q.add(new int[]{r, c});
            dist[r][c] = 0;
            labelMap[r][c] = arr[r][c];  // 섬 번호 기억
        }

        while (!q.isEmpty()) {
            int[] v = q.poll();
            int r = v[0];
            int c = v[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                // 바다인 경우 -> 확장
                if (arr[nr][nc] == 0 && dist[nr][nc] == 0) {
                    arr[nr][nc] = arr[r][c];
                    labelMap[nr][nc] = labelMap[r][c];
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[] {nr, nc});
                }

                // 다른 섬을 만난 경우 -> 최단 거리 계산
                else if (labelMap[nr][nc] != 0 && labelMap[nr][nc] != labelMap[r][c]) {
                    int total = dist[r][c] + dist[nr][nc];
                    answer = Math.min(answer, total);
                }
            }
        }
    }

}
