import java.util.*;
import java.io.*;

public class B_17144_미세먼지안녕 {
    static int R;       // 행 크기
    static int C;       // 열 크기
    static int T;       // 공기청정기 작동 횟수
    static int[][] arr; // 맵
    static int[][] machine; // 공기 청정기 위치 좌표
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        machine = new int[2][2];

        int cnt = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int temp = Integer.parseInt(st.nextToken());
                // 만약 공기청정기라면 저장
                if (temp == -1 && cnt < 2) {
                    cnt++;
                    machine[cnt-1][0] = i;
                    machine[cnt-1][1] = j;
                }
                arr[i][j] = temp;
            }
        }
        while (T > 0) {
            spread();
            // 디버깅
//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }
            operate();
            // 디버깅
//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }
            T--;
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer += arr[i][j];
            }
        }
        System.out.println(answer+2);

    }
    // 확산
    static void spread() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 미세먼지가 있는 칸이면
                if (arr[i][j] > 0) {
                    int t = arr[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        // 범위를 벗어나지 않고 공기청정기가 없다면
                        if (nr >= 0 && nr < R && nc >=0 && nc < C && arr[nr][nc] != -1) {
                            temp[nr][nc] += t;
                            temp[i][j] -= t;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += temp[i][j];
            }
        }
    }

    // 공기청정기 가동
    static void operate() {
        // 공기청정기 위쪽 가동
        int r = machine[0][0];
        for (int i = r-1; i > 0; i--) {
            arr[i][0] = arr[i-1][0];
        }
        for (int j = 0; j < C-1; j++) {
            arr[0][j] = arr[0][j+1];
        }
        for (int i = 0; i < r; i++) {
            arr[i][C-1] = arr[i+1][C-1];
        }
        for (int j = C-1; j > 1; j--) {
            arr[r][j] = arr[r][j-1];
        }
        arr[r][1] = 0;

        // 공기청정기 아래쪽 가동
        r = machine[1][0];
        for (int i = r+1; i < R-1; i++) {
            arr[i][0] = arr[i+1][0];
        }
        for (int j = 0; j < C-1; j++) {
            arr[R-1][j] = arr[R-1][j+1];
        }
        for (int i = R-1; i > r; i--) {
            arr[i][C-1] = arr[i-1][C-1];
        }
        for (int j = C-1; j > 1; j--) {
            arr[r][j] = arr[r][j-1];
        }
        arr[r][1] = 0;
    }
}
