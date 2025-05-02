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
            // dp[i][j]: i번째까지의 수에서 길이가 j인 증가하는 부분 수열의 개수
            long[][] dp = new long[n][k + 1];
            // 기저 사례: 0부터 n-1까지의 수는 길이가 1인 증가하는 부분 수열의 개수는 1개이다.
            for(int i=0; i<n; i++) dp[i][1] = 1;
            // 수열: [1, 5, 2, 4, 3]
            for (int j = 2; j <= k; j++) {  // 길이가 2부터 k까지
                for (int i = 0; i < n; i++) {  // i번째 수에 대해
                    for (int l = 0; l < i; l++) {  // i보다 앞에 있는 수들에 대해
                        if (a[l] < a[i]) {  // i를 마지막에 사용해야 dp[i][]를 만들 수 있으므로 a[i]와 a[l]을 비교
                            // l까지의 수열에서 j-1 길이인 증가하는 부분 수열의 개수
                            // ex) dp[3][3] += dp[0][2]
                            // dp[3][3] += dp[2][2]
                            // dp[i][j]를 구하려면 dp[l][j-1]을 알아야 한다. 즉, j가 밖에 있어서 길이가 j보다 작은 j-1에 대한 dp배열 값이 구해져 있어야 한다.
                            dp[i][j] += dp[l][j - 1];   // i를 사용해서 j길이의 증가하는 부분 수열은 i앞에 i보다 작은 l들의 j-1길이의 증가하는 부분 수열의 수이다.
                        }
                    }
                }
            }
            // 64비트 정수 범위 = long
            long result = 0;
            // k길이의 증가하는 부분 수열은 최소 k개의 수열이 필요하다
            for(int i=k-1; i<n; i++) result += dp[i][k];
            System.out.println(result);
        }
    }
}