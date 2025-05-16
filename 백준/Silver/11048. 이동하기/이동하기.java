import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] arr, dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                dp[i][j] = Math.max(Math.max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + arr[i][j];
            }
        }
        System.out.println(dp[n][m]);
    }
}