import java.util.*;
import java.io.*;
/*
    1. 갈 수 있는 주요소까지 간다.
    2. 그 중에서 연료를 제일 많이 주는 곳으로 간다.
 */
public class B_01826_연료채우기 {
    static class Station implements Comparable<Station> {
        int dist;
        int fuel;
        public Station(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }
        @Override
        public int compareTo(Station o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Station[] stations = new Station[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            stations[i] = new Station(d, f);
        }

        Arrays.sort(stations); // 거리 기준 오름차순 정렬

        StringTokenizer st = new StringTokenizer(br.readLine());
        int end = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 연료 최대 힙
        int idx = 0;
        int answer = 0;

        while (fuel < end) {
            // 도달 가능한 모든 주유소 저장
            while (idx < N && stations[idx].dist <= fuel) {
                maxHeap.add(stations[idx].fuel);
                idx++;
            }

            // 도달 가능한 주유소가 없음
            if (maxHeap.isEmpty()) {
                System.out.println(-1);
                return;
            }

            // 연료 가장 많이 주는 주유소 선택
            fuel += maxHeap.poll();
            answer++;
        }

        System.out.println(answer);
    }
}
