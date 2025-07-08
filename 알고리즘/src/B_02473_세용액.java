import java.util.*;
import java.io.*;

public class B_02473_세용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int answerLeft = 0;
        int answerRight = 0;
        int answerIndex = 0;
        long answer = 3000000000L;

        for (int i = 0; i < N-2; i++) {
            int left = i+1;
            int right = N-1;
            while (left < right) {
                long temp = (long)arr[left] + arr[right] + arr[i];
                long diff = Math.abs(temp);
                // 정답 갱신
                if (answer >  diff) {
                    answer = diff;
                    answerLeft = left;
                    answerRight = right;
                    answerIndex = i;
                }

                if (temp == 0) {
                    break;
                } else if (temp > 0) {
                    right--;
                } else {
                    left++;
                }

            }
        }

        System.out.println(arr[answerIndex] + " " + arr[answerLeft] + " " + arr[answerRight]);




    }
}
