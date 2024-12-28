import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;



public class B_11723_집합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String commend = st.nextToken();
			
			if (commend.equals("add")) {
				set.add(Integer.parseInt(st.nextToken()));
			} else if (commend.equals("remove")) {
				set.remove(Integer.parseInt(st.nextToken()));
			} else if (commend.equals("check")) {
				if (set.contains(Integer.parseInt(st.nextToken()))) {
					sb.append(1).append('\n');
				} else {
					sb.append(0).append('\n');
				}
			} else if (commend.equals("toggle")) {
				int item = Integer.parseInt(st.nextToken());
				if (set.contains(item)) {
					set.remove(item);
				} else {
					set.add(item);
				}
			} else if (commend.equals("all")) {
				set = new HashSet<>();
				for (int j=1; j<21; j++) {
					set.add(j);
				}
			} else if (commend.equals("empty")) {
				set.clear();
			}
		}
		System.out.println(sb);
		
	}
}
