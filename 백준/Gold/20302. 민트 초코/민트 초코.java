import java.io.*;
import java.util.*;

// 정수 vs 유리수(분수, 소수)
public class Main {
    static Map<Integer, Integer> factorCnt = new HashMap<>();   // 소인수, 출현 횟수
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        // 첫 번째 수가 0이면 항상 정수
        int first = Integer.parseInt(str[0]);
        if(first == 0){
            System.out.println("mint chocolate");
            return;
        }
        func(first, 1);
        for(int i=1; i<str.length; i+=2){
            String op = str[i];
            int num = Integer.parseInt(str[i + 1]);
            if(op.equals("*") && num == 0){
                System.out.println("mint chocolate");
                return;
            }
            if (op.equals("*")) func(num, 1);
            else func(num, -1);
        }
        // factorCnt에 key값들은 소수로만 이루어져 있다.
        // 그러므로 key값이 분모에 더 많이 있다면 분자에 어떤 수가 있더라도 약분이 불가해서 유리수가 된다.
        for(int cnt : factorCnt.values()){
            if(cnt < 0){
                System.out.println("toothpaste");
                return;
            }
        }
        System.out.println("mint chocolate");
    }

    // 소인수분해, 분자에 곱해지면 해당 소인수의 출현 횟수 +1, 분모에 곱해지면 해당 소인수의 출현 횟수 -1
    static void func(int num, int op) {
        int n = Math.abs(num);
        for(int i=2; i*i<=n; i++){
            // 2 ~ n의 제곱근까지 n을 나눠
            while(n % i == 0){
                factorCnt.put(i, factorCnt.getOrDefault(i, 0) + op);
                n /= i;
            }
        }
        // 마지막 남은 소수 처리 51 -> 3 * 17
        if(n > 1) factorCnt.put(n, factorCnt.getOrDefault(n, 0) + op);
    }
}