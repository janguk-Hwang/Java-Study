import java.io.*;
import java.util.*;

// 결과는 1~n까지의 소수들의 n을 넘지 않는 최대 제곱값들을 가지고 있어야 1부터 n까지의 모든 수들로 나누어 떨어질 수 있다.
public class Main {
    static int n;
    static final long MOD = 987654321L;
    static boolean[] isPrime;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine().trim());
        if(n <= 1){
            System.out.println(1);
            return;
        }
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        for(int i=2; (long)i*i<=n; i++){
            if(isPrime[i]){
                for(int j=i*i; j<=n; j+=i){
                    isPrime[j] = false;
                }
            }
        }
        long rst = 1L;
        for(int i=2; i<=n; i++){
            if(isPrime[i]){
                long power = i;
                while(power * i <= n) power *= i;
                // rst에 n을 넘지 않는 소수들의 제곱값을 누적
                rst = (rst * (power % MOD)) % MOD;
            }
        }
        System.out.println(rst);
    }
}