import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[] a;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            if(n == 0 && k == 0) break;
            a = new int[n + 1];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0; i<n; i++) a[i] = Integer.parseInt(st.nextToken());
            long[][] dp = new long[n][k + 1];
            for(int i=0; i<n; i++) dp[i][1] = 1;
            for (int j = 2; j <= k; j++) {
                for (int i = 0; i < n; i++) {
                    for (int l = 0; l < i; l++) {
                        if (a[l] < a[i]) dp[i][j] += dp[l][j - 1];
                    }
                }
            }
            long result = 0;
            for(int i=k-1; i<n; i++) result += dp[i][k];
            System.out.println(result);
        }
    }
}