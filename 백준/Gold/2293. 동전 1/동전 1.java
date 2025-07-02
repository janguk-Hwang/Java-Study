import java.io.*;
import java.util.*;

// 1만 사용해서 k원을 만들 수 있는 경우의 수, 1, 3을 사용해서 k원을 만들 수 있는 경우의 수
// 1, 3, 5를 사용해서 k원을 만들 수 있는 경우의 수...
public class Main {
    static int n, k;
    static int[] dp, coins;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k + 1];
        coins = new int[n];
        for(int i=0; i<n; i++) coins[i] = Integer.parseInt(br.readLine());
        dp[0] = 1;
        for(Integer coin : coins){
            for(int j=coin; j<=k; j++){
                dp[j] += dp[j - coin];
            }
        }
        System.out.print(dp[k]);
    }
}
// dp[6] += dp[3];
// 1 1 1 1 1 1 에 1 1 1 + 3과 3 + 3을 더한다
// dp[3]은 1 1 1이 있고 여기에 3을 더해서 만든 1 1 1 3과 3 3이 dp[6]에 추가되는 것이다.
// 새로운 동전을 사용해서 발생하는 경우의 수들을 기존 dp[ ]에 추가하는 것이다.
// 3을 사용할 때 dp[3]에는 3이 있고 이후에 dp[6] += dp[3]로 3 3이 된다.