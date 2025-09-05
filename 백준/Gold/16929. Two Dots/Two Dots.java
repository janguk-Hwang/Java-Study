import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] matrix;
    static boolean[][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean flag = false;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<m; j++){
                matrix[i][j] = s.charAt(j);
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                visited = new boolean[n][m];
                visited[i][j] = true;
                dfs(i, j, 1, new int[]{i, j});
                if(flag){
                    System.out.print("Yes");
                    return;
                }
            }
        }
        System.out.print("No");
    }

    public static void dfs(int r, int c, int cnt, int[] start){
        if(flag) return;
        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if(matrix[nr][nc] != matrix[start[0]][start[1]]) continue;
            if(!visited[nr][nc]){
                visited[nr][nc] = true;
                dfs(nr, nc, cnt+1, start);
                visited[nr][nc] = false;
            }
            if(visited[nr][nc]){
                if(nr == start[0] && nc == start[1] && cnt >= 4){
                    flag = true;
                    return;
                }
            }
        }
    }
}