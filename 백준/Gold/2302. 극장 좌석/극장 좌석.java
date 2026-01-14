import java.io.*;
import java.util.*;

// vip는 본인 좌석에만 앉아야 하고 1~n번 좌석은 모두 매진
public class Main {
    static int[] dp;
    static int n, m, rst;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dp = new int[41];   // dp[i]: i길이의 칸을 채우는 경우의 수
        dp[0] = 1; dp[1] = 1; dp[2] = 2;
        // i가 5일 경우, 두 가지로 나눠볼 수 있다. 1번이 자리를 바꾸는 경우와 바꾸지 않는 경우로
        // 1 2 ? ? ? or 2 1 ? ? ?
        // 1 2 ? ? ?은 1 ? ? ? ?와 같고 이것은 dp[4]와 동일하다.
        // 2 1 ? ? ?은 dp[3]과 동일하다.
        // 점화식을 세워보면 dp[i] = dp[i-2] + dp[i-1]
        for(int i=3; i<=n; i++) dp[i] = dp[i - 2] + dp[i - 1];
        rst = 1;
        int pre = 0;
        for(int i=0; i<m; i++){
            int temp = Integer.parseInt(br.readLine());
            rst *= dp[temp - pre - 1];
            pre = temp;
        }
        rst *= dp[n - pre];
        System.out.print(rst);
    }
}