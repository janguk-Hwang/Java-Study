import java.io.*;
import java.util.*;

public class Main {
    static int[][] matrix;
    static int n;
    static long[][][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        dp = new long[n][n][3];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 가로, 대각선, 세로
        dp[0][1][0] = 1;
        for(int i=0; i<n; i++){
            for(int j=2; j<n; j++){
                if(matrix[i][j] == 1) continue;
                // 가로
                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
                // 대각선
                if(i > 0 && matrix[i-1][j] == 0 && matrix[i][j-1] == 0) dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                // 세로
                if(i > 0) dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
            }
        }
        long rst = 0;
        for(int i=0; i<3; i++) rst += dp[n-1][n-1][i];
        System.out.print(rst);
    }
}