import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] dp;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        dp = new int[N + 1];
        arr = new int[N + 1];
        
        // arr 입력받기
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }
        
        dp[1] = arr[1];
        if (N >= 2) dp[2] = arr[1] + arr[2]; // N이 1일 경우 예외 처리
        
        System.out.println(dp(N));
    }
    
    public static int dp(int i) {
        if (i <= 0) return 0; // 범위를 벗어나는 경우 0 반환
        if (dp[i] > 0) return dp[i]; // 이미 계산된 값이 있는 경우 바로 반환
        
        // 연속된 세 개의 계단을 밟지 않는 경우 고려
        dp[i] = Math.max(dp(i - 2) + arr[i], dp(i - 3) + arr[i - 1] + arr[i]);
        
        return dp[i];
    }
}
