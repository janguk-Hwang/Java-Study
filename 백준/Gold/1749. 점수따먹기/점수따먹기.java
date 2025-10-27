import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] matrix, preSum;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n + 1][m + 1];
        preSum = new int[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i][j];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int sR=1; sR<=n; sR++){
            for(int sC=1; sC<=m; sC++){
                for(int eR=sR; eR<=n; eR++){
                    for(int eC=sC; eC<=m; eC++){
                        max = Math.max(max, preSum[eR][eC] - preSum[eR][sC-1] - preSum[sR-1][eC] + preSum[sR-1][sC-1]);
                    }
                }
            }
        }
        System.out.print(max);
    }
}