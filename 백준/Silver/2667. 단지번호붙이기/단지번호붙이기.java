import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] matrix;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        matrix = new int[n][n];
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<n; j++) matrix[i][j] = s.charAt(j) - '0';
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j] && matrix[i][j] == 1){
                    bfs(i, j);
                }
            }
        }
        list.sort(null);
        sb.append(list.size()).append("\n");
        for(int i : list) sb.append(i).append("\n");
        System.out.print(sb);
    }

    static void bfs(int r, int c){
        int max = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || matrix[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
                max++;
            }
        }
        list.add(max);
    }
}