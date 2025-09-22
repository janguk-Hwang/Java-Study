import java.io.*;
import java.util.*;

// 문자열이 올바른 괄호 문자열인지 판단하는 스택을 사용하는 문제가 아닌
// 길이가 L인 올바른 문자열의 개수를 구해야 한다.
public class Main {
    static final int mod = 1_000_000_007;
    static int t, max;
    static long[] dp;
    static int[] l;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        l = new int[t];
        for(int i=0; i<t; i++){
            l[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, l[i]);
        }
        dp = new long[max + 1];
        dp[0] = 1; dp[1] = 0;
        // i = 5, j = 0, 1, 2, 3, 4
        // dp[10] += dp[0] * dp[4*2];
        // dp[10] += dp[2] * dp[3*2];
        // dp[10] += dp[4] * dp[2*2];
        // dp[10] += dp[6] * dp[1*2];
        // dp[10] += dp[8] * dp[0*2];
        // 이전의 구한 올바른 괄호 문자열끼리 이어붙인다
        for(int i=1; i<=max/2; i++){       // 쌍 개수
            for(int j=0; j<i; j++){     // 분할 기준
                dp[i*2] += dp[j*2] * dp[(i - j - 1) * 2];
                dp[i*2] %= mod;
            }
        }
        for(int i=0; i<t; i++) sb.append(dp[l[i]]).append("\n");
        System.out.print(sb);
    }
}