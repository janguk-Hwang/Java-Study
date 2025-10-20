import java.io.*;
import java.util.*;

class Solution {
    static int MOD = 10_007;
    public int solution(int n, int[] tops) {
        int[] dp = new int[2 * n + 2];
        dp[0] = 1; dp[1] = 1;
        for(int i=2; i <= 2*n+1; i++){
            // 역삼각형과 tops가 올 수 있는 곳이고, tops가 1이면
            if(i % 2 == 0 && tops[(i - 1) / 2] == 1){
                // 1번째 그림 + 3번째 그림 + 2번째
                dp[i] = ((dp[i - 1] * 2) + dp[i - 2]) % MOD;
            }
            else dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        return dp[2 * n + 1];
    }
}