import java.io.*;
import java.util.*;

// 점화식
// for(소수 순회)
//   for(소수 ~ 1120)
//     for(소수의 개수)
public class Main {
    static List<Integer> prime = new ArrayList<>();
    static int t;
    static boolean[] isPrime;
    static int[][] dp;      // i를 서로 다른 j개의 소수의 합으로 만들 수 있는 모든 경우의 수
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        isPrime = new boolean[1121];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for(int i=2; i*i<=1120; i++) if(isPrime[i]) for(int j=i*i; j<=1120; j+=i) isPrime[j] = false;
        for(int i=2; i<=1120; i++) if(isPrime[i]) prime.add(i);
        dp = new int[1121][15];
        dp[0][0] = 1;
        for(int p : prime){
            // 내림차순으로 순회하여 중복 x
            for(int i=1120; i>=p; i--){
                for(int j=1; j<=14; j++){
                    dp[i][j] += dp[i-p][j-1];
                }
            }
        }
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dp[a][b]).append("\n");
        }
        System.out.print(sb);
    }
}