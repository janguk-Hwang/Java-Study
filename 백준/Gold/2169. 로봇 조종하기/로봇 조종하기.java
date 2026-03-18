import java.io.*;
import java.util.*;

// 한번 탐사한 지역은 탐사하지 않는다.
// 아래, 왼쪽, 오른쪽으로 이동 가능
// 해당 칸에 왼쪽에서 온 경우와 오른쪽에서 온 경우 모두 계산하여 더 큰 값으로 할당
// dp는 2차원으로 만들고, 각 행마다 [열의 길이][2]인 2차원 배열을 만들어 왼쪽, 오른쪽에서 온 경우를 저장
// 왼쪽, 오른쪽 중에 더 최적해를 dp에 할당
public class Main {
    static int n, m;
    static int[][] matrix, dp, temp;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        dp = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        // 기저 사례
        dp[0][0] = matrix[0][0];    // 0행 0열
        for(int j=1; j<m; j++) dp[0][j] = dp[0][j-1] + matrix[0][j];    // 0행 1열 ~
        for(int i=1; i<n; i++){
            temp = new int[m][2];   // 0: 왼쪽, 1: 오른쪽
            // 왼쪽에서 온 경우
            temp[0][0] = dp[i-1][0] + matrix[i][0];
            for(int j=1; j<m; j++) temp[j][0] = Math.max(dp[i-1][j], temp[j-1][0]) + matrix[i][j];
            // 오른쪽에서 온 경우
            temp[m-1][1] = dp[i-1][m-1] + matrix[i][m-1];
            for(int j=m-2; j>=0; j--) temp[j][1] = Math.max(dp[i-1][j], temp[j+1][1]) + matrix[i][j];
            for(int j=0; j<m; j++) dp[i][j] = Math.max(temp[j][0], temp[j][1]);
        }
        System.out.print(dp[n-1][m-1]);
    }
}