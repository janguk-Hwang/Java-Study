import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr, dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];     // i행의 j열까지 올 수 있는 최대 누적합
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<=i; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = arr[0][0];
        for(int i=1; i<n; i++){
            for(int j=0; j<=i; j++){
                if(j == 0) dp[i][j] = dp[i-1][j] + arr[i][j];
                else if(j == i) dp[i][j] = dp[i-1][j-1] + arr[i][j];
                else dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }
        int max = 0;
        for(int i=0; i<n; i++) max = Math.max(max, dp[n-1][i]);
        System.out.print(max);
    }
}