import java.io.*;
import java.util.*;

public class Main {
    static int n, m, nextCost;
    static int[][] dist;
    static int x1, y1, x2, y2;
    static char[][] matrix;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;  // 행
        y1 = Integer.parseInt(st.nextToken()) - 1;  // 열
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        matrix = new char[n][m];
        dist = new int[n][m];
        for(int i=0; i<n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        for(int i = 0; i < n; i++) matrix[i] = br.readLine().toCharArray();
        System.out.print(dijkstra() + 1);
    }

    static int dijkstra(){
        dist[x1][y1] = 0;
        pq.add(new Node(x1, y1, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int r = cur.r; int c = cur.c; int cost = cur.cost;
            // 도착하면 현재까지의 친구 겹 수 반환
            if(r == x2 && c == y2) return cost;
            if(dist[r][c] < cost) continue;
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                int nextCost = cost + ((matrix[nr][nc] == '1') ? 1 : 0);
                if(dist[nr][nc] > nextCost){
                    dist[nr][nc] = nextCost;
                    pq.add(new Node(nr, nc, nextCost));
                }
            }
        }
        return -1;
    }

    static class Node implements Comparable<Node>{
        int r; int c; int cost;
        public Node(int r, int c, int cost){
            this.r = r; this.c = c; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}