import java.io.*;
import java.util.*;

public class Main {
    static int m, n;
    static int INF = Integer.MAX_VALUE;
    static int[][] matrix;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] dist;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[m][n];
        dist = new int[m][n];
        for(int i=0; i<m; i++) Arrays.fill(dist[i], INF);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        if(matrix[0][0] == -1 || matrix[m-1][n-1] == -1){
            System.out.println(-1);
            return;
        }
        System.out.print(dijkstra());
    }

    public static int dijkstra(){
        dist[0][0] = (matrix[0][0] == 0) ? 0 : matrix[0][0];
        pq.offer(new Node(0, 0, dist[0][0]));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.x == m-1 && cur.y == n-1) return dist[cur.x][cur.y];
            if(dist[cur.x][cur.y] < cur.cost) continue;
            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(matrix[nx][ny] == -1) continue;
                int cost = matrix[nx][ny] + cur.cost;
                if(dist[nx][ny] > cost){
                    dist[nx][ny] = cost;
                    pq.offer(new Node(nx, ny, cost));
                }
            }
        }
        return -1;
    }

    static class Node implements Comparable<Node>{
        int x, y, cost;
        Node(int x, int y, int cost){
            this.x = x; this.y = y; this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(cost, o.cost);
        }
    }
}