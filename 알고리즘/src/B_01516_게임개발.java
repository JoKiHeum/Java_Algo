import java.util.*;
import java.io.*;

public class B_01516_게임개발 {
    static int N;       // 건물의 종류
    static int[] indegree;      //   진입차수
    static int[] time;          // 건물을 짓는 데 필요한 시간
    static int[] answer;        // 각 건물을 짓는데 필요한 총 시간
    static List<Integer>[] G;    // 그래프
    static List<Integer>[] P;   // 부모 그래프
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];
        time = new int[N+1];
        answer = new int[N+1];
        G = new ArrayList[N+1];
        P = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            G[i] = new ArrayList<>();
            P[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            while(true) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == -1) {
                    break;
                }
                G[temp].add(i);
                P[i].add(temp);
                indegree[i]++;

            }
        }
        topologySort();
        for (int i = 1; i <= N; i++) {
            System.out.println(answer[i]);
        }
    }
    // 위상정렬
    static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        // 진입차수가 0인 노드 q에 넣기 그리고 바로 실행되는 것들 이므로 answer에 총 필요시간 입력
        for (int i = 1; i < N+1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                answer[i] = time[i];
            }
        }
        while(!q.isEmpty()) {
            int v = q.poll();
            for (int u : G[v]) {
                indegree[u]--;
                if (indegree[u] == 0) {
                    q.add(u);
                    int max = 0;
                    // 부모 중 가장 늦게끝나는 시간 찾기
                    for (int k : P[u]) {
                        max = Math.max(max,answer[k]);
                    }
                    answer[u] = time[u] + max;
                }
            }
        }
    }
}
