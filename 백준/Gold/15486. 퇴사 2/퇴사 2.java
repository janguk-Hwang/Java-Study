import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] t, p, dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        t = new int[n+1];
        p = new int[n+1];
        dp = new int[n+2];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // 1일부터 n일까지
        for(int i=1; i<=n; i++){
            dp[i+1] = Math.max(dp[i+1], dp[i]);
            if(i+t[i] <= n+1){
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i] + p[i]);
            }
        }
        int max = 0;
        for(int i=1; i<=n+1; i++){
            max = Math.max(max, dp[i]);
        }
        System.out.print(max);
    }
}