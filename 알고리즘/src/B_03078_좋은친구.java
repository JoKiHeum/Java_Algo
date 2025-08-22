import java.util.*;
import java.io.*;

public class B_03078_좋은친구 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nameLength = new int[N];
        int[] nameLengthCount = new int[21];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            nameLength[i] = s.length();
        }
        // nameLengthCount 초기화
        for (int i = 0; i <= K; i++) {
            nameLengthCount[nameLength[i]]++;
        }

        long answer = nameLengthCount[nameLength[0]] -1;
        nameLengthCount[nameLength[0]]--;
        for (int i = 1; i < N; i++) {
            if (i+K < N) {
                nameLengthCount[nameLength[i+K]]++;
            }
            answer += nameLengthCount[nameLength[i]]-1;
            nameLengthCount[nameLength[i]]--;
        }
        System.out.println(answer);
    }
}
