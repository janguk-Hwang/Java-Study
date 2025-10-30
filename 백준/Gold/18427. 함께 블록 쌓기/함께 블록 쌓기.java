import java.io.*;
import java.util.*;

// 어떤 학생의 블록은 사용하지 않아도 되며 한 학생당 최대 1개의 블록만을 사용할 수 있다.
public class Main {
    static final int MOD = 10_007;
    static int n, m, h;
    static int[][] dp, arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][m + 1];
        dp = new int[n + 1][h + 1];     // i번째 학생까지 j높이의 탑을 만드는 경우의 수
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) if(st.hasMoreTokens()) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<=n; i++) dp[i][0] = 1;
        // 학생 순회
        for(int i=1; i<=n; i++){
            // 높이 순회
            for(int j=1; j<=h; j++){
                // i번째 학생이 가지고 있는 블록 순회
                for(int k : arr[i]){
                    if(k == 0) continue;
                    if(j - k >= 0){
                        // 선택하는 경우
                        dp[i][j] += dp[i-1][j-k];
                        dp[i][j] %= MOD;
                    }
                }
                // 선택하지 않는 경우
                dp[i][j] += dp[i-1][j];
                dp[i][j] %= MOD;
            }
        }
        System.out.print(dp[n][h]);
    }
}