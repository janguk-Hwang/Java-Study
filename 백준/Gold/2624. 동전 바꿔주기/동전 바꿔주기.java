import java.io.*;
import java.util.*;

// 최소한의 동전으로 지폐를 교환할 때의 동전 수가 아닌 교환할 수 있는 방법의 수를 구해야 한다.
// dp 사용
public class Main {
    static int[] dp;      // i원 지폐를 교환할 수 있는 방법의 수
    static int t, k;
    static int[][] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new int[k][2];    // 0: 동전 종류, 1: 동전 수
        dp = new int[t + 1];    // 1-based
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a; arr[i][1] = b;
        }
        dp[0] = 1;
        for(int i=0; i<k; i++){
            for(int j=t; j>=0; j--){
                for(int p=1; p<=arr[i][1]; p++){
                    if(j - arr[i][0] * p < 0) break;
                    dp[j] += dp[j - arr[i][0] * p];
                }
            }
        }
        System.out.print(dp[t]);
    }
}