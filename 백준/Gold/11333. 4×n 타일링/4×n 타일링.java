import java.io.*;
import java.util.*;

// 4와 3의 최소공배수는 12이므로 3칸마다 가득채울 수 있다.
public class Main {
    static int t;
    static final int MOD = 1_000_000_007;
    static int max = 0;
    static long[] dp;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            int num = Integer.parseInt(br.readLine());
            list.add(num);
            max = Math.max(max, num);
        }
        dp = new long[max + 1];
        dp[0] = 1;
        for(int i=3; i<=max; i+=3){
            dp[i] = (dp[i-3] * 3) % MOD;
            for(int j=6; j<=i; j+=3){
                dp[i] += (dp[i - j] * j / 3 * 2) % MOD;
                dp[i] %= MOD;
            }
        }
        for(int i : list) sb.append(dp[i]).append("\n");
        System.out.print(sb);
    }
}
// 3 x 4 + 3n