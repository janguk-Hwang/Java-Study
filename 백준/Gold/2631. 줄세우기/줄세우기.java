import java.io.*;
import java.util.*;

// 작은 숫자부터 앞으로 이동시키는 그리디 방식은?
// 그리디 방식으로 진행하면 항상 최적이 아니다.
// 6 1 2 3 4 5 => 그리디: 5번, 실제: 1번
// 최장 증가 부분 수열 -> if arr[i] < arr[j] : dp[i] = max(dp[i], dp[j]+1)
public class Main {
    static int n;
    static int[] arr, dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.fill(dp, 1);
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);     // 현재 dp[i]와 이전에 계산된 dp[j]에 arr[i]를 이어붙인 LIS 길이 중 큰 값을 선택
                }
            }
        }
        int maxLis = 0;
        for(int i=0; i<n; i++) maxLis = Math.max(maxLis, dp[i]);
        System.out.print(n - maxLis);
    }
}