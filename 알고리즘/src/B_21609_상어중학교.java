import java.util.*;
import java.io.*;

public class B_21609_상어중학교 {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int answer = 0;
    static class Block implements Comparable<Block> {
        int r, c, cnt, rainbowCnt;      //  기준 블록 행, 기준 블록 열, 블록 그룹에 포함된 블록 수, 무지개 블록

        public Block(int r, int c, int cnt, int rainbowCnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.rainbowCnt = rainbowCnt;
        }

        @Override
        public int compareTo(Block o) {
            // 만약 블록 그룹에 포함된 블록 수가 같지 않다면
            if (this.cnt != o.cnt) {
                return o.cnt - this.cnt;
            } else if(this.rainbowCnt != o.rainbowCnt) {        // 만약 블록 수가 같고 무지개 블록 수가 다르다면
                return o.rainbowCnt - this.rainbowCnt;
            } else if (this.r != o.r) {                         // 만약 위의 조건이 같고 행이 다르다면
                return o.r - this.r;
            } else {
                return o.c-this.c;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            Block b = findMaxBlock();
            if (b== null) {
                break;
            }
            answer+= b.cnt * b.cnt;
//            System.out.println(b.r + " " + b.c + " " + b.cnt);
            removeBlock(b);
            grvity();
            rotate();
            grvity();
//            for (int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(arr[i]));
//            }
        }
        System.out.println(answer);

    }

    // 크기가 가장 큰 블록 그룹 찾기
    static Block findMaxBlock() {
        visited = new boolean[N][N];        // 방문 배열 초기화
        PriorityQueue<Block> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 검은색 블록이면 패스
                if (arr[i][j] == -1) {
                    continue;
                }
                // 무지개 블록이면 패스
                if (arr[i][j] == 0) {
                    continue;
                }
                // 비어있는 블록이면 패스 (비어있는 블록은 99)
                if (arr[i][j] == 99) {
                    continue;
                }
                // 이미 방문한 블록이면 패스
                if (visited[i][j]) {
                    continue;
                }
                initRainbowVisited();
                Block block = findBlock(i, j, arr[i][j]);
                if (block == null) {
                    continue;
                }
                pq.add(block);
            }
        }
        // 만약 pq가 0이면 더이상 블록 그룹이 없다는 뜻
        if (pq.size() == 0) {
            return null;
        }
        return pq.poll();
    }



    // 블록 그룹 찾기
    static Block findBlock(int r, int c, int color) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r,c});
        visited[r][c] = true;
        int cnt = 1;            // 전체 블록 수
        int rainbowCnt = 0;     // 무지개 블록 수
        while(!q.isEmpty()) {
            int[] v = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    // 무지개 블록이면
                    if (arr[nr][nc] == 0) {
                        rainbowCnt++;
                        cnt++;
                        visited[nr][nc] = true;;
                        q.add(new int[] {nr, nc});
                    }

                    // 같은 색의 일반 블록이면
                    if (arr[nr][nc] == color) {
                        cnt++;
                        visited[nr][nc] =true;
                        q.add(new int[] {nr,nc});
                    }
                }
            }
        }
        // 그룹에 속한 블록의 갯수가 2보다 작으면
        if (cnt < 2) {
            return null;
        } else {
            return new Block(r, c, cnt, rainbowCnt);
        }
    }

    // 무지개 블록 방문 처리 초기화 -> 무지개 블록은 공동의 소유가 될 수 있기 때문에
    static void initRainbowVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    visited[i][j] = false;
                }
            }
        }
    }

    // 블록 제거
    static void removeBlock(Block block) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.add(new int[] {block.r, block.c});
        visited[block.r][block.c] = true;
        int color = arr[block.r][block.c];
        arr[block.r][block.c] = 99;
        while(!q.isEmpty()) {
            int[] v = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    // 같은 색의 블록이거나 무지개 블록이면 제거
                    if (arr[nr][nc] == color || arr[nr][nc] == 0) {
                        arr[nr][nc] = 99;
                        visited[nr][nc] = true;
                        q.add(new int[] {nr,nc});
                    }
                }
            }
        }
    }

    // 중력 작용
    static void grvity() {
        for (int i = 0; i < N; i++) {
            for (int j = N-2; j >= 0; j--) {
                // 검은색 블록이거나 빈칸이면 패스
                if (arr[j][i] == -1 || arr[j][i] == 99) {
                    continue;
                }
                int nextR = j+1;
                int temp = arr[j][i];
                while (true) {
                    // 인덱스를 벗어나거나 검은색 블록이거나 빈칸이 아니면
                    if (nextR >= N || arr[nextR][i] ==-1 || arr[nextR][i] != 99) {
                        break;
                    }
                    nextR++;
                }
                nextR--;
                // 만약 안 움직였으면 그냥 끝
                if (nextR == j) {
                    continue;
                }
                arr[j][i] = 99;
                arr[nextR][i] = temp;
            }
        }
    }

    // 90도 반시계 회전
    static void rotate() {
        int[][] rotateArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotateArr[i][j] = arr[j][N-1-i];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = rotateArr[i][j];
            }
        }
    }
}
