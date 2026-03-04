import java.io.*;
import java.util.*;

// 2차원 부분합
public class Main {
    static int n;
    static int[][] matrix, prefixSum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n + 1][n + 1];
        prefixSum = new int[n + 1][n + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + matrix[i][j];
            }
        }
        int max = Integer.MIN_VALUE;
        // 수확할 정사각형의 크기
        for(int i=1; i<=n; i++){
            for(int j=1; j+i-1<=n; j++){
                for(int k=1; k+i-1<=n; k++){
                    int endR = j + i - 1;
                    int endC = k + i - 1;
                    int sum = prefixSum[endR][endC] - prefixSum[j-1][endC] - prefixSum[endR][k-1] + prefixSum[j-1][k-1];
                    max = Math.max(max, sum);
                }
            }
        }
        System.out.print(max);
    }
}