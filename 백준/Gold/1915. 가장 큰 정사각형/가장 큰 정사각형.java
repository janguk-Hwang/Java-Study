import java.io.*;
import java.util.*;

// dp를 사용하기 위해서는 이전 값을 사용하면서 누적해가야 하기 때문에 dp 테이블의 의미를
// (i, j)를 오른쪽 아래 칸으로 하는 정사각형의 변의 길이로 설정
public class Main {
    static int[][] dp;  // dp[i][j]는 정사각형의 오른쪽 아래 칸으로 하는 정사각형의 변의 길이
    static int[][] matrix;
    static int n, m;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        dp = new int[n][m];
        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                matrix[i][j] = str.charAt(j) - '0';
            }
        }

        // 1 1 1
        // 1 1 0
        // 1 1 1 <- 마지막 1의 입장에서 보면 dp[2][2]는 위 정사각형, 왼쪽 위 대각선 정사각형, 왼쪽 정사각형이 모두
        //          만족하는 최소 크기에서 증가가 되야 한다.
        // 위, 왼쪽, 왼쪽 위 대각선 모두에서 정사각형이 존재해야 그 중 가장 작은 크기의 정사각형에서 확장될 수 있다.
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 1){
                    // 기저 사례 어떻게 잡아야 할까?
                    // 첫 번째 행이나 첫 번째 열에서 1로 된 칸에서는 dp 값이 1로 고정이 될 수 밖에 없다.
                    if(i == 0 || j == 0) dp[i][j] = 1;
                    if(i > 0 && j > 0) dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.print(max * max);
    }
}