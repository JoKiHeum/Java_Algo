import java.util.*;
import java.io.*;
public class B_19942_다이어트 {
    static int N;
    static int mp,mf,ms,mv;
    static class food {
        int p,f,s,v,c;  //  단백질 , 지방, 탄수화물, 비타민, 가격
        public food(int p,int f, int s, int v, int c) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Food{" + "단백질=" + p + ", 지방=" + f + ", 탄수화물=" + s + ", 비타민=" + v + ", 가격=" + c + '}';
        }
    }
    static food[] foods;
    static int answer = Integer.MAX_VALUE;
    static List<ArrayList<Integer>> answers;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        foods = new food[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            foods[i] = new food(p,f,s,v,c);
        }
        dfs(0,0,0,0,0,0);
        if (answers == null) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
            for (ArrayList<Integer> v : answers) {
                for (int w : v) {
                    System.out.print(w + " ");
                }
            }
        }

    }
    static void dfs(int level, int sumP, int sumF, int sumS, int sumV, int currentCost) {
        // 가지치기: 지금까지 비용이 최소 비용보다 크면
        if (currentCost > answer) return;

        // 현재 조건을 만족하면
        if (sumP >= mp && sumF >= mf && sumS >= ms && sumV >= mv ) {
            // 이 조건이 없으면 사전순이 안됨
            if (answer > currentCost) {
                answer = currentCost;
                answers = new ArrayList<>();
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    if (visited[i]) {
                        temp.add(i+1);
                    }
                }
                answers.add(temp);
            }
        }
        // 기저조건 2: 끝까지 탐색
        if (level == N) {
            return;
        }
        visited[level] = true;
        dfs(level+1,  sumP+foods[level].p,
                sumF+foods[level].f,
                sumS+foods[level].s,
                sumV+foods[level].v,
                currentCost+foods[level].c);
        visited[level] = false;
        dfs(level+1, sumP, sumF, sumS, sumV, currentCost);
    }
}
