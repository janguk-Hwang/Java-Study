import java.io.*;
import java.util.*;

// 이전에 풀었던 lcs 문제는 두 문자열에 대해 lcs를 구했지만 이 문제는 세 문자열에서 lcs를 구해야 한다.
// 3중 for문에서 세 문자열에서 문자가 모두 같으면 dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
// 부분 수열이지 부분 문자열이 아니기 때문에 lcs가 연속된 문자들의 모음이 아니여도 된다.
// 그러므로 계속 최적값을 갱신해줘야 한다.
// dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1])
public class Main {
    static String a, b, c;
    static int[][][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        a = br.readLine(); b = br.readLine(); c = br.readLine();
        int aL = a.length(); int bL = b.length(); int cL = c.length();
        dp = new int[aL + 1][bL + 1][cL + 1];
        for(int i=1; i<=aL; i++){
            for(int j=1; j<=bL; j++){
                for(int k=1; k<=cL; k++){
                    // 세 문자가 모두 같으면
                    if(a.charAt(i-1) == b.charAt(j-1) && b.charAt(j-1) == c.charAt(k-1)){
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                        continue;
                    }
                    // 세 문자가 모두 같지 않으면 이전 상태 중에서 최적값을 선택
                    dp[i][j][k] = Math.max(Math.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
                }
            }
        }
        System.out.print(dp[aL][bL][cL]);
    }
}