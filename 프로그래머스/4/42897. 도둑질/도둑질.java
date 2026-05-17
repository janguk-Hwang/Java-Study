// 원형 배열 - 확률과 통계처럼 임의의 집 하나를 기준으로 선택하는 경우와 선택하지 않는 경우로 나눠서 고려한다.
// dp[i]: i번째 집까지 고려했을 때, 훔칠 수 있는 돈의 최댓값
// 점화식: dp[i] = Math.max(dp[i-1], dp[i-2] + money[i])

class Solution {
    static int[] dp1, dp2;
    public int solution(int[] money) {
        int n = money.length;
        dp1 = new int[n];
        dp2 = new int[n];
        // 첫 번째 집을 선택하는 경우
        dp1[0] = money[0];
        dp1[1] = money[0];      // 두 번째 집을 선택할 수 없음
        for(int i=2; i<n-1; i++) dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        // 첫 번째 집을 선택하지 않는 경우
        dp2[0] = 0;
        dp2[1] = money[1];
        for(int i=2; i<n; i++) dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        
        return Math.max(dp1[n - 2], dp2[n -1]);
    }
}