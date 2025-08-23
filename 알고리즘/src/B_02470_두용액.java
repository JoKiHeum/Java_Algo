import java.util.*;
import java.io.*;

public class B_02470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 오름차순 정렬
        Arrays.sort(arr);

        // 이분 탐색 활용
        int left = 0;
        int right = N-1;
        int minDiff = Integer.MAX_VALUE;
        int answerLeft = 0;
        int answerRight = 0;

        while (left < right) {
            int diff = arr[left] + arr[right];

            // minDiff보다 diff가 작다면 갱신
            if (minDiff > Math.abs(diff)) {
                minDiff = Math.abs(diff);
                answerLeft = left;
                answerRight = right;
            }

            // 만약 diff 가 음수면 left 오른쪽으로 한칸 이동 아니면 right 왼쪽으로 한칸 이동
            if (diff < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(arr[answerLeft] + " " + arr[answerRight]);
    }
}
