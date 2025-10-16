import java.io.*;
import java.util.*;

// dp[i][j]: i번째 도시까지 고려했을 때, j분 만큼 걸렸을 때, 모금할 수 있는 최대 금액
/*
for(도시 순회){
    for(시간 순회){
        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-해당 도시로 이동하는데 걸리는 시간(도보)])
        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-해당 도시로 이동하는데 걸리는 시간(자전거)])
    }
}
 */
public class Main {
    static int n, k;
    static int[][] dp, arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][4];
        dp = new int[n + 1][k + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        dp[0][0] = 0;
        for(int i=1; i<=n; i++){
            for(int j=k; j>=0; j--){
                if(j - arr[i][0] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[i][0]] + arr[i][1]);
                }
                if(j - arr[i][2] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[i][2]] + arr[i][3]);
                }
            }
        }
        int rst = 0;
        for(int i=0; i<=k; i++) rst = Math.max(rst, dp[n][i]);
        System.out.print(rst);
    }
}