import java.io.*;
import java.util.*;

// dp[ ][ ]는 받을 수 있는 자두의 수뿐만 아니라 이동횟수도 고려해야 한다.
// 이동횟수가 제한되어 있기 때문
public class Main {
    static int[][] dp;      // dp[t][w]: t초까지 고려했을 때, w번 이동해서 얻을 수 있는 최대 자두 수
    static int t, w;
    static int[] arr;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new int[t + 1];
        for(int i=1; i<=t; i++) arr[i] = Integer.parseInt(br.readLine());
        dp = new int[t + 1][w + 1];
        for(int i=1; i<=t; i++){
            // 매 시간반복마다 이동하지 않는 경우와 이동하는 경우 중 자두를 많이 얻는 경우를 선택
            for(int j=0; j<=w; j++){
                int nowPos = j % 2 == 0 ? 1 : 2;
                int isJadu = arr[i] == nowPos ? 1 : 0;  // 떨어지고 있는 자두의 유무
                // 이동하지 않는 경우
                dp[i][j] = dp[i-1][j] + isJadu;
                // 이동하는 경우
                if(j > 0){
                    // 이동하지 않고 자두를 얻은 총 개수와 이동한 경우하여 얻은 총 개수 중 큰 것을 선택
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + isJadu);
                }
            }
        }
        int max = 0;
        for(int i=0; i<=w; i++){
            max = Math.max(max, dp[t][i]);
        }
        System.out.print(max);
    }
}