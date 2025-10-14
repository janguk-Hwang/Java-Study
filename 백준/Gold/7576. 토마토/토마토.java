import java.io.*;
import java.util.*;

public class Main {
    static Queue<int[]> q = new LinkedList<>();
    static int[][] matrix;
    static int n, m, rst;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        rst = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 1){
                    q.add(new int[]{i, j});
                }
            }
        }
        int rst = bfs();
        outer:
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 0){
                    rst = -1;
                    break outer;
                }
            }
        }
        System.out.print(rst);
    }

    static int bfs(){
        int day = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] cur = q.poll();
                for(int d=0; d<4; d++){
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= m || matrix[nr][nc] != 0) continue;
                    matrix[nr][nc] = 1;
                    q.add(new int[]{nr, nc});
                }
            }
            if(!q.isEmpty()) day++;
        }
        return day;
    }
}