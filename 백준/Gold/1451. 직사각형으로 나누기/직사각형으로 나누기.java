import java.io.*;
import java.util.*;

// 직사각형을 3개의 직사각형으로 나누는 방법은?
// 넓이의 곱의 최대가 아니라 써있는 숫자의 곱의 최대값이므로 회전하거나 할 수 없다.
// 6가지 경우에 대해 모두 구해봐야한다.
// 직사각형이므로 누적합을 통해 (0, 0)에서 (i, j)까지의 숫자합을 구해놓으면 빠르게 구할 수 있다.
public class Main {
    static int n, m;
    static int[][] matrix, preSum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][m+1];
        preSum = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            String line = br.readLine();
            for(int j=1; j<=m; j++){
                matrix[i][j] = line.charAt(j-1) - '0';
            }
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                preSum[i][j] = matrix[i][j] + preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1];
            }
        }
        long max = 0;
        // 세로 3개
        for(int i=1; i<m-2; i++){
            for(int j=i+1; j<=m-1; j++){
                long a1 = getSum(1, 1, n, i);
                long a2 = getSum(1, i+1, n, j);
                long a3 = getSum(1, j+1, n, m);
                max = Math.max(max, a1 * a2 * a3);
            }
        }
        // 가로 3개
        for(int i=1; i<=n-2; i++){
            for(int j=i+1; j<=n-1; j++){
                long a1 = getSum(1, 1, i, m);
                long a2 = getSum(i+1, 1, j, m);
                long a3 = getSum(j+1, 1, n, m);
                max = Math.max(max, a1 * a2 * a3);
            }
        }
        // 위 직사각형 하나, 아래 직사각형 분할
        for(int i=1; i<=n-1; i++){
            for(int j=1; j<=m-1; j++){
                long a1 = getSum(1, 1, i, m);
                long a2 = getSum(i + 1, 1, n, j);
                long a3 = getSum(i + 1, j + 1, n, m);
                max = Math.max(max, a1 * a2 * a3);
            }
        }
        // 아래 직사각형 하나, 위 직사각형 분할
        for(int i=1; i<=n-1; i++){
            for(int j=1; j<=m-1; j++){
                long a1 = getSum(1, 1, i, j);
                long a2 = getSum(1, j+1, i, m);
                long a3 = getSum(i+1, 1, n, m);
                max = Math.max(max, a1 * a2 * a3);
            }
        }
        // 왼쪽 직사각형 하나, 오른쪽 직사각형 분할
        for(int i=1; i<=m-1; i++){
            for(int j=1; j<=n-1; j++){
                long a1 = getSum(1, 1, n, i);
                long a2 = getSum(1, i+1, j, m);
                long a3 = getSum(j+1, i+1, n, m);
                max = Math.max(max, a1 * a2 * a3);
            }
        }
        // 오른쪽 직사각형 하나, 왼쪽 직사각형 분할
        for(int i=1; i<=m-1; i++){
            for(int j=1; j<=n-1; j++){
                long a1 = getSum(1, 1, j, i);
                long a2 = getSum(j+1, 1, n, i);
                long a3 = getSum(1, i+1, n, m);
                max = Math.max(max, a1 * a2 * a3);
            }
        }
        System.out.print(max);
    }

    public static long getSum(int x1, int y1, int x2, int y2){
        return preSum[x2][y2] - preSum[x2][y1-1] - preSum[x1-1][y2] + preSum[x1-1][y1-1];
    }
}