import java.util.*;
import java.io.*;

public class B_06198_옥상정원꾸미기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int num  = Integer.parseInt(br.readLine());
            while(!stack.isEmpty()) {
                if (stack.peek() <= num) {
                    stack.pop();
                } else break;
            }
            answer += stack.size();
            stack.push(num);
        }
        System.out.println(answer);
    }
}
