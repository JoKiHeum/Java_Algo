import java.util.*;
import java.io.*;

public class B_05052_전화번호목록 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                map.put(br.readLine(),i);
            }
            boolean flag = false;
            for (Map.Entry<String, Integer> entry: map.entrySet()) {
                for (int i = 0; i < entry.getKey().length(); i++) {
                    if (map.containsKey(entry.getKey().substring(0,i))) {
                        flag = true;
                    }
                }
            }
            if (flag) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }

        }
    }
}
