import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    static boolean[][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 1) matrix[i][j]++;
            }
        }
        int time = 0;
        int lastCnt = 0;
        while(true){
            int cnt = cheezeCnt();
            if(cnt == 0) break;
            lastCnt = cnt;
            meltCheeze();
            time++;
        }
        System.out.print(time);
    }

    public static void meltCheeze(){
        restore();
        visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int[] now = q.poll();
            int r = now[0]; int c = now[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d]; int nc = c + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;
                if(matrix[nr][nc] == 2){
                    matrix[nr][nc]--;
                    continue;
                }
                if(matrix[nr][nc] == 1){
                    matrix[nr][nc]--;
                    visited[nr][nc] = true;
                    continue;
                }
                // 바깥 공기
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
    }

    public static void restore(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 1) matrix[i][j] = 2;
            }
        }
    }

    public static int cheezeCnt(){
        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] >= 1) cnt++;
            }
        }
        return cnt;
    }
}