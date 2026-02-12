import java.io.*;
import java.util.*;

// 중요도, 시간을 동시에 고려하면서 공부 시간의 한계를 초과하지 않으면서 중요도의 합이 최대가 되는 중요도
// 시간, 중요도를 관리
// dp[t] = 공부 시간을 t만큼 사용했을 때 얻을 수 있는 최대 중요도
public class Main {
    static int[] dp;
    static int n, k;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n + 1];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            // 중복 방지를 위해 역순으로 순회
            for(int j=n; j>=time; j--) dp[j] = Math.max(dp[j], dp[j - time] + temp);
        }
        System.out.println(dp[n]);
    }
}