import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int testcase = sc.nextInt();
        
        for(int t = 0; t < testcase; t++) {
            HashMap<String, Integer> hashmap = new HashMap<>();
            int num = sc.nextInt();
            
            for(int i = 0; i < num; i++) {
                String name = sc.next();
                String type = sc.next();
                
                hashmap.put(type, hashmap.getOrDefault(type, 0) + 1);
            }
            
            int result = 1;
            for(int value : hashmap.values()) {
                result *= (value + 1); // 각 의류 종류별로 선택할 수 있는 경우의 수를 곱함
            }
            result -= 1; // 모든 의류를 선택하지 않은 경우를 제외함
            
            System.out.println(result);
        }
    }
}
