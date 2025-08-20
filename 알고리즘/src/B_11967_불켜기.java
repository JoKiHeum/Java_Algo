import java.util.*;
import java.io.*;

public class B_11967_불켜기 {
    static int N;
    static int M;
    static boolean[][] room;    //   불이 켜져 있으면 true 꺼져있으면 false
    static List<int[]>[][] switches;
    static boolean[][] visited;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        room = new boolean[N][N];
        visited = new boolean[N][N];
        switches = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }

        // 인접 리스트
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            switches[x][y].add(new int[]{a,b});
        }
        bfs(0,0);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (room[i][j]) answer++;
            }
        }
        System.out.println(answer);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r,c});
        visited[r][c] = true;
        room[r][c] = true;

        while(!q.isEmpty()) {
            int[] v = q.poll();
            int curR = v[0];
            int curC = v[1];
            for (int k = 0; k < 4; k++) {
                int nr = curR + dr[k];
                int nc = curC + dc[k];
                if (nr >=0 && nr < N && nc >= 0 && nc <N && room[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr,nc});
                }
            }

            // 현재 방에서 켤 수 있는 불 켜기
            for (int[] u: switches[curR][curC]) {
                if (room[u[0]][u[1]]) {
                    continue;
                }
                room[u[0]][u[1]] = true;
                // 새롭게 켜진 방이 이전에 방문한 방과 인접하다면 q에 넣기
                for (int k = 0; k < 4; k++) {
                    int nr = u[0] + dr[k];
                    int nc = u[1] + dc[k];
                    if (nr >=0 && nr < N && nc >= 0 && nc <N && visited[nr][nc]) {
                        q.add(u);
                        visited[u[0]][u[1]] = true;
                        break;
                    }
                }
            }
        }
    }
}
