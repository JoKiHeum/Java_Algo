import java.util.*;
import java.io.*;

public class B_01202_보석도둑 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //   보석의 갯수
        int K = Integer.parseInt(st.nextToken());   //   가방의 갯수

        int[][] jewels = new int[N][2];
        int[] bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            jewels[i] = new int[] {M,V};
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 정렬 보석 무게 기준 오름차순 가방 담을 수 있는 무게 기준 오름차순
        Arrays.sort(jewels,(a,b) -> {
            return a[0] - b[0];
        });
        Arrays.sort(bags);

        // 가치 기준 최대 힙(담을 수 있는 보석 후보군)
        PriorityQueue<Integer> value = new PriorityQueue<>(Collections.reverseOrder());

        long answer = 0;
        int index = 0;

        for (int i = 0; i < K; i++) {
            int c = bags[i];
            // 현재 가방의 최대 무게가 보석의 무게를 담을 수 있다면
            while(index < N && c >= jewels[index][0]) {
                value.add(jewels[index][1]);
                index++;
            }
            // 보석 후보군 중 하나 꺼내기
            if (!value.isEmpty()) {
                answer += value.poll();
            }
        }
        System.out.println(answer);
    }
}
