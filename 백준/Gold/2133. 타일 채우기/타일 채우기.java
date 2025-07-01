import java.io.*;
import java.util.*;

public class Main {
    static int[] dp = new int[31];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        if(n % 2 != 0){
            System.out.print(0);
            return;
        }
        dp[0] = 1;
        dp[2] = 3;      // 가로로 3개, π 모양, π 뒤집은 모양
        for(int i=4; i<=n; i+=2){
            dp[i] = dp[i-2] * 3;
            for(int j=0; j<=i-4; j+=2){
                dp[i] += dp[j] * 2;
            }
        }
        System.out.print(dp[n]);
    }
}