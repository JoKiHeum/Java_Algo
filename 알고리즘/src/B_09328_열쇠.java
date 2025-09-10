import java.util.*;
import java.io.*;

public class B_09328_열쇠 {
    static int N, M;
    static char[][] arr;
    static boolean[] keys;
    static List<int[]>[] gates;     // 현재 열쇠가 없어서 못들어간 문
    static boolean[][] visited;
    static int cnt;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new char[N+2][M+2];
            visited = new boolean[N+2][M+2];
            keys = new boolean[26];
            gates = new ArrayList[26];
            cnt = 0;
            for (int i = 0; i < 26; i++) {
                gates[i] = new ArrayList<>();
            }

            // 테두리 채우기
            for (int i = 0; i < N+2; i++) {
                for (int j = 0; j < M+2; j++) {
                    arr[i][j] = '.';
                }
            }

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    arr[i+1][j+1] = input.charAt(j);
                }
            }
            String input = br.readLine();
            if (!input.equals("0")) {
                for (int i = 0; i < input.length(); i++) {
                    keys[input.charAt(i) - 'a'] = true;
                }
            }
            bfs();
            sb.append(cnt+ "\n");

        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0,0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] v = q.poll();

            for (int k = 0; k < 4; k++) {
                int nr = v[0] + dr[k];
                int nc = v[1] + dc[k];

                if (nr >= 0 && nr < N+2 && nc >= 0 && nc <M+2 && arr[nr][nc] != '*' && !visited[nr][nc]) {
                    // 빈공간이라면
                    if (arr[nr][nc] == '.') {
                        q.add(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                    // 문서를 발견하면
                    else if (arr[nr][nc] == '$') {
                        q.add(new int[] {nr, nc});
                        cnt++;
                        visited[nr][nc] =true;
                    }
                    // 열쇠를 발견하면
                    else if (97 <=  arr[nr][nc] && arr[nr][nc] <= 122) {
                        q.add(new int[] {nr, nc});
                        visited[nr][nc] = true;
                        keys[arr[nr][nc] - 'a'] = true;

                        // 해당 열쇠가 이전에 열쇠가 없어서 들어갈 수 없던 문의 열쇠인지 확인
                        for (int i = 0; i < 26; i++) {
                            if (gates[i].size() !=0 && keys[i]) {
                                for (int[] w : gates[i]) {
                                    q.add(w);
                                    visited[w[0]][w[1]] = true;
                                }
                            }
                        }

                    }

                    // 문을 발견하면
                    else if (65 <= arr[nr][nc] && arr[nr][nc] <= 90) {
                        // 열쇠가 있다면
                        if (keys[arr[nr][nc] - 'A']) {
                            q.add(new int[] {nr, nc});
                            visited[nr][nc] =true;
                        } else {
                            gates[arr[nr][nc] - 'A'].add(new int[] {nr, nc});
                        }
                    }
                }
            }
        }
    }
}
