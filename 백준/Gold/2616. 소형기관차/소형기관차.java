import java.io.*;
import java.util.*;

// 객차는 최대 5만개이다. dp로 끌 수 있는 객차의 수를 끌 때, i번째 객차를 마지막으로 하는 dp[i]을 구해나간다.
// 그런데 겹치면 안 된다. 겹치지 않게 하려면 소형 기관차를 몇 대 사용했는지도 관리하면 된다.
// 점화식을 짜보니 구간의 합을 계속 구해야 해서 누적합으로 미리 계산해놓고 사용해야 효율적일 것 같다.
public class Main {
    static int n, m;
    static int[] arr, prefixSum;
    static int[][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        prefixSum = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i-1] + arr[i];
        }
        m = Integer.parseInt(br.readLine());
        dp = new int[4][n + 1];
        for(int i=1; i<=3; i++){
            for(int j=i*m; j<=n; j++){
                // i번째 칸을 사용할지 안 할지 선택 가능
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-m] + prefixSum[j] - prefixSum[j-m]);
            }
        }
        // 소형 기관차 3대가 n까지 고려했을 때, 운송할 수 있는 손님의 최대 수
        System.out.print(dp[3][n]);
    }
}