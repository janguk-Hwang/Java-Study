import java.io.*;
import java.util.*;

// 벽을 부수는 것을 비용 1, 빈 칸을 이동하는 것은 비용 0
public class Main {
    static ArrayList<Node>[] adj;
    static int INF = Integer.MAX_VALUE;
    static int n, m;
    static int[][] matrix;
    static int[] dist;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   // 가로
        n = Integer.parseInt(st.nextToken());   // 세로
        matrix = new int[n][m];
        dist = new int[n * m];
        Arrays.fill(dist, INF);
        for(int i=0; i<n; i++){
            String[] temp = br.readLine().split("");
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(temp[j]);
            }
        }
        makeAdj();
        dijkstra();
        System.out.print(dist[n * m - 1]);
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(dist[now.dest] < now.cost) continue;
            for(Node next : adj[now.dest]){
                if(dist[next.dest] > now.cost + next.cost){
                    dist[next.dest] = now.cost + next.cost;
                    pq.offer(new Node(next.dest, now.cost + next.cost));
                }
            }
        }
    }

    public static void makeAdj(){
        adj = new ArrayList[n * m];
        for(int i=0; i<n*m; i++) adj[i] = new ArrayList<>();
        for(int r=0; r<n; r++){
            for(int c=0; c<m; c++){
                int now = r * m + c;
                for(int d=0; d<4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    int dest = nr * m + nc;
                    int cost = matrix[nr][nc];
                    adj[now].add(new Node(dest, cost));
                }
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int dest; int cost;
        public Node(int dest, int cost){
            this.dest = dest; this.cost = cost;
        }
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}