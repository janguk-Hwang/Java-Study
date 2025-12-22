import java.io.*;
import java.util.*;

// 크게 돌아가는 것은 상관없다. 경사의 최대값만 최소로 만들어야 한다.
public class Main {
    static PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
    static final int INF = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix, dist;
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        dist = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
            Arrays.fill(dist[i], INF);
        }
        System.out.print(dijkstra());
    }

    static int dijkstra(){
        dist[0][0] = 0;
        pq.add(new Node(0, 0, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(dist[cur.r][cur.c] < cur.cost) continue;
            if(cur.r == n-1 && cur.c == n-1) return cur.cost;
            for(int d=0; d<4; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                int slope = Math.abs(matrix[cur.r][cur.c] - matrix[nr][nc]);
                int nxtCost = Math.max(cur.cost, slope);
                if(dist[nr][nc] > nxtCost){
                    dist[nr][nc] = nxtCost;
                    pq.add(new Node(nr, nc, nxtCost));
                }
            }
        }
        return -1;
    }

    static class Node{
        int r, c, cost;
        Node(int r, int c, int cost){
            this.r = r; this.c = c;
            this.cost = cost;
        }
    }
}