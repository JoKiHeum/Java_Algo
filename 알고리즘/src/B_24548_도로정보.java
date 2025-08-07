import java.util.*;
import java.io.*;

public class B_24548_도로정보 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int [][][][] dp = new int[3][3][3][3];
        dp[0][0][0][0] = 1;
        String st = br.readLine();

        int tCnt = 0, gCnt = 0, fCnt = 0, pCnt = 0;

        int answer = 0;

        for (int i = 0; i < N; i++) {
            char c = st.charAt(i);

            if (c == 'T') {
                tCnt = (tCnt + 1) % 3;
            } else if (c == 'G') {
                gCnt = (gCnt + 1) % 3;
            } else if (c == 'F') {
                fCnt = (fCnt + 1) % 3;
            } else if (c == 'P') {
                pCnt = (pCnt +1) % 3;
            }
            answer += dp[tCnt][gCnt][fCnt][pCnt];
            dp[tCnt][gCnt][fCnt][pCnt]++;
        }
        System.out.println(answer);



    }
}
