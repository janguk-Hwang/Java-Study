import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static int n, m, k, x, y;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[m + 1][k + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            for(int j=m; j>0; j--){
                for(int p=k; p>0; p--){
                    if(x <= j && y <= p) dp[j][p] = Math.max(dp[j][p], dp[j-x][p-y] + 1);
                }
            }
        }
        System.out.println(dp[m][k]);
    }
}