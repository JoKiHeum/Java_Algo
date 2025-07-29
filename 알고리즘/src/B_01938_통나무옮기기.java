import java.io.*;
import java.util.*;

public class B_01938_통나무옮기기 {
    static int N;
    static char[][] arr;             // 맵의 상태
    static boolean[][][] visited;   // 방문 여부(첫번째는 통나무의 가로 세로 여부)    가로 -> 0 세로 -> 1
    static List<int[]> tree;        // 초기 통나무의 좌표
    static List<int[]> end;         // 도착지
    static int status;
    static int endStatus;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        visited = new boolean[2][N][N];
        tree = new ArrayList<>();
        end = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < N; j++) {
                char temp = st.charAt(j);
                // 만약 통나무이면
                if (temp == 'B') {
                    tree.add(new int[] {i,j});
                }
                // 만약 도착지 이면
                if (temp == 'E') {
                    end.add(new int[] {i,j});
                }
                arr[i][j] = temp;
            }
        }

        // 초기 통나무 상태
        // 만약 행이 같다면 가로
        if (tree.get(0)[0] == tree.get(1)[0]) {
            status = 0;
        } else {
            status = 1;
        }

        // 도착지점 가로 세로 상태 확인
        if (end.get(0)[0] == end.get(1)[0]) {
            endStatus = 0;
        } else {
            endStatus = 1;
        }
        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        int[] t = tree.get(1);
        int[] start = new int[] {status, t[0], t[1], 0};
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[status][t[0]][t[1]] = true;
        while(!q.isEmpty()) {
            int[] v = q.poll();
            int nStatus = v[0];
            int r = v[1];
            int c = v[2];
            int cnt = v[3];
            if (nStatus == endStatus && r == end.get(1)[0] && c == end.get(1)[1]) {
                return cnt;
            }
            // 단순 이동
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                // 현재 상태가 가로라면
                if (nStatus == 0) {
                    if (0 <= nr && nr < N && 0 <= nc-1 && nc+1 < N && !visited[nStatus][nr][nc]
                    && arr[nr][nc] != '1' && arr[nr][nc-1] != '1' && arr[nr][nc+1] != '1') {
                        q.add(new int[] {nStatus, nr, nc, cnt+1});
                        visited[nStatus][nr][nc] = true;
                    }
                }
                // 현재 상태가 세로라면
                if (nStatus == 1) {
                    if (0 <= nr -1 && nr +1 < N && 0 <= nc && nc < N && !visited[nStatus][nr][nc]
                    && arr[nr][nc] != '1' && arr[nr-1][nc] != '1' && arr[nr+1][nc] != '1') {
                        q.add(new int[] {nStatus, nr, nc, cnt+1});
                        visited[nStatus][nr][nc] = true;
                    }
                }
            }
            // 회전
            // 현재 상태가 가로라면
            if (nStatus == 0) {
                // 만약 현재 통나무가 위 아래 끝지점이면 회전 안됨
                if (r == 0 || r == N-1) continue;
                // 주변에 나무가 있으면 회전 안됨
                if (arr[r-1][c-1] == '1' || arr[r-1][c] == '1' || arr[r-1][c+1] == '1'
                || arr[r+1][c-1] == '1' || arr[r+1][c] == '1' || arr[r+1][c+1] == '1')  continue;
                // 이미 방문했다면 안됨
                if(visited[1][r][c]) continue;

                q.add(new int[] {1, r, c, cnt+1});
                visited[1][r][c] = true;
            }
            // 현재 상태가 세로라면
            if (nStatus == 1) {
                // 만역 현재 통나무가 왼쪽 오른쪽 끝지점이면 회전안됨
                if (c == 0 || c == N-1) continue;
                // 주변에 나무가 있으면 회전안됨
                if (arr[r-1][c-1] == '1' || arr[r][c-1] == '1' || arr[r+1][c-1] == '1'
                || arr[r-1][c+1] == '1' || arr[r][c+1] == '1' || arr[r+1][c+1] == '1') continue;
                // 이미 방문했다면 안됨
                if (visited[0][r][c]) continue;

                q.add(new int[] {0, r, c, cnt+1});
                visited[0][r][c] =true;
            }
        }
        return 0;
    }
}
