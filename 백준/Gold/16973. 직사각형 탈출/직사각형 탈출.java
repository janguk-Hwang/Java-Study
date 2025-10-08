import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] matrix;
    static int n, m, h, w, sr, sc, fr, fc, dist;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        fr = Integer.parseInt(st.nextToken());
        fc = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1][m+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc, 0});    // 좌표, 이동 횟수
        visited[sr][sc] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == fr && cur[1] == fc){
                System.out.print(cur[2]);
                return;
            }
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 1 || nr > n-h+1 || nc < 1 || nc > m-w+1 || visited[nr][nc]) continue;
                boolean flag = false;
                outer:
                for(int i=nr; i<nr+h; i++){
                    for(int j=nc; j<nc+w; j++){
                        // 이동했을 때, 직사각형 내부에 벽이 있으면
                        if(matrix[i][j] == 1){
                            flag = true;
                            break outer;
                        }
                    }
                }
                // 다음 방향(d)으로
                if(flag) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc, cur[2]+1});
            }
        }
        System.out.print(-1);
    }
}