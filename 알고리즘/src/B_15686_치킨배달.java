import java.util.*;
import java.io.*;
public class B_15686_치킨배달 {
    static int N;
    static int M;
    static int[][] arr;                                         // 도시 지도
    static boolean[] pick;                                  // 선택한 도시
    static List<int []> home = new ArrayList<>();               // 집 좌표 저장
    static List<int []> chicken = new ArrayList<>();              // 치킨 집 위치 저장
    static int answer = Integer.MAX_VALUE;
    static int chickenCnt;                                  // 치킨 집의 갯수
    static int homeCnt;                                     // 집의 갯수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        homeCnt = 0;        //  집의 갯수
        chickenCnt = 0;     // 치킨 집의 갯수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                arr[i][j] = temp;
                // 만약 집이라면
                if (temp == 1) {
                    homeCnt++;
                    home.add(new int[] {i,j});
                }

                // 만약 치킨 집이라면
                if (temp == 2) {
                    chickenCnt++;
                    chicken.add(new int[] {i,j});
                }
            }
        }

        pick = new boolean[chickenCnt];
        dfs(0, 0);
        System.out.println(answer);

    }

    static void dfs(int level, int cnt) {   // level = 깊이 cnt = 선택한 치킨 집의 갯수
        // 선택한 치킨집이 M개를 넘어가면 종료
        if (cnt > M) {
            return;
        }

        // 마지막까지 선택지까지 선택 완료
        if (level == chickenCnt) {
            if (cnt == M) {
                int totalDist = 0;
                for (int[] h : home) {
                    int temp = Integer.MAX_VALUE;
                    for (int i = 0; i < chickenCnt; i++) {
                        // 살아남은 치킨 집이라면
                        if (pick[i]) {
                            int[] c = chicken.get(i);
                            temp = Math.min(temp, Math.abs(h[0]-c[0]) + Math.abs(h[1]-c[1]));
                        }
                    }
                    totalDist += temp;
                }
                answer = Math.min(answer, totalDist);
            }
            return;
        }

        // 선택
        else {
            pick[level] = true;
            dfs(level+1, cnt+1);
            pick[level] = false;
            dfs(level+1, cnt);
        }

    }
}
