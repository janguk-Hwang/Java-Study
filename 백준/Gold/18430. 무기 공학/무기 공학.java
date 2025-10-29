import java.io.*;
import java.util.*;

// n, m이 5밖에 되지 않는다.
// 각 칸을 "중심이 되는 칸"으로 설정하고 부메랑을 만들 수 있는 4가지를 모두 고려해본다.
// 위 과정을 백트래킹으로
public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m, max;
    static int[][] matrix;
    static boolean[][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if(n < 2 && m < 2){
            System.out.print(0);
            return;
        }
        matrix = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        backtracking(0, 0, 0);
        System.out.print(max);
    }
    static void backtracking(int row, int col, int sum){
        // 종료 조건
        if(row == n){
            max = Math.max(max, sum);
            return;
        }
        int nextR = row;
        int nextC = col + 1;
        if(nextC == m){
            nextR++;
            nextC = 0;
        }
        // 가능한 선택
        // nextR, nextC 선택 x
        backtracking(nextR, nextC, sum);
        if(!visited[row][col]){
            for(int d=0; d<4; d++){
                int nr1 = row + dr[d];
                int nc1 = col + dc[d];
                int nr2 = row + dr[(d+1)%4];
                int nc2 = col + dc[(d+1)%4];
                // 유효한 범위이고 방문되지 않은 곳이면
                if(isValid(nr1, nc1) && !visited[nr1][nc1] && isValid(nr2, nc2) && !visited[nr2][nc2]){
                    visited[row][col] = visited[nr1][nc1] = visited[nr2][nc2] = true;
                    sum += matrix[row][col] * 2 + matrix[nr1][nc1] + matrix[nr2][nc2];
                    backtracking(nextR, nextC, sum);
                    sum -= matrix[row][col] * 2 + matrix[nr1][nc1] + matrix[nr2][nc2];
                    visited[row][col] = visited[nr1][nc1] = visited[nr2][nc2] = false;
                }
            }
        }
    }

    static boolean isValid(int r, int c){
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}