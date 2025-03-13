import java.io.*;
import java.util.*;

// 어떠한 집도 앞집과 뒷집의 색과 달라야 한다.
public class Main {
    static int[][] matrix;  // 집을 칠하는 비용을 담는 2차원 배열
    static int[][] dp; // dp[a][b] -> a번째 집을 b번째 색으로 칠한 후 a번째까지의 최소 비용
    static int n;
    // static StringTokenizer st;와 동작이 같지만 st를 null로 명시적으로 초기화하여 아직 객체가 생성되지 않았다는 것을 확실히 알 수 있다.
    static StringTokenizer st = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][3];
        dp = new int[n+1][n+1];
        
        for (int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dpPr();
    }

    public static void dpPr(){
        // 초기값 설정 (첫 번째 집을 R,G,B로 칠하는 비용)
        dp[0][0] = matrix[0][0];
        dp[0][1] = matrix[0][1];
        dp[0][2] = matrix[0][2];

        // n번집까지 칠하기
        for (int i=1; i<=n; i++){
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + matrix[i][0];  // i번째 집을 R로 칠하는 최소 비용
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + matrix[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + matrix[i][2];
        }
        
        System.out.println(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]));
    }
}
