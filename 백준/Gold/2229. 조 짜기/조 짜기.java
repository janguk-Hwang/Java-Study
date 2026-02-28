import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr, dp;       // dp[i]: i까지의 고려했을 때, 조가 잘 짜여진 정도의 최댓값
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=1; i<=n; i++){
            int max = arr[i];
            int min = arr[i];
            for(int j=i; j<=n; j++){
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                dp[j] = Math.max(dp[j], dp[i-1] + max - min);
            }
        }
        System.out.print(dp[n]);
    }
}