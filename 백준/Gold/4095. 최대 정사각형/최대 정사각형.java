import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static int[][] matrix;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            matrix = new int[n][m];
            dp = new int[n][m];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
            }
            int max = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(matrix[i][j] == 1){
                        // 기저 사례
                        if(i == 0 || j == 0) dp[i][j] = 1;
                        // 현재 칸이 1이면 왼쪽, 대각선 왼쪽 위, 윗 칸의 dp 값 중에 가장 작은 값 + 1
                        if(i > 0 && j > 0) dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            sb.append(max).append("\n");
        }
        System.out.print(sb);
    }
}