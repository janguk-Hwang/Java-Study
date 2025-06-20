import java.io.*;
import java.util.*;

// 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다.
public class Main {
    static int[][] dp;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, max;
    static int[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        dp = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                max = Math.max(max, func(i, j));
            }
        }
        System.out.print(max);
    }

    // 각 칸으로 올 수 있는 경우 중에서 가장 많은 칸을 이동하고 온 경우의 이동 횟수를 저장한다면?
    // 이렇게 dp를 사용한다면?
    public static int func(int r, int c){
        if(dp[r][c] != 0) return dp[r][c];
        dp[r][c] = 1;   // 기저 사례
        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nr < n && nc >= 0 && nc < n){
                if(matrix[nr][nc] > matrix[r][c]){
                    dp[r][c] = Math.max(func(nr, nc) + 1, dp[r][c]);
                }
            }
        }
        return dp[r][c];
    }
}