import java.io.*;
import java.util.*;

public class Main {
    static int t, n;
    static int[] dp = new int[10001];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        query();
        for(int i=0; i<t; i++){
            n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);
    }

    public static void query(){
        dp[0] = 1;
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= 10000; j++) {
                dp[j] += dp[j - i];
            }
        }
    }
}