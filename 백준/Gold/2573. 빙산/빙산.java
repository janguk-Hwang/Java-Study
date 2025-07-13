import java.io.*;
import java.util.*;

// 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성.
// 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.
public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m;
    static int[][] matrix;
    static int[][] adjSea;
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
            }
        }
        int time = 0;
        while(true){
            int count = cntIce();
            if(count >= 2){
                System.out.print(time);
                return;
            }
            if(count == 0){
                System.out.print(0);
                return;
            }
            func();
            time++;
        }
    }

    public static void func(){
        adjSea = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int cnt = 0;
                for(int d=0; d<4; d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if(matrix[nr][nc] == 0) adjSea[i][j]++;
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] > 0){
                    matrix[i][j] -= adjSea[i][j];
                    if(matrix[i][j] < 0) matrix[i][j] = 0;
                }
            }
        }
    }

    public static int cntIce(){
        visited = new boolean[n][m];
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] > 0 && !visited[i][j]){
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    while(!q.isEmpty()){
                        int[] now = q.poll();
                        for(int d=0; d<4; d++){
                            int nr = now[0] + dr[d];
                            int nc = now[1] + dc[d];
                            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                            if(matrix[nr][nc] > 0 && !visited[nr][nc]){
                                visited[nr][nc] = true;
                                q.offer(new int[]{nr, nc});
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        return cnt;
    }
}