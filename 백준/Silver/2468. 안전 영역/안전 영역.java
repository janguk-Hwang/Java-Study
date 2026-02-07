import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] matrix;
    static Queue<int[]> q;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        int maxHeight = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, matrix[i][j]);
            }
        }
        int rst = Integer.MIN_VALUE;
        // 잠기는 높이 순회
        for(int i=0; i<maxHeight; i++){
            boolean[][] visited = new boolean[n][n];
            int cnt = 0;
            for(int j=0; j<n; j++){
                for(int k=0; k<n; k++){
                    // 잠기지 않은 칸에서 bfs 시작, 방문 처리, 안전한 영역 수 계산
                    if(!visited[j][k] && matrix[j][k] > i){
                        bfs(j, k, i, visited);
                        cnt++;
                    }
                }
            }
            rst = Math.max(rst, cnt);
        }
        System.out.println(rst);
    }

    static void bfs(int x, int y, int height, boolean[][] visited){
        q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0; i<4; i++){
                int nx = cur[0] + dr[i];
                int ny = cur[1] + dc[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && matrix[nx][ny] > height){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}