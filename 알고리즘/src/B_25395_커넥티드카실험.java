import java.util.*;
import java.io.*;

public class B_25395_커넥티드카실험 {
    static int N;
    static int S;
    static class Car implements Comparable<Car> {
        int idx;
        int location;
        int fuel;

        public Car(int idx, int location, int fuel) {
            this.idx = idx;             // 차량 번호
            this.location = location;   // 위치
            this.fuel = fuel;           // 연료
        }
        @Override
        public int compareTo(Car o) {
            return this.location - o.location;
        }
        @Override
        public String toString() {
            return "Car( idx: " + idx + " location: " + location + " fuel: " + fuel + ")";
        }
    }
    static Car[] arr;
    static boolean[] visited;
    static int[] X;
    static int[] H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new Car[N+1];
        arr[0] = new Car(-10000, -10000, -10000);
        visited = new boolean[N+1]; // 앞에 0 더미
        X = new int[N];
        H = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N+1; i++) {
            arr[i] = new Car(i, X[i-1], H[i-1]);
        }
        // 디버깅
//        for (int i = 1; i < N+1; i++) {
//            System.out.println(arr[i].toString());
//        }
        bfs();
        // 디버깅
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i< N+1; i++) {
            if (visited[i]) sb.append(i+ " ");
        }
        System.out.println(sb);

    }
    static void bfs() {
        Queue<Car> q = new LinkedList<>();
        q.add(arr[S]);
        visited[S] = true;
        while(!q.isEmpty()) {
            Car cur = q.poll(); // 현재 카
            // 오른쪽 먼저 탐색
            for (int i = cur.idx+1; i <= N; i++) {
                // 만약 현재 차의 위치와 연료를 합산했을 때 다음 차의 위치가 더 멀다면
                if (arr[i].location > cur.location + cur.fuel) break;
                if (visited[arr[i].idx]) continue;
                q.add(arr[i]);
                visited[i] = true;
            }

            // 왼쪽 탐색
            for (int i = cur.idx-1; i >= 1; i--) {
                if (arr[i].location < cur.location - cur.fuel) break;
                if (visited[arr[i].idx]) continue;
                q.add(arr[i]);
                visited[i] = true;
            }
        }
    }
}
