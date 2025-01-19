import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int [] wine = new int[n+2];
        int [] Dp = new int[n+2];

        for(int i=1; i<=n; i++) {
            wine[i]=scanner.nextInt();
        }

        Dp[1]=wine[1];
        Dp[2]=wine[1] + wine[2];

        for(int i=3;i<=n; i++){
            Dp[i]=Math.max(Dp[i-1], Dp[i-2]+wine[i]);
            Dp[i]=Math.max(Dp[i], Dp[i-3]+wine[i]+wine[i-1]);
        }

        System.out.println(Dp[n]);
    }
}