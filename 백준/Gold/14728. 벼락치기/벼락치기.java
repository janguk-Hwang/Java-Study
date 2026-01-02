import java.io.*;
import java.util.*;

public class Main {
    static int n, t;
    static int[] dp;    // dp[i]: i만큼의 시간으로 얻을 수 있는 최대 점수
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        dp = new int[t + 1];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            for(int j=t; j>=k; j--) dp[j] = Math.max(dp[j], dp[j-k] + s);
        }
        System.out.print(dp[t]);
    }
}