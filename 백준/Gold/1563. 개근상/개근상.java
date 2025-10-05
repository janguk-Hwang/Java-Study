import java.io.*;
import java.util.*;

// 개근상을 받을 수 없는 사람은 지각을 두 번 이상 했거나, 결석을 세 번 연속으로 한 사람이다.
// 지각 횟수, 연속으로 결석한 횟수를 관리해야 한다.
// dp[i][j][k] -> i번째 날까지 고려했을 때, j번 지각, k일 연속 결석한 경우
// 개근상을 받을 수 있는 경우는
// 0지각, 0연속 결석
// 0지각, 1연속 결석
// 0지각, 2연속 결석
// 1지각, 0연속 결석
// 1지각, 1연속 결석
// 1지각, 2연속 결석
public class Main {
    static final int MOD = 1_000_000;
    static long[][][] dp;
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][2][3];
        // 기저 사례
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        for(int i=2; i<=n; i++){
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % MOD;
            dp[i][0][1] = dp[i-1][0][0] % MOD;
            dp[i][0][2] = dp[i-1][0][1] % MOD;
            dp[i][1][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2] + dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % MOD;
            dp[i][1][1] = dp[i-1][1][0] % MOD;
            dp[i][1][2] = dp[i-1][1][1] % MOD;
        }
        long rst = 0L;
        for(int i=0; i<2; i++){
            for(int j=0; j<3; j++){
                rst += dp[n][i][j];
            }
        }
        System.out.print(rst % MOD);
    }
}