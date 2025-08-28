import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 이지케어텍코테1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        // 5개짜리 상자를 최대한 쓰고, 남는 수가 3으로 나눠질 때까지 5를 하나씩 뺀다.
        int five = n / 5;                  // 가능한 최대 5상자 개수
        int answer = -1;

        while (five >= 0) {
            int remain = n - 5 * five;     // 5상자를 five개 사용했을 때 남는 옷
            if (remain % 3 == 0) {         // 남은 옷이 3상자로 정확히 나눠떨어지면 OK
                int three = remain / 3;
                answer = five + three;     // 전체 상자 수 최소 (five를 최대부터 줄였으므로)
                break;
            }
            five--;                         // 5상자를 하나 줄여보고 다시 시도
        }

        System.out.println(answer);
    }
}
