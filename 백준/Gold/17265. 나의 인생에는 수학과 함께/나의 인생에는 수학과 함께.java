import java.io.*;
import java.util.*;

// 숫자 다음에는 연산자, 연산자 다음에는 숫자가 나오도록 주어진다
public class Main {
    static int n;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static char[][] matrix;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new char[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) matrix[i][j] = st.nextToken().charAt(0);
        }
        dfs(0,0,matrix[0][0] - '0');
        System.out.print(max + " " + min);
    }

    static void dfs(int x, int y, int num){
        // 종료 조건(학교 도착 시)
        if(x == n-1 && y == n-1){
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }
        for(int d=0; d<2; d++){
            // 연산자
            int nx = x + dx[d];
            int ny = y + dy[d];
            // 음수가 되는 경우는 일어나지 않음. dx, dy는 양수
            if(nx >= n || ny >= n) continue;
            for(int nd = 0; nd <2; nd++){
                // 계산할 숫자
                int nnx = nx + dx[nd];
                int nny = ny + dy[nd];
                if(nnx >= n || nny >= n) continue;
                int nxtNum = matrix[nnx][nny] - '0';
                if(matrix[nx][ny] == '+') dfs(nnx, nny, num + nxtNum);
                else if(matrix[nx][ny] == '-') dfs(nnx, nny, num - nxtNum);
                else dfs(nnx, nny, num * nxtNum);
            }
        }
    }
}