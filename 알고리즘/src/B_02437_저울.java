import java.util.*;
import java.io.*;

/*
풀이방법: 현재 올리려는 무게 추가 지금까지 올린 저울추의 총합보다 커지면 저울추의 총합은 측정할 수 없는 최솟값이다.
 */
public class B_02437_저울 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int sum = 1;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            if (sum < arr[i]) break;
            sum += arr[i];
        }
        System.out.println(sum);
    }
}
