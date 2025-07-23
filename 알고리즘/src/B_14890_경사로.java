import java.util.*;
import java.io.*;
public class B_14890_경사로 {
    static int N;
    static int L;
    static int[][] arr;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로 확인
        // 세로확인
        for (int i = 0; i < N; i++) {
            boolean flag = true;           // 지나갈 수 있는 길인지 check
            boolean[] visited = new boolean[N];     // 이미 경사로를 설치했는지 확인
            for (int j = 0; j < N-1; j++) {
                // 만약 다음 칸이 같다면
                if (arr[i][j] == arr[i][j+1]) {
                    continue;
                } else if (Math.abs(arr[i][j] - arr[i][j+1]) == 1) {            // 만약 차이가 1이라면
                    // 만약 다음칸이 현재 칸보다 크고 경사로를 설치하지 않은 곳이라면
                    // 현재 칸부터 L-1 칸 까지 확인
                    if (arr[i][j] < arr[i][j+1]) {
                        // 이미 경사로를 설치했다면
                        if (visited[j]) {
                            flag = false;
                            break;
                        }
                        visited[j] = true;              // 현재 칸 경사로 설치
                        for (int k = 1; k < L; k++) {
                            if (j-k >= 0 && arr[i][j] == arr[i][j-k] && !visited[j-k]) {
                                visited[j-k] = true;    // 경사로 설치
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }

                    // 만약 다음칸이 현재 칸보다 작다면
                    if (arr[i][j] > arr[i][j+1]) {
                        if (visited[j+1]) {
                            flag = false;
                            break;
                        }
                        visited[j+1] = true;
                        for (int k = 1; k < L; k++) {
                            // 만약 L-1칸 까지 같다면
                            if (j+1+k < N && arr[i][j+1] == arr[i][j+1+k] && !visited[j+1+k]) {
                                visited[j+1+k] = true;
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }
                } else {                                                        // 만약 둘다 아니라면 flag 변수 변경 후 종료
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
//                System.out.println(i + "번째 열");
            }
        }

        // 세로확인
        for (int j = 0; j < N; j++) {
            boolean flag = true;           // 지나갈 수 있는 길인지 check
            boolean[] visited = new boolean[N];     // 이미 경사로를 설치했는지 확인
            for (int i = 0; i < N-1; i++) {
                // 만약 다음 칸이 같다면
                if (arr[i][j] == arr[i+1][j]) {
                    continue;
                } else if (Math.abs(arr[i][j] - arr[i+1][j]) == 1) {            // 만약 차이가 1이라면
                    // 만약 다음칸이 현재 칸보다 크고 경사로를 설치하지 않은 곳이라면
                    // 현재 칸부터 L-1 칸 까지 확인
                    if (arr[i][j] < arr[i+1][j]) {
                        // 이미 경사로를 설치했다면
                        if (visited[i]) {
                            flag = false;
                            break;
                        }
                        visited[i] = true;              // 현재 칸 경사로 설치
                        for (int k = 1; k < L; k++) {
                            if (i-k >= 0 && arr[i][j] == arr[i-k][j] && !visited[i-k]) {
                                visited[i-k] = true;    // 경사로 설치
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }

                    // 만약 다음칸이 현재 칸보다 작다면
                    if (arr[i][j] > arr[i+1][j]) {
                        if (visited[i+1]) {
                            flag = false;
                            break;
                        }
                        visited[i+1] = true;
                        for (int k = 1; k < L; k++) {
                            // 만약 L-1칸 까지 같다면
                            if (i+1+k < N && arr[i+1][j] == arr[i+1+k][j] && !visited[i+1+k]) {
                                visited[i+1+k] = true;
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }
                } else {                                                        // 만약 둘다 아니라면 flag 변수 변경 후 종료
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
//                System.out.println(j + "번째 열");
            }
        }
        System.out.println(answer);
    }
}
