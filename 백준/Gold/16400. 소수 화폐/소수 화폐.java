import java.io.*;
import java.util.*;

public class Main {
    static final int mod = 123_456_789;
    static int n;
    static List<Integer> primes = new ArrayList<>();
    static int[] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false; isPrime[1] = false;
        for(int i=2; i*i<=n; i++){
            if(isPrime[i]){
                for(int j=i*i; j<=n; j += i){
                    isPrime[j] = false;
                }
            }
        }
        for(int i=2; i<=n; i++) if(isPrime[i]) primes.add(i);
        // i원을 만드는 방법의 수
        dp = new int[n + 1];
        dp[0] = 1;
        for(int prime : primes){
            for(int i=prime; i<=n; i++){
                dp[i] += dp[i - prime] % mod;
                dp[i] %= mod;
            }
        }
        System.out.print(dp[n]);
    }
}