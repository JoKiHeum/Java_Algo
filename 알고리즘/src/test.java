import java.util.*;
public class test {
    
    public static void main(String[] args) {
    	Map<String, Integer> map = new HashMap<>();
        map.put("apple",  3);
        map.put("banana", 5);
        map.put("orange", 2);

        // 1) entrySet() + 향상된 for문
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key   = entry.getKey();
            Integer val  = entry.getValue();
            System.out.println(key + " → " + val);
        }

        System.out.println("-----");

        // 2) Java 8+ 람다 forEach
        map.forEach((k, v) -> {
            System.out.println(k + " → " + v);
        });
    	
    }
 
}