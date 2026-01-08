import java.io.*;
import java.util.*;

public class Main {
    static long[][] dp = new long[31][31];      // dp[i][j]: w를 i번 h를 j번 사용한 경우의 수
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        dp[1][0] = 1;
        for(int i=0; i<=30; i++){
            for(int j=0; j<=30; j++){
                // w를 기록
                if(i < 30) dp[i+1][j] += dp[i][j];
                // h를 기록
                if(j < i) dp[i][j+1] += dp[i][j];
            }
        }
        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            sb.append(dp[n][n]).append("\n");
        }
        System.out.print(sb);
    }
}