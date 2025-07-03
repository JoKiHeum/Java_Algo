import java.util.*;
import java.io.*;

public class B_17836_공주님을구해라 {
    static int N;
    static int M;
    static int T;
    static int[][] arr;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();
        if (result > T) {
            System.out.println("Fail");
        } else {
            System.out.println(result);
        }
    }


    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int [][][] visited = new int[2][N][M];   // 방문 배열
        visited[0][0][0] = 1;                  // 방문 배열 초기화
        q.add(new int[] {0,0,0});             // 검 유무, 행, 열
        while(!q.isEmpty()) {
            int[] v = q.poll();
            // 도착 지점에 도착했다면
            if (v[1] == N-1 && v[2] == M-1) {
                return visited[v[0]][N-1][M-1] -1;
            }

            // 탐색 시작
            for (int k = 0; k < 4; k++) {
                int nr = v[1] + dr[k];
                int nc = v[2] + dc[k];
                // 검을 가지고 있지 않을 상태
                if (v[0] == 0) {
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[0][nr][nc] == 0) {
                        // 만약 벽을 만났다면
                        if (arr[nr][nc] == 1) {
                            continue;
                        // 검을 만났다면
                        } else if (arr[nr][nc] == 2) {
                            q.add(new int[] {1, nr, nc});
                            visited[1][nr][nc] = visited[0][v[1]][v[2]] + 1;
                        } else {
                            q.add(new int[] {0, nr, nc});
                            visited[0][nr][nc] = visited[0][v[1]][v[2]] +1;
                        }
                    }
                } else {
                    if (0 <= nr && nr < N && 0 <= nc && nc < M && visited[1][nr][nc] == 0) {
                        q.add(new int[] {1, nr, nc});
                        visited[1][nr][nc] = visited[1][v[1]][v[2]] +1;
                    }
                }

            }
        }
        return 1000000; // 공주를 못 구했으면

    }
}
