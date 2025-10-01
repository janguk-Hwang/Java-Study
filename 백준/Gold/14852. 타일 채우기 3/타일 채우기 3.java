import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[] dp;        // n열까지 채울 수 있는 최대 경우의 수
    static long[] preSum;
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        preSum = new long[n + 1];
        dp = new long[n + 1];
        dp[0] = 1;
        if(n >= 1) dp[1] = 2;
        if(n >= 2) dp[2] = 7;
        if(n >= 3) preSum[3] = dp[0];
        for(int i=3; i<=n; i++){
            dp[i] = (dp[i-1] * 2 + dp[i-2] * 3 + 2 * preSum[i]) % MOD;
            // preSum은 dp[i]부터 dp[i-3]까지의 합을 가짐.
            if(i + 1 <= n){
                preSum[i + 1] = (preSum[i] + dp[i - 2]) % MOD;
            }
        }
        System.out.print(dp[n]);
    }
}