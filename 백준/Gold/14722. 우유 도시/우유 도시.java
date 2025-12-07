import java.io.*;
import java.util.*;

// 딸기 초코 바나나 딸기
// (1, 1)에서 (n, n)까지 이동하면서 마실 수 있는 우유의 수를 구해야 한다.
// 누적되고 같은 연산을 반복하면서 이전까지의 최적해로 다음의 최적해를 도출할 수 있으므로 dp
// dp -> 좌표, 마지막으로 어떤 우유를 마셨는지가 관리되어야 한다.
// dp[i][j][milk]
// 위에서 오는 경우, 왼쪽에서 오는 경우만 존재한다.
public class Main{
    static int n;
    static int[][] matrix;
    static int[][][] dp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws Exception{
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        dp = new int[n][n][3];      // 0: 딸기 1: 초코 2: 바나나
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        // 맨 처음은 딸기
        if(matrix[0][0] == 0) dp[0][0][0] = 1;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                // 안 마시고 이동만 하는 경우(동쪽, 남쪽으로만 이동할 수 있으므로 2번째 행, 열 이후로 나눈다.)
                // 2번째 행 이후
                if(i > 0) for(int k=0; k<3; k++) dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k]);
                // 2번째 열 이후
                if(j > 0) for(int k=0; k<3; k++) dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k]);

                // 우유를 마시는 경우
                int cur = matrix[i][j];     // 현재 칸에서 마실 수 있는 우유
                int prev = (cur + 2) % 3;   // 이전 칸에서 마셨어야 하는 우유
                if(i > 0 && dp[i-1][j][prev] > 0) dp[i][j][cur] = Math.max(dp[i][j][cur], dp[i-1][j][prev] + 1);
                if(j > 0 && dp[i][j-1][prev] > 0) dp[i][j][cur] = Math.max(dp[i][j][cur], dp[i][j-1][prev] + 1);
                if(dp[i][j][cur] == 0 && cur == 0) dp[i][j][0] = 1;
            }
        }
        System.out.print(Math.max(dp[n-1][n-1][0], Math.max(dp[n-1][n-1][1], dp[n-1][n-1][2])));
    }
}