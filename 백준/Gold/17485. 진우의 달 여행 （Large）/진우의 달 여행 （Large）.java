import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] matrix;
    static int[][][] dp;    // 0:왼쪽 아래 방향, 1:아래 방향, 2: 오른쪽 아래 방향
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n][m][3];
        for(int i=0; i<n; i++) for(int j=0; j<m; j++) Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        // 기저 사례
        for(int j=0; j<m; j++) for(int d=0; d<3; d++) dp[0][j][d] = matrix[0][j];
        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                if(j+1 < m) dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + matrix[i][j];
                dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + matrix[i][j];
                if(j-1 >= 0) dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + matrix[i][j];
            }
        }
        int rst = Integer.MAX_VALUE;
        for(int j=0; j<m; j++) for(int d=0; d<3; d++) rst = Math.min(rst, dp[n-1][j][d]);
        System.out.print(rst);
    }
}