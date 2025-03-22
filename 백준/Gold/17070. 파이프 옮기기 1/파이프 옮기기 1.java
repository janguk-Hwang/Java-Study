import java.io.*;
import java.util.*;

// 벽에는 새로운 벽지를 발랐기 때문에, 파이프가 벽을 긁으면 안 된다.
public class Main {
    static int[][] matrix;
    static int n;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        dp = new int[n][n][3];  // 0: 가로, 1: 세로, 2: 대각선

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 파이프 상태 (가로)
        dp[0][1][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (matrix[i][j] == 1) continue; // 벽이면 continue

                // (i, j)에서 파이프가 가로로 위치하려면 (i, j-1)에서 가로인 경우와 대각인 경우의 합
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

                // (i, j)에서 파이프가 세로로 위치하려면 (i, j-1)에서 세로인 경우와 대각인 경우의 합
                if (i > 0) {    // 0행에는 세로로 놓일 수 없기 때문에
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                }

                // (i, j)에서 파이프가 대각으로 위치하려면 (i, j-1)에서 가로, 세로, 대각인 경우의 합
                // 문제에서 파이프가 놓여진 방향에 따라 이동할 수 있는 방법과 꼭 빈 칸이어야 하는 곳을 색으로 표시되어져 있다.
                // 특히, 대각일 때 파이프가 벽을 긁으면 안되서 (i-1, j)와 (i, j-1)도 빈 칸이어야 한다.
                if (i > 0 && matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        // (N-1, N-1)에 도달하는 모든 방법의 수 출력
        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
    }
}
