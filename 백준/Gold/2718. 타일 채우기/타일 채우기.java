import java.io.*;
import java.util.*;

public class Main {
    static int t, max;
    static int[] arr;
    static int[] dp;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        arr = new int[t];
        for(int i=0; i<t; i++){
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        dp = new int[max + 1];
        dp[0] = 1; dp[1] = 1; dp[2] = 5; dp[3] = 11;
        for(int i=4; i<=max; i++){
            dp[i] = dp[i-1] + dp[i-2] * 4;
            for(int j=i-4; j>=0; j-=2) dp[i] += dp[j] * 3;
            for(int j=i-3; j>=0; j-=2) dp[i] += dp[j] * 2;
        }
        for(int i : arr) sb.append(dp[i]).append("\n");
        System.out.print(sb);
    }
}