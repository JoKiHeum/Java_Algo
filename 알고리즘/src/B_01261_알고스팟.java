import java.util.*;
import java.io.*;
public class B_01261_알고스팟 {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        int answer = dijkstra();
        System.out.println(answer);
    }

    static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {return o1[2] - o2[2];}
        });
        pq.add(new int[] {0,0,0});
        while(!pq.isEmpty()) {
            int[] v = pq.poll();
            // 방문했으면 패스
            if (visited[v[0]][v[1]]) continue;

            visited[v[0]][v[1]] = true;

            // 도착했을 경우
            if(v[0] == N-1 && v[1] == M-1) {
                return v[2];
            }

            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                    if (arr[nr][nc] != 1) {
                        pq.add(new int[] {nr,nc,v[2]});
                    } else {
                        pq.add(new int[] {nr,nc,v[2]+1});
                    }
                }
            }
        }
        return 0;
    }
}
