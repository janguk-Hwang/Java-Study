import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n;
    static int INF = Integer.MAX_VALUE;
    static int[][] matrix, dist;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        dist = new int[n][n];
        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<n; j++){
                matrix[i][j] = line.charAt(j) - '0';
                dist[i][j] = INF;
            }
        }
        dijkstra();
        System.out.print(dist[n-1][n-1]);
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        dist[0][0] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int r = now.r;
            int c = now.c;
            int nowCost = now.cost;
            if(r == n-1 && c == n-1) return;
            if(dist[r][c] < nowCost) continue;
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr >= 0 && nr < n && nc >= 0 && nc < n){
                    int nextCost = nowCost;
                    if(matrix[nr][nc] == 0) nextCost++;
                    if(dist[nr][nc] > nextCost){
                        dist[nr][nc] = nextCost;
                        pq.offer(new Node(nr, nc, nextCost));
                    }
                }
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int r, c, cost;
        public Node(int r, int c, int cost){
            this.r = r; this.c = c; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}