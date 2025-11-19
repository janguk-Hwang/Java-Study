import java.io.*;
import java.util.*;

// dp[i][j]를 통해 역추적할 수 있다.
public class Main {
    static int[][] dp;      // (i, j)까지 lcs의 길이
    static String s1, s2;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        s1 = br.readLine(); s2 = br.readLine();
        dp = new int[s1.length() + 1][s2.length() + 1];
        for(int i=1; i<=s1.length(); i++){
            for(int j=1; j<=s2.length(); j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int i = s1.length(); int j = s2.length();
        // lcs에 포함되는 문자까지 왼쪽으로 이동하고 lcs에 포함되는 문자를 만나면 해당 문자를 StringBuilder에 쌓고
        // i, j를 모두 감소시키고 다음으로 lcs에 포함되는 문자를 찾는 것을 반복해 나간다.
        // 뒤에서부터 탐색했으므로 reverse()
        while(i > 0 && j > 0){
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
                i--; j--;
            }
            else{
                if(dp[i-1][j] >= dp[i][j-1]) i--;
                else j--;
            }
        }
        System.out.println(sb.reverse());
    }
}