import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int k;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> y.cost - x.cost);
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) board[i][j] = Integer.parseInt(st.nextToken());
        }
        k = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(i == 1 || i == n || j == 1 || j == m){
                    if(!visited[i][j]){
                        visited[i][j] = true;
                        pq.add(new Node(i, j, board[i][j]));
                    }
                }
            }
        }
        for(int i=0; i<k; i++){
            if(!pq.isEmpty()){
                Node cur = pq.poll();
                sb.append(cur.x).append(" ").append(cur.y).append("\n");
                for(int d=0; d<4; d++){
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if(nx < 1 || nx > n || ny < 1 || ny > m || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    pq.add(new Node(nx, ny, board[nx][ny]));
                }
            }
        }
        System.out.println(sb);
    }

    static class Node {
        int x; int y; int cost;
        Node(int x, int y, int v) {
            this.x = x; this.y = y; this.cost = v;
        }
    }
}