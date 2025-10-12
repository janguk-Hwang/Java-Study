import java.io.*;
import java.util.*;

// dp 테이블을 채우면서 달라지는 상태는 수열의 길이, 인접한 비트의 수
// 인접한 비트의 수가 k개가 될 때의 경우의 수를 알아내야 하기 때문에 인접한 비트의 수가 dp의 인덱스로 들어가야 한다.
// 그냥 2차원으로 했을 때, 점화식에서 문제가 발생한다.
// i번째 수가 0인지 1인지 모르기 때문에 마지막 수가 0인지 1인지 기록해야 한다.
// dp[i][j][p] -> 길이는 i, 인접한 1의 개수는 k개, 마지막 수가 0인지 p인지
public class Main {
    static int t, n, k;
    static int[][][] dp = new int[101][101][2];
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        for(int i=2; i<=100; i++){
            // j는 0부터 최대 i까지
            for(int j=0; j<=i; j++){
                // 마지막 수가 0이면 증가하지 않음
                dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1];
                // 마지막 수가 1인 경우
                if(j > 0) dp[i][j][1] = dp[i-1][j][0] + dp[i-1][j-1][1];
                if(j == 0) dp[i][j][1] = dp[i-1][j][0];
            }
        }
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            sb.append(dp[n][k][0] + dp[n][k][1]).append("\n");
        }
        System.out.print(sb);
    }
}