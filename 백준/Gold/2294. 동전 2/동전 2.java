import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] dp;
    static Set<Integer> coin = new HashSet<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k + 1];
        for(int i=0; i<n; i++) coin.add(Integer.parseInt(br.readLine()));
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int coins : coin) {
            for (int i = coins; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i - coins] + 1);
            }
        }
        System.out.println(dp[k] == 10001 ? -1 : dp[k]);
    }
}
