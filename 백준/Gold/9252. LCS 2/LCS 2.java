import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String str1 = br.readLine();
        String str2 = br.readLine();
        int n = str1.length();
        int m = str2.length();
        dp = new int[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                if(str1.charAt(i-1) != str2.charAt(j-1)){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int i = n; int j = m;
        while(i > 0 && j > 0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                sb.append(str1.charAt(i-1));
                i--; j--;
                continue;
            }
            if(dp[i-1][j] >= dp[i][j-1]){
                i--;
            }
            else j--;
        }
        sb.reverse();
        System.out.println(dp[n][m]);
        System.out.print(sb);
    }
}