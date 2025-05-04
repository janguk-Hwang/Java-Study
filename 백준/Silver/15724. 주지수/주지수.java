import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[][] matrix;
    static int[][] prefixSum;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n + 1][m + 1];     // 1-based
        prefixSum = new int[n + 1][m + 1];
        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<m+1; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());    // 1-based
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] + matrix[i][j] - prefixSum[i-1][j-1];
            }
        }
        k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            // y가 열, x가 행
            sb.append(prefixSum[x2][y2] - prefixSum[x2][y1-1] - prefixSum[x1-1][y2] + prefixSum[x1-1][y1-1]).append("\n");
        }
        System.out.println(sb);
    }
}