import java.util.*;
import java.io.*;

public class B_16235_나무재테크 {
    static int N;
    static int M;
    static int K;
    static int[][]  A;          // 로봇이 주는 양분
    static int[][] arr;         // 양분 (초기 값은 모두 5)
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static Queue<Tree> trees = new LinkedList<>();      // 나무들
    static Queue<Tree> deadTrees = new LinkedList<>();                   // 죽은 나무들
    static class Tree implements Comparable<Tree> {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = 5;          // 양분 초기값 5로 초기화
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c ,age));
        }
        Collections.sort((List<Tree>) trees);
        while(K != 0) {
            spring();
            summer();
            fall();
            winter();
            K--;
        }
        System.out.println(trees.size());

    }

    // 봄
    static void spring() {
        int size = trees.size();
        for(int i = 0; i < size; i++) {
            Tree t = trees.poll();
            // 양분보다 나이가 많을 경우 죽은 나무
            if (arr[t.r][t.c] < t.age) {
                deadTrees.add(new Tree(t.r, t.c, t.age));
            } else {
                arr[t.r][t.c] -= t.age;         //  나이만큼 양분 감소
                trees.add(new Tree(t.r, t.c, t.age+1));  //   나이 1 증가
            }
        }
    }

    // 여름
    static void summer() {
        while(!deadTrees.isEmpty()) {
            Tree t = deadTrees.poll();
            arr[t.r][t.c] += t.age/2;
        }
    }

    // 가을
    static void fall() {
        ArrayList<Tree> temp = new ArrayList<>();
        int size = trees.size();
        for(int i = 0; i < size; i++) {
            Tree t = trees.poll();
            temp.add(t);
            if (t.age % 5 == 0) {
                for (int k = 0; k < 8; k++) {
                    int nr = t.r + dr[k];
                    int nc = t.c + dc[k];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        trees.add(new Tree(nr, nc, 1));
                    }
                }
            }
        }
        for(Tree t : temp) {
            trees.add(t);
        }
    }

    // 겨울
    static void winter() {
        for (int i =0; i< N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] += A[i][j];
            }
        }
    }
}
