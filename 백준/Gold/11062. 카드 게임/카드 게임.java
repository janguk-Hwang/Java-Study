import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] card;
    static int[][] dp;
    static int n, t;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            n = Integer.parseInt(br.readLine());
            card = new int[n];
            dp = new int[n+1][n+1];
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                card[j] = Integer.parseInt(st.nextToken());
            }
            solve(0, n-1, true);
            System.out.println(dp[0][n-1]);
        }
    }

    static int solve(int left, int right, boolean turn) {

        if(left> right) return 0;
        if(dp[left][right] != 0) return dp[left][right];

        // 근우
        if(turn) {
            return dp[left][right] = Math.max(card[left] + solve(left+1, right, false),
                    card[right] + solve(left, right-1, false));
        }
        // 명우
        else {
            return dp[left][right] = Math.min(solve(left+1, right, true), solve(left, right-1, true));
        }

    }
}