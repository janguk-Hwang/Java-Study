import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] matrix, preSum;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        preSum = new int[n + 2][m + 2]; matrix = new int[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i][j];
            }
        }
        int start = 0; int end = Math.min(n, m) + 1;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            if(isPossible(mid)) start = mid;
            else end = mid;
        }
        System.out.print(start);
    }

    static boolean isPossible(int mid){
        for(int i=mid; i<=n; i++){
            for(int j=mid; j<=m; j++){
                int r = i - mid;
                int c = j - mid;
                int sum = preSum[i][j] - preSum[r][j] - preSum[i][c] + preSum[r][c];
                if(sum == 0) return true;
            }
        }
        return false;
    }
}