import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r, c, d;
    static int[][] matrix;
    static boolean[][] visited;
    static int cleanCnt = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        visited = new boolean[n][m];
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        func();
        System.out.println(cleanCnt);
    }

    public static void func() {
        while(true){
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if(!visited[r][c]){
                visited[r][c] = true;
                cleanCnt++;
            }

            boolean flag = false;
            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                // 유효하고, 빈 칸이고, 청소되지 않은 칸이라면
                if(isValid(nr, nc) && matrix[nr][nc] == 0 && !visited[nr][nc]) {
                    flag = true;
                    break;
                }
            }

            // 있는 경우
            if(flag){
                d = (d + 3) % 4;
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isValid(nr, nc) && matrix[nr][nc] == 0 && !visited[nr][nc]){
                    r = nr;
                    c = nc;
                    continue;       // 1번으로 돌아간다.
                }
                continue;       // 1번으로 돌아간다.
            }

            // 없는 경우
            int backDir = (d + 2) % 4;
            int nr = r + dr[backDir];
            int nc = c + dc[backDir];
            // 후진할 수 있으면
            if(isValid(nr, nc) && matrix[nr][nc] == 0){
                r = nr;
                c = nc;
                continue;       // 1번으로 돌아간다.
            }
            break;      // 후진할 수 없다면 작동을 멈춘다.
        }
    }

    public static boolean isValid(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < m;
    }
}