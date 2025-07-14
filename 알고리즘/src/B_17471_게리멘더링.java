import java.util.*;
import java.io.*;

public class B_17471_게리멘더링 {
    static int N;               // 지역 수
    static int[] arr;           //  인구수 리스트
    static List<Integer>[] G;
    static boolean[] selected;      //  true = A, false = B
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        G = new ArrayList[N+1];
        selected = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N+1; i++) {
            G[i] = new ArrayList<>();
        }
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                G[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        dfs(0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    // 지역 나누기
    static void dfs(int level) {
        // 기저 조건
        if (level == N) {
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();
            for (int i = 1; i < N+1; i++) {
                if (selected[i]) {
                    A.add(i);
                } else {
                    B.add(i);
                }
            }
            // 한곳에 몰린경우
            if (A.size() == 0 || B.size() == 0) return;

            // 아니면 연결됐는지 확인
            boolean resultA = bfs(A);
            boolean resultB = bfs(B);
//            System.out.println(resultA + " " + resultB);
            //  둘다 참이면
            if (resultA && resultB) {
                int totalA = 0;
                int totalB = 0;
                for (int a : A) {
                    totalA += arr[a];
                }
                for (int b : B) {
                    totalB += arr[b];
                }
                answer = Math.min(answer, Math.abs(totalA - totalB));
            }
            return;
        }


        selected[level+1] = true;
        dfs(level+1);
        selected[level+1] = false;
        dfs(level+1);

    }

    // 연결 확인
    static boolean bfs(List<Integer> list) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        visited[list.get(0)] = true;
        q.add(list.get(0));
        // 시작 노드를 포함해야 한다.
        int cnt = 1;
        while(!q.isEmpty()) {
            int v = q.poll();
            for (int u : G[v]) {
                // 지역구에 속해있고 방문하지 않았다면
                if (list.contains(u)&&!visited[u]) {
                    visited[u] = true;
                    q.add(u);
                    cnt++;
                }
            }
        }
        if (cnt == list.size()) {
            return true;
        } else {
            return false;
        }

    }
}

