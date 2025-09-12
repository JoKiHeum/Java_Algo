import java.util.*;
import java.io.*;
import java.util.stream.StreamSupport;

public class B_04991_로봇청소기 {
    static int W,H;
    static char[][] arr;
    static int[][] visited;
    static List<int[]> dirty;
    static int[][] dist;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int answer;
    static boolean[] used;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;
            if (W ==0 && H ==0) break;

            arr = new char[H][W];
            dirty = new ArrayList<>();

            int startIndex = -1;    //   시작점 위치를 제일 앞으로 놓기 위해
            for (int i = 0; i < H; i++) {
                String inputs = br.readLine();
                for (int j = 0; j < W; j++) {
                    char input = inputs.charAt(j);
                    if (input == 'o') {
                        dirty.add(new int[] {i,j});
                        startIndex = dirty.size()-1;
                    } else if (input == '*') {
                        dirty.add(new int[] {i,j});
                    }
                    arr[i][j] = input;
                }
            }
            // dirty 리스트 제일 앞에 로봇 청소기가 오게 설정
            if (startIndex != 0) Collections.swap(dirty, 0, startIndex);
            int cnt = dirty.size();
            used = new boolean[cnt];
            dist = new int[cnt][cnt];
            boolean check = true;
            for (int i = 0; i < dirty.size(); i++) {
                int startR = dirty.get(i)[0];
                int startC = dirty.get(i)[1];
                for (int j = 0; j < dirty.size(); j++) {
                    int endR = dirty.get(j)[0];
                    int endC = dirty.get(j)[1];
                    int result = bfs(startR, startC, endR, endC);
                    if (result == -1) check = false;
                    dist[i][j] = result;
                }
            }

            if (!check) {
                sb.append(-1 + "\n");
            } else {
                dfs(0, 0,0, cnt-1);
                sb.append(answer+ "\n");
            }

        }
        System.out.println(sb);
    }

    static int bfs(int startR, int startC, int endR, int endC) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {startR, startC});
        visited = new int[H][W];
        visited[startR][startC] = 1;

        while(!q.isEmpty()) {
            int[] v = q.poll();

            // 도착지점을 만났다면
            if (v[0] == endR && v[1] == endC) {
                return visited[endR][endC]-1;
            }

            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];

                if(nr < 0 || nr >= H || nc < 0 || nc >= W || visited[nr][nc] != 0 || arr[nr][nc] == 'x') continue;
                q.add(new int[] {nr,nc});
                visited[nr][nc] = visited[v[0]][v[1]] +1;
            }
        }
        return -1;
    }

    static void dfs(int cur, int cost, int level, int cnt) {
        // 기저조건
        if (cost > answer) {
            return;
        }
        if (level == cnt) {
            answer = cost;
        }

        for (int i = 1; i < cnt+1; i++) {
            if (used[i]) continue;

            used[i] = true;
            dfs(i, cost+dist[cur][i], level+1, cnt);
            used[i] = false;
        }
    }
}
