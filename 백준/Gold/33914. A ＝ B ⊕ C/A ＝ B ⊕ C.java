import java.io.*;
import java.util.*;

// (1 0 1) | (1 1 0) | (0 0 0) | (0 1 1)
public class Main {
    static int x, y;
    static int mod = 1_000_000_007;
    static int[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dp = new int[x+1][y+1];

        dp[0][0] = 1;
        for(int i=0; i<=x; i++){    // 1의 개수
            for(int j=0; j<=y; j++){    // 0의 개수
                if(dp[i][j] == 0) continue;
                // 0을 3개 더 사용해도 y개를 넘지 않으면
                if(j+3 <= y){
                    dp[i][j+3] = (dp[i][j+3] + dp[i][j]) % mod;
                }
                // 1 2개, 0 1개를 사용
                if(i+2 <= x && j+1 <= y){
                    dp[i+2][j+1] = (int)((dp[i+2][j+1] + 3L * dp[i][j]) % mod);
                }
            }
        }
        System.out.println(dp[x][y]);
    }
}