import java.util.*;
import java.io.*;

public class Main {
    static int t;
    static long[][] dp;
    static int MOD = 1_000_000_009;
    static int MAX = 100_001;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        dp = new long[MAX][4];
        dp[1][1] = 1; // 1
        dp[2][2] = 1; // 2
        dp[3][1] = 1; // 2+1
        dp[3][2] = 1; // 1+2
        dp[3][3] = 1; // 3
        
        for (int i = 4; i < MAX; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % MOD;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % MOD;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % MOD;
        }

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long result = (dp[n][1] + dp[n][2] + dp[n][3]) % MOD;
            System.out.println(result);
        }
    }
}
