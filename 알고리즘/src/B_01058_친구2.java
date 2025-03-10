import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class B_01058_친구2 {
	static int n;
	static int arr[][];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		// 입력 받기 
		for (int i = 0; i < n; i++) {
			String st = br.readLine();
			for (int j = 0; j < n; j++) {
				char ch = st.charAt(j);
				if (ch == 'Y') {
					arr[i][j] = 1;
				} else {
					arr[i][j] = Integer.MAX_VALUE/2;
				}
				if (i==j) {
					arr[i][j]=0;
				}
			}
		}

		
		// 플로이드 워샬
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		
		int max = 0;
		
		for (int i = 0; i < n; i++){
			int cnt = 0;
			for (int j = 0; j < n; j++) {
				if (arr[i][j]== 2 || arr[i][j]==1) {
					cnt += 1;
				}
			}
			if (max < cnt) {
				max = cnt;
			}
		}

		System.out.println(max);
	}
}
