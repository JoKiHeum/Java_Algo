import java.util.*;
import java.io.*;
public class B_13904_과제 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        //   과제 마감일 기준 내림차순 정렬
        Comparator<int[]> comp1 = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        };

        // 과제 점수 기준 내림차순 정렬
        Comparator<int[]> comp2 = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        };

        // 과제 마감일이 늦은 순서대로 우선순위큐에 삽입
        PriorityQueue<int[]> pq1 = new PriorityQueue<>(comp1);
        int maxDay = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            if (d > maxDay) {
                maxDay = d;
            }
            pq1.add(new int[] {d,score});
        }

        int answer = 0;

        while (maxDay > 0) {
            PriorityQueue<int[]> temp = new PriorityQueue<>(comp2);
            while(!pq1.isEmpty()) {
                int[] work = pq1.poll();
                // 만약 maxDay 보다 작으면
                if (work[0] < maxDay) {
                    pq1.add(work);
                    break;
                } else {
                    temp.add(work);
                }
            }

            maxDay--;

            // 만약 temp Q에 아무것도 없다면 패스
            if (temp.isEmpty()) continue;

            // 하나라도 있다면
            // 첫번째로 나온것이 그날의 과제
            int[] dayWork = temp.poll();
            answer += dayWork[1];

            // 나머지 과제들 다시 pq1에 넣기

            while(!temp.isEmpty()) {
                pq1.add(temp.poll());
            }

        }
        System.out.println(answer);

    }
}
