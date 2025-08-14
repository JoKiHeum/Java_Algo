import java.util.*;
import java.io.*;

public class B_09466_텀프로젝트 {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished;      // 검사한 경우 체크
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            arr = new int[N+1];
            visited = new boolean[N+1];
            finished = new boolean[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N+1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i < N+1; i++) {
                if (visited[i]) {
                    continue;
                }
                dfs(i);
            }
            System.out.println(N-answer);
        }
    }

    static void dfs(int v) {
        visited[v] = true;
        int u = arr[v]; // v가 가리키는 다음 노드

        if (!visited[u]) {
            // 처음 보는 노드면 계속 DFS
            dfs(u);

        } else if (!finished[u]) {
            // visited[u] == true && finished[u] == false → 현재 경로로 되돌아옴 = 사이클
            // u부터 다시 u로 돌아올 때까지 사이클 노드만 카운트
            answer++;
            int w = u;
            while(u != arr[w]) {
                answer++;
                w = arr[w];
            }

        }

        // v에서 출발하는 모든 처리가 끝났음(스택에서 내려옴)
        finished[v] = true;
    }
}
