// dp[i]: 가로 길이가 i일 때, 2 x i 크기의 바닥을 타일로 채우는 모든 경우의 수
// 점화식
// dp[n] = dp[n - 1] + dp[n -2]
class Solution {
    public int solution(int n) {
        int rst = 0;
        int[] dp = new int[n + 1];
        dp[1] = 1; dp[2] = 2;
        for(int i=3; i<=n; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000_007;
        }
        return dp[n];
    }
}