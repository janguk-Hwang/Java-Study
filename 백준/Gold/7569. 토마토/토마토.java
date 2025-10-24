import java.io.*;
import java.util.*;

public class Main {
    static int n, m, h;
    static int[][][] matrix;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        matrix = new int[h][n][m];
        visited = new boolean[h][n][m];
        Queue<int[]> q = new LinkedList<>();
        for(int z=0; z<h; z++){
            for(int r=0; r<n; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<m; c++){
                    matrix[z][r][c] = Integer.parseInt(st.nextToken());
                    if(matrix[z][r][c] == 1){
                        q.add(new int[]{z, r, c});
                        visited[z][r][c] = true;
                    }
                }
            }
        }
        int rst = bfs(q);
        System.out.print(rst);
    }

    static int bfs(Queue<int[]> q) {
        int time = -1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] cur = q.poll();
                int z = cur[0], r = cur[1], c = cur[2];
                for(int d=0; d<6; d++){
                    int nz = z + dz[d];
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nz < 0 || nz >= h || nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nz][nr][nc]) continue;
                    if(matrix[nz][nr][nc] != 0) continue; // 익은 토마토 또는 빈 칸
                    visited[nz][nr][nc] = true;
                    matrix[nz][nr][nc] = 1;
                    q.add(new int[]{nz, nr, nc});
                }
            }
            time++;
        }
        for(int z=0; z<h; z++){
            for(int r=0; r<n; r++){
                for(int c=0; c<m; c++){
                    if(matrix[z][r][c] == 0) return -1;
                }
            }
        }
        return Math.max(time, 0);
    }
}