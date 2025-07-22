import java.util.*;
import java.io.*;

public class B_15661_링크와스타트 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0);
        System.out.println(answer);
    }

    static void backtrack(int level) {
        // 기저 조건
        if (level == N) {
            List<Integer> linkTeam = new ArrayList<>();         // True -> link, false -> start
            List<Integer> startTeam = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    linkTeam.add(i);
                } else {
                    startTeam.add(i);
                }
            }
            // 디버깅
//            System.out.print("linkTeam: ");
//            for (int a : linkTeam) {
//                System.out.print(a + " ");
//            }
//            System.out.println();
//            System.out.print("startTeam: ");
//            for (int b : startTeam) {
//                System.out.print(b + " ");
//            }
//            System.out.println();
            // 만약 한팀에 몰렸다면 종료
            if(linkTeam.size() == 0 || startTeam.size() == 0) return;

            // 계산
            int result = cal(linkTeam, startTeam);
            answer = Math.min(answer, result);
            return;
        }

        visited[level] = true;
        backtrack(level+1);
        visited[level] = false;
        backtrack(level+1);
    }

    static int cal(List<Integer> A, List<Integer> B) {
        int senergyA = 0;
        int senergyB = 0;
//        System.out.println(A.size());
//        System.out.println(B.size());
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.size(); j++) {
                senergyA += arr[A.get(i)][A.get(j)];
            }
        }

        for (int i = 0; i < B.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                senergyB += arr[B.get(i)][B.get(j)];
            }
        }

        return Math.abs(senergyA - senergyB);
    }

}
