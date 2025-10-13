import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];
        matrix = new int[n + 1][m + 1];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        int rst = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && matrix[i][j] == 1){
                    rst = Math.max(bfs(i, j), rst);
                    cnt++;
                }
            }
        }
        System.out.print(cnt + " " + rst);
    }

    static int bfs(int r, int c){
        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, 0});
        visited[r][c] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;
                if(matrix[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, cur[2] + 1});
                    size++;
                }
            }
        }
        return size;
    }
}