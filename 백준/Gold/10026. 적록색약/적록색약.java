import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n;
    static boolean[][] visited;
    static char[][] matrix;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new char[n][n];
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<n; j++) matrix[i][j] = s.charAt(j);
        }
        int cnt = 0;
        visited = new boolean[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    bfs(i, j, matrix[i][j], false);
                    cnt++;
                }
            }
        }
        visited = new boolean[n][n];
        int blindCnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    bfs(i, j, matrix[i][j], true);
                    blindCnt++;
                }
            }
        }
        System.out.print(cnt + " " + blindCnt);
    }

    static void bfs(int r, int c, char color, boolean isColorBlindness){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]) continue;
                if(isColorBlindness){
                    if((color == 'R' || color == 'G') && (matrix[nr][nc] == 'R' || matrix[nr][nc] == 'G')){}
                    else if(matrix[nr][nc] != color) continue;
                }
                if(!isColorBlindness) if(matrix[nr][nc] != color) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }
    }
}