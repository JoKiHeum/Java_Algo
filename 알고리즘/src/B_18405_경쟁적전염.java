import java.util.*;
import java.io.*;

public class B_18405_경쟁적전염 {
    static int N;
    static int K;
    static int[][] arr;
    static boolean[][] visited;
    static int S;
    static int X,Y;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();

        ArrayList<int[]> temp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    temp.add(new int[] {i,j,num,0});    //   좌표, 바이러스 번호, 시간
                }
                arr[i][j] = num;
            }
        }

        // 바이러스 번호 낮은 순으로 정렬
        Collections.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // Q에 넣기
        for (int[] v: temp) {
            q.add(v);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(arr[X-1][Y-1]);
    }

    static void bfs() {
        while(!q.isEmpty()) {
            int[] v = q.poll();

            if (v[3] == S) {
                return;
            }
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];

                if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0) {
                    arr[nr][nc] = v[2];
                    q.add(new int[] {nr,nc,v[2], v[3]+1});
                }
            }
        }
    }

}
