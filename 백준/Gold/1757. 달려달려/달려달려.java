import java.io.*;
import java.util.*;

// 관리해야 될 속성은 시간과 지침 지수
// dp[i][j]: i시간에 j지침 지수를 가질 때의 최대 이동 거리
public class Main {
    static int n, m;
    static int[] d;
    static int[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = new int[n + 1];
        for(int i=1; i<=n; i++) d[i] = Integer.parseInt(br.readLine());
        dp = new int[n + 1][m + 1];
        for(int i=0; i<=n; i++) Arrays.fill(dp[i], -1);
        dp[0][0] = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(dp[i-1][j-1] == -1) continue;
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + d[i]);
            }
            // 한 번 쉬면 지침 지수가 0이 될 때까지 쉬어야 하기 때문에 지침 지수가 0인 경우를 고려해야 됨
            dp[i][0] = dp[i-1][0];
            for(int j=1; j<=m && i>=j; j++){
                if(dp[i-j][j] == -1) continue;
                dp[i][0] = Math.max(dp[i][0], dp[i-j][j]);
            }
        }
        System.out.print(dp[n][0]);
    }
}