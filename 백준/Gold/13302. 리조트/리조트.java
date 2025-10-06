import java.io.*;
import java.util.*;

// 1일은 1만원, 3일은 2만 5천원, 5일은 3만 7천원
// 3일은 쿠폰 1장, 5일은 쿠폰 2장, 쿠폰 3장 = 1일 무료
// 연속 3일권과 연속 5일권은 구입일로부터 연속으로 3일 혹은 5일간만 이용이 가능하지만 해당 기간을 모두 이용할 필요는 없다.
// dp에 반영해야 하는 것은 날짜, 쿠폰의 수
// 2차원으로 dp를 생성하여 dp[i][j] -> i일까지 고려했을 때, j개의 쿠폰을 가지고 있을 때, 필요한 최소 비용
// 1. 1일권 구매
// 2. 3일권 구매 + 쿠폰 1장 받음
// 3. 5일권 구매 + 쿠폰 2장 받음
// 4. 쿠폰 3장으로 1일 무료 이용
// 5. 리조트를 가지 못하는 날이면 이전까지의 i-1날까지의 최적해와 i날까지의 최적해 중 작은 값 선택
// 보유한 쿠폰의 수를 순회하는 것은 내부 for문으로
public class Main {
    static int INF = Integer.MAX_VALUE;
    static HashSet<Integer> cantGoDay = new HashSet<>();
    static int n, m, rst;
    static int[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if(m > 0){
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++) cantGoDay.add(Integer.parseInt(st.nextToken()));
        }
        int maxCoupon = n / 5 * 2 + 3;
        dp = new int[n + 6][maxCoupon + 1];
        for(int i=0; i<n+6; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;
        // 외부 for문은 날짜 순회
        for(int i=0; i<n; i++){
            for(int j=0; j<maxCoupon; j++){
                // 도달한 적 없으면 건너뜀
                if(dp[i][j] == INF) continue;
                // 5. 리조트에 가지 못하는 날이면 기존에 계산된 i+1날까지의 최적해와 i날까지의 최적해 중 작은 값으로 갱신
                // 리조트에 가지 못하는 날에는 이용권을 구매하거나 쿠폰으로 이용할 수 없으므로 continue
                if(cantGoDay.contains(i+1)){
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                    continue;
                }
                // 1. 1일권 구매(dp[i+1][j]는 여러 경로에서 도달할 수 있으므로 기존 dp[i+1][j]와 비교하여 갱신)
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 10000);
                // 2. 3일권 구매
                dp[i+3][j+1] = Math.min(dp[i+3][j+1], dp[i][j] + 25000);
                // 3. 5일권 구매
                dp[i+5][j+2] = Math.min(dp[i+5][j+2], dp[i][j] + 37000);
                // 4. 쿠폰 3장으로 1일 이용
                if(j >= 3) dp[i+1][j-3] = Math.min(dp[i+1][j-3], dp[i][j]);
            }
        }
        rst = Integer.MAX_VALUE;
        for(int j=0; j<maxCoupon; j++) rst = Math.min(rst, dp[n][j]);
        System.out.print(rst);
    }
}