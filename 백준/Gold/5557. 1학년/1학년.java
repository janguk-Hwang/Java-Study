import java.util.*;
import java.io.*;

// 음수를 배우지 않았고, 20을 넘는 수는 모른다
public class Main {
    static int n;
    static int[] numbers;
    static long[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) numbers[i] = Integer.parseInt(st.nextToken());
        dp = new long[n-1][21];
        dp[0][numbers[0]] = 1;

        for(int i=1; i<n-1; i++){
            for(int j=0; j<=20; j++){
                if(dp[i-1][j] > 0){
                    int plus = j + numbers[i];
                    int minus = j - numbers[i];
                    if(plus <= 20) dp[i][plus] += dp[i-1][j];
                    if(minus >= 0) dp[i][minus] += dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n-2][numbers[n-1]]);
    }
}