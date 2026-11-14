import java.util.*;
import java.io.*;

public class 위상정렬 {
    static int N;                    // 정점 갯수
    static int M;                    // 간선 갯수
    static ArrayList<Integer>[] G;   // 그래프
    static int[] indegree;           // 진입 차수
    static Queue<Integer> q;         // 큐
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());

       G = new ArrayList[N+1];
       indegree = new int[N+1];

       for (int i = 1; i < N+1; i++) {
           G[i] = new ArrayList<>();
        }

       // Graph 만들기 및 진입 차수 리스트 만들기
       for (int i = 0; i < M; i++) {
           st = new StringTokenizer((br.readLine()));
           int a = Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());
           G[a].add(b);
           indegree[b]++;
       }
       // q에 진입 차수가 0인 정점 넣기
       q = new LinkedList<>();
       for (int i = 1; i < N+1; i++) {
           if (indegree[i] == 0) {
               q.add(i);
           }
       }
       topologicalSort();
       System.out.println(sb);
    }

    static void topologicalSort () {
        while (!q.isEmpty()) {
            int v = q.poll();
            sb.append(v).append(' ');
            for (int w : G[v]) {
                indegree[w]--;

                if (indegree[w] == 0) {
                    q.add(w);
                }
            }
        }
    }
}
