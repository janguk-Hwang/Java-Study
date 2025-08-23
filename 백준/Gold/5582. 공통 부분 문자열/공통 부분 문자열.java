import java.io.*;
import java.util.*;

// LCS(longest common substring) 최장 공통 부분 수열
// 부분 문자열은 중간에 끊기면 안 된다.
// 두 문자열의 i, j번째 문자가 같으면 공통 부분 문자열 확장
public class Main {
    static int[][] dp;  // dp[i][j]: s1의 i번째, s2의 j번째까지 고려했을 때의 LCS 길이
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String s1 = br.readLine();
        String s2 = br.readLine();
        dp = new int[s1.length() + 1][s2.length() + 1];
        int max = 0;
        for(int i=1; i<=s1.length(); i++){
            for(int j=1; j<=s2.length(); j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if(dp[i][j] > max) max = dp[i][j];
                }
            }
        }
        System.out.print(max);
    }
}