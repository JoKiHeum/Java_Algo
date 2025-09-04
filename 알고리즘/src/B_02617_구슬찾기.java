import java.util.*;
import java.io.*;

public class B_02617_구슬찾기 {
    static int N;
    static int M;
    static boolean[] visited;
    static List<Integer>[] G1;  // 자신보다 무거운 거 연결
    static List<Integer>[] G2;  // 자시본다 가벼운 거 연결
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        G1 = new ArrayList[N+1];
        G2 = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            G1[i] = new ArrayList<>();
            G2[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());
            G1[small].add(big);
            G2[big].add(small);
        }

        int answer = 0;
        for (int i = 1; i < N+1; i++) {
            int bigCnt = bfs(G1, i);
            int smallCnt = bfs(G2, i);
            if (bigCnt > (N-1)/2 || smallCnt > (N-1)/2) {
                answer++;
            }
        }
        System.out.println(answer);

    }

    static int bfs(List<Integer>[] G, int start) {
        int cnt = 0;
        visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()) {
            int v = q.poll();
            for (int u : G[v]) {
                if (!visited[u]) {
                    q.add(u);
                    cnt++;
                    visited[u] = true;
                }
            }
        }
        return cnt;
    }
}
