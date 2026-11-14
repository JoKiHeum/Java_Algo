import java.util.*;
public class 인접리스트연습 {
	public static void main(String[] args) {
		ArrayList<int[]> a = new ArrayList<>();
		a.add(new int[] {10,11});
		a.add(new int[] {13, 15});
		a.add(new int[] {17, 19});
		a.add(new int[] {0,1});

		Comparator<int[]> comp = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		};

		Collections.sort(a, comp);
		for (int[] v: a) {
			System.out.println(Arrays.toString(v));
		}

		
		Integer[] b = new Integer[] {10, 12, 11, 13};
		Arrays.sort(b, Collections.reverseOrder());
		System.out.println(Arrays.toString(b));
	}
}
