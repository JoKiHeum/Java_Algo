import java.util.*;
import java.io.*;
public class B_6497_전력난 {
    static class Edge implements Comparable<Edge> {
        int v;
        int weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N;
    static int M;
    static boolean[] visited;       // 방문 확인
    static int[] dist;        //
    static List<Edge>[] G;   //   그래프
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N ==0 & M ==0) {
                break;
            }
            visited = new boolean[N];
            dist = new int[N];
            G = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                G[i] = new ArrayList<>();
                dist[i] = Integer.MAX_VALUE;
            }
            int totalcost = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                G[u].add(new Edge(v,weight));
                G[v].add(new Edge(u,weight));
                totalcost += weight;
            }
            Prim();
            int answer = 0;
            for (int i = 0; i < N; i++) {
                answer += dist[i];
            }
//        System.out.println(Arrays.toString(dist));
            System.out.println(totalcost-answer);
        }

    }


    static void Prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0,0));
        dist[0] = 0;
        int cnt = N;
        while (!pq.isEmpty() && cnt != 0) {
            Edge e = pq.poll();
            // 이미 거리가 확정됐다면 패스
            if (visited[e.v]) continue;
            visited[e.v] = true;

            for (Edge edge: G[e.v]) {
                if (dist[edge.v] > edge.weight && !visited[edge.v]) {
                    dist[edge.v] = edge.weight;
                    pq.add(edge);
                }
            }
            cnt--;
        }
    }
}

