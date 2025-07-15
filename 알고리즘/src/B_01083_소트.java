import java.util.*;
import java.io.*;

public class B_01083_소트 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = Integer.parseInt(br.readLine());    // 바꿀 수 있는 기회

        int idx = 0;

        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        };
        while (idx < N && s > 0) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(comp);
            for (int j = idx; j < N; j++) {
                pq.add(new int[] {arr[j], j});
            }

            while (!pq.isEmpty()) {
                int target = pq.poll()[1];
                int cnt = target - idx;         // 스왑에 필요한 횟수
                // 만약 남은 횟수가 cnt 이상이면
                if (s >= cnt) {
                    int temp = arr[target];
                    for (int i = target; i > idx; i--) {
                        arr[i] = arr[i-1];
                    }
                    arr[idx] = temp;
                    s -= cnt;
                    break;
                }

            }
            idx++;
        }



        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
