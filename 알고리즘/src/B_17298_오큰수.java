import java.util.*;
import java.io.*;

/*
    스택 활용한 문제
    현재 peek 보다 큰 수라면 pop
    현재 peek qeek 보다 작은 수 라면 push
    마지막에 스택에 남아있는건 다 -1
 */
public class B_17298_오큰수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] answer = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<int[]> stack = new Stack<>();     //  [index, 값]

        for (int i = 0; i < N; i++) {
            int num = arr[i];

            while(!stack.isEmpty() && stack.peek()[1] < num) {
                int[] top = stack.pop();
                answer[top[0]] = num;
            }
            stack.push(new int[] {i, num});
        }
        // stack에 남아있는거 처리
        while(!stack.isEmpty()) {
            int[] top = stack.pop();
            answer[top[0]] = -1;
        }
        for (int i = 0; i < N; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb);

    }
}
