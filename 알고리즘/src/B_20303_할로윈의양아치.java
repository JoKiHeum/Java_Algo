import java.util.*;
import java.io.*;

public class B_20303_할로윈의양아치 {
    static int N;
    static int M;
    static int K;
    static int[] candy;
    static int[] parents;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N+1];
        parents = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            candy[i+1] = Integer.parseInt(st.nextToken());
            parents[i+1] = i+1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            unionSet(a,b);
        }

        int[] groupSize = new int[N+1];
        int[] groupCandy = new int[N+1];

        for (int i = 0; i < N; i++) {
            int r = findSet(i+1);
            groupSize[r]++;
            groupCandy[r] += candy[i+1];
        }

        // 무리 목록 추출
        List<int[]> groups = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (groupSize[i] > 0) {
                groups.add(new int[] {groupSize[i], groupCandy[i]});
            }
        }
        int G = groups.size();
        int[][] dp = new int[G+1][K];       // i 번째 그룹까지 고려했을때 인원수 합이 j이하일 때 얻을 수있는 최대 사탕

        for (int i = 1; i <= G; i++) {
            int size = groups.get(i-1)[0];
            int cnt = groups.get(i-1)[1];
            for (int j = 0; j < K; j++) {
                // 선택하지 않는 경우
                dp[i][j] = dp[i-1][j];
                // 선택하는 경우
                if (j >= size) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-size] + cnt);
                }
            }
        }

        System.out.println(dp[G][K-1]);

    }

    static int findSet(int a) {
        if (parents[a] != a) {
            return findSet(parents[a]);
        }
        return a;
    }

    static void unionSet(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot > bRoot) {
            parents[bRoot] = aRoot;
        } else {
            parents[aRoot] = bRoot;
        }
    }

}

