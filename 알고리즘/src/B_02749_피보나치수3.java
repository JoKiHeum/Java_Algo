import java.util.*;
import java.io.*;

public class B_02749_피보나치수3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(br.readLine());
		
		long[] fibo = new long[1500000];
		
		fibo[0] = 0;
		fibo[1] = 1;
		
		for (int i = 2; i < 1500000; i++) {
			fibo[i] = (fibo[i-1]+fibo[i-2]) % 1000000;
		}
		
		System.out.println(fibo[(int)(N%1500000)]);
		
		
	}
}
