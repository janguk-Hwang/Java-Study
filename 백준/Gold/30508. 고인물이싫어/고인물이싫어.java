import java.io.*;
import java.util.*;

// 모든 칸은 상하좌우로 인접한 칸 중 높이가 자신보다 낮거나 같은 칸에 하수구나 물이 빠진 칸이 있으면 물이 빠진다.
// 연속된 h개의 행과 w개의 열
public class Main {
    static int n, m, h, w;
    static int[][] matrix;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] isDrain;     // 고인물 여부
    static int[][] flood;       // 각 칸의 물 양(누적합을 위해 int형)
    static int[][] prefixSum;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        isDrain = new boolean[n][m];
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            // 0-based
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            // 하수구는 고인 물 x
            isDrain[r][c] = true;
            // 하수구에서 bfs 시작
            q.add(new int[]{r, c});
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= n|| nc < 0 || nc >= m) continue;
                // 고이지 않은 칸은 이미 방문한 칸
                if(isDrain[nr][nc]) continue;
                // 높은 칸을 만나면 높은 칸은 물이 빠지고 다음 턴을 위해 큐에 삽입
                if(matrix[nr][nc] >= matrix[cur[0]][cur[1]]){
                    isDrain[nr][nc] = true;
                    q.add(new int[]{nr,nc});
                }
            }
        }
        flood = new int[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                // 물이 고인 칸은 flood 값이 1
                if(!isDrain[i-1][j-1]) flood[i][j] = 1;
            }
        }
        prefixSum = new int[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + flood[i][j];
            }
        }
        int rst = 0;
        // h x w 크기의 직사각형 검사
        for(int i=1; i<=n-h+1; i++){
            for(int j=1; j<=m-w+1; j++){
                int r2 = i + h - 1;
                int c2 = j + w - 1;
                int sum = prefixSum[r2][c2] - prefixSum[i-1][c2] - prefixSum[r2][j-1] + prefixSum[i-1][j-1];
                if(sum == 0) rst++;
            }
        }
        System.out.println(rst);
    }
}