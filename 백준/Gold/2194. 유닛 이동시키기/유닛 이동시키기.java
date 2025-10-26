import java.io.*;
import java.util.*;

// 부분합 사용
public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m, a, b, k;
    static int[][] matrix, preSum;
    static boolean[][] visited;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n + 1][m + 1];
        preSum = new int[n + 1][m + 1];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[r][c] = 1;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i][j];
            }
        }
        st = new StringTokenizer(br.readLine());
        int sR = Integer.parseInt(st.nextToken());
        int sC = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int eR = Integer.parseInt(st.nextToken());
        int eC = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sR, sC, 0});
        visited = new boolean[n + 1][m + 1];
        visited[sR][sC] = true;
        int rst = -1;
        outer:
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == eR && cur[1] == eC){
                rst = cur[2];
                break outer;
            }
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 1 || nr+a-1 > n || nc < 1 || nc+b-1 > m || visited[nr][nc]) continue;
                // 우측 아래 기준
                int r2 = nr + a - 1; int c2 = nc + b - 1;
                int obstacle = preSum[r2][c2] - preSum[nr-1][c2] - preSum[r2][nc-1] + preSum[nr-1][nc-1];
                if(obstacle > 0) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc, cur[2] + 1});
            }
        }
        System.out.print(rst);
    }
}