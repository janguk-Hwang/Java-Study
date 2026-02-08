import java.io.*;
import java.util.*;

// 절벽을 정확히 하나 골라 주기가 M분인 오작교를 하나 더 놓아 주기로 했다
public class Main {
    static int n, m, rst = Integer.MAX_VALUE;
    static int[][] matrix;
    static boolean[][][] visited;       // r, c, 오작교 사용 여부(0, 1)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        visited = new boolean[n][n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        findCantBuild();
        bfs();
        System.out.print(rst);
    }

    // 절벽인 칸 중에 상하, 좌우에 절벽이 1개 이상 있으면 오작교 설치 불가
    public static void findCantBuild(){
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if(matrix[r][c]==0){
                    int cnt = 0;
                    if((r-1 >= 0 && matrix[r-1][c] == 0) || (r+1 < n && matrix[r+1][c] == 0)) cnt++;
                    if((c-1 >= 0 && matrix[r][c-1] == 0) || (c+1 < n && matrix[r][c+1] == 0)) cnt++;
                    if(cnt == 2) matrix[r][c] = -1;
                }
            }
        }
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0, 0});     // r, c, k, t(ime)
        visited[0][0][0] = true;
        while(true){
            int[] cur = q.poll();
            int r = cur[0]; int c = cur[1];
            int k = cur[2]; int t = cur[3];
            if(r == n-1 && c == n-1){
                rst = t;
                break;
            }
            for(int i=0; i<4; i++){
                int nr = r + dr[i]; int nc = c + dc[i]; int nt = t + 1;
                if(nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc][k]) continue;
                // 다음 위치가 땅일 때
                if(matrix[nr][nc] == 1){
                    visited[nr][nc][k] = true;
                    q.offer(new int[]{nr, nc, k, nt});
                }
                // 다음 위치가 오작교 위일 때(기존 오작교)
                if(matrix[nr][nc] > 1){
                    // 현재 위치가 땅이어야만 이동가능(연속으로 오작교 x)
                    if(matrix[r][c] == 1){
                        // 주기가 맞으면 오작교 사용
                        if(nt % matrix[nr][nc] == 0) q.offer(new int[]{nr, nc, k, nt});
                        else q.offer(new int[]{r, c, k, nt});
                    }
                }
                // 새로 오작교 설치
                if(matrix[nr][nc] == 0 && k == 0){
                    if(nt % m == 0) q.offer(new int[]{nr, nc, k+1, nt});
                    else q.offer(new int[]{r, c, k, nt});
                }
            }
        }
    }
}