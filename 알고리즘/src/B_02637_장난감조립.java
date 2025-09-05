import java.util.*;
import java.io.*;

public class B_02637_장난감조립 {
    static int N;
    static int M;
    static int[][] dp;      // dp 정의: dp[i][j] -> i부품을 만들기 위해 필요한 j의 수
    static int[] indegree;
    static class Edge {
        int next;      // 만들어지는 부품 번호
        int cnt;        // 필용한 부품 수

        public Edge (int next, int cnt) {
            this.next = next;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Edge{next=" + next + ", count = " + cnt + "}";
        }
    }
    static List<Edge>[] G;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N+1][N+1];
        indegree = new int[N+1];
        G = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int next = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            G[cur].add(new Edge(next, cnt));
            indegree[next] +=1;
        }
        List<Integer> baseList = new ArrayList<>();

        // dp 초기화 기본 부품 1로(진입 차수가 0인 것이 기본 부품)
        for (int i = 1; i < N+1; i++) {
            if (indegree[i] == 0) {
                dp[i][i] = 1;
                baseList.add(i);
            }
        }
        topologySort();
        for (int i: baseList) {
            sb.append(i).append(" " + dp[N][i] + "\n");
        }
        System.out.println(sb);
    }

    static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N+1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            for (Edge e : G[cur]) {
                for (int k = 1; k < N+1; k++) {
                    dp[e.next][k] += dp[cur][k] * e.cnt;
                }
                indegree[e.next]--;
                if (indegree[e.next] == 0) q.add(e.next);
            }
        }
    }
}
