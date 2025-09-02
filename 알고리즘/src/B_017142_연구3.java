import java.util.*;
import java.io.*;

public class B_017142_연구3 {
    static int N;   // 연구소 크기
    static int M;   // 바이러스 수
    static int[][] arr;
    static List<Integer> pick = new ArrayList<>();
    static List<int[]> virus;
    static int answer = Integer.MAX_VALUE;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    static int empty;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        int cnt = 0;
        empty = 0;
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    virus.add(new int[] {i,j});         // 비활성화 바이러스 좌표 저장
                    cnt++;
                }
                if (num == 0) {
                    empty++;
                }
                arr[i][j] = num;
            }
        }
        dfs(0, cnt, M, empty);
        if (empty == 0) {
            System.out.println(0);
        } else if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void dfs(int start, int n, int r, int emptyCnt) {
        if (r == 0) {
            int result = bfs(pick, emptyCnt);
            if (result == -1) {
                return;
            } else {
                answer = Math.min(answer,result);
            }
        }

        for (int i = start; i < n; i++) {
            pick.add(i);
            dfs(i+1, n, r-1, emptyCnt);
            pick.remove(Integer.valueOf(i));
        }
    }

    static int bfs(List<Integer> pick, int emptyCnt) {
        int[][] visited = new int[N][N];
        Queue<int[]> q = new LinkedList<>();
        for (int index : pick) {
            int[] v = virus.get(index);
            q.add(v);
            visited[v[0]][v[1]] = 1;
        }
        while(!q.isEmpty()) {
            int[] v = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];

                if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] == 0 && arr[nr][nc] !=1) {
                    if (arr[nr][nc] == 0) {
                        emptyCnt--;
                    }
                    visited[nr][nc] = visited[v[0]][v[1]]+1;
                    q.add(new int[] {nr, nc});

                    if (emptyCnt == 0) {
                        return visited[v[0]][v[1]];
                    }
                }
            }
        }
        return -1;
    }
}
