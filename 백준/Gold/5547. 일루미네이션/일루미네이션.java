import java.io.*;
import java.util.*;

public class Main {
    static Queue<int[]> q = new LinkedList<>();
    static int w, h;
    static int[][] map;
    static boolean[][] visited;
    static int[][] evenDir = {{-1, 0}, {-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}};
    static int[][] oddDir = {{-1, 0}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h + 2][w + 2];
        visited = new boolean[h + 2][w + 2];
        for(int i=1; i<=h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=w; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bfs());
    }

    // 빈 칸에서만 6방향 탐색, 큐에는 빈 칸만 담김.
    // 건물들로 둘러싸인 빈 칸은 큐에 담기지 않음. 큐에 빈 칸만 담기기 때문.
    static int bfs(){
        visited[0][0] = true;
        q.offer(new int[]{0, 0});
        int total = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int[][] dir = (cur[0] % 2 == 1) ? oddDir : evenDir;
            for(int d=0; d<6; d++){
                int nc = cur[1] + dir[d][0];
                int nr = cur[0] + dir[d][1];
                if(nr < 0 || nr > h + 1 || nc < 0 || nc > w + 1) continue;
                if(map[nr][nc] == 1) total++;
                // 방문되지 않았고 빈 칸이면
                else if(!visited[nr][nc] && map[nr][nc] == 0){
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return total;
    }
}