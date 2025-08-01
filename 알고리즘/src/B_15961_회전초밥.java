import java.util.*;
import java.io.*;

public class B_15961_회전초밥 {
    static int N;   //  테이블에 있는 초밥의 갯수
    static int d;   // 초밥의 가짓수
    static int k;   // 연속해서 먹는 초밥의 갯수
    static int c;   // 쿠폰 번호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];             // 테이블 표현
        int[] eated = new int[d+1];         // 먹은 초밥


        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 최대 먹는 경우 초기 설정(0번부터 k-1까지 먹는 경우)
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (eated[arr[i]] == 0) {
                cnt++;
            }
            eated[arr[i]]++;
        }
        int maxCnt = cnt;
        if(eated[c] == 0) {
            maxCnt++;
        }


        for (int i = 1; i < N; i++) {
            int end = (i + k-1) % N;        // 마지막 지점
            // 이전 단계의 시작점 초밥 제거하고
            // 만약 0이면 cnt -1
            eated[arr[i-1]]--;
            if (eated[arr[i-1]] == 0) {
                cnt--;
            }

            // 만약 새로 먹는 것이 이전에 먹지 않았다면
            if (eated[arr[end]]==0) {
                cnt++;
            }
            // 새로 먹은 거 체크해주기
            eated[arr[end]]++;

            // 쿠폰 사용
            if (eated[c] == 0) {
                maxCnt = Math.max(maxCnt, cnt+1);
            } else {
                maxCnt = Math.max(maxCnt, cnt);
            }

        }

        System.out.println(maxCnt);


    }
}
