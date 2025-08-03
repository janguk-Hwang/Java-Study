import java.io.*;
import java.util.*;

// 1번 집을 빨강, 초록, 파랑을 선택한 경우에 대해 각각 진행
// 고려해야할 부분은 1번 - 2번, n-1번 - n번, 2 ~ n-1 구간에서 각 집마다
public class Main {
    static int min = Integer.MAX_VALUE;
    static int[][] cost;
    static int[][] dp;
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        cost = new int[n][3];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        // 0번집 색 선택
        for(int color=0; color<3; color++){
            dp = new int[n][3];
            for(int i=0; i<3; i++){
                if(i != color){
                    dp[0][i] = 1000 * 1000;
                    continue;
                }
                dp[0][i] = cost[0][i];
            }

            // 2번 집부터 n-1번 집까지
            for(int i=1; i<n; i++){
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
            }

            for(int i=0; i<3; i++){
                if(i == color) continue;
                min = Math.min(min, dp[n-1][i]);
            }
        }
        System.out.print(min);
    }
}