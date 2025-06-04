import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new int[21];
        dp[0] = 0; dp[1] = 1; dp[2] = 2;
        dp[3] = 4; dp[4] = 7;
        for(int i=5; i<=20; i++){
            dp[i] = dp[i-1] * 2;
            if(i % 2 == 0){
                dp[i] -= (dp[i-4] + dp[i-5]);
            }
        }
        System.out.print(dp[n]);
    }
}