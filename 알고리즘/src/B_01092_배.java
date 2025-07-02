import java.util.*;
import java.io.*;

public class B_01092_배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] crane = new int[N];   // 각 크레인의 감당 가능한 무게

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }

        // 크레인 무게 역순 정렬
        Arrays.sort(crane);
        for (int i = 0; i < N/2; i++) {
            int temp = crane[i];
            crane[i] = crane[N-1-i];
            crane[N-1-i] = temp;
        }
        // 제일 무거운 크레인
        int maxCrane = crane[0];

        // box input 받기
        int M = Integer.parseInt(br.readLine());
        List<Integer> box = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        // box 역순 정렬
        Collections.sort(box, Collections.reverseOrder());
        int maxBox = box.get(0);


        int answer = 0;
        while(maxCrane >= maxBox && !box.isEmpty()) {
            int craneIdx = 0;
            int boxIdx = 0;
            while (craneIdx < N && boxIdx < box.size()) {
                // 만약 크레인이 box를 들수있다면
                if (crane[craneIdx] >= box.get(boxIdx)) {
                    box.remove(boxIdx);
                    craneIdx++;
                } else {
                    boxIdx++;
                }
            }
            answer++;
        }

        if (maxCrane < maxBox) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
