import java.io.*;
import java.util.*;

public class Main {
    static Queue<Node> q = new LinkedList<>();
    static int n, m, hx, hy, ex, ey;
    static int[][] matrix;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        visited = new boolean[n][m][2];
        st = new StringTokenizer(br.readLine());
        hx = Integer.parseInt(st.nextToken()) - 1;
        hy = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.print(bfs());
    }

    static int bfs(){
        q.add(new Node(hx, hy, 0, 0));
        visited[hx][hy][0] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.x == ex && cur.y == ey) return cur.dist;
            for(int d=0; d<4; d++){
                int nx = cur.x + dr[d];
                int ny = cur.y + dc[d];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 빈 칸
                if(matrix[nx][ny] == 0 && !visited[nx][ny][cur.used]){
                    visited[nx][ny][cur.used] = true;
                    q.add(new Node(nx, ny, cur.used, cur.dist +1));
                }
                // 벽
                if(matrix[nx][ny] == 1 && cur.used == 0 && !visited[nx][ny][1]){
                    visited[nx][ny][1] = true;
                    q.add(new Node(nx, ny, 1, cur.dist +1));
                }
            }
        }
        return -1;
    }

    static class Node{
        int x, y, used, dist;
        Node(int x, int y, int used, int dist){
            this.x = x; this.y = y; this.used = used; this.dist = dist;
        }
    }
}