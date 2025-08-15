import java.util.*;
import java.io.*;

public class B_1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //  최대힙
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();     // 최소힙

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            // 먼저 최대힙의 크기가 0이면 최대 힙에 먼저 삽입
            if (maxHeap.size() == 0) {
                maxHeap.add(num);
                sb.append(num).append("\n");
                continue;
            }

            // 최대 힙의 크기가 최소 힙의 크기보다 크다면
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }

            // 크기 비교
            int maxTop = maxHeap.poll();
            int minTop = minHeap.poll();

            // 만약 maxTop이 minTop보다 크다면 교환
            if (maxTop > minTop) {
                maxHeap.add(minTop);
                minHeap.add(maxTop);
            } else {
                maxHeap.add(maxTop);
                minHeap.add(minTop);
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
