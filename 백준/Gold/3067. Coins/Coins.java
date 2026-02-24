import java.io.*;
import java.util.*;

// 주어진 금액을 만드는 모든 방법(동전 개수 제한x)
public class Main {
    static int t, n, m;
    static int[] arr, dp;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(br.readLine());
            dp = new int[m + 1];
            dp[0] = 1;
            for(int i : arr){
                for(int j=i; j<=m; j++){
                    dp[j] += dp[j - i];
                }
            }
            sb.append(dp[m]).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
}